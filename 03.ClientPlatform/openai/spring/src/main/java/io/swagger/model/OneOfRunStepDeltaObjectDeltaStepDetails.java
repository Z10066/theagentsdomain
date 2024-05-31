package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepDeltaObjectDeltaStepDetails
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsMessageCreationObject.class, name = "RunStepDeltaStepDetailsMessageCreationObject"),
  @JsonSubTypes.Type(value = RunStepDeltaStepDetailsToolCallsObject.class, name = "RunStepDeltaStepDetailsToolCallsObject")
})
public interface OneOfRunStepDeltaObjectDeltaStepDetails {

}
