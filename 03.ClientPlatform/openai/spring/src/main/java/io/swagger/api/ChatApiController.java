package io.swagger.api;

import io.swagger.model.CreateChatCompletionRequest;
import io.swagger.model.CreateChatCompletionResponse;
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
public class ChatApiController implements ChatApi {

    private static final Logger log = LoggerFactory.getLogger(ChatApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ChatApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateChatCompletionResponse> createChatCompletion(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateChatCompletionRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateChatCompletionResponse>(objectMapper.readValue("{\n  \"created\" : 2,\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"choices\" : [ {\n    \"finish_reason\" : \"stop\",\n    \"index\" : 0,\n    \"message\" : {\n      \"role\" : \"assistant\",\n      \"function_call\" : {\n        \"name\" : \"name\",\n        \"arguments\" : \"arguments\"\n      },\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ],\n      \"content\" : \"content\"\n    },\n    \"logprobs\" : {\n      \"content\" : [ {\n        \"top_logprobs\" : [ {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        }, {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        } ],\n        \"logprob\" : 6.027456183070403,\n        \"bytes\" : [ 1, 1 ],\n        \"token\" : \"token\"\n      }, {\n        \"top_logprobs\" : [ {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        }, {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        } ],\n        \"logprob\" : 6.027456183070403,\n        \"bytes\" : [ 1, 1 ],\n        \"token\" : \"token\"\n      } ]\n    }\n  }, {\n    \"finish_reason\" : \"stop\",\n    \"index\" : 0,\n    \"message\" : {\n      \"role\" : \"assistant\",\n      \"function_call\" : {\n        \"name\" : \"name\",\n        \"arguments\" : \"arguments\"\n      },\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ],\n      \"content\" : \"content\"\n    },\n    \"logprobs\" : {\n      \"content\" : [ {\n        \"top_logprobs\" : [ {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        }, {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        } ],\n        \"logprob\" : 6.027456183070403,\n        \"bytes\" : [ 1, 1 ],\n        \"token\" : \"token\"\n      }, {\n        \"top_logprobs\" : [ {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        }, {\n          \"logprob\" : 5.962133916683182,\n          \"bytes\" : [ 5, 5 ],\n          \"token\" : \"token\"\n        } ],\n        \"logprob\" : 6.027456183070403,\n        \"bytes\" : [ 1, 1 ],\n        \"token\" : \"token\"\n      } ]\n    }\n  } ],\n  \"system_fingerprint\" : \"system_fingerprint\",\n  \"object\" : \"chat.completion\"\n}", CreateChatCompletionResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateChatCompletionResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateChatCompletionResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
