package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateTranscriptionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateTranscriptionRequest   {
  @JsonProperty("file")
  private Resource file = null;

  @JsonProperty("model")
  private AnyOfCreateTranscriptionRequestModel model = null;

  @JsonProperty("language")
  private String language = null;

  @JsonProperty("prompt")
  private String prompt = null;

  /**
   * The format of the transcript output, in one of these options: `json`, `text`, `srt`, `verbose_json`, or `vtt`. 
   */
  public enum ResponseFormatEnum {
    JSON("json"),
    
    TEXT("text"),
    
    SRT("srt"),
    
    VERBOSE_JSON("verbose_json"),
    
    VTT("vtt");

    private String value;

    ResponseFormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResponseFormatEnum fromValue(String text) {
      for (ResponseFormatEnum b : ResponseFormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("response_format")
  private ResponseFormatEnum responseFormat = ResponseFormatEnum.JSON;

  @JsonProperty("temperature")
  private BigDecimal temperature = new BigDecimal(0);

  /**
   * Gets or Sets timestampGranularities
   */
  public enum TimestampGranularitiesEnum {
    WORD("word"),
    
    SEGMENT("segment");

    private String value;

    TimestampGranularitiesEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TimestampGranularitiesEnum fromValue(String text) {
      for (TimestampGranularitiesEnum b : TimestampGranularitiesEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("timestamp_granularities[]")
  @Valid
  private List<TimestampGranularitiesEnum> timestampGranularities = null;

  public CreateTranscriptionRequest file(Resource file) {
    this.file = file;
    return this;
  }

  /**
   * The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm. 
   * @return file
   **/
  @Schema(required = true, description = "The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm. ")
      @NotNull

    @Valid
    public Resource getFile() {
    return file;
  }

  public void setFile(Resource file) {
    this.file = file;
  }

  public CreateTranscriptionRequest model(AnyOfCreateTranscriptionRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * ID of the model to use. Only `whisper-1` (which is powered by our open source Whisper V2 model) is currently available. 
   * @return model
   **/
  @Schema(example = "whisper-1", required = true, description = "ID of the model to use. Only `whisper-1` (which is powered by our open source Whisper V2 model) is currently available. ")
      @NotNull

    public AnyOfCreateTranscriptionRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfCreateTranscriptionRequestModel model) {
    this.model = model;
  }

  public CreateTranscriptionRequest language(String language) {
    this.language = language;
    return this;
  }

  /**
   * The language of the input audio. Supplying the input language in [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy and latency. 
   * @return language
   **/
  @Schema(description = "The language of the input audio. Supplying the input language in [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy and latency. ")
  
    public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public CreateTranscriptionRequest prompt(String prompt) {
    this.prompt = prompt;
    return this;
  }

  /**
   * An optional text to guide the model's style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should match the audio language. 
   * @return prompt
   **/
  @Schema(description = "An optional text to guide the model's style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should match the audio language. ")
  
    public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public CreateTranscriptionRequest responseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * The format of the transcript output, in one of these options: `json`, `text`, `srt`, `verbose_json`, or `vtt`. 
   * @return responseFormat
   **/
  @Schema(description = "The format of the transcript output, in one of these options: `json`, `text`, `srt`, `verbose_json`, or `vtt`. ")
  
    public ResponseFormatEnum getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateTranscriptionRequest temperature(BigDecimal temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit. 
   * @return temperature
   **/
  @Schema(description = "The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit. ")
  
    @Valid
    public BigDecimal getTemperature() {
    return temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = temperature;
  }

  public CreateTranscriptionRequest timestampGranularities(List<TimestampGranularitiesEnum> timestampGranularities) {
    this.timestampGranularities = timestampGranularities;
    return this;
  }

  public CreateTranscriptionRequest addTimestampGranularitiesItem(TimestampGranularitiesEnum timestampGranularitiesItem) {
    if (this.timestampGranularities == null) {
      this.timestampGranularities = new ArrayList<TimestampGranularitiesEnum>();
    }
    this.timestampGranularities.add(timestampGranularitiesItem);
    return this;
  }

  /**
   * The timestamp granularities to populate for this transcription. `response_format` must be set `verbose_json` to use timestamp granularities. Either or both of these options are supported: `word`, or `segment`. Note: There is no additional latency for segment timestamps, but generating word timestamps incurs additional latency. 
   * @return timestampGranularities
   **/
  @Schema(description = "The timestamp granularities to populate for this transcription. `response_format` must be set `verbose_json` to use timestamp granularities. Either or both of these options are supported: `word`, or `segment`. Note: There is no additional latency for segment timestamps, but generating word timestamps incurs additional latency. ")
  
    public List<TimestampGranularitiesEnum> getTimestampGranularities() {
    return timestampGranularities;
  }

  public void setTimestampGranularities(List<TimestampGranularitiesEnum> timestampGranularities) {
    this.timestampGranularities = timestampGranularities;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTranscriptionRequest createTranscriptionRequest = (CreateTranscriptionRequest) o;
    return Objects.equals(this.file, createTranscriptionRequest.file) &&
        Objects.equals(this.model, createTranscriptionRequest.model) &&
        Objects.equals(this.language, createTranscriptionRequest.language) &&
        Objects.equals(this.prompt, createTranscriptionRequest.prompt) &&
        Objects.equals(this.responseFormat, createTranscriptionRequest.responseFormat) &&
        Objects.equals(this.temperature, createTranscriptionRequest.temperature) &&
        Objects.equals(this.timestampGranularities, createTranscriptionRequest.timestampGranularities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, model, language, prompt, responseFormat, temperature, timestampGranularities);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTranscriptionRequest {\n");
    
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    timestampGranularities: ").append(toIndentedString(timestampGranularities)).append("\n");
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
