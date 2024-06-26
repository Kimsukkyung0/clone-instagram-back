package com.example.cloneinstagramback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity(debug = true)
@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception{
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST
                                ,"/signup","/error","/**").permitAll()
                                .requestMatchers("/swagger-ui/**","/api-docs/**").permitAll().anyRequest().authenticated())
                .addFilterAfter(new JwtTokenGeneratorFilter() , BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidationFilter(),BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
//                .formLogin().disable()
//                .httpBasic().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
