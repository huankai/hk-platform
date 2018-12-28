package com.hk.oauth2.server.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hk.commons.JsonResult;
import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.util.StringUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

/**
 * @author huangkai
 * @date 2018-12-28 15:29
 */
public class Oauth2ExceptionSerializer<T extends OAuth2Exception> extends StdSerializer<T> {

    protected Oauth2ExceptionSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        //统一异常，所有响应返三个字段，@see com.hk.commons.JsonResult
        gen.writeNumberField("statusCode", getStatus(value));
        gen.writeStringField("message", getMessage(value));
        gen.writeObjectField("data", getData(value));
        gen.writeEndObject();
    }

    /**
     * 获取data字段值
     *
     * @param exception exception
     * @see JsonResult#data
     */
    protected Object getData(T exception) {
        return null;
    }

    /**
     * 获取错误消息
     *
     * @param exception exception
     * @return 错误消息
     * @see JsonResult#message
     */
    protected String getMessage(T exception) {
        return exception.getMessage();
    }

    /**
     * @param exception
     * @return
     * @see JsonResult#getStatusCode()
     */
    protected int getStatus(T exception) {
        return EnumDisplayUtils.getDisplayOrder(JsonResult.Status.BAD_REQUEST);
    }


    public static class Oauth2AppStatusExceptionJackson2Serializer extends Oauth2ExceptionSerializer<Oauth2AppStatusException> {

        protected Oauth2AppStatusExceptionJackson2Serializer() {
            super(Oauth2AppStatusException.class);
        }
    }

    public static class OAuth2UnsupportedGrantedExceptionJackson2Serializer extends Oauth2ExceptionSerializer<Oauth2UnsupportedGrantTypeException> {

        protected OAuth2UnsupportedGrantedExceptionJackson2Serializer() {
            super(Oauth2UnsupportedGrantTypeException.class);
        }

        /**
         * 获取错误消息
         *
         * @param exception exception
         * @return 错误消息
         */
        @Override
        protected String getMessage(Oauth2UnsupportedGrantTypeException exception) {
            String errorMessage = exception.getMessage();
            String[] delimitArr = StringUtils.delimitedListToStringArray(errorMessage, ":");
            if (ArrayUtils.isNotEmpty(delimitArr) && delimitArr.length == 2) {
                errorMessage = delimitArr[1];
            }
            return errorMessage;
        }
    }
}
