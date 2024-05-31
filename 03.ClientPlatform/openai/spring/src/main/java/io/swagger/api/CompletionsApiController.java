package io.swagger.api;

import io.swagger.model.CreateCompletionRequest;
import io.swagger.model.CreateCompletionResponse;
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
public class CompletionsApiController implements CompletionsApi {

    private static final Logger log = LoggerFactory.getLogger(CompletionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CompletionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateCompletionResponse> createCompletion(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateCompletionRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateCompletionResponse>(objectMapper.readValue("{\n  \"created\" : 5,\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"choices\" : [ {\n    \"finish_reason\" : \"stop\",\n    \"index\" : 0,\n    \"text\" : \"text\",\n    \"logprobs\" : {\n      \"top_logprobs\" : [ {\n        \"key\" : 5.962133916683182\n      }, {\n        \"key\" : 5.962133916683182\n      } ],\n      \"token_logprobs\" : [ 1.4658129805029452, 1.4658129805029452 ],\n      \"tokens\" : [ \"tokens\", \"tokens\" ],\n      \"text_offset\" : [ 6, 6 ]\n    }\n  }, {\n    \"finish_reason\" : \"stop\",\n    \"index\" : 0,\n    \"text\" : \"text\",\n    \"logprobs\" : {\n      \"top_logprobs\" : [ {\n        \"key\" : 5.962133916683182\n      }, {\n        \"key\" : 5.962133916683182\n      } ],\n      \"token_logprobs\" : [ 1.4658129805029452, 1.4658129805029452 ],\n      \"tokens\" : [ \"tokens\", \"tokens\" ],\n      \"text_offset\" : [ 6, 6 ]\n    }\n  } ],\n  \"system_fingerprint\" : \"system_fingerprint\",\n  \"object\" : \"text_completion\"\n}", CreateCompletionResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateCompletionResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateCompletionResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
