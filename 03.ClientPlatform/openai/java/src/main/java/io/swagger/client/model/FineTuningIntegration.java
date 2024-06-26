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
import io.swagger.client.model.CreateFineTuningJobRequestWandb;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * FineTuningIntegration
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class FineTuningIntegration implements OneOfFineTuningJobIntegrationsItems {
  /**
   * The type of the integration being enabled for the fine-tuning job
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    WANDB("wandb");

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

  @SerializedName("wandb")
  private CreateFineTuningJobRequestWandb wandb = null;

  public FineTuningIntegration type(TypeEnum type) {
    this.type = type;
    return this;
  }

   /**
   * The type of the integration being enabled for the fine-tuning job
   * @return type
  **/
  @Schema(required = true, description = "The type of the integration being enabled for the fine-tuning job")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public FineTuningIntegration wandb(CreateFineTuningJobRequestWandb wandb) {
    this.wandb = wandb;
    return this;
  }

   /**
   * Get wandb
   * @return wandb
  **/
  @Schema(required = true, description = "")
  public CreateFineTuningJobRequestWandb getWandb() {
    return wandb;
  }

  public void setWandb(CreateFineTuningJobRequestWandb wandb) {
    this.wandb = wandb;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FineTuningIntegration fineTuningIntegration = (FineTuningIntegration) o;
    return Objects.equals(this.type, fineTuningIntegration.type) &&
        Objects.equals(this.wandb, fineTuningIntegration.wandb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, wandb);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FineTuningIntegration {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    wandb: ").append(toIndentedString(wandb)).append("\n");
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
