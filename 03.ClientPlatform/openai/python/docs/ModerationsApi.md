# swagger_client.ModerationsApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_moderation**](ModerationsApi.md#create_moderation) | **POST** /moderations | Classifies if text is potentially harmful.

# **create_moderation**
> CreateModerationResponse create_moderation(body)

Classifies if text is potentially harmful.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.ModerationsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateModerationRequest() # CreateModerationRequest | 

try:
    # Classifies if text is potentially harmful.
    api_response = api_instance.create_moderation(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ModerationsApi->create_moderation: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateModerationRequest**](CreateModerationRequest.md)|  | 

### Return type

[**CreateModerationResponse**](CreateModerationResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

