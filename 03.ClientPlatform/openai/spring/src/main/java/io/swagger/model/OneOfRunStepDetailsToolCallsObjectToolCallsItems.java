package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepDetailsToolCallsObjectToolCallsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsCodeObject.class, name = "RunStepDetailsToolCallsCodeObject"),
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsFileSearchObject.class, name = "RunStepDetailsToolCallsFileSearchObject"),
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsFunctionObject.class, name = "RunStepDetailsToolCallsFunctionObject")
})
public interface OneOfRunStepDetailsToolCallsObjectToolCallsItems {

}
