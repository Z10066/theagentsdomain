# CreateImageVariationRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**image** | [**File**](File.md) | The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square. | 
**model** | **AnyOfCreateImageVariationRequestModel** | The model to use for image generation. Only &#x60;dall-e-2&#x60; is supported at this time. |  [optional]
**n** | **Integer** | The number of images to generate. Must be between 1 and 10. For &#x60;dall-e-3&#x60;, only &#x60;n&#x3D;1&#x60; is supported. |  [optional]
**responseFormat** | [**ResponseFormatEnum**](#ResponseFormatEnum) | The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. URLs are only valid for 60 minutes after the image has been generated. |  [optional]
**size** | [**SizeEnum**](#SizeEnum) | The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. |  [optional]
**user** | **String** | A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  |  [optional]

<a name="ResponseFormatEnum"></a>
## Enum: ResponseFormatEnum
Name | Value
---- | -----
URL | &quot;url&quot;
B64_JSON | &quot;b64_json&quot;

<a name="SizeEnum"></a>
## Enum: SizeEnum
Name | Value
---- | -----
_256X256 | &quot;256x256&quot;
_512X512 | &quot;512x512&quot;
_1024X1024 | &quot;1024x1024&quot;
