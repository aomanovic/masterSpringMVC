package masterSpringMVC.config;

import masterSpringMVC.date.USLocalDateFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.time.LocalDate;
import java.util.Locale;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter
{
    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addFormatterForFieldType(LocalDate.class, new USLocalDateFormatter());
    }

    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource result = new ResourceBundleMessageSource();

        String[] basenames = {
                "messages"
        };

        result.setBasenames(basenames);

        return result;

    }


    @Bean
    public LocaleResolver localeResolver()
    {
        SessionLocaleResolver result = new SessionLocaleResolver();
        result.setDefaultLocale(Locale.ENGLISH);

        return result;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor()
    {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeChangeInterceptor());
    }
}