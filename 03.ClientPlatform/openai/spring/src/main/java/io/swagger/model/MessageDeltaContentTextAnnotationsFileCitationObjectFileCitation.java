package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation   {
  @JsonProperty("file_id")
  private String fileId = null;

  @JsonProperty("quote")
  private String quote = null;

  public MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  /**
   * The ID of the specific File the citation is from.
   * @return fileId
   **/
  @Schema(description = "The ID of the specific File the citation is from.")
  
    public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation quote(String quote) {
    this.quote = quote;
    return this;
  }

  /**
   * The specific quote in the file.
   * @return quote
   **/
  @Schema(description = "The specific quote in the file.")
  
    public String getQuote() {
    return quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation messageDeltaContentTextAnnotationsFileCitationObjectFileCitation = (MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation) o;
    return Objects.equals(this.fileId, messageDeltaContentTextAnnotationsFileCitationObjectFileCitation.fileId) &&
        Objects.equals(this.quote, messageDeltaContentTextAnnotationsFileCitationObjectFileCitation.quote);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId, quote);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation {\n");
    
    sb.append("    fileId: ").append(toIndentedString(fileId)).append("\n");
    sb.append("    quote: ").append(toIndentedString(quote)).append("\n");
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
