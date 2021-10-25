package edu.supavenir.spanimals;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class Webconfig extends WebMvcConfigurerAdapter {

    public void addRessourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/webjars/**")
          .addResourceLocations("/webjars/");
    }
    
}
