package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepDeltaStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsCodeOutputLogsObject.class, name = "RunStepDeltaStepDetailsToolCallsCodeOutputLogsObject"),
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsCodeOutputImageObject.class, name = "RunStepDeltaStepDetailsToolCallsCodeOutputImageObject")
})
public interface OneOfRunStepDeltaStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems {

}
