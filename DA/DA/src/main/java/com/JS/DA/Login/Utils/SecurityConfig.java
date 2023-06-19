package com.JS.DA.Login.Utils;


import com.JS.DA.Login.Service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/css/**", "/js/**", "/", "/register", "/error","/userfiles/**","https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js","https://cdn.jsdelivr.net/emojione/2.2.7/lib/js/emojione.min.js","/ckfinder/static/ckfinder.js","https://code.jquery.com/jquery-3.6.0.min.js","https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js")
                        .permitAll()
                        .requestMatchers( "/admin/**")
                        .hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()

                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)

                        .permitAll()

                )
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()

                )
                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                        .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/403"))
                .build();
    }
}
