# VectorStoreExpirationAfter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**anchor** | [**AnchorEnum**](#AnchorEnum) | Anchor timestamp after which the expiration policy applies. Supported anchors: &#x60;last_active_at&#x60;. | 
**days** | **Integer** | The number of days after the anchor time that the vector store will expire. | 

<a name="AnchorEnum"></a>
## Enum: AnchorEnum
Name | Value
---- | -----
LAST_ACTIVE_AT | &quot;last_active_at&quot;
