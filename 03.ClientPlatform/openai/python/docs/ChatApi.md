# swagger_client.ChatApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_chat_completion**](ChatApi.md#create_chat_completion) | **POST** /chat/completions | Creates a model response for the given chat conversation.

# **create_chat_completion**
> CreateChatCompletionResponse create_chat_completion(body)

Creates a model response for the given chat conversation.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.ChatApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateChatCompletionRequest() # CreateChatCompletionRequest | 

try:
    # Creates a model response for the given chat conversation.
    api_response = api_instance.create_chat_completion(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ChatApi->create_chat_completion: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateChatCompletionRequest**](CreateChatCompletionRequest.md)|  | 

### Return type

[**CreateChatCompletionResponse**](CreateChatCompletionResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

