# ChatCompletionRequestAssistantMessage

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**content** | **str** | The contents of the assistant message. Required unless &#x60;tool_calls&#x60; or &#x60;function_call&#x60; is specified.  | [optional] 
**role** | **str** | The role of the messages author, in this case &#x60;assistant&#x60;. | 
**name** | **str** | An optional name for the participant. Provides the model information to differentiate between participants of the same role. | [optional] 
**tool_calls** | [**ChatCompletionMessageToolCalls**](ChatCompletionMessageToolCalls.md) |  | [optional] 
**function_call** | [**ChatCompletionRequestAssistantMessageFunctionCall**](ChatCompletionRequestAssistantMessageFunctionCall.md) |  | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)

