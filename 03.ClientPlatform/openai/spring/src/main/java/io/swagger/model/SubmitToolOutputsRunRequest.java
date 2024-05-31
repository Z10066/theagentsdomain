package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.SubmitToolOutputsRunRequestToolOutputs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SubmitToolOutputsRunRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class SubmitToolOutputsRunRequest   {
  @JsonProperty("tool_outputs")
  @Valid
  private List<SubmitToolOutputsRunRequestToolOutputs> toolOutputs = new ArrayList<SubmitToolOutputsRunRequestToolOutputs>();

  @JsonProperty("stream")
  private Boolean stream = null;

  public SubmitToolOutputsRunRequest toolOutputs(List<SubmitToolOutputsRunRequestToolOutputs> toolOutputs) {
    this.toolOutputs = toolOutputs;
    return this;
  }

  public SubmitToolOutputsRunRequest addToolOutputsItem(SubmitToolOutputsRunRequestToolOutputs toolOutputsItem) {
    this.toolOutputs.add(toolOutputsItem);
    return this;
  }

  /**
   * A list of tools for which the outputs are being submitted.
   * @return toolOutputs
   **/
  @Schema(required = true, description = "A list of tools for which the outputs are being submitted.")
      @NotNull
    @Valid
    public List<SubmitToolOutputsRunRequestToolOutputs> getToolOutputs() {
    return toolOutputs;
  }

  public void setToolOutputs(List<SubmitToolOutputsRunRequestToolOutputs> toolOutputs) {
    this.toolOutputs = toolOutputs;
  }

  public SubmitToolOutputsRunRequest stream(Boolean stream) {
    this.stream = stream;
    return this;
  }

  /**
   * If `true`, returns a stream of events that happen during the Run as server-sent events, terminating when the Run enters a terminal state with a `data: [DONE]` message. 
   * @return stream
   **/
  @Schema(description = "If `true`, returns a stream of events that happen during the Run as server-sent events, terminating when the Run enters a terminal state with a `data: [DONE]` message. ")
  
    public Boolean isStream() {
    return stream;
  }

  public void setStream(Boolean stream) {
    this.stream = stream;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubmitToolOutputsRunRequest submitToolOutputsRunRequest = (SubmitToolOutputsRunRequest) o;
    return Objects.equals(this.toolOutputs, submitToolOutputsRunRequest.toolOutputs) &&
        Objects.equals(this.stream, submitToolOutputsRunRequest.stream);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toolOutputs, stream);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmitToolOutputsRunRequest {\n");
    
    sb.append("    toolOutputs: ").append(toIndentedString(toolOutputs)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
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
