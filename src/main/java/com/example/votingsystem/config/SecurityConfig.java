package com.example.votingsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.votingsystem.security.CustomAuthenticationSuccessHandler;
import com.example.votingsystem.service.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
             .authorizeHttpRequests(authorize -> authorize
                 .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                 .anyRequest().authenticated()
             )
             .formLogin(form -> form
                 .loginPage("/login")
                 .successHandler(customAuthenticationSuccessHandler) // Use our custom handler
                 .permitAll()
             )
             .logout(logout -> logout
                 .logoutSuccessUrl("/login?logout")
                 .permitAll()
             )
             .authenticationProvider(authenticationProvider());
         return http.build();
    }
}
