package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.CreateModerationResponseCategories;
import io.swagger.model.CreateModerationResponseCategoryScores;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateModerationResponseResults
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateModerationResponseResults   {
  @JsonProperty("flagged")
  private Boolean flagged = null;

  @JsonProperty("categories")
  private CreateModerationResponseCategories categories = null;

  @JsonProperty("category_scores")
  private CreateModerationResponseCategoryScores categoryScores = null;

  public CreateModerationResponseResults flagged(Boolean flagged) {
    this.flagged = flagged;
    return this;
  }

  /**
   * Whether any of the below categories are flagged.
   * @return flagged
   **/
  @Schema(required = true, description = "Whether any of the below categories are flagged.")
      @NotNull

    public Boolean isFlagged() {
    return flagged;
  }

  public void setFlagged(Boolean flagged) {
    this.flagged = flagged;
  }

  public CreateModerationResponseResults categories(CreateModerationResponseCategories categories) {
    this.categories = categories;
    return this;
  }

  /**
   * Get categories
   * @return categories
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CreateModerationResponseCategories getCategories() {
    return categories;
  }

  public void setCategories(CreateModerationResponseCategories categories) {
    this.categories = categories;
  }

  public CreateModerationResponseResults categoryScores(CreateModerationResponseCategoryScores categoryScores) {
    this.categoryScores = categoryScores;
    return this;
  }

  /**
   * Get categoryScores
   * @return categoryScores
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CreateModerationResponseCategoryScores getCategoryScores() {
    return categoryScores;
  }

  public void setCategoryScores(CreateModerationResponseCategoryScores categoryScores) {
    this.categoryScores = categoryScores;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateModerationResponseResults createModerationResponseResults = (CreateModerationResponseResults) o;
    return Objects.equals(this.flagged, createModerationResponseResults.flagged) &&
        Objects.equals(this.categories, createModerationResponseResults.categories) &&
        Objects.equals(this.categoryScores, createModerationResponseResults.categoryScores);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flagged, categories, categoryScores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateModerationResponseResults {\n");
    
    sb.append("    flagged: ").append(toIndentedString(flagged)).append("\n");
    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
    sb.append("    categoryScores: ").append(toIndentedString(categoryScores)).append("\n");
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
