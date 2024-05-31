# MessageDeltaObjectDelta

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**role** | [**RoleEnum**](#RoleEnum) | The entity that produced the message. One of &#x60;user&#x60; or &#x60;assistant&#x60;. |  [optional]
**content** | **List&lt;OneOfMessageDeltaObjectDeltaContentItems&gt;** | The content of the message in array of text and/or images. |  [optional]

<a name="RoleEnum"></a>
## Enum: RoleEnum
Name | Value
---- | -----
USER | &quot;user&quot;
ASSISTANT | &quot;assistant&quot;
