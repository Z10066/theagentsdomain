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
import io.swagger.client.model.MessageDeltaContentImageFileObjectImageFile;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * References an image [File](/docs/api-reference/files) in the content of a message.
 */
@Schema(description = "References an image [File](/docs/api-reference/files) in the content of a message.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class MessageDeltaContentImageFileObject implements OneOfMessageDeltaObjectDeltaContentItems {
  @SerializedName("index")
  private Integer index = null;

  /**
   * Always &#x60;image_file&#x60;.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    IMAGE_FILE("image_file");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static TypeEnum fromValue(String input) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(String.valueOf(enumeration.getValue()));
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        Object value = jsonReader.nextString();
        return TypeEnum.fromValue((String)(value));
      }
    }
  }  @SerializedName("type")
  private TypeEnum type = null;

  @SerializedName("image_file")
  private MessageDeltaContentImageFileObjectImageFile imageFile = null;

  public MessageDeltaContentImageFileObject index(Integer index) {
    this.index = index;
    return this;
  }

   /**
   * The index of the content part in the message.
   * @return index
  **/
  @Schema(required = true, description = "The index of the content part in the message.")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public MessageDeltaContentImageFileObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * Always &#x60;image_file&#x60;.
   * @return type
  **/
  @Schema(required = true, description = "Always `image_file`.")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public MessageDeltaContentImageFileObject imageFile(MessageDeltaContentImageFileObjectImageFile imageFile) {
    this.imageFile = imageFile;
    return this;
  }

   /**
   * Get imageFile
   * @return imageFile
  **/
  @Schema(description = "")
  public MessageDeltaContentImageFileObjectImageFile getImageFile() {
    return imageFile;
  }

  public void setImageFile(MessageDeltaContentImageFileObjectImageFile imageFile) {
    this.imageFile = imageFile;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDeltaContentImageFileObject messageDeltaContentImageFileObject = (MessageDeltaContentImageFileObject) o;
    return Objects.equals(this.index, messageDeltaContentImageFileObject.index) &&
        Objects.equals(this.type, messageDeltaContentImageFileObject.type) &&
        Objects.equals(this.imageFile, messageDeltaContentImageFileObject.imageFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, type, imageFile);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageDeltaContentImageFileObject {\n");
    
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    imageFile: ").append(toIndentedString(imageFile)).append("\n");
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
