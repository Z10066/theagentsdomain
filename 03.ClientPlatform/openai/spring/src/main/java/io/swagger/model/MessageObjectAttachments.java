package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MessageObjectAttachments
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class MessageObjectAttachments   {
  @JsonProperty("file_id")
  private String fileId = null;

  @JsonProperty("tools")
  @Valid
  private List<OneOfMessageObjectAttachmentsToolsItems> tools = null;

  public MessageObjectAttachments fileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  /**
   * The ID of the file to attach to the message.
   * @return fileId
   **/
  @Schema(description = "The ID of the file to attach to the message.")
  
    public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public MessageObjectAttachments tools(List<OneOfMessageObjectAttachmentsToolsItems> tools) {
    this.tools = tools;
    return this;
  }

  public MessageObjectAttachments addToolsItem(OneOfMessageObjectAttachmentsToolsItems toolsItem) {
    if (this.tools == null) {
      this.tools = new ArrayList<OneOfMessageObjectAttachmentsToolsItems>();
    }
    this.tools.add(toolsItem);
    return this;
  }

  /**
   * The tools to add this file to.
   * @return tools
   **/
  @Schema(description = "The tools to add this file to.")
  
    public List<OneOfMessageObjectAttachmentsToolsItems> getTools() {
    return tools;
  }

  public void setTools(List<OneOfMessageObjectAttachmentsToolsItems> tools) {
    this.tools = tools;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageObjectAttachments messageObjectAttachments = (MessageObjectAttachments) o;
    return Objects.equals(this.fileId, messageObjectAttachments.fileId) &&
        Objects.equals(this.tools, messageObjectAttachments.tools);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileId, tools);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageObjectAttachments {\n");
    
    sb.append("    fileId: ").append(toIndentedString(fileId)).append("\n");
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
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
