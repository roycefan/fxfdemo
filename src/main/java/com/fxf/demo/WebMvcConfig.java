//package com.fxf.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    /**
//     * 自定义JSON转换器
//     *
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //日期格式化
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        //处理中文乱码问题
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//
//        converter.setSupportedMediaTypes(fastMediaTypes);
//        converter.setFastJsonConfig(fastJsonConfig);
//
//        converters.add(converter);
//    }
//
//}