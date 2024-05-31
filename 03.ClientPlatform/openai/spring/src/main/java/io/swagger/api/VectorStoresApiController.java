package io.swagger.api;

import io.swagger.model.CreateVectorStoreFileBatchRequest;
import io.swagger.model.CreateVectorStoreFileRequest;
import io.swagger.model.CreateVectorStoreRequest;
import io.swagger.model.DeleteVectorStoreFileResponse;
import io.swagger.model.DeleteVectorStoreResponse;
import io.swagger.model.ListVectorStoreFilesResponse;
import io.swagger.model.ListVectorStoresResponse;
import io.swagger.model.UpdateVectorStoreRequest;
import io.swagger.model.VectorStoreFileBatchObject;
import io.swagger.model.VectorStoreFileObject;
import io.swagger.model.VectorStoreObject;
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
public class VectorStoresApiController implements VectorStoresApi {

    private static final Logger log = LoggerFactory.getLogger(VectorStoresApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VectorStoresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<VectorStoreFileBatchObject> cancelVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file batch belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.PATH, description = "The ID of the file batch to cancel.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreFileBatchObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 6,\n    \"total\" : 2,\n    \"cancelled\" : 5,\n    \"completed\" : 1,\n    \"failed\" : 5\n  },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store.files_batch\",\n  \"vector_store_id\" : \"vector_store_id\",\n  \"status\" : \"in_progress\"\n}", VectorStoreFileBatchObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreObject> createVectorStore(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 1,\n    \"total\" : 7,\n    \"cancelled\" : 2,\n    \"completed\" : 5,\n    \"failed\" : 5\n  },\n  \"metadata\" : { },\n  \"expires_at\" : 3,\n  \"expires_after\" : {\n    \"anchor\" : \"last_active_at\",\n    \"days\" : 339\n  },\n  \"last_active_at\" : 2,\n  \"usage_bytes\" : 6,\n  \"name\" : \"name\",\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store\",\n  \"status\" : \"expired\"\n}", VectorStoreObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreFileObject> createVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store for which to create a File. ", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreFileRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreFileObject>(objectMapper.readValue("{\n  \"usage_bytes\" : 0,\n  \"created_at\" : 6,\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"internal_error\",\n    \"message\" : \"message\"\n  },\n  \"object\" : \"vector_store.file\",\n  \"vector_store_id\" : \"vector_store_id\",\n  \"status\" : \"in_progress\"\n}", VectorStoreFileObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreFileObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreFileObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreFileBatchObject> createVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store for which to create a File Batch. ", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreFileBatchRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreFileBatchObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 6,\n    \"total\" : 2,\n    \"cancelled\" : 5,\n    \"completed\" : 1,\n    \"failed\" : 5\n  },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store.files_batch\",\n  \"vector_store_id\" : \"vector_store_id\",\n  \"status\" : \"in_progress\"\n}", VectorStoreFileBatchObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteVectorStoreResponse> deleteVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to delete.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteVectorStoreResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store.deleted\"\n}", DeleteVectorStoreResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteVectorStoreResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteVectorStoreResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteVectorStoreFileResponse> deleteVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.PATH, description = "The ID of the file to delete.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteVectorStoreFileResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store.file.deleted\"\n}", DeleteVectorStoreFileResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteVectorStoreFileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteVectorStoreFileResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreObject> getVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to retrieve.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 1,\n    \"total\" : 7,\n    \"cancelled\" : 2,\n    \"completed\" : 5,\n    \"failed\" : 5\n  },\n  \"metadata\" : { },\n  \"expires_at\" : 3,\n  \"expires_after\" : {\n    \"anchor\" : \"last_active_at\",\n    \"days\" : 339\n  },\n  \"last_active_at\" : 2,\n  \"usage_bytes\" : 6,\n  \"name\" : \"name\",\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store\",\n  \"status\" : \"expired\"\n}", VectorStoreObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreFileObject> getVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.PATH, description = "The ID of the file being retrieved.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreFileObject>(objectMapper.readValue("{\n  \"usage_bytes\" : 0,\n  \"created_at\" : 6,\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"internal_error\",\n    \"message\" : \"message\"\n  },\n  \"object\" : \"vector_store.file\",\n  \"vector_store_id\" : \"vector_store_id\",\n  \"status\" : \"in_progress\"\n}", VectorStoreFileObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreFileObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreFileObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreFileBatchObject> getVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file batch belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.PATH, description = "The ID of the file batch being retrieved.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreFileBatchObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 6,\n    \"total\" : 2,\n    \"cancelled\" : 5,\n    \"completed\" : 1,\n    \"failed\" : 5\n  },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store.files_batch\",\n  \"vector_store_id\" : \"vector_store_id\",\n  \"status\" : \"in_progress\"\n}", VectorStoreFileBatchObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreFileBatchObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListVectorStoreFilesResponse> listFilesInVectorStoreBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the files belong to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.PATH, description = "The ID of the file batch that the files belong to.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before,@Parameter(in = ParameterIn.QUERY, description = "Filter by file status. One of `in_progress`, `completed`, `failed`, `cancelled`." ,schema=@Schema(allowableValues={ "in_progress", "completed", "failed", "cancelled" }
)) @Valid @RequestParam(value = "filter", required = false) String filter) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListVectorStoreFilesResponse>(objectMapper.readValue("{\n  \"first_id\" : \"file-abc123\",\n  \"data\" : [ {\n    \"usage_bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"internal_error\",\n      \"message\" : \"message\"\n    },\n    \"object\" : \"vector_store.file\",\n    \"vector_store_id\" : \"vector_store_id\",\n    \"status\" : \"in_progress\"\n  }, {\n    \"usage_bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"internal_error\",\n      \"message\" : \"message\"\n    },\n    \"object\" : \"vector_store.file\",\n    \"vector_store_id\" : \"vector_store_id\",\n    \"status\" : \"in_progress\"\n  } ],\n  \"last_id\" : \"file-abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListVectorStoreFilesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListVectorStoreFilesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListVectorStoreFilesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListVectorStoreFilesResponse> listVectorStoreFiles(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the files belong to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before,@Parameter(in = ParameterIn.QUERY, description = "Filter by file status. One of `in_progress`, `completed`, `failed`, `cancelled`." ,schema=@Schema(allowableValues={ "in_progress", "completed", "failed", "cancelled" }
)) @Valid @RequestParam(value = "filter", required = false) String filter) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListVectorStoreFilesResponse>(objectMapper.readValue("{\n  \"first_id\" : \"file-abc123\",\n  \"data\" : [ {\n    \"usage_bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"internal_error\",\n      \"message\" : \"message\"\n    },\n    \"object\" : \"vector_store.file\",\n    \"vector_store_id\" : \"vector_store_id\",\n    \"status\" : \"in_progress\"\n  }, {\n    \"usage_bytes\" : 0,\n    \"created_at\" : 6,\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"internal_error\",\n      \"message\" : \"message\"\n    },\n    \"object\" : \"vector_store.file\",\n    \"vector_store_id\" : \"vector_store_id\",\n    \"status\" : \"in_progress\"\n  } ],\n  \"last_id\" : \"file-abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListVectorStoreFilesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListVectorStoreFilesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListVectorStoreFilesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListVectorStoresResponse> listVectorStores(@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListVectorStoresResponse>(objectMapper.readValue("{\n  \"first_id\" : \"vs_abc123\",\n  \"data\" : [ {\n    \"file_counts\" : {\n      \"in_progress\" : 1,\n      \"total\" : 7,\n      \"cancelled\" : 2,\n      \"completed\" : 5,\n      \"failed\" : 5\n    },\n    \"metadata\" : { },\n    \"expires_at\" : 3,\n    \"expires_after\" : {\n      \"anchor\" : \"last_active_at\",\n      \"days\" : 339\n    },\n    \"last_active_at\" : 2,\n    \"usage_bytes\" : 6,\n    \"name\" : \"name\",\n    \"created_at\" : 0,\n    \"id\" : \"id\",\n    \"object\" : \"vector_store\",\n    \"status\" : \"expired\"\n  }, {\n    \"file_counts\" : {\n      \"in_progress\" : 1,\n      \"total\" : 7,\n      \"cancelled\" : 2,\n      \"completed\" : 5,\n      \"failed\" : 5\n    },\n    \"metadata\" : { },\n    \"expires_at\" : 3,\n    \"expires_after\" : {\n      \"anchor\" : \"last_active_at\",\n      \"days\" : 339\n    },\n    \"last_active_at\" : 2,\n    \"usage_bytes\" : 6,\n    \"name\" : \"name\",\n    \"created_at\" : 0,\n    \"id\" : \"id\",\n    \"object\" : \"vector_store\",\n    \"status\" : \"expired\"\n  } ],\n  \"last_id\" : \"vs_abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListVectorStoresResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListVectorStoresResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListVectorStoresResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<VectorStoreObject> modifyVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to modify.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdateVectorStoreRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<VectorStoreObject>(objectMapper.readValue("{\n  \"file_counts\" : {\n    \"in_progress\" : 1,\n    \"total\" : 7,\n    \"cancelled\" : 2,\n    \"completed\" : 5,\n    \"failed\" : 5\n  },\n  \"metadata\" : { },\n  \"expires_at\" : 3,\n  \"expires_after\" : {\n    \"anchor\" : \"last_active_at\",\n    \"days\" : 339\n  },\n  \"last_active_at\" : 2,\n  \"usage_bytes\" : 6,\n  \"name\" : \"name\",\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"vector_store\",\n  \"status\" : \"expired\"\n}", VectorStoreObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<VectorStoreObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<VectorStoreObject>(HttpStatus.NOT_IMPLEMENTED);
    }

}
