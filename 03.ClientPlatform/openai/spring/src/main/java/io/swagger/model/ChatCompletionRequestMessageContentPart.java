package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* ChatCompletionRequestMessageContentPart
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ChatCompletionRequestMessageContentPartText.class, name = "ChatCompletionRequestMessageContentPartText"),
  @JsonSubTypes.Type(value = ChatCompletionRequestMessageContentPartImage.class, name = "ChatCompletionRequestMessageContentPartImage")
})
public interface ChatCompletionRequestMessageContentPart {

}
