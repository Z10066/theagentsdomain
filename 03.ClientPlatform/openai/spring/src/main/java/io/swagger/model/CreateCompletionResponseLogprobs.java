package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateCompletionResponseLogprobs
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateCompletionResponseLogprobs   {
  @JsonProperty("text_offset")
  @Valid
  private List<Integer> textOffset = null;

  @JsonProperty("token_logprobs")
  @Valid
  private List<BigDecimal> tokenLogprobs = null;

  @JsonProperty("tokens")
  @Valid
  private List<String> tokens = null;

  @JsonProperty("top_logprobs")
  @Valid
  private List<Map<String, BigDecimal>> topLogprobs = null;

  public CreateCompletionResponseLogprobs textOffset(List<Integer> textOffset) {
    this.textOffset = textOffset;
    return this;
  }

  public CreateCompletionResponseLogprobs addTextOffsetItem(Integer textOffsetItem) {
    if (this.textOffset == null) {
      this.textOffset = new ArrayList<Integer>();
    }
    this.textOffset.add(textOffsetItem);
    return this;
  }

  /**
   * Get textOffset
   * @return textOffset
   **/
  @Schema(description = "")
  
    public List<Integer> getTextOffset() {
    return textOffset;
  }

  public void setTextOffset(List<Integer> textOffset) {
    this.textOffset = textOffset;
  }

  public CreateCompletionResponseLogprobs tokenLogprobs(List<BigDecimal> tokenLogprobs) {
    this.tokenLogprobs = tokenLogprobs;
    return this;
  }

  public CreateCompletionResponseLogprobs addTokenLogprobsItem(BigDecimal tokenLogprobsItem) {
    if (this.tokenLogprobs == null) {
      this.tokenLogprobs = new ArrayList<BigDecimal>();
    }
    this.tokenLogprobs.add(tokenLogprobsItem);
    return this;
  }

  /**
   * Get tokenLogprobs
   * @return tokenLogprobs
   **/
  @Schema(description = "")
      @Valid
    public List<BigDecimal> getTokenLogprobs() {
    return tokenLogprobs;
  }

  public void setTokenLogprobs(List<BigDecimal> tokenLogprobs) {
    this.tokenLogprobs = tokenLogprobs;
  }

  public CreateCompletionResponseLogprobs tokens(List<String> tokens) {
    this.tokens = tokens;
    return this;
  }

  public CreateCompletionResponseLogprobs addTokensItem(String tokensItem) {
    if (this.tokens == null) {
      this.tokens = new ArrayList<String>();
    }
    this.tokens.add(tokensItem);
    return this;
  }

  /**
   * Get tokens
   * @return tokens
   **/
  @Schema(description = "")
  
    public List<String> getTokens() {
    return tokens;
  }

  public void setTokens(List<String> tokens) {
    this.tokens = tokens;
  }

  public CreateCompletionResponseLogprobs topLogprobs(List<Map<String, BigDecimal>> topLogprobs) {
    this.topLogprobs = topLogprobs;
    return this;
  }

  public CreateCompletionResponseLogprobs addTopLogprobsItem(Map<String, BigDecimal> topLogprobsItem) {
    if (this.topLogprobs == null) {
      this.topLogprobs = new ArrayList<Map<String, BigDecimal>>();
    }
    this.topLogprobs.add(topLogprobsItem);
    return this;
  }

  /**
   * Get topLogprobs
   * @return topLogprobs
   **/
  @Schema(description = "")
      @Valid
    public List<Map<String, BigDecimal>> getTopLogprobs() {
    return topLogprobs;
  }

  public void setTopLogprobs(List<Map<String, BigDecimal>> topLogprobs) {
    this.topLogprobs = topLogprobs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCompletionResponseLogprobs createCompletionResponseLogprobs = (CreateCompletionResponseLogprobs) o;
    return Objects.equals(this.textOffset, createCompletionResponseLogprobs.textOffset) &&
        Objects.equals(this.tokenLogprobs, createCompletionResponseLogprobs.tokenLogprobs) &&
        Objects.equals(this.tokens, createCompletionResponseLogprobs.tokens) &&
        Objects.equals(this.topLogprobs, createCompletionResponseLogprobs.topLogprobs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(textOffset, tokenLogprobs, tokens, topLogprobs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCompletionResponseLogprobs {\n");
    
    sb.append("    textOffset: ").append(toIndentedString(textOffset)).append("\n");
    sb.append("    tokenLogprobs: ").append(toIndentedString(tokenLogprobs)).append("\n");
    sb.append("    tokens: ").append(toIndentedString(tokens)).append("\n");
    sb.append("    topLogprobs: ").append(toIndentedString(topLogprobs)).append("\n");
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
