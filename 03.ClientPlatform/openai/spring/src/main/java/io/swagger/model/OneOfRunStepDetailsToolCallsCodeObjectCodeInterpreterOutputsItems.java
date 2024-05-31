package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsCodeOutputLogsObject.class, name = "RunStepDetailsToolCallsCodeOutputLogsObject"),
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsCodeOutputImageObject.class, name = "RunStepDetailsToolCallsCodeOutputImageObject")
})
public interface OneOfRunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems {

}
