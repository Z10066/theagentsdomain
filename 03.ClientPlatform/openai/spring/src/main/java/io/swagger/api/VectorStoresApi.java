/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")
@Validated
public interface VectorStoresApi {

    @Operation(summary = "Cancel a vector store file batch. This attempts to cancel the processing of files in this batch as soon as possible.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreFileBatchObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/file_batches/{batch_id}/cancel",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<VectorStoreFileBatchObject> cancelVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file batch belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.PATH, description = "The ID of the file batch to cancel.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId);


    @Operation(summary = "Create a vector store.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreObject.class))) })
    @RequestMapping(value = "/vector_stores",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<VectorStoreObject> createVectorStore(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreRequest body);


    @Operation(summary = "Create a vector store file by attaching a [File](/docs/api-reference/files) to a [vector store](/docs/api-reference/vector-stores/object).", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreFileObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/files",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<VectorStoreFileObject> createVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store for which to create a File. ", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreFileRequest body);


    @Operation(summary = "Create a vector store file batch.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreFileBatchObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/file_batches",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<VectorStoreFileBatchObject> createVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store for which to create a File Batch. ", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateVectorStoreFileBatchRequest body);


    @Operation(summary = "Delete a vector store.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteVectorStoreResponse.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<DeleteVectorStoreResponse> deleteVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to delete.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId);


    @Operation(summary = "Delete a vector store file. This will remove the file from the vector store but the file itself will not be deleted. To delete the file, use the [delete file](/docs/api-reference/files/delete) endpoint.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteVectorStoreFileResponse.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/files/{file_id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<DeleteVectorStoreFileResponse> deleteVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.PATH, description = "The ID of the file to delete.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId);


    @Operation(summary = "Retrieves a vector store.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<VectorStoreObject> getVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to retrieve.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId);


    @Operation(summary = "Retrieves a vector store file.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreFileObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/files/{file_id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<VectorStoreFileObject> getVectorStoreFile(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.PATH, description = "The ID of the file being retrieved.", required=true, schema=@Schema()) @PathVariable("file_id") String fileId);


    @Operation(summary = "Retrieves a vector store file batch.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreFileBatchObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/file_batches/{batch_id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<VectorStoreFileBatchObject> getVectorStoreFileBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the file batch belongs to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.PATH, description = "The ID of the file batch being retrieved.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId);


    @Operation(summary = "Returns a list of vector store files in a batch.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListVectorStoreFilesResponse.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/file_batches/{batch_id}/files",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListVectorStoreFilesResponse> listFilesInVectorStoreBatch(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the files belong to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.PATH, description = "The ID of the file batch that the files belong to.", required=true, schema=@Schema()) @PathVariable("batch_id") String batchId, @Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before, @Parameter(in = ParameterIn.QUERY, description = "Filter by file status. One of `in_progress`, `completed`, `failed`, `cancelled`." ,schema=@Schema(allowableValues={ "in_progress", "completed", "failed", "cancelled" }
)) @Valid @RequestParam(value = "filter", required = false) String filter);


    @Operation(summary = "Returns a list of vector store files.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListVectorStoreFilesResponse.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}/files",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListVectorStoreFilesResponse> listVectorStoreFiles(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store that the files belong to.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before, @Parameter(in = ParameterIn.QUERY, description = "Filter by file status. One of `in_progress`, `completed`, `failed`, `cancelled`." ,schema=@Schema(allowableValues={ "in_progress", "completed", "failed", "cancelled" }
)) @Valid @RequestParam(value = "filter", required = false) String filter);


    @Operation(summary = "Returns a list of vector stores.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListVectorStoresResponse.class))) })
    @RequestMapping(value = "/vector_stores",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ListVectorStoresResponse> listVectorStores(@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit, @Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after, @Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before);


    @Operation(summary = "Modifies a vector store.", description = "", security = {
        @SecurityRequirement(name = "ApiKeyAuth")    }, tags={ "Vector Stores" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VectorStoreObject.class))) })
    @RequestMapping(value = "/vector_stores/{vector_store_id}",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<VectorStoreObject> modifyVectorStore(@Parameter(in = ParameterIn.PATH, description = "The ID of the vector store to modify.", required=true, schema=@Schema()) @PathVariable("vector_store_id") String vectorStoreId, @Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody UpdateVectorStoreRequest body);

}
