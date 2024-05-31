# MessageDeltaObject

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The identifier of the message, which can be referenced in API endpoints. | 
**object** | [**ObjectEnum**](#ObjectEnum) | The object type, which is always &#x60;thread.message.delta&#x60;. | 
**delta** | [**MessageDeltaObjectDelta**](MessageDeltaObjectDelta.md) |  | 

<a name="ObjectEnum"></a>
## Enum: ObjectEnum
Name | Value
---- | -----
THREAD_MESSAGE_DELTA | &quot;thread.message.delta&quot;
