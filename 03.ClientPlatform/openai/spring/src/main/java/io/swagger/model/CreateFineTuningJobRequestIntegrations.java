package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.CreateFineTuningJobRequestWandb;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateFineTuningJobRequestIntegrations
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateFineTuningJobRequestIntegrations   {
  @JsonProperty("type")
  private OneOfCreateFineTuningJobRequestIntegrationsType type = null;

  @JsonProperty("wandb")
  private CreateFineTuningJobRequestWandb wandb = null;

  public CreateFineTuningJobRequestIntegrations type(OneOfCreateFineTuningJobRequestIntegrationsType type) {
    this.type = type;
    return this;
  }

  /**
   * The type of integration to enable. Currently, only \"wandb\" (Weights and Biases) is supported. 
   * @return type
   **/
  @Schema(required = true, description = "The type of integration to enable. Currently, only \"wandb\" (Weights and Biases) is supported. ")
      @NotNull

    public OneOfCreateFineTuningJobRequestIntegrationsType getType() {
    return type;
  }

  public void setType(OneOfCreateFineTuningJobRequestIntegrationsType type) {
    this.type = type;
  }

  public CreateFineTuningJobRequestIntegrations wandb(CreateFineTuningJobRequestWandb wandb) {
    this.wandb = wandb;
    return this;
  }

  /**
   * Get wandb
   * @return wandb
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
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
    CreateFineTuningJobRequestIntegrations createFineTuningJobRequestIntegrations = (CreateFineTuningJobRequestIntegrations) o;
    return Objects.equals(this.type, createFineTuningJobRequestIntegrations.type) &&
        Objects.equals(this.wandb, createFineTuningJobRequestIntegrations.wandb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, wandb);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateFineTuningJobRequestIntegrations {\n");
    
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
