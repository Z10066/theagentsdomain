/*
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.model.CreateEmbeddingRequest;
import io.swagger.client.model.CreateEmbeddingResponse;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for EmbeddingsApi
 */
@Ignore
public class EmbeddingsApiTest {

    private final EmbeddingsApi api = new EmbeddingsApi();

    /**
     * Creates an embedding vector representing the input text.
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void createEmbeddingTest() throws Exception {
        CreateEmbeddingRequest body = null;
        CreateEmbeddingResponse response = api.createEmbedding(body);

        // TODO: test validations
    }
}