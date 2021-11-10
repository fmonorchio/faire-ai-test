package faireai.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreBeanFactory {

    @Bean
    public RestTemplate client() {
        return new RestTemplate();
    }

}
