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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    // Cache CORS preflight response for 1 hour
    private static final Long MAX_AGE = 3600L;

    // Ensure CORS filter executes BEFORE Spring Security filter
    // This is important because preflight OPTIONS requests
    // can be blocked by Spring Security if CORS runs too late
    private static final int CORS_FILTER_ORDER = -102;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        // Create CORS configuration object
        CorsConfiguration config = new CorsConfiguration();

        // FIX 1:
        // Allow cookies, authorization headers, and sessions
        // in cross-origin requests
        config.setAllowCredentials(true);

        // FIX 2:
        // Explicitly allow your frontend domain
        // IMPORTANT:
        // When allowCredentials=true, you CANNOT use "*"
        config.setAllowedOrigins(Arrays.asList(
            "https://bank.aceglobalpod.online"
        ));

        // FIX 3:
        // Allow important headers sent by the frontend
        config.setAllowedHeaders(Arrays.asList(
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.ACCEPT,
            HttpHeaders.ORIGIN
        ));

        // FIX 4:
        // VERY IMPORTANT:
        // OPTIONS must be allowed because browsers send
        // a preflight OPTIONS request before POST requests
        // that contain application/json
        config.setAllowedMethods(Arrays.asList(
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "OPTIONS"
        ));

        // FIX 5:
        // Cache preflight response for performance
        config.setMaxAge(MAX_AGE);

        // Register CORS configuration for all routes
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        // Create CORS filter
        FilterRegistrationBean<CorsFilter> bean =
                new FilterRegistrationBean<>(new CorsFilter(source));

        // FIX 6:
        // Execute CORS filter BEFORE Spring Security
        bean.setOrder(CORS_FILTER_ORDER);

        return bean;
    }
}