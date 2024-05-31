package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* InlineResponse2001
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CreateTranslationResponseJson.class, name = "CreateTranslationResponseJson"),
  @JsonSubTypes.Type(value = CreateTranslationResponseVerboseJson.class, name = "CreateTranslationResponseVerboseJson")
})
public interface InlineResponse2001 {

}
