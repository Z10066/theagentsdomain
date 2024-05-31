# Embedding

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**index** | **Integer** | The index of the embedding in the list of embeddings. | 
**embedding** | [**List&lt;BigDecimal&gt;**](BigDecimal.md) | The embedding vector, which is a list of floats. The length of vector depends on the model as listed in the [embedding guide](/docs/guides/embeddings).  | 
**object** | [**ObjectEnum**](#ObjectEnum) | The object type, which is always \&quot;embedding\&quot;. | 

<a name="ObjectEnum"></a>
## Enum: ObjectEnum
Name | Value
---- | -----
EMBEDDING | &quot;embedding&quot;
