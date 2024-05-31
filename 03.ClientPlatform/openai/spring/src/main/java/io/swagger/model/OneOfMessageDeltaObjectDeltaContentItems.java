package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfMessageDeltaObjectDeltaContentItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MessageDeltaContentImageFileObject.class, name = "MessageDeltaContentImageFileObject"),
  @JsonSubTypes.Type(value = MessageDeltaContentTextObject.class, name = "MessageDeltaContentTextObject"),
  @JsonSubTypes.Type(value = MessageDeltaContentImageUrlObject.class, name = "MessageDeltaContentImageUrlObject")
})
public interface OneOfMessageDeltaObjectDeltaContentItems {

}
