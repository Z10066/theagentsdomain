# ChatCompletionResponseMessage

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**content** | **String** | The contents of the message. | 
**toolCalls** | [**ChatCompletionMessageToolCalls**](ChatCompletionMessageToolCalls.md) |  |  [optional]
**role** | [**RoleEnum**](#RoleEnum) | The role of the author of this message. | 
**functionCall** | [**ChatCompletionResponseMessageFunctionCall**](ChatCompletionResponseMessageFunctionCall.md) |  |  [optional]

<a name="RoleEnum"></a>
## Enum: RoleEnum
Name | Value
---- | -----
ASSISTANT | &quot;assistant&quot;
