package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The last error associated with this vector store file. Will be &#x60;null&#x60; if there are no errors.
 */
@Schema(description = "The last error associated with this vector store file. Will be `null` if there are no errors.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class VectorStoreFileObjectLastError   {
  /**
   * One of `server_error` or `rate_limit_exceeded`.
   */
  public enum CodeEnum {
    INTERNAL_ERROR("internal_error"),
    
    FILE_NOT_FOUND("file_not_found"),
    
    PARSING_ERROR("parsing_error"),
    
    UNHANDLED_MIME_TYPE("unhandled_mime_type");

    private String value;

    CodeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CodeEnum fromValue(String text) {
      for (CodeEnum b : CodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("code")
  private CodeEnum code = null;

  @JsonProperty("message")
  private String message = null;

  public VectorStoreFileObjectLastError code(CodeEnum code) {
    this.code = code;
    return this;
  }

  /**
   * One of `server_error` or `rate_limit_exceeded`.
   * @return code
   **/
  @Schema(required = true, description = "One of `server_error` or `rate_limit_exceeded`.")
      @NotNull

    public CodeEnum getCode() {
    return code;
  }

  public void setCode(CodeEnum code) {
    this.code = code;
  }

  public VectorStoreFileObjectLastError message(String message) {
    this.message = message;
    return this;
  }

  /**
   * A human-readable description of the error.
   * @return message
   **/
  @Schema(required = true, description = "A human-readable description of the error.")
      @NotNull

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VectorStoreFileObjectLastError vectorStoreFileObjectLastError = (VectorStoreFileObjectLastError) o;
    return Objects.equals(this.code, vectorStoreFileObjectLastError.code) &&
        Objects.equals(this.message, vectorStoreFileObjectLastError.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VectorStoreFileObjectLastError {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
