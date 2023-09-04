package com.capitoleconsulting.techproof.infrastructure.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.capitoleconsulting.techproof.TechProofConstants.UTILITY_CLASS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class TestUtils {

    static <T> void testUtilsConstructor(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("Expected an IllegalStateException to be thrown.");
        } catch (InvocationTargetException exception) {
            assertThat(exception.getCause().getMessage()).isEqualTo(UTILITY_CLASS);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
            fail("Expected an IllegalStateException to be thrown.");
        }
    }
}
