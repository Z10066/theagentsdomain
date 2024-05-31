package io.swagger.api;

import io.swagger.model.DeleteFileResponse;
import io.swagger.model.ListFilesResponse;
import io.swagger.model.OpenAIFile;
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
public class FilesApiController implements FilesApi {

    private static final Logger log = LoggerFactory.getLogger(FilesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FilesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<OpenAIFile> createFile(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile file,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema(allowableValues={ "assistants", "batch", "fine-tune", "vision" }
)) @RequestParam(value="purpose", required=true)  String purpose) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<OpenAIFile>(objectMapper.readValue("{\n  \"filename\" : \"filename\",\n  \"purpose\" : \"assistants\",\n  \"bytes\" : 0,\n  \"created_at\" : 6,\n  \"id\" : \"id\",\n  \"status_details\" : \"status_details\",\n  \"object\" : \"file\",\n  \"status\" : \"uploaded\"\n}", OpenAIFile.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<OpenAIFile>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<OpenAIFile>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteFileResponse> deleteFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the file to use for this request.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteFileResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"file\"\n}", DeleteFileResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteFileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteFileResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> downloadFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the file to use for this request.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListFilesResponse> listFiles(@Parameter(in = ParameterIn.QUERY, description = "Only return files with the given purpose." ,schema=@Schema()) @Valid @RequestParam(value = "purpose", required = false) String purpose) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListFilesResponse>(objectMapper.readValue("{\n  \"data\" : [ {\n    \"filename\" : \"filename\",\n    \"purpose\" : \"assistants\",\n    \"bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"status_details\" : \"status_details\",\n    \"object\" : \"file\",\n    \"status\" : \"uploaded\"\n  }, {\n    \"filename\" : \"filename\",\n    \"purpose\" : \"assistants\",\n    \"bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"status_details\" : \"status_details\",\n    \"object\" : \"file\",\n    \"status\" : \"uploaded\"\n  } ],\n  \"object\" : \"list\"\n}", ListFilesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListFilesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListFilesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<OpenAIFile> retrieveFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the file to use for this request.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<OpenAIFile>(objectMapper.readValue("{\n  \"filename\" : \"filename\",\n  \"purpose\" : \"assistants\",\n  \"bytes\" : 0,\n  \"created_at\" : 6,\n  \"id\" : \"id\",\n  \"status_details\" : \"status_details\",\n  \"object\" : \"file\",\n  \"status\" : \"uploaded\"\n}", OpenAIFile.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<OpenAIFile>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<OpenAIFile>(HttpStatus.NOT_IMPLEMENTED);
    }

}
