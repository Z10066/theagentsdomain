# ChatCompletionStreamResponseDelta

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**content** | **String** | The contents of the chunk message. |  [optional]
**functionCall** | [**ChatCompletionStreamResponseDeltaFunctionCall**](ChatCompletionStreamResponseDeltaFunctionCall.md) |  |  [optional]
**toolCalls** | [**List&lt;ChatCompletionMessageToolCallChunk&gt;**](ChatCompletionMessageToolCallChunk.md) |  |  [optional]
**role** | [**RoleEnum**](#RoleEnum) | The role of the author of this message. |  [optional]

<a name="RoleEnum"></a>
## Enum: RoleEnum
Name | Value
---- | -----
SYSTEM | &quot;system&quot;
USER | &quot;user&quot;
ASSISTANT | &quot;assistant&quot;
TOOL | &quot;tool&quot;
