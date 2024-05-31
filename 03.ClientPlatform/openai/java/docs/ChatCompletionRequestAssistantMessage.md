# ChatCompletionRequestAssistantMessage

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**content** | **String** | The contents of the assistant message. Required unless &#x60;tool_calls&#x60; or &#x60;function_call&#x60; is specified.  |  [optional]
**role** | [**RoleEnum**](#RoleEnum) | The role of the messages author, in this case &#x60;assistant&#x60;. | 
**name** | **String** | An optional name for the participant. Provides the model information to differentiate between participants of the same role. |  [optional]
**toolCalls** | [**ChatCompletionMessageToolCalls**](ChatCompletionMessageToolCalls.md) |  |  [optional]
**functionCall** | [**ChatCompletionRequestAssistantMessageFunctionCall**](ChatCompletionRequestAssistantMessageFunctionCall.md) |  |  [optional]

<a name="RoleEnum"></a>
## Enum: RoleEnum
Name | Value
---- | -----
ASSISTANT | &quot;assistant&quot;
