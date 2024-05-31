package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfRunStepObjectStepDetails
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = RunStepDetailsMessageCreationObject.class, name = "RunStepDetailsMessageCreationObject"),
  @JsonSubTypes.Type(value = RunStepDetailsToolCallsObject.class, name = "RunStepDetailsToolCallsObject")
})
public interface OneOfRunStepObjectStepDetails {

}
