package com.shb.springbootstudy.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 类型转换配置
 * @author Hamburger
 * @since 2021-05-12
 */
@Configuration
public class ConverterConfiguration {

    /**
     * String -> LocalDateTime
     * @return
     */
    @Bean
    public Converter<String,LocalDateTime> localDateTimeConverter(){
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
            }
        };
    }

    /**
     * String -> Date
     * @return
     */
    @Bean
    public Converter<String, Date> dateConverter(){
        return new Converter<String, Date>() {
            @Override
            public Date convert(String value) {
                if(StrUtil.isEmpty(value)) {
                    return null;
                }
                SimpleDateFormat formatter = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
                try {
                    return formatter.parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException();
            }
        };
    }
}
