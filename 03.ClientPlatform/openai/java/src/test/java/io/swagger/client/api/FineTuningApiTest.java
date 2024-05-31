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

import io.swagger.client.model.CreateFineTuningJobRequest;
import io.swagger.client.model.FineTuningJob;
import io.swagger.client.model.ListFineTuningJobCheckpointsResponse;
import io.swagger.client.model.ListFineTuningJobEventsResponse;
import io.swagger.client.model.ListPaginatedFineTuningJobsResponse;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for FineTuningApi
 */
@Ignore
public class FineTuningApiTest {

    private final FineTuningApi api = new FineTuningApi();

    /**
     * Immediately cancel a fine-tune job. 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void cancelFineTuningJobTest() throws Exception {
        String fineTuningJobId = null;
        FineTuningJob response = api.cancelFineTuningJob(fineTuningJobId);

        // TODO: test validations
    }
    /**
     * Creates a fine-tuning job which begins the process of creating a new model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void createFineTuningJobTest() throws Exception {
        CreateFineTuningJobRequest body = null;
        FineTuningJob response = api.createFineTuningJob(body);

        // TODO: test validations
    }
    /**
     * Get status updates for a fine-tuning job. 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void listFineTuningEventsTest() throws Exception {
        String fineTuningJobId = null;
        String after = null;
        Integer limit = null;
        ListFineTuningJobEventsResponse response = api.listFineTuningEvents(fineTuningJobId, after, limit);

        // TODO: test validations
    }
    /**
     * List checkpoints for a fine-tuning job. 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void listFineTuningJobCheckpointsTest() throws Exception {
        String fineTuningJobId = null;
        String after = null;
        Integer limit = null;
        ListFineTuningJobCheckpointsResponse response = api.listFineTuningJobCheckpoints(fineTuningJobId, after, limit);

        // TODO: test validations
    }
    /**
     * List your organization&#x27;s fine-tuning jobs 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void listPaginatedFineTuningJobsTest() throws Exception {
        String after = null;
        Integer limit = null;
        ListPaginatedFineTuningJobsResponse response = api.listPaginatedFineTuningJobs(after, limit);

        // TODO: test validations
    }
    /**
     * Get info about a fine-tuning job.  [Learn more about fine-tuning](/docs/guides/fine-tuning) 
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void retrieveFineTuningJobTest() throws Exception {
        String fineTuningJobId = null;
        FineTuningJob response = api.retrieveFineTuningJob(fineTuningJobId);

        // TODO: test validations
    }
}
