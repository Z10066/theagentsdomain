package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BatchErrorsData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class BatchErrorsData   {
  @JsonProperty("code")
  private String code = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("param")
  private String param = null;

  @JsonProperty("line")
  private Integer line = null;

  public BatchErrorsData code(String code) {
    this.code = code;
    return this;
  }

  /**
   * An error code identifying the error type.
   * @return code
   **/
  @Schema(description = "An error code identifying the error type.")
  
    public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public BatchErrorsData message(String message) {
    this.message = message;
    return this;
  }

  /**
   * A human-readable message providing more details about the error.
   * @return message
   **/
  @Schema(description = "A human-readable message providing more details about the error.")
  
    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BatchErrorsData param(String param) {
    this.param = param;
    return this;
  }

  /**
   * The name of the parameter that caused the error, if applicable.
   * @return param
   **/
  @Schema(description = "The name of the parameter that caused the error, if applicable.")
  
    public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public BatchErrorsData line(Integer line) {
    this.line = line;
    return this;
  }

  /**
   * The line number of the input file where the error occurred, if applicable.
   * @return line
   **/
  @Schema(description = "The line number of the input file where the error occurred, if applicable.")
  
    public Integer getLine() {
    return line;
  }

  public void setLine(Integer line) {
    this.line = line;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchErrorsData batchErrorsData = (BatchErrorsData) o;
    return Objects.equals(this.code, batchErrorsData.code) &&
        Objects.equals(this.message, batchErrorsData.message) &&
        Objects.equals(this.param, batchErrorsData.param) &&
        Objects.equals(this.line, batchErrorsData.line);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, param, line);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchErrorsData {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    param: ").append(toIndentedString(param)).append("\n");
    sb.append("    line: ").append(toIndentedString(line)).append("\n");
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
