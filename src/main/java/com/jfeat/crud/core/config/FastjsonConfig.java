package com.jfeat.crud.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.alibaba.fastjson.util.TypeUtils;
import com.jfeat.crud.core.properties.AmProperties;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastjsonConfig implements InitializingBean {
    @Resource
    AmProperties amProperties;

    public FastjsonConfig() {
    }

    @Bean
    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue});
        ParserConfig parserConfig = ParserConfig.getGlobalInstance();
        parserConfig.putDeserializer(Date.class, new FormatDateCodec(this.amProperties.getJsonSerializeDateFormat()));
        fastJsonConfig.setParserConfig(parserConfig);
        fastJsonConfig.setDateFormat(this.amProperties.getJsonSerializeDateFormat());
        ValueFilter valueFilter = (object, name, value) -> {

            /// bug: do not convert null to ""  empty string
            //if (null == value) {
            //    value = "";
            //}

            /// Long转字符串，前端javascript无法处理 long 长整型
            return value instanceof Long ? value.toString() : value;
        };
        fastJsonConfig.setSerializeFilters(new SerializeFilter[]{valueFilter});
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }

    public void afterPropertiesSet() throws Exception {
        TypeUtils.compatibleWithJavaBean = true;
    }
}
