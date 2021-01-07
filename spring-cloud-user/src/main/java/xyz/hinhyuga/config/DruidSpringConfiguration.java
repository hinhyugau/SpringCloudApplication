package xyz.hinhyuga.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Description: Config Aop
 * @Author bryan
 * @Date 2021/1/7 6:07 下午
 * @Version 1.0
 */
@Aspect
@Configuration
public class DruidSpringConfiguration {

    /**
     * Interceptor
     *
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    /**
     * aop
     *
     * @return
     */
    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPatterns("xyz.hinhyuga.web.*", "xyz.hinhyuga.service.*");
        return jdkRegexpMethodPointcut;
    }

    @Bean
    public Advisor druidAdviceAdvisor() {
        DefaultBeanFactoryPointcutAdvisor defaultBeanFactoryPointcutAdvisor = new DefaultBeanFactoryPointcutAdvisor();
        defaultBeanFactoryPointcutAdvisor.setAdvice(druidStatInterceptor());
        defaultBeanFactoryPointcutAdvisor.setPointcut(druidStatPointcut());
        return defaultBeanFactoryPointcutAdvisor;
    }
}