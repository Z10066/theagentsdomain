package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.VectorStoreFileObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ListVectorStoreFilesResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class ListVectorStoreFilesResponse   {
  @JsonProperty("object")
  private String object = null;

  @JsonProperty("data")
  @Valid
  private List<VectorStoreFileObject> data = new ArrayList<VectorStoreFileObject>();

  @JsonProperty("first_id")
  private String firstId = null;

  @JsonProperty("last_id")
  private String lastId = null;

  @JsonProperty("has_more")
  private Boolean hasMore = null;

  public ListVectorStoreFilesResponse object(String object) {
    this.object = object;
    return this;
  }

  /**
   * Get object
   * @return object
   **/
  @Schema(example = "list", required = true, description = "")
      @NotNull

    public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public ListVectorStoreFilesResponse data(List<VectorStoreFileObject> data) {
    this.data = data;
    return this;
  }

  public ListVectorStoreFilesResponse addDataItem(VectorStoreFileObject dataItem) {
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<VectorStoreFileObject> getData() {
    return data;
  }

  public void setData(List<VectorStoreFileObject> data) {
    this.data = data;
  }

  public ListVectorStoreFilesResponse firstId(String firstId) {
    this.firstId = firstId;
    return this;
  }

  /**
   * Get firstId
   * @return firstId
   **/
  @Schema(example = "file-abc123", required = true, description = "")
      @NotNull

    public String getFirstId() {
    return firstId;
  }

  public void setFirstId(String firstId) {
    this.firstId = firstId;
  }

  public ListVectorStoreFilesResponse lastId(String lastId) {
    this.lastId = lastId;
    return this;
  }

  /**
   * Get lastId
   * @return lastId
   **/
  @Schema(example = "file-abc456", required = true, description = "")
      @NotNull

    public String getLastId() {
    return lastId;
  }

  public void setLastId(String lastId) {
    this.lastId = lastId;
  }

  public ListVectorStoreFilesResponse hasMore(Boolean hasMore) {
    this.hasMore = hasMore;
    return this;
  }

  /**
   * Get hasMore
   * @return hasMore
   **/
  @Schema(example = "false", required = true, description = "")
      @NotNull

    public Boolean isHasMore() {
    return hasMore;
  }

  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListVectorStoreFilesResponse listVectorStoreFilesResponse = (ListVectorStoreFilesResponse) o;
    return Objects.equals(this.object, listVectorStoreFilesResponse.object) &&
        Objects.equals(this.data, listVectorStoreFilesResponse.data) &&
        Objects.equals(this.firstId, listVectorStoreFilesResponse.firstId) &&
        Objects.equals(this.lastId, listVectorStoreFilesResponse.lastId) &&
        Objects.equals(this.hasMore, listVectorStoreFilesResponse.hasMore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, data, firstId, lastId, hasMore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListVectorStoreFilesResponse {\n");
    
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    firstId: ").append(toIndentedString(firstId)).append("\n");
    sb.append("    lastId: ").append(toIndentedString(lastId)).append("\n");
    sb.append("    hasMore: ").append(toIndentedString(hasMore)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
