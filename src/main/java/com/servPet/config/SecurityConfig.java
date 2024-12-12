package com.servPet.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
            .authorizeHttpRequests((requests) -> requests
                .antMatchers("/**","/front_end/login","/css/**", "/images/**", "/javascript/**", "/jquery/**", "/slick/**", "/front_end/**", "/pg/**").permitAll()
                .antMatchers("/front_end/profile").authenticated()
                .anyRequest().authenticated()

            )
            
            .formLogin((form) -> form
                .loginPage("/front_end/login")
                .usernameParameter("mebMail") // 自定義用戶名字段
                .passwordParameter("mebPwd") // 自定義密碼字段
                .defaultSuccessUrl("/front_end/index", true)
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/front_end/login?logout")
                .permitAll()
                
            )
            .exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                // 返回 JSON 格式的未授權錯誤
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"status\":\"error\",\"message\":\"未授權的請求，請先登入\"}");
            })            .and()
            .sessionManagement()
            .sessionFixation().migrateSession() // 會話固定攻擊防護
            .invalidSessionUrl("/front_end/index"); // 會話過期處理
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
