package ch.zhaw.oop.stocks;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>FEM: Configuration class for Spring Web MVC.</p>
 * <p>Enables Cross-Origin Resource Sharing (CORS) for all URL mappings.</p>
 *
 * @author Adrian Schaerer, Dominic Troll, Manuel Ferretti
 * @version 0.1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * FEM: Configures CORS for all URL mappings.
     *
     * @param registry the CORS registry to add mappings and configurations
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // FEM: Allow all URL mappings.
                .allowedOrigins("http://localhost:4200") // FEM: Allow Cross-Origin Resource Sharing beween front- and backend.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // FEM: Allow basic methods.
                .allowedHeaders("*"); // FEM: Allow all headers.
    }
}