package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.FineTuningJobCheckpoint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ListFineTuningJobCheckpointsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class ListFineTuningJobCheckpointsResponse   {
  @JsonProperty("data")
  @Valid
  private List<FineTuningJobCheckpoint> data = new ArrayList<FineTuningJobCheckpoint>();

  /**
   * Gets or Sets object
   */
  public enum ObjectEnum {
    LIST("list");

    private String value;

    ObjectEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ObjectEnum fromValue(String text) {
      for (ObjectEnum b : ObjectEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("object")
  private ObjectEnum object = null;

  @JsonProperty("first_id")
  private String firstId = null;

  @JsonProperty("last_id")
  private String lastId = null;

  @JsonProperty("has_more")
  private Boolean hasMore = null;

  public ListFineTuningJobCheckpointsResponse data(List<FineTuningJobCheckpoint> data) {
    this.data = data;
    return this;
  }

  public ListFineTuningJobCheckpointsResponse addDataItem(FineTuningJobCheckpoint dataItem) {
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
    public List<FineTuningJobCheckpoint> getData() {
    return data;
  }

  public void setData(List<FineTuningJobCheckpoint> data) {
    this.data = data;
  }

  public ListFineTuningJobCheckpointsResponse object(ObjectEnum object) {
    this.object = object;
    return this;
  }

  /**
   * Get object
   * @return object
   **/
  @Schema(required = true, description = "")
      @NotNull

    public ObjectEnum getObject() {
    return object;
  }

  public void setObject(ObjectEnum object) {
    this.object = object;
  }

  public ListFineTuningJobCheckpointsResponse firstId(String firstId) {
    this.firstId = firstId;
    return this;
  }

  /**
   * Get firstId
   * @return firstId
   **/
  @Schema(description = "")
  
    public String getFirstId() {
    return firstId;
  }

  public void setFirstId(String firstId) {
    this.firstId = firstId;
  }

  public ListFineTuningJobCheckpointsResponse lastId(String lastId) {
    this.lastId = lastId;
    return this;
  }

  /**
   * Get lastId
   * @return lastId
   **/
  @Schema(description = "")
  
    public String getLastId() {
    return lastId;
  }

  public void setLastId(String lastId) {
    this.lastId = lastId;
  }

  public ListFineTuningJobCheckpointsResponse hasMore(Boolean hasMore) {
    this.hasMore = hasMore;
    return this;
  }

  /**
   * Get hasMore
   * @return hasMore
   **/
  @Schema(required = true, description = "")
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
    ListFineTuningJobCheckpointsResponse listFineTuningJobCheckpointsResponse = (ListFineTuningJobCheckpointsResponse) o;
    return Objects.equals(this.data, listFineTuningJobCheckpointsResponse.data) &&
        Objects.equals(this.object, listFineTuningJobCheckpointsResponse.object) &&
        Objects.equals(this.firstId, listFineTuningJobCheckpointsResponse.firstId) &&
        Objects.equals(this.lastId, listFineTuningJobCheckpointsResponse.lastId) &&
        Objects.equals(this.hasMore, listFineTuningJobCheckpointsResponse.hasMore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, object, firstId, lastId, hasMore);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListFineTuningJobCheckpointsResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
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
