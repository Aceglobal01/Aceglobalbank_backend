// package com.onlinebankingsystem.config;

// import java.util.Arrays;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @Configuration
// @EnableWebMvc
// public class WebConfig {

//     private static final Long MAX_AGE = 3600L;
//     private static final int CORS_FILTER_ORDER = -102;

//     @Bean
//     public FilterRegistrationBean<CorsFilter> corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
//         config.setAllowCredentials(true);
//         config.addAllowedOrigin("https://bank.aceglobalpod.online");
//         config.setAllowedHeaders(Arrays.asList(
//                 HttpHeaders.AUTHORIZATION,
//                 HttpHeaders.CONTENT_TYPE,
//                 HttpHeaders.ACCEPT));
//         config.setAllowedMethods(Arrays.asList(
//                 HttpMethod.GET.name(),
//                 HttpMethod.POST.name(),
//                 HttpMethod.PUT.name(),
//                 HttpMethod.DELETE.name()));
//         config.setMaxAge(MAX_AGE);
//         source.registerCorsConfiguration("/**", config);
//         FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

//         // should be set order to -100 because we need to CorsFilter before SpringSecurityFilter
//         bean.setOrder(CORS_FILTER_ORDER);
//         return bean;
//     }
// }


package com.onlinebankingsystem.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://bank.aceglobalpod.online");
        
        // FIX 1: Added Authorization header was already there, but added
        // HttpHeaders.ORIGIN and HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN
        // to ensure preflight headers are not stripped
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.ORIGIN,                        // ADDED
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)); // ADDED

        // FIX 2: Added OPTIONS method — without this the preflight request
        // is rejected and the browser never gets Allow-Origin header back
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()));               // ADDED

        config.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        // FIX 3: Changed order from -102 to -100 to ensure CORS filter runs
        // before Spring Security filter which was blocking OPTIONS requests
        bean.setOrder(-100);                              // CHANGED from -102
        return bean;
    }
}