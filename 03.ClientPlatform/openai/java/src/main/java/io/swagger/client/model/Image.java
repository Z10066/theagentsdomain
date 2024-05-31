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
 * Represents the url or the content of an image generated by the OpenAI API.
 */
@Schema(description = "Represents the url or the content of an image generated by the OpenAI API.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class Image {
  @SerializedName("b64_json")
  private String b64Json = null;

  @SerializedName("url")
  private String url = null;

  @SerializedName("revised_prompt")
  private String revisedPrompt = null;

  public Image b64Json(String b64Json) {
    this.b64Json = b64Json;
    return this;
  }

   /**
   * The base64-encoded JSON of the generated image, if &#x60;response_format&#x60; is &#x60;b64_json&#x60;.
   * @return b64Json
  **/
  @Schema(description = "The base64-encoded JSON of the generated image, if `response_format` is `b64_json`.")
  public String getB64Json() {
    return b64Json;
  }

  public void setB64Json(String b64Json) {
    this.b64Json = b64Json;
  }

  public Image url(String url) {
    this.url = url;
    return this;
  }

   /**
   * The URL of the generated image, if &#x60;response_format&#x60; is &#x60;url&#x60; (default).
   * @return url
  **/
  @Schema(description = "The URL of the generated image, if `response_format` is `url` (default).")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Image revisedPrompt(String revisedPrompt) {
    this.revisedPrompt = revisedPrompt;
    return this;
  }

   /**
   * The prompt that was used to generate the image, if there was any revision to the prompt.
   * @return revisedPrompt
  **/
  @Schema(description = "The prompt that was used to generate the image, if there was any revision to the prompt.")
  public String getRevisedPrompt() {
    return revisedPrompt;
  }

  public void setRevisedPrompt(String revisedPrompt) {
    this.revisedPrompt = revisedPrompt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(this.b64Json, image.b64Json) &&
        Objects.equals(this.url, image.url) &&
        Objects.equals(this.revisedPrompt, image.revisedPrompt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(b64Json, url, revisedPrompt);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    
    sb.append("    b64Json: ").append(toIndentedString(b64Json)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    revisedPrompt: ").append(toIndentedString(revisedPrompt)).append("\n");
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
