package faireai.tinyweatherbulletin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

@Configuration
public class ApplicationBeanFactory {

    @Autowired
    private Set<Converter<?, ?>> converters;

    @Bean
    public ConversionServiceFactoryBean conversionService() {

        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(converters);

        return bean;
    }

}
