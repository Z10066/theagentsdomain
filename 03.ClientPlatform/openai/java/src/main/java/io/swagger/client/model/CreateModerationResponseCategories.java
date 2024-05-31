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
import java.io.IOException;
/**
 * A list of the categories, and whether they are flagged or not.
 */
@Schema(description = "A list of the categories, and whether they are flagged or not.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2024-05-31T08:40:29.491725559+09:00[Asia/Tokyo]")
public class CreateModerationResponseCategories {
  @SerializedName("hate")
  private Boolean hate = null;

  @SerializedName("hate/threatening")
  private Boolean hatethreatening = null;

  @SerializedName("harassment")
  private Boolean harassment = null;

  @SerializedName("harassment/threatening")
  private Boolean harassmentthreatening = null;

  @SerializedName("self-harm")
  private Boolean selfHarm = null;

  @SerializedName("self-harm/intent")
  private Boolean selfHarmintent = null;

  @SerializedName("self-harm/instructions")
  private Boolean selfHarminstructions = null;

  @SerializedName("sexual")
  private Boolean sexual = null;

  @SerializedName("sexual/minors")
  private Boolean sexualminors = null;

  @SerializedName("violence")
  private Boolean violence = null;

  @SerializedName("violence/graphic")
  private Boolean violencegraphic = null;

  public CreateModerationResponseCategories hate(Boolean hate) {
    this.hate = hate;
    return this;
  }

   /**
   * Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.
   * @return hate
  **/
  @Schema(required = true, description = "Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.")
  public Boolean isHate() {
    return hate;
  }

  public void setHate(Boolean hate) {
    this.hate = hate;
  }

  public CreateModerationResponseCategories hatethreatening(Boolean hatethreatening) {
    this.hatethreatening = hatethreatening;
    return this;
  }

   /**
   * Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.
   * @return hatethreatening
  **/
  @Schema(required = true, description = "Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.")
  public Boolean isHatethreatening() {
    return hatethreatening;
  }

  public void setHatethreatening(Boolean hatethreatening) {
    this.hatethreatening = hatethreatening;
  }

  public CreateModerationResponseCategories harassment(Boolean harassment) {
    this.harassment = harassment;
    return this;
  }

   /**
   * Content that expresses, incites, or promotes harassing language towards any target.
   * @return harassment
  **/
  @Schema(required = true, description = "Content that expresses, incites, or promotes harassing language towards any target.")
  public Boolean isHarassment() {
    return harassment;
  }

  public void setHarassment(Boolean harassment) {
    this.harassment = harassment;
  }

  public CreateModerationResponseCategories harassmentthreatening(Boolean harassmentthreatening) {
    this.harassmentthreatening = harassmentthreatening;
    return this;
  }

   /**
   * Harassment content that also includes violence or serious harm towards any target.
   * @return harassmentthreatening
  **/
  @Schema(required = true, description = "Harassment content that also includes violence or serious harm towards any target.")
  public Boolean isHarassmentthreatening() {
    return harassmentthreatening;
  }

  public void setHarassmentthreatening(Boolean harassmentthreatening) {
    this.harassmentthreatening = harassmentthreatening;
  }

  public CreateModerationResponseCategories selfHarm(Boolean selfHarm) {
    this.selfHarm = selfHarm;
    return this;
  }

   /**
   * Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.
   * @return selfHarm
  **/
  @Schema(required = true, description = "Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.")
  public Boolean isSelfHarm() {
    return selfHarm;
  }

  public void setSelfHarm(Boolean selfHarm) {
    this.selfHarm = selfHarm;
  }

  public CreateModerationResponseCategories selfHarmintent(Boolean selfHarmintent) {
    this.selfHarmintent = selfHarmintent;
    return this;
  }

   /**
   * Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.
   * @return selfHarmintent
  **/
  @Schema(required = true, description = "Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.")
  public Boolean isSelfHarmintent() {
    return selfHarmintent;
  }

  public void setSelfHarmintent(Boolean selfHarmintent) {
    this.selfHarmintent = selfHarmintent;
  }

  public CreateModerationResponseCategories selfHarminstructions(Boolean selfHarminstructions) {
    this.selfHarminstructions = selfHarminstructions;
    return this;
  }

   /**
   * Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.
   * @return selfHarminstructions
  **/
  @Schema(required = true, description = "Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.")
  public Boolean isSelfHarminstructions() {
    return selfHarminstructions;
  }

  public void setSelfHarminstructions(Boolean selfHarminstructions) {
    this.selfHarminstructions = selfHarminstructions;
  }

  public CreateModerationResponseCategories sexual(Boolean sexual) {
    this.sexual = sexual;
    return this;
  }

   /**
   * Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).
   * @return sexual
  **/
  @Schema(required = true, description = "Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).")
  public Boolean isSexual() {
    return sexual;
  }

  public void setSexual(Boolean sexual) {
    this.sexual = sexual;
  }

  public CreateModerationResponseCategories sexualminors(Boolean sexualminors) {
    this.sexualminors = sexualminors;
    return this;
  }

   /**
   * Sexual content that includes an individual who is under 18 years old.
   * @return sexualminors
  **/
  @Schema(required = true, description = "Sexual content that includes an individual who is under 18 years old.")
  public Boolean isSexualminors() {
    return sexualminors;
  }

  public void setSexualminors(Boolean sexualminors) {
    this.sexualminors = sexualminors;
  }

  public CreateModerationResponseCategories violence(Boolean violence) {
    this.violence = violence;
    return this;
  }

   /**
   * Content that depicts death, violence, or physical injury.
   * @return violence
  **/
  @Schema(required = true, description = "Content that depicts death, violence, or physical injury.")
  public Boolean isViolence() {
    return violence;
  }

  public void setViolence(Boolean violence) {
    this.violence = violence;
  }

  public CreateModerationResponseCategories violencegraphic(Boolean violencegraphic) {
    this.violencegraphic = violencegraphic;
    return this;
  }

   /**
   * Content that depicts death, violence, or physical injury in graphic detail.
   * @return violencegraphic
  **/
  @Schema(required = true, description = "Content that depicts death, violence, or physical injury in graphic detail.")
  public Boolean isViolencegraphic() {
    return violencegraphic;
  }

  public void setViolencegraphic(Boolean violencegraphic) {
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
    CreateModerationResponseCategories createModerationResponseCategories = (CreateModerationResponseCategories) o;
    return Objects.equals(this.hate, createModerationResponseCategories.hate) &&
        Objects.equals(this.hatethreatening, createModerationResponseCategories.hatethreatening) &&
        Objects.equals(this.harassment, createModerationResponseCategories.harassment) &&
        Objects.equals(this.harassmentthreatening, createModerationResponseCategories.harassmentthreatening) &&
        Objects.equals(this.selfHarm, createModerationResponseCategories.selfHarm) &&
        Objects.equals(this.selfHarmintent, createModerationResponseCategories.selfHarmintent) &&
        Objects.equals(this.selfHarminstructions, createModerationResponseCategories.selfHarminstructions) &&
        Objects.equals(this.sexual, createModerationResponseCategories.sexual) &&
        Objects.equals(this.sexualminors, createModerationResponseCategories.sexualminors) &&
        Objects.equals(this.violence, createModerationResponseCategories.violence) &&
        Objects.equals(this.violencegraphic, createModerationResponseCategories.violencegraphic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hate, hatethreatening, harassment, harassmentthreatening, selfHarm, selfHarmintent, selfHarminstructions, sexual, sexualminors, violence, violencegraphic);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseCategories {\n");
    
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
