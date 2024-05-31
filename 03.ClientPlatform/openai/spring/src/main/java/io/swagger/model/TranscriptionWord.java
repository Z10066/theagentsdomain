package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TranscriptionWord
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class TranscriptionWord   {
  @JsonProperty("word")
  private String word = null;

  @JsonProperty("start")
  private Float start = null;

  @JsonProperty("end")
  private Float end = null;

  public TranscriptionWord word(String word) {
    this.word = word;
    return this;
  }

  /**
   * The text content of the word.
   * @return word
   **/
  @Schema(required = true, description = "The text content of the word.")
      @NotNull

    public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public TranscriptionWord start(Float start) {
    this.start = start;
    return this;
  }

  /**
   * Start time of the word in seconds.
   * @return start
   **/
  @Schema(required = true, description = "Start time of the word in seconds.")
      @NotNull

    public Float getStart() {
    return start;
  }

  public void setStart(Float start) {
    this.start = start;
  }

  public TranscriptionWord end(Float end) {
    this.end = end;
    return this;
  }

  /**
   * End time of the word in seconds.
   * @return end
   **/
  @Schema(required = true, description = "End time of the word in seconds.")
      @NotNull

    public Float getEnd() {
    return end;
  }

  public void setEnd(Float end) {
    this.end = end;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TranscriptionWord transcriptionWord = (TranscriptionWord) o;
    return Objects.equals(this.word, transcriptionWord.word) &&
        Objects.equals(this.start, transcriptionWord.start) &&
        Objects.equals(this.end, transcriptionWord.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(word, start, end);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TranscriptionWord {\n");
    
    sb.append("    word: ").append(toIndentedString(word)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
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
