package io.swagger.api;

import io.swagger.model.CreateImageRequest;
import io.swagger.model.ImagesResponse;
import org.springframework.core.io.Resource;
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
public class ImagesApiController implements ImagesApi {

    private static final Logger log = LoggerFactory.getLogger(ImagesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ImagesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ImagesResponse> createImage(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateImageRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ImagesResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  }, {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  } ],\n  \"created\" : 0\n}", ImagesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ImagesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ImagesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ImagesResponse> createImageEdit(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile image,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="prompt", required=true)  String prompt,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile mask,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="model", required=true)  Object model,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "10", "1" }, minimum="1", maximum="10"
)) @RequestParam(value="n", required=true)  Integer n,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "256x256", "512x512", "1024x1024" }
)) @RequestParam(value="size", required=true)  String size,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "url", "b64_json" }
)) @RequestParam(value="response_format", required=true)  String responseFormat,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="user", required=true)  String user) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ImagesResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  }, {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  } ],\n  \"created\" : 0\n}", ImagesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ImagesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ImagesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ImagesResponse> createImageVariation(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile image,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="model", required=true)  Object model,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "10", "1" }, minimum="1", maximum="10"
)) @RequestParam(value="n", required=true)  Integer n,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "url", "b64_json" }
)) @RequestParam(value="response_format", required=true)  String responseFormat,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "256x256", "512x512", "1024x1024" }
)) @RequestParam(value="size", required=true)  String size,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="user", required=true)  String user) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ImagesResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  }, {\n    \"revised_prompt\" : \"revised_prompt\",\n    \"b64_json\" : \"b64_json\",\n    \"url\" : \"url\"\n  } ],\n  \"created\" : 0\n}", ImagesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ImagesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ImagesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
