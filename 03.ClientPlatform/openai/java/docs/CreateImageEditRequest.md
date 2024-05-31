# CreateImageEditRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**image** | [**File**](File.md) | The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask. | 
**prompt** | **String** | A text description of the desired image(s). The maximum length is 1000 characters. | 
**mask** | [**File**](File.md) | An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where &#x60;image&#x60; should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as &#x60;image&#x60;. |  [optional]
**model** | **AnyOfCreateImageEditRequestModel** | The model to use for image generation. Only &#x60;dall-e-2&#x60; is supported at this time. |  [optional]
**n** | **Integer** | The number of images to generate. Must be between 1 and 10. |  [optional]
**size** | [**SizeEnum**](#SizeEnum) | The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;. |  [optional]
**responseFormat** | [**ResponseFormatEnum**](#ResponseFormatEnum) | The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;. URLs are only valid for 60 minutes after the image has been generated. |  [optional]
**user** | **String** | A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).  |  [optional]

<a name="SizeEnum"></a>
## Enum: SizeEnum
Name | Value
---- | -----
_256X256 | &quot;256x256&quot;
_512X512 | &quot;512x512&quot;
_1024X1024 | &quot;1024x1024&quot;

<a name="ResponseFormatEnum"></a>
## Enum: ResponseFormatEnum
Name | Value
---- | -----
URL | &quot;url&quot;
B64_JSON | &quot;b64_json&quot;
