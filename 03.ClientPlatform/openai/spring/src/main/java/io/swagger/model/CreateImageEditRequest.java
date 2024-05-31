package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateImageEditRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateImageEditRequest   {
  @JsonProperty("image")
  private Resource image = null;

  @JsonProperty("prompt")
  private String prompt = null;

  @JsonProperty("mask")
  private Resource mask = null;

  @JsonProperty("model")
  private AnyOfCreateImageEditRequestModel model = dall-e-2;

  @JsonProperty("n")
  private Integer n = 1;

  /**
   * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.
   */
  public enum SizeEnum {
    _256X256("256x256"),
    
    _512X512("512x512"),
    
    _1024X1024("1024x1024");

    private String value;

    SizeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SizeEnum fromValue(String text) {
      for (SizeEnum b : SizeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("size")
  private SizeEnum size = SizeEnum._1024X1024;

  /**
   * The format in which the generated images are returned. Must be one of `url` or `b64_json`. URLs are only valid for 60 minutes after the image has been generated.
   */
  public enum ResponseFormatEnum {
    URL("url"),
    
    B64_JSON("b64_json");

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
  private ResponseFormatEnum responseFormat = ResponseFormatEnum.URL;

  @JsonProperty("user")
  private String user = null;

  public CreateImageEditRequest image(Resource image) {
    this.image = image;
    return this;
  }

  /**
   * The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask.
   * @return image
   **/
  @Schema(required = true, description = "The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask.")
      @NotNull

    @Valid
    public Resource getImage() {
    return image;
  }

  public void setImage(Resource image) {
    this.image = image;
  }

  public CreateImageEditRequest prompt(String prompt) {
    this.prompt = prompt;
    return this;
  }

  /**
   * A text description of the desired image(s). The maximum length is 1000 characters.
   * @return prompt
   **/
  @Schema(example = "A cute baby sea otter wearing a beret", required = true, description = "A text description of the desired image(s). The maximum length is 1000 characters.")
      @NotNull

    public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public CreateImageEditRequest mask(Resource mask) {
    this.mask = mask;
    return this;
  }

  /**
   * An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where `image` should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as `image`.
   * @return mask
   **/
  @Schema(description = "An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where `image` should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as `image`.")
  
    @Valid
    public Resource getMask() {
    return mask;
  }

  public void setMask(Resource mask) {
    this.mask = mask;
  }

  public CreateImageEditRequest model(AnyOfCreateImageEditRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * The model to use for image generation. Only `dall-e-2` is supported at this time.
   * @return model
   **/
  @Schema(example = "dall-e-2", description = "The model to use for image generation. Only `dall-e-2` is supported at this time.")
  
    public AnyOfCreateImageEditRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfCreateImageEditRequestModel model) {
    this.model = model;
  }

  public CreateImageEditRequest n(Integer n) {
    this.n = n;
    return this;
  }

  /**
   * The number of images to generate. Must be between 1 and 10.
   * minimum: 1
   * maximum: 10
   * @return n
   **/
  @Schema(example = "1", description = "The number of images to generate. Must be between 1 and 10.")
  
  @Min(1) @Max(10)   public Integer getN() {
    return n;
  }

  public void setN(Integer n) {
    this.n = n;
  }

  public CreateImageEditRequest size(SizeEnum size) {
    this.size = size;
    return this;
  }

  /**
   * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.
   * @return size
   **/
  @Schema(example = "1024x1024", description = "The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.")
  
    public SizeEnum getSize() {
    return size;
  }

  public void setSize(SizeEnum size) {
    this.size = size;
  }

  public CreateImageEditRequest responseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
    return this;
  }

  /**
   * The format in which the generated images are returned. Must be one of `url` or `b64_json`. URLs are only valid for 60 minutes after the image has been generated.
   * @return responseFormat
   **/
  @Schema(example = "url", description = "The format in which the generated images are returned. Must be one of `url` or `b64_json`. URLs are only valid for 60 minutes after the image has been generated.")
  
    public ResponseFormatEnum getResponseFormat() {
    return responseFormat;
  }

  public void setResponseFormat(ResponseFormatEnum responseFormat) {
    this.responseFormat = responseFormat;
  }

  public CreateImageEditRequest user(String user) {
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
    CreateImageEditRequest createImageEditRequest = (CreateImageEditRequest) o;
    return Objects.equals(this.image, createImageEditRequest.image) &&
        Objects.equals(this.prompt, createImageEditRequest.prompt) &&
        Objects.equals(this.mask, createImageEditRequest.mask) &&
        Objects.equals(this.model, createImageEditRequest.model) &&
        Objects.equals(this.n, createImageEditRequest.n) &&
        Objects.equals(this.size, createImageEditRequest.size) &&
        Objects.equals(this.responseFormat, createImageEditRequest.responseFormat) &&
        Objects.equals(this.user, createImageEditRequest.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, prompt, mask, model, n, size, responseFormat, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateImageEditRequest {\n");
    
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    prompt: ").append(toIndentedString(prompt)).append("\n");
    sb.append("    mask: ").append(toIndentedString(mask)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
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
