package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.BatchErrorsData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BatchErrors
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class BatchErrors   {
  @JsonProperty("object")
  private String object = null;

  @JsonProperty("data")
  @Valid
  private List<BatchErrorsData> data = null;

  public BatchErrors object(String object) {
    this.object = object;
    return this;
  }

  /**
   * The object type, which is always `list`.
   * @return object
   **/
  @Schema(description = "The object type, which is always `list`.")
  
    public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public BatchErrors data(List<BatchErrorsData> data) {
    this.data = data;
    return this;
  }

  public BatchErrors addDataItem(BatchErrorsData dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<BatchErrorsData>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
   **/
  @Schema(description = "")
      @Valid
    public List<BatchErrorsData> getData() {
    return data;
  }

  public void setData(List<BatchErrorsData> data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchErrors batchErrors = (BatchErrors) o;
    return Objects.equals(this.object, batchErrors.object) &&
        Objects.equals(this.data, batchErrors.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(object, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchErrors {\n");
    
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
