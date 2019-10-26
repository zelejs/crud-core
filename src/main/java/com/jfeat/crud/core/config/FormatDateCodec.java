package com.jfeat.crud.core.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.serializer.DateCodec;
import java.lang.reflect.Type;

public class FormatDateCodec extends DateCodec {
    public static final FormatDateCodec instance = new FormatDateCodec();
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public FormatDateCodec() {
    }

    public FormatDateCodec(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public <T> T cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
        parser.setDateFormat(this.dateFormat);
        return super.cast(parser, clazz, fieldName, val);
    }
}
