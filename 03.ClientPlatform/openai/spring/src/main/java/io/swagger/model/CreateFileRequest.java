package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateFileRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateFileRequest   {
  @JsonProperty("file")
  private Resource file = null;

  /**
   * The intended purpose of the uploaded file.  Use \"assistants\" for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \"vision\" for Assistants image file inputs, \"batch\" for [Batch API](/docs/guides/batch), and \"fine-tune\" for [Fine-tuning](/docs/api-reference/fine-tuning). 
   */
  public enum PurposeEnum {
    ASSISTANTS("assistants"),
    
    BATCH("batch"),
    
    FINE_TUNE("fine-tune"),
    
    VISION("vision");

    private String value;

    PurposeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PurposeEnum fromValue(String text) {
      for (PurposeEnum b : PurposeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("purpose")
  private PurposeEnum purpose = null;

  public CreateFileRequest file(Resource file) {
    this.file = file;
    return this;
  }

  /**
   * The File object (not file name) to be uploaded. 
   * @return file
   **/
  @Schema(required = true, description = "The File object (not file name) to be uploaded. ")
      @NotNull

    @Valid
    public Resource getFile() {
    return file;
  }

  public void setFile(Resource file) {
    this.file = file;
  }

  public CreateFileRequest purpose(PurposeEnum purpose) {
    this.purpose = purpose;
    return this;
  }

  /**
   * The intended purpose of the uploaded file.  Use \"assistants\" for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \"vision\" for Assistants image file inputs, \"batch\" for [Batch API](/docs/guides/batch), and \"fine-tune\" for [Fine-tuning](/docs/api-reference/fine-tuning). 
   * @return purpose
   **/
  @Schema(required = true, description = "The intended purpose of the uploaded file.  Use \"assistants\" for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \"vision\" for Assistants image file inputs, \"batch\" for [Batch API](/docs/guides/batch), and \"fine-tune\" for [Fine-tuning](/docs/api-reference/fine-tuning). ")
      @NotNull

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
    return Objects.hash(file, purpose);
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
