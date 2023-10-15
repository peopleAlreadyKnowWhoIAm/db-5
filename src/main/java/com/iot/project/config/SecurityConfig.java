package com.iot.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults()).authorizeHttpRequests((authorizeHttpRequests)-> {
            authorizeHttpRequests.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("ILoveMother").roles("USER").build();
        UserDetails admin_mother = User.withDefaultPasswordEncoder().username("admin_mother").password("KillTheChild").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin, admin_mother);
    }

}
