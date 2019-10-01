package com.springaft.common.exception;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class Auth2ExceptionSerializer extends StdSerializer<OAuth2Exception> {

    private static final int FAIL = 1;

    public Auth2ExceptionSerializer() {
        super(OAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(OAuth2Exception e, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("code", FAIL);
        jsonGenerator.writeStringField("msg", e.getMessage());
        jsonGenerator.writeStringField("data", e.getOAuth2ErrorCode());
        jsonGenerator.writeEndObject();
    }
}
