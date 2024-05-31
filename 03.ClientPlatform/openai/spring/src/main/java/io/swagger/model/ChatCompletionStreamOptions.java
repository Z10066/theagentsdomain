package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Options for streaming response. Only set this when you set &#x60;stream: true&#x60;. 
 */
@Schema(description = "Options for streaming response. Only set this when you set `stream: true`. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class ChatCompletionStreamOptions   {
  @JsonProperty("include_usage")
  private Boolean includeUsage = null;

  public ChatCompletionStreamOptions includeUsage(Boolean includeUsage) {
    this.includeUsage = includeUsage;
    return this;
  }

  /**
   * If set, an additional chunk will be streamed before the `data: [DONE]` message. The `usage` field on this chunk shows the token usage statistics for the entire request, and the `choices` field will always be an empty array. All other chunks will also include a `usage` field, but with a null value. 
   * @return includeUsage
   **/
  @Schema(description = "If set, an additional chunk will be streamed before the `data: [DONE]` message. The `usage` field on this chunk shows the token usage statistics for the entire request, and the `choices` field will always be an empty array. All other chunks will also include a `usage` field, but with a null value. ")
  
    public Boolean isIncludeUsage() {
    return includeUsage;
  }

  public void setIncludeUsage(Boolean includeUsage) {
    this.includeUsage = includeUsage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionStreamOptions chatCompletionStreamOptions = (ChatCompletionStreamOptions) o;
    return Objects.equals(this.includeUsage, chatCompletionStreamOptions.includeUsage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(includeUsage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionStreamOptions {\n");
    
    sb.append("    includeUsage: ").append(toIndentedString(includeUsage)).append("\n");
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
