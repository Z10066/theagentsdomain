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
import io.swagger.client.model.ChatCompletionNamedToolChoiceFunction;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * Specifies a tool the model should use. Use to force the model to call a specific tool.
 */
@Schema(description = "Specifies a tool the model should use. Use to force the model to call a specific tool.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class AssistantsNamedToolChoice implements AssistantsApiToolChoiceOption {
  /**
   * The type of the tool. If type is &#x60;function&#x60;, the function name must be set
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    FUNCTION("function"),
    CODE_INTERPRETER("code_interpreter"),
    FILE_SEARCH("file_search");

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

  @SerializedName("function")
  private ChatCompletionNamedToolChoiceFunction function = null;

  public AssistantsNamedToolChoice type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * The type of the tool. If type is &#x60;function&#x60;, the function name must be set
   * @return type
  **/
  @Schema(required = true, description = "The type of the tool. If type is `function`, the function name must be set")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public AssistantsNamedToolChoice function(ChatCompletionNamedToolChoiceFunction function) {
    this.function = function;
    return this;
  }

   /**
   * Get function
   * @return function
  **/
  @Schema(description = "")
  public ChatCompletionNamedToolChoiceFunction getFunction() {
    return function;
  }

  public void setFunction(ChatCompletionNamedToolChoiceFunction function) {
    this.function = function;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssistantsNamedToolChoice assistantsNamedToolChoice = (AssistantsNamedToolChoice) o;
    return Objects.equals(this.type, assistantsNamedToolChoice.type) &&
        Objects.equals(this.function, assistantsNamedToolChoice.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, function);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssistantsNamedToolChoice {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    function: ").append(toIndentedString(function)).append("\n");
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
