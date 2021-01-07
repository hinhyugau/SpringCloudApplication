package xyz.hinhyuga.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Description: DruidConfig
 * @Author bryan
 * @Date 2021/1/7 5:14 下午
 * @Version 1.0
 */
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        //stat视图设置
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*"); //对应路径映射
        HashMap<String, String> initParametes = new HashMap();
        initParametes.put("loginUsername", "admin");   //登录监控页面用户名
        initParametes.put("loginPassword", "admin");   //登录监控页面密码
        initParametes.put("resetEnable", "false");   //禁用HTML页面的"Rest All"功能
        initParametes.put("alow", "");             //IP白名单（没有配置或者为空表示允许所有）
        initParametes.put("deny", "192.168.22.11");   //IP黑名单（和白名单存在同时，deny优先于allow）
        servletRegistrationBean.setInitParameters(initParametes);
        return servletRegistrationBean;
    }

    @Bean(name = "wallFilter")
    public WallFilter wallFilter() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setDeleteWhereNoneCheck(true);   //进行无条件清空表格的检查（拦截）
        wallConfig.setMultiStatementAllow(true);   //允许sql批量操作
        wallConfig.setNoneBaseStatementAllow(true);  //允许非基本语句的其他语句
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        WebStatFilter webStatFilter = new WebStatFilter();
        webStatFilter.setProfileEnable(true);
        webStatFilter.setSessionStatEnable(true);
        filterRegistrationBean.setFilter(webStatFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        HashMap<String, String> initParamters = new HashMap();
        initParamters.put("sessionStatMaxCount", "1000");
        initParamters.put("principalCookieName", "USER_COOKIE");
        initParamters.put("principalSessionName", "USER_SESSION");
        initParamters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");  //设置忽略资源
        filterRegistrationBean.setInitParameters(initParamters);
        return filterRegistrationBean;
    }
}