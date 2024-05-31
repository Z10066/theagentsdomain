package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.RunStepDeltaStepDetailsToolCallsFunctionObjectFunction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RunStepDeltaStepDetailsToolCallsFunctionObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class RunStepDeltaStepDetailsToolCallsFunctionObject  implements OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems {
  @JsonProperty("index")
  private Integer index = null;

  @JsonProperty("id")
  private String id = null;

  /**
   * The type of tool call. This is always going to be `function` for this type of tool call.
   */
  public enum TypeEnum {
    FUNCTION("function");

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

  @JsonProperty("function")
  private RunStepDeltaStepDetailsToolCallsFunctionObjectFunction function = null;

  public RunStepDeltaStepDetailsToolCallsFunctionObject index(Integer index) {
    this.index = index;
    return this;
  }

  /**
   * The index of the tool call in the tool calls array.
   * @return index
   **/
  @Schema(required = true, description = "The index of the tool call in the tool calls array.")
      @NotNull

    public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public RunStepDeltaStepDetailsToolCallsFunctionObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the tool call object.
   * @return id
   **/
  @Schema(description = "The ID of the tool call object.")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RunStepDeltaStepDetailsToolCallsFunctionObject type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * The type of tool call. This is always going to be `function` for this type of tool call.
   * @return type
   **/
  @Schema(required = true, description = "The type of tool call. This is always going to be `function` for this type of tool call.")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public RunStepDeltaStepDetailsToolCallsFunctionObject function(RunStepDeltaStepDetailsToolCallsFunctionObjectFunction function) {
    this.function = function;
    return this;
  }

  /**
   * Get function
   * @return function
   **/
  @Schema(description = "")
  
    @Valid
    public RunStepDeltaStepDetailsToolCallsFunctionObjectFunction getFunction() {
    return function;
  }

  public void setFunction(RunStepDeltaStepDetailsToolCallsFunctionObjectFunction function) {
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
    RunStepDeltaStepDetailsToolCallsFunctionObject runStepDeltaStepDetailsToolCallsFunctionObject = (RunStepDeltaStepDetailsToolCallsFunctionObject) o;
    return Objects.equals(this.index, runStepDeltaStepDetailsToolCallsFunctionObject.index) &&
        Objects.equals(this.id, runStepDeltaStepDetailsToolCallsFunctionObject.id) &&
        Objects.equals(this.type, runStepDeltaStepDetailsToolCallsFunctionObject.type) &&
        Objects.equals(this.function, runStepDeltaStepDetailsToolCallsFunctionObject.function);
  }

  @Override
  public int hashCode() {
    return Objects.hash(index, id, type, function);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunStepDeltaStepDetailsToolCallsFunctionObject {\n");
    
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
