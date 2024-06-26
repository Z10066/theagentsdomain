/*
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * MessageDeltaContentTextObjectText
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class MessageDeltaContentTextObjectText {
  @SerializedName("value")
  private String value = null;

  @SerializedName("annotations")
  private List<OneOfMessageDeltaContentTextObjectTextAnnotationsItems> annotations = null;

  public MessageDeltaContentTextObjectText value(String value) {
    this.value = value;
    return this;
  }

   /**
   * The data that makes up the text.
   * @return value
  **/
  @Schema(description = "The data that makes up the text.")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public MessageDeltaContentTextObjectText annotations(List<OneOfMessageDeltaContentTextObjectTextAnnotationsItems> annotations) {
    this.annotations = annotations;
    return this;
  }

  public MessageDeltaContentTextObjectText addAnnotationsItem(OneOfMessageDeltaContentTextObjectTextAnnotationsItems annotationsItem) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<OneOfMessageDeltaContentTextObjectTextAnnotationsItems>();
    }
    this.annotations.add(annotationsItem);
    return this;
  }

   /**
   * Get annotations
   * @return annotations
  **/
  @Schema(description = "")
  public List<OneOfMessageDeltaContentTextObjectTextAnnotationsItems> getAnnotations() {
    return annotations;
  }

  public void setAnnotations(List<OneOfMessageDeltaContentTextObjectTextAnnotationsItems> annotations) {
    this.annotations = annotations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDeltaContentTextObjectText messageDeltaContentTextObjectText = (MessageDeltaContentTextObjectText) o;
    return Objects.equals(this.value, messageDeltaContentTextObjectText.value) &&
        Objects.equals(this.annotations, messageDeltaContentTextObjectText.annotations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, annotations);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageDeltaContentTextObjectText {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    annotations: ").append(toIndentedString(annotations)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
