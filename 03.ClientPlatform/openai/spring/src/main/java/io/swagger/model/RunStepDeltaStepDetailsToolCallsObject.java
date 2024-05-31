package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Details of the tool call.
 */
@Schema(description = "Details of the tool call.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class RunStepDeltaStepDetailsToolCallsObject  implements OneOfRunStepDeltaObjectDeltaStepDetails {
  /**
   * Always `tool_calls`.
   */
  public enum TypeEnum {
    TOOL_CALLS("tool_calls");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("tool_calls")
  @Valid
  private List<OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems> toolCalls = null;

  public RunStepDeltaStepDetailsToolCallsObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Always `tool_calls`.
   * @return type
   **/
  @Schema(required = true, description = "Always `tool_calls`.")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDeltaStepDetailsToolCallsObject toolCalls(List<OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems> toolCalls) {
    this.toolCalls = toolCalls;
    return this;
  }

  public RunStepDeltaStepDetailsToolCallsObject addToolCallsItem(OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems toolCallsItem) {
    if (this.toolCalls == null) {
      this.toolCalls = new ArrayList<OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems>();
    }
    this.toolCalls.add(toolCallsItem);
    return this;
  }

  /**
   * An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `file_search`, or `function`. 
   * @return toolCalls
   **/
  @Schema(description = "An array of tool calls the run step was involved in. These can be associated with one of three types of tools: `code_interpreter`, `file_search`, or `function`. ")
  
    public List<OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems> getToolCalls() {
    return toolCalls;
  }

  public void setToolCalls(List<OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems> toolCalls) {
    this.toolCalls = toolCalls;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunStepDeltaStepDetailsToolCallsObject runStepDeltaStepDetailsToolCallsObject = (RunStepDeltaStepDetailsToolCallsObject) o;
    return Objects.equals(this.type, runStepDeltaStepDetailsToolCallsObject.type) &&
        Objects.equals(this.toolCalls, runStepDeltaStepDetailsToolCallsObject.toolCalls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, toolCalls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDeltaStepDetailsToolCallsObject {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    toolCalls: ").append(toIndentedString(toolCalls)).append("\n");
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
