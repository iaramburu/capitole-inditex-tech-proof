package com.capitoleconsulting.techproof.application;

import com.capitoleconsulting.techproof.domain.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static com.capitoleconsulting.techproof.infrastructure.utils.MvcUtils.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles(profiles = { "mock-services" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = initializeMvc(this.context);
    }

    @AfterEach
    void teardown() {
        reset(this.productService);
    }

    @Test
    void testFindAll() throws Exception {
        List<Long> expectedProductIds = Arrays.asList(2L, 4L, 6L);

        doReturn(expectedProductIds).when(this.productService).findAll();

        MvcResult result = processFindAll();
        checkList(result, expectedProductIds, new TypeReference<>() {});
    }

    private MvcResult processFindAll() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        MockHttpServletRequestBuilder requestBuilder = get("/products")
                .session(new MockHttpSession())
                .params(params);
        return doCall(this.mockMvc, requestBuilder);
    }
}
