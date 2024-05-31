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
 * CreateImageVariationRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")


public class CreateImageVariationRequest   {
  @JsonProperty("image")
  private Resource image = null;

  @JsonProperty("model")
  private AnyOfCreateImageVariationRequestModel model = dall-e-2;

  @JsonProperty("n")
  private Integer n = 1;

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

  @JsonProperty("user")
  private String user = null;

  public CreateImageVariationRequest image(Resource image) {
    this.image = image;
    return this;
  }

  /**
   * The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.
   * @return image
   **/
  @Schema(required = true, description = "The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.")
      @NotNull

    @Valid
    public Resource getImage() {
    return image;
  }

  public void setImage(Resource image) {
    this.image = image;
  }

  public CreateImageVariationRequest model(AnyOfCreateImageVariationRequestModel model) {
    this.model = model;
    return this;
  }

  /**
   * The model to use for image generation. Only `dall-e-2` is supported at this time.
   * @return model
   **/
  @Schema(example = "dall-e-2", description = "The model to use for image generation. Only `dall-e-2` is supported at this time.")
  
    public AnyOfCreateImageVariationRequestModel getModel() {
    return model;
  }

  public void setModel(AnyOfCreateImageVariationRequestModel model) {
    this.model = model;
  }

  public CreateImageVariationRequest n(Integer n) {
    this.n = n;
    return this;
  }

  /**
   * The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.
   * minimum: 1
   * maximum: 10
   * @return n
   **/
  @Schema(example = "1", description = "The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.")
  
  @Min(1) @Max(10)   public Integer getN() {
    return n;
  }

  public void setN(Integer n) {
    this.n = n;
  }

  public CreateImageVariationRequest responseFormat(ResponseFormatEnum responseFormat) {
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

  public CreateImageVariationRequest size(SizeEnum size) {
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

  public CreateImageVariationRequest user(String user) {
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
    CreateImageVariationRequest createImageVariationRequest = (CreateImageVariationRequest) o;
    return Objects.equals(this.image, createImageVariationRequest.image) &&
        Objects.equals(this.model, createImageVariationRequest.model) &&
        Objects.equals(this.n, createImageVariationRequest.n) &&
        Objects.equals(this.responseFormat, createImageVariationRequest.responseFormat) &&
        Objects.equals(this.size, createImageVariationRequest.size) &&
        Objects.equals(this.user, createImageVariationRequest.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, model, n, responseFormat, size, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateImageVariationRequest {\n");
    
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    n: ").append(toIndentedString(n)).append("\n");
    sb.append("    responseFormat: ").append(toIndentedString(responseFormat)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
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
