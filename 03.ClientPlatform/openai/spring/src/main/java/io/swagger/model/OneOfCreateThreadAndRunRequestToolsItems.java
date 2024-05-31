package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfCreateThreadAndRunRequestToolsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = AssistantToolsCode.class, name = "AssistantToolsCode"),
  @JsonSubTypes.Type(value = AssistantToolsFileSearch.class, name = "AssistantToolsFileSearch"),
  @JsonSubTypes.Type(value = AssistantToolsFunction.class, name = "AssistantToolsFunction")
})
public interface OneOfCreateThreadAndRunRequestToolsItems {

}
