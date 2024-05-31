# swagger_client.EmbeddingsApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_embedding**](EmbeddingsApi.md#create_embedding) | **POST** /embeddings | Creates an embedding vector representing the input text.

# **create_embedding**
> CreateEmbeddingResponse create_embedding(body)

Creates an embedding vector representing the input text.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.EmbeddingsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateEmbeddingRequest() # CreateEmbeddingRequest | 

try:
    # Creates an embedding vector representing the input text.
    api_response = api_instance.create_embedding(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling EmbeddingsApi->create_embedding: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateEmbeddingRequest**](CreateEmbeddingRequest.md)|  | 

### Return type

[**CreateEmbeddingResponse**](CreateEmbeddingResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

