package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfMessageContentTextObjectTextAnnotationsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MessageContentTextAnnotationsFileCitationObject.class, name = "MessageContentTextAnnotationsFileCitationObject"),
  @JsonSubTypes.Type(value = MessageContentTextAnnotationsFilePathObject.class, name = "MessageContentTextAnnotationsFilePathObject")
})
public interface OneOfMessageContentTextObjectTextAnnotationsItems {

}
