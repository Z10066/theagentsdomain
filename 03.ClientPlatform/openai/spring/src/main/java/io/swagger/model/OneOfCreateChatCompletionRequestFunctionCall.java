package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfCreateChatCompletionRequestFunctionCall
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ChatCompletionFunctionCallOption.class, name = "ChatCompletionFunctionCallOption")
})
public interface OneOfCreateChatCompletionRequestFunctionCall {

}
