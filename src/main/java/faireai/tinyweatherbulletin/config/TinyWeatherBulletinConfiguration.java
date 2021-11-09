package faireai.tinyweatherbulletin.config;

import faireai.tinyweatherbulletin.converter.StringToLocalTimeRangeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class TinyWeatherBulletinConfiguration implements WebMvcConfigurer {

    @Autowired
    private StringToLocalTimeRangeConverter string2LocalTimeRangeConverter;

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();

        Set<Converter<?, ?>> converters = new HashSet<>();

        converters.add(string2LocalTimeRangeConverter);

        bean.setConverters(converters);
        return bean;
    }

    @Bean
    public RestTemplate client() {
        return new RestTemplate();
    }

}
