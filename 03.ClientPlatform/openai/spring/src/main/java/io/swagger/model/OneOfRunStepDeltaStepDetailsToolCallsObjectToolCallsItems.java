package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsCodeObject.class, name = "RunStepDeltaStepDetailsToolCallsCodeObject"),
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsFileSearchObject.class, name = "RunStepDeltaStepDetailsToolCallsFileSearchObject"),
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsFunctionObject.class, name = "RunStepDeltaStepDetailsToolCallsFunctionObject")
})
public interface OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems {

}
