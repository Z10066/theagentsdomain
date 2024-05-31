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
/**
 * Deprecated and replaced by &#x60;tool_calls&#x60;. The name and arguments of a function that should be called, as generated by the model.
 */
@Schema(description = "Deprecated and replaced by `tool_calls`. The name and arguments of a function that should be called, as generated by the model.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class ChatCompletionRequestAssistantMessageFunctionCall {
  @SerializedName("arguments")
  private String arguments = null;

  @SerializedName("name")
  private String name = null;

  public ChatCompletionRequestAssistantMessageFunctionCall arguments(String arguments) {
    this.arguments = arguments;
    return this;
  }

   /**
   * The arguments to call the function with, as generated by the model in JSON format. Note that the model does not always generate valid JSON, and may hallucinate parameters not defined by your function schema. Validate the arguments in your code before calling your function.
   * @return arguments
  **/
  @Schema(required = true, description = "The arguments to call the function with, as generated by the model in JSON format. Note that the model does not always generate valid JSON, and may hallucinate parameters not defined by your function schema. Validate the arguments in your code before calling your function.")
  public String getArguments() {
    return arguments;
  }

  public void setArguments(String arguments) {
    this.arguments = arguments;
  }

  public ChatCompletionRequestAssistantMessageFunctionCall name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the function to call.
   * @return name
  **/
  @Schema(required = true, description = "The name of the function to call.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChatCompletionRequestAssistantMessageFunctionCall chatCompletionRequestAssistantMessageFunctionCall = (ChatCompletionRequestAssistantMessageFunctionCall) o;
    return Objects.equals(this.arguments, chatCompletionRequestAssistantMessageFunctionCall.arguments) &&
        Objects.equals(this.name, chatCompletionRequestAssistantMessageFunctionCall.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arguments, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChatCompletionRequestAssistantMessageFunctionCall {\n");
    
    sb.append("    arguments: ").append(toIndentedString(arguments)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
