package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.CreateMessageRequest;
import io.swagger.model.CreateThreadRequestToolResources;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateThreadRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateThreadRequest   {
  @JsonProperty("messages")
  @Valid
  private List<CreateMessageRequest> messages = null;

  @JsonProperty("tool_resources")
  private CreateThreadRequestToolResources toolResources = null;

  @JsonProperty("metadata")
  private Object metadata = null;

  public CreateThreadRequest messages(List<CreateMessageRequest> messages) {
    this.messages = messages;
    return this;
  }

  public CreateThreadRequest addMessagesItem(CreateMessageRequest messagesItem) {
    if (this.messages == null) {
      this.messages = new ArrayList<CreateMessageRequest>();
    }
    this.messages.add(messagesItem);
    return this;
  }

  /**
   * A list of [messages](/docs/api-reference/messages) to start the thread with.
   * @return messages
   **/
  @Schema(description = "A list of [messages](/docs/api-reference/messages) to start the thread with.")
      @Valid
    public List<CreateMessageRequest> getMessages() {
    return messages;
  }

  public void setMessages(List<CreateMessageRequest> messages) {
    this.messages = messages;
  }

  public CreateThreadRequest toolResources(CreateThreadRequestToolResources toolResources) {
    this.toolResources = toolResources;
    return this;
  }

  /**
   * Get toolResources
   * @return toolResources
   **/
  @Schema(description = "")
  
    @Valid
    public CreateThreadRequestToolResources getToolResources() {
    return toolResources;
  }

  public void setToolResources(CreateThreadRequestToolResources toolResources) {
    this.toolResources = toolResources;
  }

  public CreateThreadRequest metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
   **/
  @Schema(description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ")
  
    public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateThreadRequest createThreadRequest = (CreateThreadRequest) o;
    return Objects.equals(this.messages, createThreadRequest.messages) &&
        Objects.equals(this.toolResources, createThreadRequest.toolResources) &&
        Objects.equals(this.metadata, createThreadRequest.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messages, toolResources, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateThreadRequest {\n");
    
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
    sb.append("    toolResources: ").append(toIndentedString(toolResources)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
