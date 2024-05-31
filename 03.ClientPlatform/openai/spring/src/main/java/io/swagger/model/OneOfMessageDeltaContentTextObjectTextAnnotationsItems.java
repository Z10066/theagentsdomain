package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfMessageDeltaContentTextObjectTextAnnotationsItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MessageDeltaContentTextAnnotationsFileCitationObject.class, name = "MessageDeltaContentTextAnnotationsFileCitationObject"),
  @JsonSubTypes.Type(value = MessageDeltaContentTextAnnotationsFilePathObject.class, name = "MessageDeltaContentTextAnnotationsFilePathObject")
})
public interface OneOfMessageDeltaContentTextObjectTextAnnotationsItems {

}
