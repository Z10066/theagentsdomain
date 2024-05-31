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
import java.io.File;
import java.io.IOException;
/**
 * CreateFileRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class CreateFileRequest {
  @SerializedName("file")
  private File file = null;

  /**
   * The intended purpose of the uploaded file.  Use \&quot;assistants\&quot; for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \&quot;vision\&quot; for Assistants image file inputs, \&quot;batch\&quot; for [Batch API](/docs/guides/batch), and \&quot;fine-tune\&quot; for [Fine-tuning](/docs/api-reference/fine-tuning). 
   */
  @JsonAdapter(PurposeEnum.Adapter.class)
  public enum PurposeEnum {
    ASSISTANTS("assistants"),
    BATCH("batch"),
    FINE_TUNE("fine-tune"),
    VISION("vision");

    private String value;

    PurposeEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static PurposeEnum fromValue(String input) {
      for (PurposeEnum b : PurposeEnum.values()) {
        if (b.value.equals(input)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<PurposeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final PurposeEnum enumeration) throws IOException {
        jsonWriter.value(String.valueOf(enumeration.getValue()));
      }

      @Override
      public PurposeEnum read(final JsonReader jsonReader) throws IOException {
        Object value = jsonReader.nextString();
        return PurposeEnum.fromValue((String)(value));
      }
    }
  }  @SerializedName("purpose")
  private PurposeEnum purpose = null;

  public CreateFileRequest file(File file) {
    this.file = file;
    return this;
  }

   /**
   * The File object (not file name) to be uploaded. 
   * @return file
  **/
  @Schema(required = true, description = "The File object (not file name) to be uploaded. ")
  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public CreateFileRequest purpose(PurposeEnum purpose) {
    this.purpose = purpose;
    return this;
  }

   /**
   * The intended purpose of the uploaded file.  Use \&quot;assistants\&quot; for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \&quot;vision\&quot; for Assistants image file inputs, \&quot;batch\&quot; for [Batch API](/docs/guides/batch), and \&quot;fine-tune\&quot; for [Fine-tuning](/docs/api-reference/fine-tuning). 
   * @return purpose
  **/
  @Schema(required = true, description = "The intended purpose of the uploaded file.  Use \"assistants\" for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \"vision\" for Assistants image file inputs, \"batch\" for [Batch API](/docs/guides/batch), and \"fine-tune\" for [Fine-tuning](/docs/api-reference/fine-tuning). ")
  public PurposeEnum getPurpose() {
    return purpose;
  }

  public void setPurpose(PurposeEnum purpose) {
    this.purpose = purpose;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFileRequest createFileRequest = (CreateFileRequest) o;
    return Objects.equals(this.file, createFileRequest.file) &&
        Objects.equals(this.purpose, createFileRequest.purpose);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Objects.hashCode(file), purpose);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateFileRequest {\n");
    
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    purpose: ").append(toIndentedString(purpose)).append("\n");
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
