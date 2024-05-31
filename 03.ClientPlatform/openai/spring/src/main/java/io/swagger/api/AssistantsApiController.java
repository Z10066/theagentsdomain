package io.swagger.api;

import io.swagger.model.AssistantObject;
import io.swagger.model.CreateAssistantRequest;
import io.swagger.model.DeleteAssistantResponse;
import io.swagger.model.ListAssistantsResponse;
import io.swagger.model.ModifyAssistantRequest;
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
public class AssistantsApiController implements AssistantsApi {

    private static final Logger log = LoggerFactory.getLogger(AssistantsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AssistantsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<AssistantObject> createAssistant(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateAssistantRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AssistantObject>(objectMapper.readValue("{\n  \"instructions\" : \"instructions\",\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"description\" : \"description\",\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 1,\n  \"response_format\" : \"\",\n  \"name\" : \"name\",\n  \"temperature\" : 1,\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"object\" : \"assistant\"\n}", AssistantObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AssistantObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AssistantObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteAssistantResponse> deleteAssistant(@Parameter(in = ParameterIn.PATH, description = "The ID of the assistant to delete.", required=true, schema=@Schema()) @PathVariable("assistant_id") String assistantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteAssistantResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"assistant.deleted\"\n}", DeleteAssistantResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteAssistantResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteAssistantResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<AssistantObject> getAssistant(@Parameter(in = ParameterIn.PATH, description = "The ID of the assistant to retrieve.", required=true, schema=@Schema()) @PathVariable("assistant_id") String assistantId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AssistantObject>(objectMapper.readValue("{\n  \"instructions\" : \"instructions\",\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"description\" : \"description\",\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 1,\n  \"response_format\" : \"\",\n  \"name\" : \"name\",\n  \"temperature\" : 1,\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"object\" : \"assistant\"\n}", AssistantObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AssistantObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AssistantObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListAssistantsResponse> listAssistants(@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListAssistantsResponse>(objectMapper.readValue("{\n  \"first_id\" : \"asst_abc123\",\n  \"data\" : [ {\n    \"instructions\" : \"instructions\",\n    \"tool_resources\" : {\n      \"code_interpreter\" : {\n        \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n      },\n      \"file_search\" : {\n        \"vector_store_ids\" : [ \"vector_store_ids\" ]\n      }\n    },\n    \"metadata\" : { },\n    \"created_at\" : 0,\n    \"description\" : \"description\",\n    \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n    \"top_p\" : 1,\n    \"response_format\" : \"\",\n    \"name\" : \"name\",\n    \"temperature\" : 1,\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"object\" : \"assistant\"\n  }, {\n    \"instructions\" : \"instructions\",\n    \"tool_resources\" : {\n      \"code_interpreter\" : {\n        \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n      },\n      \"file_search\" : {\n        \"vector_store_ids\" : [ \"vector_store_ids\" ]\n      }\n    },\n    \"metadata\" : { },\n    \"created_at\" : 0,\n    \"description\" : \"description\",\n    \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n    \"top_p\" : 1,\n    \"response_format\" : \"\",\n    \"name\" : \"name\",\n    \"temperature\" : 1,\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"object\" : \"assistant\"\n  } ],\n  \"last_id\" : \"asst_abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListAssistantsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListAssistantsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListAssistantsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<AssistantObject> modifyAssistant(@Parameter(in = ParameterIn.PATH, description = "The ID of the assistant to modify.", required=true, schema=@Schema()) @PathVariable("assistant_id") String assistantId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody ModifyAssistantRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AssistantObject>(objectMapper.readValue("{\n  \"instructions\" : \"instructions\",\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"description\" : \"description\",\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 1,\n  \"response_format\" : \"\",\n  \"name\" : \"name\",\n  \"temperature\" : 1,\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"object\" : \"assistant\"\n}", AssistantObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AssistantObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AssistantObject>(HttpStatus.NOT_IMPLEMENTED);
    }

}
