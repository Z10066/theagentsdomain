package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SubmitToolOutputsRunRequestToolOutputs
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class SubmitToolOutputsRunRequestToolOutputs   {
  @JsonProperty("tool_call_id")
  private String toolCallId = null;

  @JsonProperty("output")
  private String output = null;

  public SubmitToolOutputsRunRequestToolOutputs toolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
    return this;
  }

  /**
   * The ID of the tool call in the `required_action` object within the run object the output is being submitted for.
   * @return toolCallId
   **/
  @Schema(description = "The ID of the tool call in the `required_action` object within the run object the output is being submitted for.")
  
    public String getToolCallId() {
    return toolCallId;
  }

  public void setToolCallId(String toolCallId) {
    this.toolCallId = toolCallId;
  }

  public SubmitToolOutputsRunRequestToolOutputs output(String output) {
    this.output = output;
    return this;
  }

  /**
   * The output of the tool call to be submitted to continue the run.
   * @return output
   **/
  @Schema(description = "The output of the tool call to be submitted to continue the run.")
  
    public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubmitToolOutputsRunRequestToolOutputs submitToolOutputsRunRequestToolOutputs = (SubmitToolOutputsRunRequestToolOutputs) o;
    return Objects.equals(this.toolCallId, submitToolOutputsRunRequestToolOutputs.toolCallId) &&
        Objects.equals(this.output, submitToolOutputsRunRequestToolOutputs.output);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toolCallId, output);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmitToolOutputsRunRequestToolOutputs {\n");
    
    sb.append("    toolCallId: ").append(toIndentedString(toolCallId)).append("\n");
    sb.append("    output: ").append(toIndentedString(output)).append("\n");
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
