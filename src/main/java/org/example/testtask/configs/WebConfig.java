package org.example.testtask.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/");

        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cat").setViewName("views/main-page.html");

        // TODO: need to refactor on generic static resolving files
        registry.addViewController("/js/delete-button.js").setViewName("js/delete-button.js");
        registry.addViewController("/js/create-button.js").setViewName("js/create-button.js");
        registry.addViewController("/js/edit-button.js").setViewName("js/edit-button.js");
        registry.addViewController("/js/cat-grid.js").setViewName("js/cat-grid.js");
        registry.addViewController("/js/validators.js").setViewName("js/validators.js");
    }
}
