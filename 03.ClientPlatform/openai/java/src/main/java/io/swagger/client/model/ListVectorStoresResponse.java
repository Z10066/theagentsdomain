/*
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.client.model.VectorStoreObject;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * ListVectorStoresResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class ListVectorStoresResponse {
  @SerializedName("object")
  private String object = null;

  @SerializedName("data")
  private List<VectorStoreObject> data = new ArrayList<VectorStoreObject>();

  @SerializedName("first_id")
  private String firstId = null;

  @SerializedName("last_id")
  private String lastId = null;

  @SerializedName("has_more")
  private Boolean hasMore = null;

  public ListVectorStoresResponse object(String object) {
    this.object = object;
    return this;
  }

   /**
   * Get object
   * @return object
  **/
  @Schema(example = "list", required = true, description = "")
  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public ListVectorStoresResponse data(List<VectorStoreObject> data) {
    this.data = data;
    return this;
  }

  public ListVectorStoresResponse addDataItem(VectorStoreObject dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @Schema(required = true, description = "")
  public List<VectorStoreObject> getData() {
    return data;
  }

  public void setData(List<VectorStoreObject> data) {
    this.data = data;
  }

  public ListVectorStoresResponse firstId(String firstId) {
    this.firstId = firstId;
    return this;
  }

   /**
   * Get firstId
   * @return firstId
  **/
  @Schema(example = "vs_abc123", required = true, description = "")
  public String getFirstId() {
    return firstId;
  }

  public void setFirstId(String firstId) {
    this.firstId = firstId;
  }

  public ListVectorStoresResponse lastId(String lastId) {
    this.lastId = lastId;
    return this;
  }

   /**
   * Get lastId
   * @return lastId
  **/
  @Schema(example = "vs_abc456", required = true, description = "")
  public String getLastId() {
    return lastId;
  }

  public void setLastId(String lastId) {
    this.lastId = lastId;
  }

  public ListVectorStoresResponse hasMore(Boolean hasMore) {
    this.hasMore = hasMore;
    return this;
  }

   /**
   * Get hasMore
   * @return hasMore
  **/
  @Schema(example = "false", required = true, description = "")
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
    ListVectorStoresResponse listVectorStoresResponse = (ListVectorStoresResponse) o;
    return Objects.equals(this.object, listVectorStoresResponse.object) &&
        Objects.equals(this.data, listVectorStoresResponse.data) &&
        Objects.equals(this.firstId, listVectorStoresResponse.firstId) &&
        Objects.equals(this.lastId, listVectorStoresResponse.lastId) &&
        Objects.equals(this.hasMore, listVectorStoresResponse.hasMore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, data, firstId, lastId, hasMore);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListVectorStoresResponse {\n");
    
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
