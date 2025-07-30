package com.suelas.prueba.springboot.jpa.suelaspruebasjpa.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.security.filter.JwtAuthenticationFilter;
import com.suelas.prueba.springboot.jpa.suelaspruebasjpa.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                .requestMatchers(HttpMethod.GET,"/product").permitAll()
                .requestMatchers("/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/catalog").permitAll()
                .requestMatchers(HttpMethod.GET,"/listado").permitAll()
                .requestMatchers("/colores/{name}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/tallas").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/tallas").hasRole("ADMIN")
                .requestMatchers("/tallas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/tamanios/{name}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/tipos/{name}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/pages").permitAll()
                .requestMatchers("/pages/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(configurationSource()))
                .build();
    }

    @Bean
    CorsConfigurationSource configurationSource(){

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;

    }

    @Bean
    FilterRegistrationBean<CorsFilter>corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
            new CorsFilter(configurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }

}
