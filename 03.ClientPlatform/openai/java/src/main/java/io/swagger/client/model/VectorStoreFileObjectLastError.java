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
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * The last error associated with this vector store file. Will be &#x60;null&#x60; if there are no errors.
 */
@Schema(description = "The last error associated with this vector store file. Will be `null` if there are no errors.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class VectorStoreFileObjectLastError {
  /**
   * One of &#x60;server_error&#x60; or &#x60;rate_limit_exceeded&#x60;.
   */
  @JsonAdapter(CodeEnum.Adapter.class)
  public enum CodeEnum {
    INTERNAL_ERROR("internal_error"),
    FILE_NOT_FOUND("file_not_found"),
    PARSING_ERROR("parsing_error"),
    UNHANDLED_MIME_TYPE("unhandled_mime_type");

    private String value;

    CodeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static CodeEnum fromValue(String input) {
      for (CodeEnum b : CodeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<CodeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CodeEnum enumeration) throws IOException {
        jsonWriter.value(String.valueOf(enumeration.getValue()));
      }

      @Override
      public CodeEnum read(final JsonReader jsonReader) throws IOException {
        Object value = jsonReader.nextString();
        return CodeEnum.fromValue((String)(value));
      }
    }
  }  @SerializedName("code")
  private CodeEnum code = null;

  @SerializedName("message")
  private String message = null;

  public VectorStoreFileObjectLastError code(CodeEnum code) {
    this.code = code;
    return this;
  }

   /**
   * One of &#x60;server_error&#x60; or &#x60;rate_limit_exceeded&#x60;.
   * @return code
  **/
  @Schema(required = true, description = "One of `server_error` or `rate_limit_exceeded`.")
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
