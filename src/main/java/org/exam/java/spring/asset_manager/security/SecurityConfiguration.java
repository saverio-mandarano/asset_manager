package org.exam.java.spring.asset_manager.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity // dico a Spring che deve utilizzare questa configuraziione per tutte le
                   // richieste web sicurity
public class SecurityConfiguration {
    @Bean
    // @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // .cors(Customizer.withDefaults()) // questo per rest api
                // .csrf(csrf -> csrf.disable()) // questo per rest api
                .authenticationProvider(authenticationProvider()) // ?
                .authorizeHttpRequests(requests -> requests

                        // ADMIN
                        .requestMatchers("/assets/create", "/assets/edit/**", "/assets/delete/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/assets/**")
                        .hasAuthority("ADMIN")

                        .requestMatchers("/tags/create", "/tags/edit/**", "/tags/delete/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/tags/**")
                        .hasAuthority("ADMIN")

                        .requestMatchers("/categories/create", "/categories/edit/**", "/categories/delete/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/categories/**")
                        .hasAuthority("ADMIN")

                        // api
                        .requestMatchers("/api/**").permitAll()

                        // tutto il resto richiedo autenticazione
                        .anyRequest()
                        .authenticated())

                .formLogin(form -> form
                        .permitAll())

                .logout(logout -> logout
                        .permitAll())

                .exceptionHandling(exception -> {
                });

        return http.build();
    }

    // @SuppressWarnings("deprecation")
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailService());

        // authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
