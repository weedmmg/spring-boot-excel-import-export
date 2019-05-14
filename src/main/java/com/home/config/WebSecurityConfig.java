package com.home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 配置整体站点安全
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();//开放iframe
        httpSecurity.formLogin()
            // 登录页面的位置
            .loginPage("/login")
            .defaultSuccessUrl("/index",true)
            .and()
            .logout()
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            // 退出时删除cookie
            .deleteCookies("JSESSIONID")
            .and()
            .authorizeRequests()
            .antMatchers("/**/*.css","/img/**","/*.ico","/**/*.js","/layui/**",
                      "/login","/user/login","/board/submit")
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/**")
            .authenticated();

        httpSecurity.logout().logoutSuccessUrl("/login"); // 退出默认跳转页面 (5)

        // 禁用缓存
        httpSecurity.headers().cacheControl();


    }

}
