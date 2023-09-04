package com.capitoleconsulting.techproof.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.capitoleconsulting.techproof.domain.JsonTestObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capitoleconsulting.techproof.Builders.validJsonTestObject;
import static com.capitoleconsulting.techproof.infrastructure.utils.JsonUtils.*;
import static com.capitoleconsulting.techproof.infrastructure.utils.TestUtils.testUtilsConstructor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

@ExtendWith(SpringExtension.class)
class JsonUtilsTest {

    private static final String EXPECTED_BIG_DECIMAL_VALUE = "5.1";

    @Test
    void testConstructorException() {
        testUtilsConstructor(JsonUtils.class);
    }

    @Test
    void testToJsonWhenExceptionIsThrown() {
        assertThat(toJson("", null, false)).isNull();
    }

    @Test
    void testToJson() {
        JsonTestObject jsonTestObject = validJsonTestObject();
        String expectedJson = String.format("{\"integerValue\":%s,\"longValue\":%s,\"doubleValue\":%s,\"floatValue\":%s,\"bigDecimalValue\":%s,\"stringValue\":\"%s\"}",
            jsonTestObject.getIntegerValue(), jsonTestObject.getLongValue(), jsonTestObject.getDoubleValue(),
            jsonTestObject.getFloatValue(), EXPECTED_BIG_DECIMAL_VALUE, jsonTestObject.getStringValue());
        assertThat(toJson(jsonTestObject)).isEqualTo(expectedJson);
    }

    @Test
    void testToJsonPrettify() {
        JsonTestObject jsonTestObject = validJsonTestObject();
        String lineSeparator = System.lineSeparator().replace("\n", "");
        String expectedJson = String.format("""
            {%s
              "integerValue" : %s,%s
              "longValue" : %s,%s
              "doubleValue" : %s,%s
              "floatValue" : %s,%s
              "bigDecimalValue" : %s,%s
              "stringValue" : "%s"%s
            }""",
            lineSeparator,
            jsonTestObject.getIntegerValue(), lineSeparator,
            jsonTestObject.getLongValue(), lineSeparator,
            jsonTestObject.getDoubleValue(), lineSeparator,
            jsonTestObject.getFloatValue(), lineSeparator,
            EXPECTED_BIG_DECIMAL_VALUE, lineSeparator,
            jsonTestObject.getStringValue(), lineSeparator);
        assertThat(toJson(jsonTestObject, StandardCharsets.UTF_8, true)).isEqualTo(expectedJson);
    }

    @Test
    void testFromJsonClassWithByteArrayWhenIsNull() {
        byte[] json = null;
        assertThat(fromJson(json, JsonTestObject.class)).isNull();
    }

    @Test
    void testFromJsonClassWithByteArrayWhenIsEmpty() {
        byte[] json = new byte[0];
        assertThat(fromJson(json, JsonTestObject.class)).isNull();
    }

    @Test
    void testFromJsonClassWhenIsEmpty() {
        String json = null;
        assertThat(fromJson(json, JsonTestObject.class)).isNull();
    }

    @Test
    void testFromJsonClassWhenIsInvalid() {
        String json = "invalid";
        assertThat(fromJson(json, JsonTestObject.class)).isNull();
    }

    @Test
    void testFromJsonClass() {
        JsonTestObject expectedObject = validJsonTestObject();
        String json = String.format("{\"integerValue\":%s,\"longValue\":%s,\"doubleValue\":%s,\"floatValue\":%s,\"bigDecimalValue\":%s,\"stringValue\":\"%s\"}",
            expectedObject.getIntegerValue(), expectedObject.getLongValue(), expectedObject.getDoubleValue(),
            expectedObject.getFloatValue(), EXPECTED_BIG_DECIMAL_VALUE, expectedObject.getStringValue());

        assertThat(fromJson(json, JsonTestObject.class)).isEqualTo(expectedObject);
    }

    @Test
    void testFromJsonTypeReferenceWithByteArrayWhenIsNull() {
        byte[] json = null;
        assertThat((String) fromJson(json, new TypeReference<>() {})).isNull();
    }

    @Test
    void testFromJsonTypeReferenceWithByteArrayWhenIsEmpty() {
        byte[] json = new byte[0];
        assertThat((String) fromJson(json, new TypeReference<>() {})).isNull();
    }

    @Test
    void testFromJsonTypeReferenceWhenIsEmpty() {
        String json = null;
        assertThat((String) fromJson(json, new TypeReference<>() {})).isNull();
    }

    @Test
    void testFromJsonTypeReferenceWhenIsInvalid() {
        String json = "invalid";
        assertThat((String) fromJson(json, new TypeReference<>() {})).isNull();
    }

    @Test
    void testFromJsonTypeReference() {
        JsonTestObject expectedObject = validJsonTestObject();
        List<JsonTestObject> expectedObjects = newArrayList(expectedObject);
        String json = String.format("[{\"integerValue\":%s,\"longValue\":%s,\"doubleValue\":%s,\"floatValue\":%s,\"bigDecimalValue\":%s,\"stringValue\":\"%s\"}]",
            expectedObject.getIntegerValue(), expectedObject.getLongValue(), expectedObject.getDoubleValue(),
            expectedObject.getFloatValue(), EXPECTED_BIG_DECIMAL_VALUE, expectedObject.getStringValue());
        List<JsonTestObject> retrieved = fromJson(json, new TypeReference<>() {});
        assertThat(retrieved).isEqualTo(expectedObjects);
    }

    @Test
    void testFromMap() {
        JsonTestObject expectedObject = validJsonTestObject();
        Map<String, Object> map = new HashMap<>();
        map.put("integerValue", expectedObject.getIntegerValue());
        map.put("longValue", expectedObject.getLongValue());
        map.put("doubleValue", expectedObject.getDoubleValue());
        map.put("floatValue", expectedObject.getFloatValue());
        map.put("bigDecimalValue", expectedObject.getBigDecimalValue());
        map.put("stringValue", expectedObject.getStringValue());

        assertThat(fromMap(map, JsonTestObject.class)).isEqualTo(expectedObject);
    }

    @Test
    void testFromMapWhenIsEmpty() {
        assertThat(fromMap(null, JsonTestObject.class)).isNull();
    }

    @Test
    void testFromMapWhenMapIsInvalid() {
        Map<String, Object> map = new HashMap<>();
        map.put("longValue", "stringValue");
        assertThat(fromMap(map, JsonTestObject.class)).isNull();
    }
}
