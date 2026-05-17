// // package com.onlinebankingsystem;

// // import java.util.HashSet;

// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;
// // import org.springframework.boot.SpringApplication;
// // import org.springframework.boot.autoconfigure.SpringBootApplication;
// // import org.springframework.web.servlet.config.annotation.CorsRegistry;
// // import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// // import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// // import com.onlinebankingsystem.resource.BankAccountResource;

// // @SpringBootApplication
// // @EnableWebMvc
// // public class OnlineBankingSystemApplication implements WebMvcConfigurer {
	
// // 	private final Logger LOG = LoggerFactory.getLogger(BankAccountResource.class);

// // 	private static int ACCESS_CONTROL_MAX_AGE_IN_SECONDS = 12 * 60 * 60;

// // 	private static final HashSet<String> TRUSTED_SOURCES = new HashSet<String>();

// // 	static {
// // 		TRUSTED_SOURCES.add("*");
// // 	}

// // 	// method to add trusted sources via application context
// // 	public static void setTrustedSources(final HashSet<String> sources) {
// // 		TRUSTED_SOURCES.addAll(sources);
// // 	}

// // 	public static void main(String[] args) {
// // 		SpringApplication.run(OnlineBankingSystemApplication.class, args);
// // 	}

// // //	@Bean
// // //	public WebMvcConfigurer corsConfigurer() {
// // //		return new WebMvcConfigurer() {
// // //			@Override
// // //			public void addCorsMappings(CorsRegistry registry) {
// // //				registry.addMapping("/**")
// // //                .allowedOrigins("*") // Replace with your allowed origins
// // //                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// // //                .allowedHeaders("Authorization", "Content-Type") // Specify the headers you want to allow
// // //                .allowCredentials(true);
// // //			}
// // //		};
// // //	}

// // 	@Override
// // 	public void addCorsMappings(CorsRegistry registry) {

// // 		LOG.info(TRUSTED_SOURCES.toString());
		
// // 		registry.addMapping("/**")
// // 				// .allowedOrigins(TRUSTED_SOURCES.toArray(new String[TRUSTED_SOURCES.size()]))
// // 				.allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
// // 				.allowedHeaders("origin", "content-type", "accept", "authorization", "user-agent", "host",
// // 						"X-Forwarded-For", "X-Forwarded-Proto", "X-Forwarded-Port", "X-Redirected-Path",
// // 						"X-Redirected-Params", "X-TraceId", "X-Feature-Flags", "X-Partner-Id")
// // 				.exposedHeaders("Content-Length", "Content-Type", "Content-Disposition", "Cache-Control")
// // 				.allowCredentials(true).maxAge(ACCESS_CONTROL_MAX_AGE_IN_SECONDS);

// // 	}

// // }
// // ########################################################################################################################//#endregion
// package com.onlinebankingsystem;

// import java.util.HashSet;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.onlinebankingsystem.resource.BankAccountResource;

// @SpringBootApplication
// @EnableWebMvc
// public class OnlineBankingSystemApplication implements WebMvcConfigurer { // Implements WebMvcConfigurer to customize MVC configuration
	
// 	private final Logger LOG = LoggerFactory.getLogger(BankAccountResource.class); // Logger instance for logging events

// 	private static int ACCESS_CONTROL_MAX_AGE_IN_SECONDS = 12 * 60 * 60; // Max age for CORS preflight cache (12 hours)

// 	private static final HashSet<String> TRUSTED_SOURCES = new HashSet<String>(); // A set to hold trusted origins

// 	static {
// 		TRUSTED_SOURCES.add("*"); // Adding wildcard to allow all origins initially (not secure for production)
// 	}

// 	// Method to dynamically add trusted sources at runtime
// 	public static void setTrustedSources(final HashSet<String> sources) {
// 		TRUSTED_SOURCES.addAll(sources); // Add new sources to the existing trusted sources
// 	}

// 	// Main method to start the Spring Boot application
// 	public static void main(String[] args) {
// 		SpringApplication.run(OnlineBankingSystemApplication.class, args);
// 	}

// 	// Uncommented code block with bean definition for CORS settings
// 	// This is an alternative to overriding addCorsMappings() directly
// 	// @Bean
// 	// public WebMvcConfigurer corsConfigurer() {
// 	//     return new WebMvcConfigurer() {
// 	//         @Override
// 	//         public void addCorsMappings(CorsRegistry registry) {
// 	//             registry.addMapping("/**")
// 	//                   .allowedOrigins("*") // Replace with your allowed origins
// 	//                   .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
// 	//                   .allowedHeaders("Authorization", "Content-Type") // Specify the headers you want to allow
// 	//                   .allowCredentials(true);
// 	//         }
// 	//     };
// 	// }

// 	@Override
// 	public void addCorsMappings(CorsRegistry registry) {
// 		LOG.info(TRUSTED_SOURCES.toString()); // Log the list of trusted sources for debugging purposes

// 		registry.addMapping("/**") // Allow CORS on all paths
// 				// .allowedOrigins(TRUSTED_SOURCES.toArray(new String[TRUSTED_SOURCES.size()]))
// 				.allowedOrigins("*") // Allow all origins; replace with specific origins for security in production
// 				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Specify allowed HTTP methods
// 				.allowedHeaders("origin", "content-type", "accept", "authorization", "user-agent", "host", 
// 						"X-Forwarded-For", "X-Forwarded-Proto", "X-Forwarded-Port", "X-Redirected-Path", 
// 						"X-Redirected-Params", "X-TraceId", "X-Feature-Flags", "X-Partner-Id") // Specify headers
// 				.exposedHeaders("Content-Length", "Content-Type", "Content-Disposition", "Cache-Control") // Expose specific headers
// 				.allowCredentials(true) // Allow sending cookies or authentication headers
// 				.maxAge(ACCESS_CONTROL_MAX_AGE_IN_SECONDS); // Set max age for preflight requests
// 	}
// }

package com.onlinebankingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinebankingsystem.resource.BankAccountResource;

@SpringBootApplication
public class OnlineBankingSystemApplication {

    private final Logger LOG = LoggerFactory.getLogger(BankAccountResource.class); // Logger instance for logging events

    // Main method to start the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingSystemApplication.class, args);
    }
}
