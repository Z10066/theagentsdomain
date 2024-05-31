package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.AssistantsApiResponseFormatOption;
import io.swagger.model.ModifyAssistantRequestToolResources;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ModifyAssistantRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class ModifyAssistantRequest   {
  @JsonProperty("model")
  private AnyOfModifyAssistantRequestModel model = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("instructions")
  private String instructions = null;

  @JsonProperty("tools")
  @Valid
  private List<OneOfModifyAssistantRequestToolsItems> tools = null;

  @JsonProperty("tool_resources")
  private ModifyAssistantRequestToolResources toolResources = null;

  @JsonProperty("metadata")
  private Object metadata = null;

  @JsonProperty("temperature")
  private BigDecimal temperature = new BigDecimal(1);

  @JsonProperty("top_p")
  private BigDecimal topP = new BigDecimal(1);

  @JsonProperty("response_format")
  private AssistantsApiResponseFormatOption responseFormat = null;

  public ModifyAssistantRequest model(AnyOfModifyAssistantRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. 
   * @return model
   **/
  @Schema(description = "ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. ")
  
    public AnyOfModifyAssistantRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfModifyAssistantRequestModel model) {
    this.model = model;
  }

  public ModifyAssistantRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the assistant. The maximum length is 256 characters. 
   * @return name
   **/
  @Schema(description = "The name of the assistant. The maximum length is 256 characters. ")
  
  @Size(max=256)   public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ModifyAssistantRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description of the assistant. The maximum length is 512 characters. 
   * @return description
   **/
  @Schema(description = "The description of the assistant. The maximum length is 512 characters. ")
  
  @Size(max=512)   public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ModifyAssistantRequest instructions(String instructions) {
    this.instructions = instructions;
    return this;
  }

  /**
   * The system instructions that the assistant uses. The maximum length is 256,000 characters. 
   * @return instructions
   **/
  @Schema(description = "The system instructions that the assistant uses. The maximum length is 256,000 characters. ")
  
  @Size(max=256000)   public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }

  public ModifyAssistantRequest tools(List<OneOfModifyAssistantRequestToolsItems> tools) {
    this.tools = tools;
    return this;
  }

  public ModifyAssistantRequest addToolsItem(OneOfModifyAssistantRequestToolsItems toolsItem) {
    if (this.tools == null) {
      this.tools = new ArrayList<OneOfModifyAssistantRequestToolsItems>();
    }
    this.tools.add(toolsItem);
    return this;
  }

  /**
   * A list of tool enabled on the assistant. There can be a maximum of 128 tools per assistant. Tools can be of types `code_interpreter`, `file_search`, or `function`. 
   * @return tools
   **/
  @Schema(description = "A list of tool enabled on the assistant. There can be a maximum of 128 tools per assistant. Tools can be of types `code_interpreter`, `file_search`, or `function`. ")
  
  @Size(max=128)   public List<OneOfModifyAssistantRequestToolsItems> getTools() {
    return tools;
  }

  public void setTools(List<OneOfModifyAssistantRequestToolsItems> tools) {
    this.tools = tools;
  }

  public ModifyAssistantRequest toolResources(ModifyAssistantRequestToolResources toolResources) {
    this.toolResources = toolResources;
    return this;
  }

  /**
   * Get toolResources
   * @return toolResources
   **/
  @Schema(description = "")
  
    @Valid
    public ModifyAssistantRequestToolResources getToolResources() {
    return toolResources;
  }

  public void setToolResources(ModifyAssistantRequestToolResources toolResources) {
    this.toolResources = toolResources;
  }

  public ModifyAssistantRequest metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. 
   * @return metadata
   **/
  @Schema(description = "Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long. ")
  
    public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public ModifyAssistantRequest temperature(BigDecimal temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. 
   * minimum: 0
   * maximum: 2
   * @return temperature
   **/
  @Schema(example = "1", description = "What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. ")
  
    @Valid
  @DecimalMin("0") @DecimalMax("2")   public BigDecimal getTemperature() {
    return temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = temperature;
  }

  public ModifyAssistantRequest topP(BigDecimal topP) {
    this.topP = topP;
    return this;
  }

  /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or temperature but not both. 
   * minimum: 0
   * maximum: 1
   * @return topP
   **/
  @Schema(example = "1", description = "An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or temperature but not both. ")
  
    @Valid
  @DecimalMin("0") @DecimalMax("1")   public BigDecimal getTopP() {
    return topP;
  }

  public void setTopP(BigDecimal topP) {
    this.topP = topP;
  }

  public ModifyAssistantRequest responseFormat(AssistantsApiResponseFormatOption responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * Get responseFormat
   * @return responseFormat
   **/
  @Schema(description = "")
  
    @Valid
    public AssistantsApiResponseFormatOption getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(AssistantsApiResponseFormatOption responseFormat) {
    this.responseFormat = responseFormat;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModifyAssistantRequest modifyAssistantRequest = (ModifyAssistantRequest) o;
    return Objects.equals(this.model, modifyAssistantRequest.model) &&
        Objects.equals(this.name, modifyAssistantRequest.name) &&
        Objects.equals(this.description, modifyAssistantRequest.description) &&
        Objects.equals(this.instructions, modifyAssistantRequest.instructions) &&
        Objects.equals(this.tools, modifyAssistantRequest.tools) &&
        Objects.equals(this.toolResources, modifyAssistantRequest.toolResources) &&
        Objects.equals(this.metadata, modifyAssistantRequest.metadata) &&
        Objects.equals(this.temperature, modifyAssistantRequest.temperature) &&
        Objects.equals(this.topP, modifyAssistantRequest.topP) &&
        Objects.equals(this.responseFormat, modifyAssistantRequest.responseFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, name, description, instructions, tools, toolResources, metadata, temperature, topP, responseFormat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyAssistantRequest {\n");
    
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    instructions: ").append(toIndentedString(instructions)).append("\n");
    sb.append("    tools: ").append(toIndentedString(tools)).append("\n");
    sb.append("    toolResources: ").append(toIndentedString(toolResources)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    topP: ").append(toIndentedString(topP)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
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
