package com.example.demo.security;

import static com.example.demo.common.WebConst.CORRELATION_PRD_NORMAL_URL;
import static com.example.demo.common.WebConst.CORRELATION_TMS_NORMAL_URL;
import static com.example.demo.common.WebConst.CORRELATION_PRD_AUTO_URL;
import static com.example.demo.common.WebConst.CORRELATION_TMS_AUTO_URL;
import static com.example.demo.common.WebConst.ENVIRONMENT_FIND_URL;
import static com.example.demo.common.WebConst.GET;
import static com.example.demo.common.WebConst.LOCALHOST_3000;
import static com.example.demo.common.WebConst.LOCATION_FIND_URL;
import static com.example.demo.common.WebConst.POWER_VOLUME_FIND_URL;
import static com.example.demo.common.WebConst.POST;
import static com.example.demo.common.WebConst.TRAFFIC_PRD_ENV_FIND_URL;
import static com.example.demo.common.WebConst.TRAFFIC_TMS_ENV_FIND_URL;
import static com.example.demo.common.WebConst.TRAFFIC_VOLUME_TMS_FIND_URL;
import static com.example.demo.common.WebConst.TRAFFIC_VOLUME_PRD_FIND_URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

    	http
        .authorizeRequests()
        	.mvcMatchers(TRAFFIC_VOLUME_TMS_FIND_URL).permitAll()
        	.mvcMatchers(TRAFFIC_VOLUME_PRD_FIND_URL).permitAll()
        	.mvcMatchers(CORRELATION_TMS_NORMAL_URL).permitAll()
        	.mvcMatchers(CORRELATION_PRD_NORMAL_URL).permitAll()
        	.mvcMatchers(CORRELATION_TMS_AUTO_URL).permitAll()
        	.mvcMatchers(CORRELATION_PRD_AUTO_URL).permitAll()
        	.mvcMatchers(POWER_VOLUME_FIND_URL).permitAll()
        	.mvcMatchers(TRAFFIC_PRD_ENV_FIND_URL).permitAll()
        	.mvcMatchers(TRAFFIC_TMS_ENV_FIND_URL).permitAll()
        	.mvcMatchers(LOCATION_FIND_URL).permitAll()
        	.mvcMatchers(ENVIRONMENT_FIND_URL).permitAll()
            .anyRequest().authenticated()
            .and()
        .csrf()
//    		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        	.disable()
        .cors()
            .configurationSource(this.corsConfigurationSource())
        ;

	}
	
    private CorsConfigurationSource corsConfigurationSource() {
    	
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(LOCALHOST_3000));
		corsConfiguration.setAllowedMethods(Arrays.asList(POST,GET));
		corsConfiguration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;

    }
}
