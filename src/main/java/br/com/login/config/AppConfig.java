package br.com.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class AppConfig implements WebMvcConfigurer {
	
	@Value(value = "${cors.origin.patterns:default}")
	private String corsOriginPatterns = "";
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.favorParameter(false).ignoreAcceptHeader(false).useRegisteredExtensionsOnly(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowedOrigins = corsOriginPatterns.split(",");
		registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns(allowedOrigins).allowCredentials(true);
	}
}