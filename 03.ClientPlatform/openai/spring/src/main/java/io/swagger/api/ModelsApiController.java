package io.swagger.api;

import io.swagger.model.DeleteModelResponse;
import io.swagger.model.ListModelsResponse;
import io.swagger.model.Model;
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
public class ModelsApiController implements ModelsApi {

    private static final Logger log = LoggerFactory.getLogger(ModelsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ModelsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<DeleteModelResponse> deleteModel(@Parameter(in = ParameterIn.PATH, description = "The model to delete", required=true, schema=@Schema()) @PathVariable("model") String model) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteModelResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"object\"\n}", DeleteModelResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteModelResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteModelResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListModelsResponse> listModels() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListModelsResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"created\" : 0,\n    \"owned_by\" : \"owned_by\",\n    \"id\" : \"id\",\n    \"object\" : \"model\"\n  }, {\n    \"created\" : 0,\n    \"owned_by\" : \"owned_by\",\n    \"id\" : \"id\",\n    \"object\" : \"model\"\n  } ],\n  \"object\" : \"list\"\n}", ListModelsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListModelsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListModelsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Model> retrieveModel(@Parameter(in = ParameterIn.PATH, description = "The ID of the model to use for this request", required=true, schema=@Schema()) @PathVariable("model") String model) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Model>(objectMapper.readValue("{\n  \"created\" : 0,\n  \"owned_by\" : \"owned_by\",\n  \"id\" : \"id\",\n  \"object\" : \"model\"\n}", Model.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Model>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Model>(HttpStatus.NOT_IMPLEMENTED);
    }

}
