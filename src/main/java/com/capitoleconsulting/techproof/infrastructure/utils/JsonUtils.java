package com.capitoleconsulting.techproof.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TimeZone;

import static com.capitoleconsulting.techproof.TechProofConstants.UTILITY_CLASS;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    static final String PARSE_JSON_ERROR_MESSAGE = "Error parsing object to JSON.";

    private JsonUtils() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static String toJson(Object data) {
        return toJson(data, StandardCharsets.UTF_8, false);
    }

    public static String toJson(Object data, Charset charset, boolean pretty) {
        try {
            ObjectMapper mapper = createMapper();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (pretty) {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            }
            mapper.writeValue(baos, data);
            return baos.toString(charset);
        } catch (Exception e) {
            LOGGER.error(PARSE_JSON_ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T fromJson(String data, Class<T> valueType) {
        if (isEmpty(data)) {
            return null;
        }
        return fromJson(data.getBytes(StandardCharsets.UTF_8), valueType);
    }

    public static <T> T fromJson(byte[] data, Class<T> valueType) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            return createMapper().readValue(data, valueType);
        } catch (Exception e) {
            LOGGER.error(PARSE_JSON_ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T fromJson(String data, TypeReference<T> valueType) {
        if (isEmpty(data)) {
            return null;
        }
        return fromJson(data.getBytes(StandardCharsets.UTF_8), valueType);
    }

    public static <T> T fromJson(byte[] data, TypeReference<T> valueType) {
        if (data == null || data.length == 0) {
            return null;
        }
        try {
            return createMapper().readValue(data, valueType);
        } catch (Exception e) {
            LOGGER.error(PARSE_JSON_ERROR_MESSAGE, e);
        }
        return null;
    }

    public static <T> T fromMap(Map<String, Object> map, Class<T> valueType) {
        if (map == null) {
            return null;
        }
        try {
            return createMapper().convertValue(map, valueType);
        } catch (Exception e) {
            LOGGER.error(PARSE_JSON_ERROR_MESSAGE, e);
        }
        return null;
    }

    public static ObjectMapper createMapper() {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
        ObjectMapper objectMapper = builder.build();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();
        objectMapper.registerModule(simpleModule);
        objectMapper.setTimeZone(TimeZone.getTimeZone("CET"));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }
}
