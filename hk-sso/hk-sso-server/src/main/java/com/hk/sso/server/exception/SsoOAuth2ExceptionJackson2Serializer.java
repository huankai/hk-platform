package com.hk.sso.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.web.JsonResult;

import java.io.IOException;

/**
 * 异常信息序列化
 *
 * @author: kevin
 * @date: 2018-08-20 08:45
 */
public class SsoOAuth2ExceptionJackson2Serializer extends StdSerializer<SsoUnsupportedGrantTypeException> {

    protected SsoOAuth2ExceptionJackson2Serializer() {
        super(SsoUnsupportedGrantTypeException.class);
    }

    @Override
    public void serialize(SsoUnsupportedGrantTypeException value, JsonGenerator jGen, SerializerProvider provider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("status", String.valueOf(EnumDisplayUtils.getDisplayOrder(JsonResult.Status.BAD_REQUEST)));
        String errorMessage = value.getMessage();
        String[] delimitArr = StringUtils.delimitedListToStringArray(errorMessage, ":");
        if (ArrayUtils.isNotEmpty(delimitArr) && delimitArr.length == 2) {
            errorMessage = delimitArr[1];
        }
        jGen.writeStringField("message", "不支持的认证类型：" + errorMessage);
        jGen.writeEndObject();
    }
}
