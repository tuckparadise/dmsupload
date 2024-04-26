package com.rhb.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import java.util.Optional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class Config implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter converter;

    @Override
    public void afterPropertiesSet() {
        configureJacksonToFailOnUnknownProperties();
    }

    private void configureJacksonToFailOnUnknownProperties() {
       Optional<MappingJackson2HttpMessageConverter> value =  converter.getMessageConverters().stream()
                .filter(mc -> mc.getClass()
                        .equals(MappingJackson2HttpMessageConverter.class))
                .map(mc -> (MappingJackson2HttpMessageConverter) mc)
                .findFirst();
        if (value.isPresent()) {
            MappingJackson2HttpMessageConverter httpMessageConverter = value.get();

            httpMessageConverter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
