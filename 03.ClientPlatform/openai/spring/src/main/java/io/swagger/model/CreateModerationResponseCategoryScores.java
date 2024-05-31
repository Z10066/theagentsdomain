package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A list of the categories along with their scores as predicted by model.
 */
@Schema(description = "A list of the categories along with their scores as predicted by model.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateModerationResponseCategoryScores   {
  @JsonProperty("hate")
  private BigDecimal hate = null;

  @JsonProperty("hate/threatening")
  private BigDecimal hatethreatening = null;

  @JsonProperty("harassment")
  private BigDecimal harassment = null;

  @JsonProperty("harassment/threatening")
  private BigDecimal harassmentthreatening = null;

  @JsonProperty("self-harm")
  private BigDecimal selfHarm = null;

  @JsonProperty("self-harm/intent")
  private BigDecimal selfHarmintent = null;

  @JsonProperty("self-harm/instructions")
  private BigDecimal selfHarminstructions = null;

  @JsonProperty("sexual")
  private BigDecimal sexual = null;

  @JsonProperty("sexual/minors")
  private BigDecimal sexualminors = null;

  @JsonProperty("violence")
  private BigDecimal violence = null;

  @JsonProperty("violence/graphic")
  private BigDecimal violencegraphic = null;

  public CreateModerationResponseCategoryScores hate(BigDecimal hate) {
    this.hate = hate;
    return this;
  }

  /**
   * The score for the category 'hate'.
   * @return hate
   **/
  @Schema(required = true, description = "The score for the category 'hate'.")
      @NotNull

    @Valid
    public BigDecimal getHate() {
    return hate;
  }

  public void setHate(BigDecimal hate) {
    this.hate = hate;
  }

  public CreateModerationResponseCategoryScores hatethreatening(BigDecimal hatethreatening) {
    this.hatethreatening = hatethreatening;
    return this;
  }

  /**
   * The score for the category 'hate/threatening'.
   * @return hatethreatening
   **/
  @Schema(required = true, description = "The score for the category 'hate/threatening'.")
      @NotNull

    @Valid
    public BigDecimal getHatethreatening() {
    return hatethreatening;
  }

  public void setHatethreatening(BigDecimal hatethreatening) {
    this.hatethreatening = hatethreatening;
  }

  public CreateModerationResponseCategoryScores harassment(BigDecimal harassment) {
    this.harassment = harassment;
    return this;
  }

  /**
   * The score for the category 'harassment'.
   * @return harassment
   **/
  @Schema(required = true, description = "The score for the category 'harassment'.")
      @NotNull

    @Valid
    public BigDecimal getHarassment() {
    return harassment;
  }

  public void setHarassment(BigDecimal harassment) {
    this.harassment = harassment;
  }

  public CreateModerationResponseCategoryScores harassmentthreatening(BigDecimal harassmentthreatening) {
    this.harassmentthreatening = harassmentthreatening;
    return this;
  }

  /**
   * The score for the category 'harassment/threatening'.
   * @return harassmentthreatening
   **/
  @Schema(required = true, description = "The score for the category 'harassment/threatening'.")
      @NotNull

    @Valid
    public BigDecimal getHarassmentthreatening() {
    return harassmentthreatening;
  }

  public void setHarassmentthreatening(BigDecimal harassmentthreatening) {
    this.harassmentthreatening = harassmentthreatening;
  }

  public CreateModerationResponseCategoryScores selfHarm(BigDecimal selfHarm) {
    this.selfHarm = selfHarm;
    return this;
  }

  /**
   * The score for the category 'self-harm'.
   * @return selfHarm
   **/
  @Schema(required = true, description = "The score for the category 'self-harm'.")
      @NotNull

    @Valid
    public BigDecimal getSelfHarm() {
    return selfHarm;
  }

  public void setSelfHarm(BigDecimal selfHarm) {
    this.selfHarm = selfHarm;
  }

  public CreateModerationResponseCategoryScores selfHarmintent(BigDecimal selfHarmintent) {
    this.selfHarmintent = selfHarmintent;
    return this;
  }

  /**
   * The score for the category 'self-harm/intent'.
   * @return selfHarmintent
   **/
  @Schema(required = true, description = "The score for the category 'self-harm/intent'.")
      @NotNull

    @Valid
    public BigDecimal getSelfHarmintent() {
    return selfHarmintent;
  }

  public void setSelfHarmintent(BigDecimal selfHarmintent) {
    this.selfHarmintent = selfHarmintent;
  }

  public CreateModerationResponseCategoryScores selfHarminstructions(BigDecimal selfHarminstructions) {
    this.selfHarminstructions = selfHarminstructions;
    return this;
  }

  /**
   * The score for the category 'self-harm/instructions'.
   * @return selfHarminstructions
   **/
  @Schema(required = true, description = "The score for the category 'self-harm/instructions'.")
      @NotNull

    @Valid
    public BigDecimal getSelfHarminstructions() {
    return selfHarminstructions;
  }

  public void setSelfHarminstructions(BigDecimal selfHarminstructions) {
    this.selfHarminstructions = selfHarminstructions;
  }

  public CreateModerationResponseCategoryScores sexual(BigDecimal sexual) {
    this.sexual = sexual;
    return this;
  }

  /**
   * The score for the category 'sexual'.
   * @return sexual
   **/
  @Schema(required = true, description = "The score for the category 'sexual'.")
      @NotNull

    @Valid
    public BigDecimal getSexual() {
    return sexual;
  }

  public void setSexual(BigDecimal sexual) {
    this.sexual = sexual;
  }

  public CreateModerationResponseCategoryScores sexualminors(BigDecimal sexualminors) {
    this.sexualminors = sexualminors;
    return this;
  }

  /**
   * The score for the category 'sexual/minors'.
   * @return sexualminors
   **/
  @Schema(required = true, description = "The score for the category 'sexual/minors'.")
      @NotNull

    @Valid
    public BigDecimal getSexualminors() {
    return sexualminors;
  }

  public void setSexualminors(BigDecimal sexualminors) {
    this.sexualminors = sexualminors;
  }

  public CreateModerationResponseCategoryScores violence(BigDecimal violence) {
    this.violence = violence;
    return this;
  }

  /**
   * The score for the category 'violence'.
   * @return violence
   **/
  @Schema(required = true, description = "The score for the category 'violence'.")
      @NotNull

    @Valid
    public BigDecimal getViolence() {
    return violence;
  }

  public void setViolence(BigDecimal violence) {
    this.violence = violence;
  }

  public CreateModerationResponseCategoryScores violencegraphic(BigDecimal violencegraphic) {
    this.violencegraphic = violencegraphic;
    return this;
  }

  /**
   * The score for the category 'violence/graphic'.
   * @return violencegraphic
   **/
  @Schema(required = true, description = "The score for the category 'violence/graphic'.")
      @NotNull

    @Valid
    public BigDecimal getViolencegraphic() {
    return violencegraphic;
  }

  public void setViolencegraphic(BigDecimal violencegraphic) {
    this.violencegraphic = violencegraphic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModerationResponseCategoryScores createModerationResponseCategoryScores = (CreateModerationResponseCategoryScores) o;
    return Objects.equals(this.hate, createModerationResponseCategoryScores.hate) &&
        Objects.equals(this.hatethreatening, createModerationResponseCategoryScores.hatethreatening) &&
        Objects.equals(this.harassment, createModerationResponseCategoryScores.harassment) &&
        Objects.equals(this.harassmentthreatening, createModerationResponseCategoryScores.harassmentthreatening) &&
        Objects.equals(this.selfHarm, createModerationResponseCategoryScores.selfHarm) &&
        Objects.equals(this.selfHarmintent, createModerationResponseCategoryScores.selfHarmintent) &&
        Objects.equals(this.selfHarminstructions, createModerationResponseCategoryScores.selfHarminstructions) &&
        Objects.equals(this.sexual, createModerationResponseCategoryScores.sexual) &&
        Objects.equals(this.sexualminors, createModerationResponseCategoryScores.sexualminors) &&
        Objects.equals(this.violence, createModerationResponseCategoryScores.violence) &&
        Objects.equals(this.violencegraphic, createModerationResponseCategoryScores.violencegraphic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hate, hatethreatening, harassment, harassmentthreatening, selfHarm, selfHarmintent, selfHarminstructions, sexual, sexualminors, violence, violencegraphic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseCategoryScores {\n");
    
    sb.append("    hate: ").append(toIndentedString(hate)).append("\n");
    sb.append("    hatethreatening: ").append(toIndentedString(hatethreatening)).append("\n");
    sb.append("    harassment: ").append(toIndentedString(harassment)).append("\n");
    sb.append("    harassmentthreatening: ").append(toIndentedString(harassmentthreatening)).append("\n");
    sb.append("    selfHarm: ").append(toIndentedString(selfHarm)).append("\n");
    sb.append("    selfHarmintent: ").append(toIndentedString(selfHarmintent)).append("\n");
    sb.append("    selfHarminstructions: ").append(toIndentedString(selfHarminstructions)).append("\n");
    sb.append("    sexual: ").append(toIndentedString(sexual)).append("\n");
    sb.append("    sexualminors: ").append(toIndentedString(sexualminors)).append("\n");
    sb.append("    violence: ").append(toIndentedString(violence)).append("\n");
    sb.append("    violencegraphic: ").append(toIndentedString(violencegraphic)).append("\n");
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
