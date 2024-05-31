package io.swagger.api;

import io.swagger.model.CreateFineTuningJobRequest;
import io.swagger.model.FineTuningJob;
import io.swagger.model.ListFineTuningJobCheckpointsResponse;
import io.swagger.model.ListFineTuningJobEventsResponse;
import io.swagger.model.ListPaginatedFineTuningJobsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")
@RestController
public class FineTuningApiController implements FineTuningApi {

    private static final Logger log = LoggerFactory.getLogger(FineTuningApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FineTuningApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<FineTuningJob> cancelFineTuningJob(@Parameter(in = ParameterIn.PATH, description = "The ID of the fine-tuning job to cancel. ", required=true, schema=@Schema()) @PathVariable("fine_tuning_job_id") String fineTuningJobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<FineTuningJob>(objectMapper.readValue("{\n  \"training_file\" : \"training_file\",\n  \"result_files\" : [ \"file-abc123\", \"file-abc123\" ],\n  \"finished_at\" : 6,\n  \"seed\" : 5,\n  \"fine_tuned_model\" : \"fine_tuned_model\",\n  \"validation_file\" : \"validation_file\",\n  \"created_at\" : 0,\n  \"error\" : {\n    \"code\" : \"code\",\n    \"param\" : \"param\",\n    \"message\" : \"message\"\n  },\n  \"estimated_finish\" : 5,\n  \"organization_id\" : \"organization_id\",\n  \"hyperparameters\" : {\n    \"n_epochs\" : \"\"\n  },\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"trained_tokens\" : 1,\n  \"integrations\" : [ \"\", \"\", \"\", \"\", \"\" ],\n  \"object\" : \"fine_tuning.job\",\n  \"status\" : \"validating_files\"\n}", FineTuningJob.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<FineTuningJob>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<FineTuningJob>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<FineTuningJob> createFineTuningJob(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateFineTuningJobRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<FineTuningJob>(objectMapper.readValue("{\n  \"training_file\" : \"training_file\",\n  \"result_files\" : [ \"file-abc123\", \"file-abc123\" ],\n  \"finished_at\" : 6,\n  \"seed\" : 5,\n  \"fine_tuned_model\" : \"fine_tuned_model\",\n  \"validation_file\" : \"validation_file\",\n  \"created_at\" : 0,\n  \"error\" : {\n    \"code\" : \"code\",\n    \"param\" : \"param\",\n    \"message\" : \"message\"\n  },\n  \"estimated_finish\" : 5,\n  \"organization_id\" : \"organization_id\",\n  \"hyperparameters\" : {\n    \"n_epochs\" : \"\"\n  },\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"trained_tokens\" : 1,\n  \"integrations\" : [ \"\", \"\", \"\", \"\", \"\" ],\n  \"object\" : \"fine_tuning.job\",\n  \"status\" : \"validating_files\"\n}", FineTuningJob.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<FineTuningJob>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<FineTuningJob>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListFineTuningJobEventsResponse> listFineTuningEvents(@Parameter(in = ParameterIn.PATH, description = "The ID of the fine-tuning job to get events for. ", required=true, schema=@Schema()) @PathVariable("fine_tuning_job_id") String fineTuningJobId,@Parameter(in = ParameterIn.QUERY, description = "Identifier for the last event from the previous pagination request." ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "Number of events to retrieve." ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListFineTuningJobEventsResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"level\" : \"info\",\n    \"created_at\" : 0,\n    \"id\" : \"id\",\n    \"message\" : \"message\",\n    \"object\" : \"fine_tuning.job.event\"\n  }, {\n    \"level\" : \"info\",\n    \"created_at\" : 0,\n    \"id\" : \"id\",\n    \"message\" : \"message\",\n    \"object\" : \"fine_tuning.job.event\"\n  } ],\n  \"object\" : \"list\"\n}", ListFineTuningJobEventsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListFineTuningJobEventsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListFineTuningJobEventsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListFineTuningJobCheckpointsResponse> listFineTuningJobCheckpoints(@Parameter(in = ParameterIn.PATH, description = "The ID of the fine-tuning job to get checkpoints for. ", required=true, schema=@Schema()) @PathVariable("fine_tuning_job_id") String fineTuningJobId,@Parameter(in = ParameterIn.QUERY, description = "Identifier for the last checkpoint ID from the previous pagination request." ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "Number of checkpoints to retrieve." ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "limit", required = false, defaultValue="10") Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListFineTuningJobCheckpointsResponse>(objectMapper.readValue("{\n  \"first_id\" : \"first_id\",\n  \"data\" : [ {\n    \"step_number\" : 6,\n    \"created_at\" : 0,\n    \"fine_tuning_job_id\" : \"fine_tuning_job_id\",\n    \"id\" : \"id\",\n    \"metrics\" : {\n      \"full_valid_mean_token_accuracy\" : 3.616076749251911,\n      \"valid_loss\" : 2.3021358869347655,\n      \"full_valid_loss\" : 9.301444243932576,\n      \"train_mean_token_accuracy\" : 5.637376656633329,\n      \"valid_mean_token_accuracy\" : 7.061401241503109,\n      \"train_loss\" : 5.962133916683182,\n      \"step\" : 1.4658129805029452\n    },\n    \"fine_tuned_model_checkpoint\" : \"fine_tuned_model_checkpoint\",\n    \"object\" : \"fine_tuning.job.checkpoint\"\n  }, {\n    \"step_number\" : 6,\n    \"created_at\" : 0,\n    \"fine_tuning_job_id\" : \"fine_tuning_job_id\",\n    \"id\" : \"id\",\n    \"metrics\" : {\n      \"full_valid_mean_token_accuracy\" : 3.616076749251911,\n      \"valid_loss\" : 2.3021358869347655,\n      \"full_valid_loss\" : 9.301444243932576,\n      \"train_mean_token_accuracy\" : 5.637376656633329,\n      \"valid_mean_token_accuracy\" : 7.061401241503109,\n      \"train_loss\" : 5.962133916683182,\n      \"step\" : 1.4658129805029452\n    },\n    \"fine_tuned_model_checkpoint\" : \"fine_tuned_model_checkpoint\",\n    \"object\" : \"fine_tuning.job.checkpoint\"\n  } ],\n  \"last_id\" : \"last_id\",\n  \"has_more\" : true,\n  \"object\" : \"list\"\n}", ListFineTuningJobCheckpointsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListFineTuningJobCheckpointsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListFineTuningJobCheckpointsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListPaginatedFineTuningJobsResponse> listPaginatedFineTuningJobs(@Parameter(in = ParameterIn.QUERY, description = "Identifier for the last job from the previous pagination request." ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "Number of fine-tuning jobs to retrieve." ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListPaginatedFineTuningJobsResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"training_file\" : \"training_file\",\n    \"result_files\" : [ \"file-abc123\", \"file-abc123\" ],\n    \"finished_at\" : 6,\n    \"seed\" : 5,\n    \"fine_tuned_model\" : \"fine_tuned_model\",\n    \"validation_file\" : \"validation_file\",\n    \"created_at\" : 0,\n    \"error\" : {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"message\" : \"message\"\n    },\n    \"estimated_finish\" : 5,\n    \"organization_id\" : \"organization_id\",\n    \"hyperparameters\" : {\n      \"n_epochs\" : \"\"\n    },\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"trained_tokens\" : 1,\n    \"integrations\" : [ \"\", \"\", \"\", \"\", \"\" ],\n    \"object\" : \"fine_tuning.job\",\n    \"status\" : \"validating_files\"\n  }, {\n    \"training_file\" : \"training_file\",\n    \"result_files\" : [ \"file-abc123\", \"file-abc123\" ],\n    \"finished_at\" : 6,\n    \"seed\" : 5,\n    \"fine_tuned_model\" : \"fine_tuned_model\",\n    \"validation_file\" : \"validation_file\",\n    \"created_at\" : 0,\n    \"error\" : {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"message\" : \"message\"\n    },\n    \"estimated_finish\" : 5,\n    \"organization_id\" : \"organization_id\",\n    \"hyperparameters\" : {\n      \"n_epochs\" : \"\"\n    },\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"trained_tokens\" : 1,\n    \"integrations\" : [ \"\", \"\", \"\", \"\", \"\" ],\n    \"object\" : \"fine_tuning.job\",\n    \"status\" : \"validating_files\"\n  } ],\n  \"has_more\" : true,\n  \"object\" : \"list\"\n}", ListPaginatedFineTuningJobsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListPaginatedFineTuningJobsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListPaginatedFineTuningJobsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<FineTuningJob> retrieveFineTuningJob(@Parameter(in = ParameterIn.PATH, description = "The ID of the fine-tuning job. ", required=true, schema=@Schema()) @PathVariable("fine_tuning_job_id") String fineTuningJobId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<FineTuningJob>(objectMapper.readValue("{\n  \"training_file\" : \"training_file\",\n  \"result_files\" : [ \"file-abc123\", \"file-abc123\" ],\n  \"finished_at\" : 6,\n  \"seed\" : 5,\n  \"fine_tuned_model\" : \"fine_tuned_model\",\n  \"validation_file\" : \"validation_file\",\n  \"created_at\" : 0,\n  \"error\" : {\n    \"code\" : \"code\",\n    \"param\" : \"param\",\n    \"message\" : \"message\"\n  },\n  \"estimated_finish\" : 5,\n  \"organization_id\" : \"organization_id\",\n  \"hyperparameters\" : {\n    \"n_epochs\" : \"\"\n  },\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"trained_tokens\" : 1,\n  \"integrations\" : [ \"\", \"\", \"\", \"\", \"\" ],\n  \"object\" : \"fine_tuning.job\",\n  \"status\" : \"validating_files\"\n}", FineTuningJob.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<FineTuningJob>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<FineTuningJob>(HttpStatus.NOT_IMPLEMENTED);
    }

}
