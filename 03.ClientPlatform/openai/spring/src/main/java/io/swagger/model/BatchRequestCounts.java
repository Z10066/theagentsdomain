package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The request counts for different statuses within the batch.
 */
@Schema(description = "The request counts for different statuses within the batch.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class BatchRequestCounts   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("completed")
  private Integer completed = null;

  @JsonProperty("failed")
  private Integer failed = null;

  public BatchRequestCounts total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Total number of requests in the batch.
   * @return total
   **/
  @Schema(required = true, description = "Total number of requests in the batch.")
      @NotNull

    public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public BatchRequestCounts completed(Integer completed) {
    this.completed = completed;
    return this;
  }

  /**
   * Number of requests that have been completed successfully.
   * @return completed
   **/
  @Schema(required = true, description = "Number of requests that have been completed successfully.")
      @NotNull

    public Integer getCompleted() {
    return completed;
  }

  public void setCompleted(Integer completed) {
    this.completed = completed;
  }

  public BatchRequestCounts failed(Integer failed) {
    this.failed = failed;
    return this;
  }

  /**
   * Number of requests that have failed.
   * @return failed
   **/
  @Schema(required = true, description = "Number of requests that have failed.")
      @NotNull

    public Integer getFailed() {
    return failed;
  }

  public void setFailed(Integer failed) {
    this.failed = failed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchRequestCounts batchRequestCounts = (BatchRequestCounts) o;
    return Objects.equals(this.total, batchRequestCounts.total) &&
        Objects.equals(this.completed, batchRequestCounts.completed) &&
        Objects.equals(this.failed, batchRequestCounts.failed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, completed, failed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchRequestCounts {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
    sb.append("    failed: ").append(toIndentedString(failed)).append("\n");
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
