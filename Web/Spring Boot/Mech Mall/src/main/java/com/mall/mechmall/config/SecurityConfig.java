package com.mall.mechmall.config;

import com.mall.mechmall.filter.JwtAuthenticationFilter;
import com.mall.mechmall.security.JwtAuthenticationEntryPoint;
import com.mall.mechmall.security.PasswordEncoder;
import com.mall.mechmall.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF，因为使用 JWT
                .csrf().disable()

                // 配置请求授权规则
                .authorizeRequests()
                // 允许所有人访问注册和登录接口
                .antMatchers("/user/auth/**").permitAll()
                // 允许所有人访问静态资源
                .antMatchers("/static/**").permitAll() // 指定静态资源路径
                .antMatchers("/static/img/**").permitAll()
                .antMatchers("/static/img/goods/**").permitAll()
                // 允许所有人访问 OPTIONS 请求（为了支持跨域请求的预检请求）
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 限制 /admin/** 路径只有 ADMIN 角色可以访问
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 限制 /user/** 路径只有 USER 和 ADMIN 角色可以访问
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                // 其他任何请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 异常处理配置
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                // 配置会话管理策略为无状态（因为我们使用 JWT）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加 JWT 过滤器，处理每个请求的 JWT 验证
        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtils, userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }


}
