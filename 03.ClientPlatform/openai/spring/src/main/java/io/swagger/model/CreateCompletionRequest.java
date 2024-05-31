package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.ChatCompletionStreamOptions;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateCompletionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateCompletionRequest   {
  @JsonProperty("model")
  private AnyOfCreateCompletionRequestModel model = null;

  @JsonProperty("prompt")
  private OneOfCreateCompletionRequestPrompt prompt = <|endoftext|>;

  @JsonProperty("best_of")
  private Integer bestOf = 1;

  @JsonProperty("echo")
  private Boolean echo = false;

  @JsonProperty("frequency_penalty")
  private BigDecimal frequencyPenalty = new BigDecimal(0);

  @JsonProperty("logit_bias")
  @Valid
  private Map<String, Integer> logitBias = null;

  @JsonProperty("logprobs")
  private Integer logprobs = null;

  @JsonProperty("max_tokens")
  private Integer maxTokens = 16;

  @JsonProperty("n")
  private Integer n = 1;

  @JsonProperty("presence_penalty")
  private BigDecimal presencePenalty = new BigDecimal(0);

  @JsonProperty("seed")
  private Integer seed = null;

  @JsonProperty("stop")
  private OneOfCreateCompletionRequestStop stop = null;

  @JsonProperty("stream")
  private Boolean stream = false;

  @JsonProperty("stream_options")
  private ChatCompletionStreamOptions streamOptions = null;

  @JsonProperty("suffix")
  private String suffix = null;

  @JsonProperty("temperature")
  private BigDecimal temperature = new BigDecimal(1);

  @JsonProperty("top_p")
  private BigDecimal topP = new BigDecimal(1);

  @JsonProperty("user")
  private String user = null;

  public CreateCompletionRequest model(AnyOfCreateCompletionRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. 
   * @return model
   **/
  @Schema(required = true, description = "ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them. ")
      @NotNull

    public AnyOfCreateCompletionRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfCreateCompletionRequestModel model) {
    this.model = model;
  }

  public CreateCompletionRequest prompt(OneOfCreateCompletionRequestPrompt prompt) {
    this.prompt = prompt;
    return this;
  }

  /**
   * The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens, or array of token arrays.  Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document. 
   * @return prompt
   **/
  @Schema(required = true, description = "The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens, or array of token arrays.  Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document. ")
      @NotNull

    public OneOfCreateCompletionRequestPrompt getPrompt() {
    return prompt;
  }

  public void setPrompt(OneOfCreateCompletionRequestPrompt prompt) {
    this.prompt = prompt;
  }

  public CreateCompletionRequest bestOf(Integer bestOf) {
    this.bestOf = bestOf;
    return this;
  }

  /**
   * Generates `best_of` completions server-side and returns the \"best\" (the one with the highest log probability per token). Results cannot be streamed.  When used with `n`, `best_of` controls the number of candidate completions and `n` specifies how many to return – `best_of` must be greater than `n`.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. 
   * minimum: 0
   * maximum: 20
   * @return bestOf
   **/
  @Schema(description = "Generates `best_of` completions server-side and returns the \"best\" (the one with the highest log probability per token). Results cannot be streamed.  When used with `n`, `best_of` controls the number of candidate completions and `n` specifies how many to return – `best_of` must be greater than `n`.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. ")
  
  @Min(0) @Max(20)   public Integer getBestOf() {
    return bestOf;
  }

  public void setBestOf(Integer bestOf) {
    this.bestOf = bestOf;
  }

  public CreateCompletionRequest echo(Boolean echo) {
    this.echo = echo;
    return this;
  }

  /**
   * Echo back the prompt in addition to the completion 
   * @return echo
   **/
  @Schema(description = "Echo back the prompt in addition to the completion ")
  
    public Boolean isEcho() {
    return echo;
  }

  public void setEcho(Boolean echo) {
    this.echo = echo;
  }

  public CreateCompletionRequest frequencyPenalty(BigDecimal frequencyPenalty) {
    this.frequencyPenalty = frequencyPenalty;
    return this;
  }

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return frequencyPenalty
   **/
  @Schema(description = "Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) ")
  
    @Valid
  @DecimalMin("-2") @DecimalMax("2")   public BigDecimal getFrequencyPenalty() {
    return frequencyPenalty;
  }

  public void setFrequencyPenalty(BigDecimal frequencyPenalty) {
    this.frequencyPenalty = frequencyPenalty;
  }

  public CreateCompletionRequest logitBias(Map<String, Integer> logitBias) {
    this.logitBias = logitBias;
    return this;
  }

  public CreateCompletionRequest putLogitBiasItem(String key, Integer logitBiasItem) {
    if (this.logitBias == null) {
      this.logitBias = new HashMap<String, Integer>();
    }
    this.logitBias.put(key, logitBiasItem);
    return this;
  }

  /**
   * Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this [tokenizer tool](/tokenizer?view=bpe) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.  As an example, you can pass `{\"50256\": -100}` to prevent the <|endoftext|> token from being generated. 
   * @return logitBias
   **/
  @Schema(description = "Modify the likelihood of specified tokens appearing in the completion.  Accepts a JSON object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this [tokenizer tool](/tokenizer?view=bpe) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.  As an example, you can pass `{\"50256\": -100}` to prevent the <|endoftext|> token from being generated. ")
  
    public Map<String, Integer> getLogitBias() {
    return logitBias;
  }

  public void setLogitBias(Map<String, Integer> logitBias) {
    this.logitBias = logitBias;
  }

  public CreateCompletionRequest logprobs(Integer logprobs) {
    this.logprobs = logprobs;
    return this;
  }

  /**
   * Include the log probabilities on the `logprobs` most likely output tokens, as well the chosen tokens. For example, if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token, so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5. 
   * minimum: 0
   * maximum: 5
   * @return logprobs
   **/
  @Schema(description = "Include the log probabilities on the `logprobs` most likely output tokens, as well the chosen tokens. For example, if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token, so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5. ")
  
  @Min(0) @Max(5)   public Integer getLogprobs() {
    return logprobs;
  }

  public void setLogprobs(Integer logprobs) {
    this.logprobs = logprobs;
  }

  public CreateCompletionRequest maxTokens(Integer maxTokens) {
    this.maxTokens = maxTokens;
    return this;
  }

  /**
   * The maximum number of [tokens](/tokenizer) that can be generated in the completion.  The token count of your prompt plus `max_tokens` cannot exceed the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. 
   * minimum: 0
   * @return maxTokens
   **/
  @Schema(example = "16", description = "The maximum number of [tokens](/tokenizer) that can be generated in the completion.  The token count of your prompt plus `max_tokens` cannot exceed the model's context length. [Example Python code](https://cookbook.openai.com/examples/how_to_count_tokens_with_tiktoken) for counting tokens. ")
  
  @Min(0)  public Integer getMaxTokens() {
    return maxTokens;
  }

  public void setMaxTokens(Integer maxTokens) {
    this.maxTokens = maxTokens;
  }

  public CreateCompletionRequest n(Integer n) {
    this.n = n;
    return this;
  }

  /**
   * How many completions to generate for each prompt.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. 
   * minimum: 1
   * maximum: 128
   * @return n
   **/
  @Schema(example = "1", description = "How many completions to generate for each prompt.  **Note:** Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for `max_tokens` and `stop`. ")
  
  @Min(1) @Max(128)   public Integer getN() {
    return n;
  }

  public void setN(Integer n) {
    this.n = n;
  }

  public CreateCompletionRequest presencePenalty(BigDecimal presencePenalty) {
    this.presencePenalty = presencePenalty;
    return this;
  }

  /**
   * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) 
   * minimum: -2
   * maximum: 2
   * @return presencePenalty
   **/
  @Schema(description = "Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/guides/text-generation/parameter-details) ")
  
    @Valid
  @DecimalMin("-2") @DecimalMax("2")   public BigDecimal getPresencePenalty() {
    return presencePenalty;
  }

  public void setPresencePenalty(BigDecimal presencePenalty) {
    this.presencePenalty = presencePenalty;
  }

  public CreateCompletionRequest seed(Integer seed) {
    this.seed = seed;
    return this;
  }

  /**
   * If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result.  Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. 
   * minimum: -9223372036854775808
   * maximum: 9223372036854775807
   * @return seed
   **/
  @Schema(description = "If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same `seed` and parameters should return the same result.  Determinism is not guaranteed, and you should refer to the `system_fingerprint` response parameter to monitor changes in the backend. ")
  
  @Min(-9223372036854775808) @Max(9223372036854775807)   public Integer getSeed() {
    return seed;
  }

  public void setSeed(Integer seed) {
    this.seed = seed;
  }

  public CreateCompletionRequest stop(OneOfCreateCompletionRequestStop stop) {
    this.stop = stop;
    return this;
  }

  /**
   * Up to 4 sequences where the API will stop generating further tokens. The returned text will not contain the stop sequence. 
   * @return stop
   **/
  @Schema(description = "Up to 4 sequences where the API will stop generating further tokens. The returned text will not contain the stop sequence. ")
  
    public OneOfCreateCompletionRequestStop getStop() {
    return stop;
  }

  public void setStop(OneOfCreateCompletionRequestStop stop) {
    this.stop = stop;
  }

  public CreateCompletionRequest stream(Boolean stream) {
    this.stream = stream;
    return this;
  }

  /**
   * Whether to stream back partial progress. If set, tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). 
   * @return stream
   **/
  @Schema(description = "Whether to stream back partial progress. If set, tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message. [Example Python code](https://cookbook.openai.com/examples/how_to_stream_completions). ")
  
    public Boolean isStream() {
    return stream;
  }

  public void setStream(Boolean stream) {
    this.stream = stream;
  }

  public CreateCompletionRequest streamOptions(ChatCompletionStreamOptions streamOptions) {
    this.streamOptions = streamOptions;
    return this;
  }

  /**
   * Get streamOptions
   * @return streamOptions
   **/
  @Schema(description = "")
  
    @Valid
    public ChatCompletionStreamOptions getStreamOptions() {
    return streamOptions;
  }

  public void setStreamOptions(ChatCompletionStreamOptions streamOptions) {
    this.streamOptions = streamOptions;
  }

  public CreateCompletionRequest suffix(String suffix) {
    this.suffix = suffix;
    return this;
  }

  /**
   * The suffix that comes after a completion of inserted text.  This parameter is only supported for `gpt-3.5-turbo-instruct`. 
   * @return suffix
   **/
  @Schema(example = "test.", description = "The suffix that comes after a completion of inserted text.  This parameter is only supported for `gpt-3.5-turbo-instruct`. ")
  
    public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public CreateCompletionRequest temperature(BigDecimal temperature) {
    this.temperature = temperature;
    return this;
  }

  /**
   * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.  We generally recommend altering this or `top_p` but not both. 
   * minimum: 0
   * maximum: 2
   * @return temperature
   **/
  @Schema(example = "1", description = "What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.  We generally recommend altering this or `top_p` but not both. ")
  
    @Valid
  @DecimalMin("0") @DecimalMax("2")   public BigDecimal getTemperature() {
    return temperature;
  }

  public void setTemperature(BigDecimal temperature) {
    this.temperature = temperature;
  }

  public CreateCompletionRequest topP(BigDecimal topP) {
    this.topP = topP;
    return this;
  }

  /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or `temperature` but not both. 
   * minimum: 0
   * maximum: 1
   * @return topP
   **/
  @Schema(example = "1", description = "An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or `temperature` but not both. ")
  
    @Valid
  @DecimalMin("0") @DecimalMax("1")   public BigDecimal getTopP() {
    return topP;
  }

  public void setTopP(BigDecimal topP) {
    this.topP = topP;
  }

  public CreateCompletionRequest user(String user) {
    this.user = user;
    return this;
  }

  /**
   * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). 
   * @return user
   **/
  @Schema(example = "user-1234", description = "A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids). ")
  
    public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCompletionRequest createCompletionRequest = (CreateCompletionRequest) o;
    return Objects.equals(this.model, createCompletionRequest.model) &&
        Objects.equals(this.prompt, createCompletionRequest.prompt) &&
        Objects.equals(this.bestOf, createCompletionRequest.bestOf) &&
        Objects.equals(this.echo, createCompletionRequest.echo) &&
        Objects.equals(this.frequencyPenalty, createCompletionRequest.frequencyPenalty) &&
        Objects.equals(this.logitBias, createCompletionRequest.logitBias) &&
        Objects.equals(this.logprobs, createCompletionRequest.logprobs) &&
        Objects.equals(this.maxTokens, createCompletionRequest.maxTokens) &&
        Objects.equals(this.n, createCompletionRequest.n) &&
        Objects.equals(this.presencePenalty, createCompletionRequest.presencePenalty) &&
        Objects.equals(this.seed, createCompletionRequest.seed) &&
        Objects.equals(this.stop, createCompletionRequest.stop) &&
        Objects.equals(this.stream, createCompletionRequest.stream) &&
        Objects.equals(this.streamOptions, createCompletionRequest.streamOptions) &&
        Objects.equals(this.suffix, createCompletionRequest.suffix) &&
        Objects.equals(this.temperature, createCompletionRequest.temperature) &&
        Objects.equals(this.topP, createCompletionRequest.topP) &&
        Objects.equals(this.user, createCompletionRequest.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, prompt, bestOf, echo, frequencyPenalty, logitBias, logprobs, maxTokens, n, presencePenalty, seed, stop, stream, streamOptions, suffix, temperature, topP, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCompletionRequest {\n");
    
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    bestOf: ").append(toIndentedString(bestOf)).append("\n");
    sb.append("    echo: ").append(toIndentedString(echo)).append("\n");
    sb.append("    frequencyPenalty: ").append(toIndentedString(frequencyPenalty)).append("\n");
    sb.append("    logitBias: ").append(toIndentedString(logitBias)).append("\n");
    sb.append("    logprobs: ").append(toIndentedString(logprobs)).append("\n");
    sb.append("    maxTokens: ").append(toIndentedString(maxTokens)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    presencePenalty: ").append(toIndentedString(presencePenalty)).append("\n");
    sb.append("    seed: ").append(toIndentedString(seed)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    stream: ").append(toIndentedString(stream)).append("\n");
    sb.append("    streamOptions: ").append(toIndentedString(streamOptions)).append("\n");
    sb.append("    suffix: ").append(toIndentedString(suffix)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    topP: ").append(toIndentedString(topP)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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