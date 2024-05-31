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
import io.swagger.client.model.CreateEmbeddingResponseUsage;
import io.swagger.client.model.Embedding;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * CreateEmbeddingResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class CreateEmbeddingResponse {
  @SerializedName("data")
  private List<Embedding> data = new ArrayList<Embedding>();

  @SerializedName("model")
  private String model = null;

  /**
   * The object type, which is always \&quot;list\&quot;.
   */
  @JsonAdapter(ObjectEnum.Adapter.class)
  public enum ObjectEnum {
    LIST("list");

    private String value;

    ObjectEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static ObjectEnum fromValue(String input) {
      for (ObjectEnum b : ObjectEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<ObjectEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ObjectEnum enumeration) throws IOException {
        jsonWriter.value(String.valueOf(enumeration.getValue()));
      }

      @Override
      public ObjectEnum read(final JsonReader jsonReader) throws IOException {
        Object value = jsonReader.nextString();
        return ObjectEnum.fromValue((String)(value));
      }
    }
  }  @SerializedName("object")
  private ObjectEnum object = null;

  @SerializedName("usage")
  private CreateEmbeddingResponseUsage usage = null;

  public CreateEmbeddingResponse data(List<Embedding> data) {
    this.data = data;
    return this;
  }

  public CreateEmbeddingResponse addDataItem(Embedding dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * The list of embeddings generated by the model.
   * @return data
  **/
  @Schema(required = true, description = "The list of embeddings generated by the model.")
  public List<Embedding> getData() {
    return data;
  }

  public void setData(List<Embedding> data) {
    this.data = data;
  }

  public CreateEmbeddingResponse model(String model) {
    this.model = model;
    return this;
  }

   /**
   * The name of the model used to generate the embedding.
   * @return model
  **/
  @Schema(required = true, description = "The name of the model used to generate the embedding.")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public CreateEmbeddingResponse object(ObjectEnum object) {
    this.object = object;
    return this;
  }

   /**
   * The object type, which is always \&quot;list\&quot;.
   * @return object
  **/
  @Schema(required = true, description = "The object type, which is always \"list\".")
  public ObjectEnum getObject() {
    return object;
  }

  public void setObject(ObjectEnum object) {
    this.object = object;
  }

  public CreateEmbeddingResponse usage(CreateEmbeddingResponseUsage usage) {
    this.usage = usage;
    return this;
  }

   /**
   * Get usage
   * @return usage
  **/
  @Schema(required = true, description = "")
  public CreateEmbeddingResponseUsage getUsage() {
    return usage;
  }

  public void setUsage(CreateEmbeddingResponseUsage usage) {
    this.usage = usage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateEmbeddingResponse createEmbeddingResponse = (CreateEmbeddingResponse) o;
    return Objects.equals(this.data, createEmbeddingResponse.data) &&
        Objects.equals(this.model, createEmbeddingResponse.model) &&
        Objects.equals(this.object, createEmbeddingResponse.object) &&
        Objects.equals(this.usage, createEmbeddingResponse.usage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, model, object, usage);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateEmbeddingResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
    sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
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
