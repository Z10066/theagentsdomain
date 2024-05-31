package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* OneOfMessageObjectContentItems
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MessageContentImageFileObject.class, name = "MessageContentImageFileObject"),
  @JsonSubTypes.Type(value = MessageContentImageUrlObject.class, name = "MessageContentImageUrlObject"),
  @JsonSubTypes.Type(value = MessageContentTextObject.class, name = "MessageContentTextObject")
})
public interface OneOfMessageObjectContentItems {

}
