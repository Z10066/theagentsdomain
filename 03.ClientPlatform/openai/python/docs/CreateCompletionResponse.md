# CreateCompletionResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **str** | A unique identifier for the completion. | 
**choices** | [**list[CreateCompletionResponseChoices]**](CreateCompletionResponseChoices.md) | The list of completion choices the model generated for the input prompt. | 
**created** | **int** | The Unix timestamp (in seconds) of when the completion was created. | 
**model** | **str** | The model used for completion. | 
**system_fingerprint** | **str** | This fingerprint represents the backend configuration that the model runs with.  Can be used in conjunction with the &#x60;seed&#x60; request parameter to understand when backend changes have been made that might impact determinism.  | [optional] 
**object** | **str** | The object type, which is always \&quot;text_completion\&quot; | 
**usage** | [**CompletionUsage**](CompletionUsage.md) |  | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)

