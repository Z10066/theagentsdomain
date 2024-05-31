package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.MessageObjectAttachments;
import io.swagger.model.MessageObjectIncompleteDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Represents a message within a [thread](/docs/api-reference/threads).
 */
@Schema(description = "Represents a message within a [thread](/docs/api-reference/threads).")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class MessageObject   {
  @JsonProperty("id")
  private String id = null;

  /**
   * The object type, which is always `thread.message`.
   */
  public enum ObjectEnum {
    THREAD_MESSAGE("thread.message");

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

  @JsonProperty("created_at")
  private Integer createdAt = null;

  @JsonProperty("thread_id")
  private String threadId = null;

  /**
   * The status of the message, which can be either `in_progress`, `incomplete`, or `completed`.
   */
  public enum StatusEnum {
    IN_PROGRESS("in_progress"),
    
    INCOMPLETE("incomplete"),
    
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("incomplete_details")
  private MessageObjectIncompleteDetails incompleteDetails = null;

  @JsonProperty("completed_at")
  private Integer completedAt = null;

  @JsonProperty("incomplete_at")
  private Integer incompleteAt = null;

  /**
   * The entity that produced the message. One of `user` or `assistant`.
   */
  public enum RoleEnum {
    USER("user"),
    
    ASSISTANT("assistant");

    private String value;

    RoleEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RoleEnum fromValue(String text) {
      for (RoleEnum b : RoleEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("role")
  private RoleEnum role = null;

  @JsonProperty("content")
  @Valid
  private List<OneOfMessageObjectContentItems> content = new ArrayList<OneOfMessageObjectContentItems>();

  @JsonProperty("assistant_id")
  private String assistantId = null;

  @JsonProperty("run_id")
  private String runId = null;

  @JsonProperty("attachments")
  @Valid
  private List<MessageObjectAttachments> attachments = new ArrayList<MessageObjectAttachments>();

  @JsonProperty("metadata")
  private Object metadata = null;

  public MessageObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier, which can be referenced in API endpoints.
   * @return id
   **/
  @Schema(required = true, description = "The identifier, which can be referenced in API endpoints.")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MessageObject object(ObjectEnum object) {
    this.object = object;
    return this;
  }

  /**
   * The object type, which is always `thread.message`.
   * @return object
   **/
  @Schema(required = true, description = "The object type, which is always `thread.message`.")
      @NotNull

    public ObjectEnum getObject() {
    return object;
  }

  public void setObject(ObjectEnum object) {
    this.object = object;
  }

  public MessageObject createdAt(Integer createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the message was created.
   * @return createdAt
   **/
  @Schema(required = true, description = "The Unix timestamp (in seconds) for when the message was created.")
      @NotNull

    public Integer getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Integer createdAt) {
    this.createdAt = createdAt;
  }

  public MessageObject threadId(String threadId) {
    this.threadId = threadId;
    return this;
  }

  /**
   * The [thread](/docs/api-reference/threads) ID that this message belongs to.
   * @return threadId
   **/
  @Schema(required = true, description = "The [thread](/docs/api-reference/threads) ID that this message belongs to.")
      @NotNull

    public String getThreadId() {
    return threadId;
  }

  public void setThreadId(String threadId) {
    this.threadId = threadId;
  }

  public MessageObject status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * The status of the message, which can be either `in_progress`, `incomplete`, or `completed`.
   * @return status
   **/
  @Schema(required = true, description = "The status of the message, which can be either `in_progress`, `incomplete`, or `completed`.")
      @NotNull

    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public MessageObject incompleteDetails(MessageObjectIncompleteDetails incompleteDetails) {
    this.incompleteDetails = incompleteDetails;
    return this;
  }

  /**
   * Get incompleteDetails
   * @return incompleteDetails
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public MessageObjectIncompleteDetails getIncompleteDetails() {
    return incompleteDetails;
  }

  public void setIncompleteDetails(MessageObjectIncompleteDetails incompleteDetails) {
    this.incompleteDetails = incompleteDetails;
  }

  public MessageObject completedAt(Integer completedAt) {
    this.completedAt = completedAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the message was completed.
   * @return completedAt
   **/
  @Schema(required = true, description = "The Unix timestamp (in seconds) for when the message was completed.")
      @NotNull

    public Integer getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(Integer completedAt) {
    this.completedAt = completedAt;
  }

  public MessageObject incompleteAt(Integer incompleteAt) {
    this.incompleteAt = incompleteAt;
    return this;
  }

  /**
   * The Unix timestamp (in seconds) for when the message was marked as incomplete.
   * @return incompleteAt
   **/
  @Schema(required = true, description = "The Unix timestamp (in seconds) for when the message was marked as incomplete.")
      @NotNull

    public Integer getIncompleteAt() {
    return incompleteAt;
  }

  public void setIncompleteAt(Integer incompleteAt) {
    this.incompleteAt = incompleteAt;
  }

  public MessageObject role(RoleEnum role) {
    this.role = role;
    return this;
  }

  /**
   * The entity that produced the message. One of `user` or `assistant`.
   * @return role
   **/
  @Schema(required = true, description = "The entity that produced the message. One of `user` or `assistant`.")
      @NotNull

    public RoleEnum getRole() {
    return role;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public MessageObject content(List<OneOfMessageObjectContentItems> content) {
    this.content = content;
    return this;
  }

  public MessageObject addContentItem(OneOfMessageObjectContentItems contentItem) {
    this.content.add(contentItem);
    return this;
  }

  /**
   * The content of the message in array of text and/or images.
   * @return content
   **/
  @Schema(required = true, description = "The content of the message in array of text and/or images.")
      @NotNull

    public List<OneOfMessageObjectContentItems> getContent() {
    return content;
  }

  public void setContent(List<OneOfMessageObjectContentItems> content) {
    this.content = content;
  }

  public MessageObject assistantId(String assistantId) {
    this.assistantId = assistantId;
    return this;
  }

  /**
   * If applicable, the ID of the [assistant](/docs/api-reference/assistants) that authored this message.
   * @return assistantId
   **/
  @Schema(required = true, description = "If applicable, the ID of the [assistant](/docs/api-reference/assistants) that authored this message.")
      @NotNull

    public String getAssistantId() {
    return assistantId;
  }

  public void setAssistantId(String assistantId) {
    this.assistantId = assistantId;
  }

  public MessageObject runId(String runId) {
    this.runId = runId;
    return this;
  }

  /**
   * The ID of the [run](/docs/api-reference/runs) associated with the creation of this message. Value is `null` when messages are created manually using the create message or create thread endpoints.
   * @return runId
   **/
  @Schema(required = true, description = "The ID of the [run](/docs/api-reference/runs) associated with the creation of this message. Value is `null` when messages are created manually using the create message or create thread endpoints.")
      @NotNull

    public String getRunId() {
    return runId;
  }

  public void setRunId(String runId) {
    this.runId = runId;
  }

  public MessageObject attachments(List<MessageObjectAttachments> attachments) {
    this.attachments = attachments;
    return this;
  }

  public MessageObject addAttachmentsItem(MessageObjectAttachments attachmentsItem) {
    this.attachments.add(attachmentsItem);
    return this;
  }

  /**
   * A list of files attached to the message, and the tools they were added to.
   * @return attachments
   **/
  @Schema(required = true, description = "A list of files attached to the message, and the tools they were added to.")
      @NotNull
    @Valid
    public List<MessageObjectAttachments> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<MessageObjectAttachments> attachments) {
    this.attachments = attachments;
  }

  public MessageObject metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
   **/
  @Schema(required = true, description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ")
      @NotNull

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
    MessageObject messageObject = (MessageObject) o;
    return Objects.equals(this.id, messageObject.id) &&
        Objects.equals(this.object, messageObject.object) &&
        Objects.equals(this.createdAt, messageObject.createdAt) &&
        Objects.equals(this.threadId, messageObject.threadId) &&
        Objects.equals(this.status, messageObject.status) &&
        Objects.equals(this.incompleteDetails, messageObject.incompleteDetails) &&
        Objects.equals(this.completedAt, messageObject.completedAt) &&
        Objects.equals(this.incompleteAt, messageObject.incompleteAt) &&
        Objects.equals(this.role, messageObject.role) &&
        Objects.equals(this.content, messageObject.content) &&
        Objects.equals(this.assistantId, messageObject.assistantId) &&
        Objects.equals(this.runId, messageObject.runId) &&
        Objects.equals(this.attachments, messageObject.attachments) &&
        Objects.equals(this.metadata, messageObject.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, object, createdAt, threadId, status, incompleteDetails, completedAt, incompleteAt, role, content, assistantId, runId, attachments, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageObject {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    threadId: ").append(toIndentedString(threadId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    incompleteDetails: ").append(toIndentedString(incompleteDetails)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
    sb.append("    incompleteAt: ").append(toIndentedString(incompleteAt)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    assistantId: ").append(toIndentedString(assistantId)).append("\n");
    sb.append("    runId: ").append(toIndentedString(runId)).append("\n");
    sb.append("    attachments: ").append(toIndentedString(attachments)).append("\n");
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
