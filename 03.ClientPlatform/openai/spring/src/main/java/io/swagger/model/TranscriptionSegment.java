package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TranscriptionSegment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class TranscriptionSegment   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("seek")
  private Integer seek = null;

  @JsonProperty("start")
  private Float start = null;

  @JsonProperty("end")
  private Float end = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("tokens")
  @Valid
  private List<Integer> tokens = new ArrayList<Integer>();

  @JsonProperty("temperature")
  private Float temperature = null;

  @JsonProperty("avg_logprob")
  private Float avgLogprob = null;

  @JsonProperty("compression_ratio")
  private Float compressionRatio = null;

  @JsonProperty("no_speech_prob")
  private Float noSpeechProb = null;

  public TranscriptionSegment id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the segment.
   * @return id
   **/
  @Schema(required = true, description = "Unique identifier of the segment.")
      @NotNull

    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TranscriptionSegment seek(Integer seek) {
    this.seek = seek;
    return this;
  }

  /**
   * Seek offset of the segment.
   * @return seek
   **/
  @Schema(required = true, description = "Seek offset of the segment.")
      @NotNull

    public Integer getSeek() {
    return seek;
  }

  public void setSeek(Integer seek) {
    this.seek = seek;
  }

  public TranscriptionSegment start(Float start) {
    this.start = start;
    return this;
  }

  /**
   * Start time of the segment in seconds.
   * @return start
   **/
  @Schema(required = true, description = "Start time of the segment in seconds.")
      @NotNull

    public Float getStart() {
    return start;
  }

  public void setStart(Float start) {
    this.start = start;
  }

  public TranscriptionSegment end(Float end) {
    this.end = end;
    return this;
  }

  /**
   * End time of the segment in seconds.
   * @return end
   **/
  @Schema(required = true, description = "End time of the segment in seconds.")
      @NotNull

    public Float getEnd() {
    return end;
  }

  public void setEnd(Float end) {
    this.end = end;
  }

  public TranscriptionSegment text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Text content of the segment.
   * @return text
   **/
  @Schema(required = true, description = "Text content of the segment.")
      @NotNull

    public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public TranscriptionSegment tokens(List<Integer> tokens) {
    this.tokens = tokens;
    return this;
  }

  public TranscriptionSegment addTokensItem(Integer tokensItem) {
    this.tokens.add(tokensItem);
    return this;
  }

  /**
   * Array of token IDs for the text content.
   * @return tokens
   **/
  @Schema(required = true, description = "Array of token IDs for the text content.")
      @NotNull

    public List<Integer> getTokens() {
    return tokens;
  }

  public void setTokens(List<Integer> tokens) {
    this.tokens = tokens;
  }

  public TranscriptionSegment temperature(Float temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * Temperature parameter used for generating the segment.
   * @return temperature
   **/
  @Schema(required = true, description = "Temperature parameter used for generating the segment.")
      @NotNull

    public Float getTemperature() {
    return temperature;
  }

  public void setTemperature(Float temperature) {
    this.temperature = temperature;
  }

  public TranscriptionSegment avgLogprob(Float avgLogprob) {
    this.avgLogprob = avgLogprob;
    return this;
  }

  /**
   * Average logprob of the segment. If the value is lower than -1, consider the logprobs failed.
   * @return avgLogprob
   **/
  @Schema(required = true, description = "Average logprob of the segment. If the value is lower than -1, consider the logprobs failed.")
      @NotNull

    public Float getAvgLogprob() {
    return avgLogprob;
  }

  public void setAvgLogprob(Float avgLogprob) {
    this.avgLogprob = avgLogprob;
  }

  public TranscriptionSegment compressionRatio(Float compressionRatio) {
    this.compressionRatio = compressionRatio;
    return this;
  }

  /**
   * Compression ratio of the segment. If the value is greater than 2.4, consider the compression failed.
   * @return compressionRatio
   **/
  @Schema(required = true, description = "Compression ratio of the segment. If the value is greater than 2.4, consider the compression failed.")
      @NotNull

    public Float getCompressionRatio() {
    return compressionRatio;
  }

  public void setCompressionRatio(Float compressionRatio) {
    this.compressionRatio = compressionRatio;
  }

  public TranscriptionSegment noSpeechProb(Float noSpeechProb) {
    this.noSpeechProb = noSpeechProb;
    return this;
  }

  /**
   * Probability of no speech in the segment. If the value is higher than 1.0 and the `avg_logprob` is below -1, consider this segment silent.
   * @return noSpeechProb
   **/
  @Schema(required = true, description = "Probability of no speech in the segment. If the value is higher than 1.0 and the `avg_logprob` is below -1, consider this segment silent.")
      @NotNull

    public Float getNoSpeechProb() {
    return noSpeechProb;
  }

  public void setNoSpeechProb(Float noSpeechProb) {
    this.noSpeechProb = noSpeechProb;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TranscriptionSegment transcriptionSegment = (TranscriptionSegment) o;
    return Objects.equals(this.id, transcriptionSegment.id) &&
        Objects.equals(this.seek, transcriptionSegment.seek) &&
        Objects.equals(this.start, transcriptionSegment.start) &&
        Objects.equals(this.end, transcriptionSegment.end) &&
        Objects.equals(this.text, transcriptionSegment.text) &&
        Objects.equals(this.tokens, transcriptionSegment.tokens) &&
        Objects.equals(this.temperature, transcriptionSegment.temperature) &&
        Objects.equals(this.avgLogprob, transcriptionSegment.avgLogprob) &&
        Objects.equals(this.compressionRatio, transcriptionSegment.compressionRatio) &&
        Objects.equals(this.noSpeechProb, transcriptionSegment.noSpeechProb);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, seek, start, end, text, tokens, temperature, avgLogprob, compressionRatio, noSpeechProb);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TranscriptionSegment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    seek: ").append(toIndentedString(seek)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    tokens: ").append(toIndentedString(tokens)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    avgLogprob: ").append(toIndentedString(avgLogprob)).append("\n");
    sb.append("    compressionRatio: ").append(toIndentedString(compressionRatio)).append("\n");
    sb.append("    noSpeechProb: ").append(toIndentedString(noSpeechProb)).append("\n");
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
