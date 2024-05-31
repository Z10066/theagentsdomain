package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateTranslationRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateTranslationRequest   {
  @JsonProperty("file")
  private Resource file = null;

  @JsonProperty("model")
  private AnyOfCreateTranslationRequestModel model = null;

  @JsonProperty("prompt")
  private String prompt = null;

  @JsonProperty("response_format")
  private String responseFormat = "json";

  @JsonProperty("temperature")
  private BigDecimal temperature = new BigDecimal(0);

  public CreateTranslationRequest file(Resource file) {
    this.file = file;
    return this;
  }

  /**
   * The audio file object (not file name) translate, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm. 
   * @return file
   **/
  @Schema(required = true, description = "The audio file object (not file name) translate, in one of these formats: flac, mp3, mp4, mpeg, mpga, m4a, ogg, wav, or webm. ")
      @NotNull

    @Valid
    public Resource getFile() {
    return file;
  }

  public void setFile(Resource file) {
    this.file = file;
  }

  public CreateTranslationRequest model(AnyOfCreateTranslationRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * ID of the model to use. Only `whisper-1` (which is powered by our open source Whisper V2 model) is currently available. 
   * @return model
   **/
  @Schema(example = "whisper-1", required = true, description = "ID of the model to use. Only `whisper-1` (which is powered by our open source Whisper V2 model) is currently available. ")
      @NotNull

    public AnyOfCreateTranslationRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfCreateTranslationRequestModel model) {
    this.model = model;
  }

  public CreateTranslationRequest prompt(String prompt) {
    this.prompt = prompt;
    return this;
  }

  /**
   * An optional text to guide the model's style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should be in English. 
   * @return prompt
   **/
  @Schema(description = "An optional text to guide the model's style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should be in English. ")
  
    public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public CreateTranslationRequest responseFormat(String responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * The format of the transcript output, in one of these options: `json`, `text`, `srt`, `verbose_json`, or `vtt`. 
   * @return responseFormat
   **/
  @Schema(description = "The format of the transcript output, in one of these options: `json`, `text`, `srt`, `verbose_json`, or `vtt`. ")
  
    public String getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(String responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateTranslationRequest temperature(BigDecimal temperature) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTranslationRequest createTranslationRequest = (CreateTranslationRequest) o;
    return Objects.equals(this.file, createTranslationRequest.file) &&
        Objects.equals(this.model, createTranslationRequest.model) &&
        Objects.equals(this.prompt, createTranslationRequest.prompt) &&
        Objects.equals(this.responseFormat, createTranslationRequest.responseFormat) &&
        Objects.equals(this.temperature, createTranslationRequest.temperature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, model, prompt, responseFormat, temperature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTranslationRequest {\n");
    
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
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
