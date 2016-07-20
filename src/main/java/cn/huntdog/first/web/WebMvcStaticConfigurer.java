package cn.huntdog.first.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by JonDai on 2016/7/19.
 */
@Configuration
public class WebMvcStaticConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(new String[]{"/**"}).
                    addResourceLocations("/webapp/static/");
        super.addResourceHandlers(registry);
    }
}
