package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* ChatCompletionRequestMessage
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ChatCompletionRequestSystemMessage.class, name = "ChatCompletionRequestSystemMessage"),
  @JsonSubTypes.Type(value = ChatCompletionRequestUserMessage.class, name = "ChatCompletionRequestUserMessage"),
  @JsonSubTypes.Type(value = ChatCompletionRequestAssistantMessage.class, name = "ChatCompletionRequestAssistantMessage"),
  @JsonSubTypes.Type(value = ChatCompletionRequestToolMessage.class, name = "ChatCompletionRequestToolMessage"),
  @JsonSubTypes.Type(value = ChatCompletionRequestFunctionMessage.class, name = "ChatCompletionRequestFunctionMessage")
})
public interface ChatCompletionRequestMessage {

}
