package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* InlineResponse200
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CreateTranscriptionResponseJson.class, name = "CreateTranscriptionResponseJson"),
  @JsonSubTypes.Type(value = CreateTranscriptionResponseVerboseJson.class, name = "CreateTranscriptionResponseVerboseJson")
})
public interface InlineResponse200 {

}
