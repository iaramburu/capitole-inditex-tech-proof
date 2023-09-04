package com.capitoleconsulting.techproof.infrastructure.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.capitoleconsulting.techproof.infrastructure.utils.JsonUtils.fromJson;
import static org.assertj.core.api.Assertions.assertThat;

public class MvcUtils {

    public static MockMvc initializeMvc(WebApplicationContext context) {
        return MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    public static <T> void checkList(MvcResult result, List<T> expectedResponse, TypeReference<List<T>> typeReference) {
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        List<T> response = fromJson(result.getResponse().getContentAsByteArray(), typeReference);
        assertThat(response).isEqualTo(expectedResponse);
    }

    public static MvcResult doCall(
            MockMvc mockMvc, MockHttpServletRequestBuilder httpBuilder
    ) throws Exception {
        return doCall(mockMvc, httpBuilder, false);
    }

    public static MvcResult doCall(
            MockMvc mockMvc, MockHttpServletRequestBuilder httpBuilder, boolean isCheckStatus
    ) throws Exception {
        MvcResult result = mockMvc
                .perform(httpBuilder)
                .andReturn();
        if (isCheckStatus) {
            assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        }
        return result;
    }
}
