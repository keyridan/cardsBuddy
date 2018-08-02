package com.j0rsa.cardsbuddy.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class ConversionServiceConfigurator {
    private Set<Converter> converters;
    private ConverterRegistry converterRegistry;

    public ConversionServiceConfigurator(Set<Converter> converters, ConverterRegistry converterRegistry) {
        this.converters = converters;
        this.converterRegistry = converterRegistry;
    }


    @PostConstruct
    public void afterPropertiesSet() {
        ConversionServiceFactory.registerConverters(converters, converterRegistry);
    }
}