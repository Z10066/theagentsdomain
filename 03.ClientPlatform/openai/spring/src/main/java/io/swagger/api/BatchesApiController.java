package io.swagger.api;

import io.swagger.model.Batch;
import io.swagger.model.BatchesBody;
import io.swagger.model.ListBatchesResponse;
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
public class BatchesApiController implements BatchesApi {

    private static final Logger log = LoggerFactory.getLogger(BatchesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public BatchesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Batch> cancelBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the batch to cancel.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Batch>(objectMapper.readValue("{\n  \"cancelled_at\" : 2,\n  \"metadata\" : { },\n  \"request_counts\" : {\n    \"total\" : 4,\n    \"completed\" : 7,\n    \"failed\" : 1\n  },\n  \"input_file_id\" : \"input_file_id\",\n  \"output_file_id\" : \"output_file_id\",\n  \"error_file_id\" : \"error_file_id\",\n  \"created_at\" : 6,\n  \"in_progress_at\" : 1,\n  \"expired_at\" : 9,\n  \"finalizing_at\" : 5,\n  \"completed_at\" : 2,\n  \"endpoint\" : \"endpoint\",\n  \"expires_at\" : 5,\n  \"cancelling_at\" : 3,\n  \"completion_window\" : \"completion_window\",\n  \"id\" : \"id\",\n  \"failed_at\" : 7,\n  \"errors\" : {\n    \"data\" : [ {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    }, {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    } ],\n    \"object\" : \"object\"\n  },\n  \"object\" : \"batch\",\n  \"status\" : \"validating\"\n}", Batch.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Batch>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Batch>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Batch> createBatch(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody BatchesBody body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Batch>(objectMapper.readValue("{\n  \"cancelled_at\" : 2,\n  \"metadata\" : { },\n  \"request_counts\" : {\n    \"total\" : 4,\n    \"completed\" : 7,\n    \"failed\" : 1\n  },\n  \"input_file_id\" : \"input_file_id\",\n  \"output_file_id\" : \"output_file_id\",\n  \"error_file_id\" : \"error_file_id\",\n  \"created_at\" : 6,\n  \"in_progress_at\" : 1,\n  \"expired_at\" : 9,\n  \"finalizing_at\" : 5,\n  \"completed_at\" : 2,\n  \"endpoint\" : \"endpoint\",\n  \"expires_at\" : 5,\n  \"cancelling_at\" : 3,\n  \"completion_window\" : \"completion_window\",\n  \"id\" : \"id\",\n  \"failed_at\" : 7,\n  \"errors\" : {\n    \"data\" : [ {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    }, {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    } ],\n    \"object\" : \"object\"\n  },\n  \"object\" : \"batch\",\n  \"status\" : \"validating\"\n}", Batch.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Batch>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Batch>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListBatchesResponse> listBatches(@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListBatchesResponse>(objectMapper.readValue("{\n  \"first_id\" : \"batch_abc123\",\n  \"data\" : [ {\n    \"cancelled_at\" : 2,\n    \"metadata\" : { },\n    \"request_counts\" : {\n      \"total\" : 4,\n      \"completed\" : 7,\n      \"failed\" : 1\n    },\n    \"input_file_id\" : \"input_file_id\",\n    \"output_file_id\" : \"output_file_id\",\n    \"error_file_id\" : \"error_file_id\",\n    \"created_at\" : 6,\n    \"in_progress_at\" : 1,\n    \"expired_at\" : 9,\n    \"finalizing_at\" : 5,\n    \"completed_at\" : 2,\n    \"endpoint\" : \"endpoint\",\n    \"expires_at\" : 5,\n    \"cancelling_at\" : 3,\n    \"completion_window\" : \"completion_window\",\n    \"id\" : \"id\",\n    \"failed_at\" : 7,\n    \"errors\" : {\n      \"data\" : [ {\n        \"code\" : \"code\",\n        \"param\" : \"param\",\n        \"line\" : 0,\n        \"message\" : \"message\"\n      }, {\n        \"code\" : \"code\",\n        \"param\" : \"param\",\n        \"line\" : 0,\n        \"message\" : \"message\"\n      } ],\n      \"object\" : \"object\"\n    },\n    \"object\" : \"batch\",\n    \"status\" : \"validating\"\n  }, {\n    \"cancelled_at\" : 2,\n    \"metadata\" : { },\n    \"request_counts\" : {\n      \"total\" : 4,\n      \"completed\" : 7,\n      \"failed\" : 1\n    },\n    \"input_file_id\" : \"input_file_id\",\n    \"output_file_id\" : \"output_file_id\",\n    \"error_file_id\" : \"error_file_id\",\n    \"created_at\" : 6,\n    \"in_progress_at\" : 1,\n    \"expired_at\" : 9,\n    \"finalizing_at\" : 5,\n    \"completed_at\" : 2,\n    \"endpoint\" : \"endpoint\",\n    \"expires_at\" : 5,\n    \"cancelling_at\" : 3,\n    \"completion_window\" : \"completion_window\",\n    \"id\" : \"id\",\n    \"failed_at\" : 7,\n    \"errors\" : {\n      \"data\" : [ {\n        \"code\" : \"code\",\n        \"param\" : \"param\",\n        \"line\" : 0,\n        \"message\" : \"message\"\n      }, {\n        \"code\" : \"code\",\n        \"param\" : \"param\",\n        \"line\" : 0,\n        \"message\" : \"message\"\n      } ],\n      \"object\" : \"object\"\n    },\n    \"object\" : \"batch\",\n    \"status\" : \"validating\"\n  } ],\n  \"last_id\" : \"batch_abc456\",\n  \"has_more\" : true,\n  \"object\" : \"list\"\n}", ListBatchesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListBatchesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListBatchesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Batch> retrieveBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the batch to retrieve.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Batch>(objectMapper.readValue("{\n  \"cancelled_at\" : 2,\n  \"metadata\" : { },\n  \"request_counts\" : {\n    \"total\" : 4,\n    \"completed\" : 7,\n    \"failed\" : 1\n  },\n  \"input_file_id\" : \"input_file_id\",\n  \"output_file_id\" : \"output_file_id\",\n  \"error_file_id\" : \"error_file_id\",\n  \"created_at\" : 6,\n  \"in_progress_at\" : 1,\n  \"expired_at\" : 9,\n  \"finalizing_at\" : 5,\n  \"completed_at\" : 2,\n  \"endpoint\" : \"endpoint\",\n  \"expires_at\" : 5,\n  \"cancelling_at\" : 3,\n  \"completion_window\" : \"completion_window\",\n  \"id\" : \"id\",\n  \"failed_at\" : 7,\n  \"errors\" : {\n    \"data\" : [ {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    }, {\n      \"code\" : \"code\",\n      \"param\" : \"param\",\n      \"line\" : 0,\n      \"message\" : \"message\"\n    } ],\n    \"object\" : \"object\"\n  },\n  \"object\" : \"batch\",\n  \"status\" : \"validating\"\n}", Batch.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Batch>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Batch>(HttpStatus.NOT_IMPLEMENTED);
    }

}
