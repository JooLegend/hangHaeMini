package com.sparta.hanghaemini.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        registry.addMapping("/**")
                .allowedOrigins("/**")  //클라이언트에서 허용
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Access_Token")
                //x exposedHeaders("*")은 모든 헤더값을 반환한다고 하는데, 이렇게했을 때 헤더값에 값이 안읽어와짐 그래서 직접 "name"을 넣어주었더니 됐음
                .allowedMethods("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
    }
}