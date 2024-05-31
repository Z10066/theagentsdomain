# swagger_client.ImagesApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_image**](ImagesApi.md#create_image) | **POST** /images/generations | Creates an image given a prompt.
[**create_image_edit**](ImagesApi.md#create_image_edit) | **POST** /images/edits | Creates an edited or extended image given an original image and a prompt.
[**create_image_variation**](ImagesApi.md#create_image_variation) | **POST** /images/variations | Creates a variation of a given image.

# **create_image**
> ImagesResponse create_image(body)

Creates an image given a prompt.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.ImagesApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateImageRequest() # CreateImageRequest | 

try:
    # Creates an image given a prompt.
    api_response = api_instance.create_image(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ImagesApi->create_image: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateImageRequest**](CreateImageRequest.md)|  | 

### Return type

[**ImagesResponse**](ImagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_image_edit**
> ImagesResponse create_image_edit(image, prompt, mask, model, n, size, response_format, user)

Creates an edited or extended image given an original image and a prompt.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.ImagesApi(swagger_client.ApiClient(configuration))
image = 'image_example' # str | 
prompt = 'prompt_example' # str | 
mask = 'mask_example' # str | 
model = NULL # object | 
n = 56 # int | 
size = 'size_example' # str | 
response_format = 'response_format_example' # str | 
user = 'user_example' # str | 

try:
    # Creates an edited or extended image given an original image and a prompt.
    api_response = api_instance.create_image_edit(image, prompt, mask, model, n, size, response_format, user)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ImagesApi->create_image_edit: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **image** | **str**|  | 
 **prompt** | **str**|  | 
 **mask** | **str**|  | 
 **model** | [**object**](.md)|  | 
 **n** | **int**|  | 
 **size** | **str**|  | 
 **response_format** | **str**|  | 
 **user** | **str**|  | 

### Return type

[**ImagesResponse**](ImagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_image_variation**
> ImagesResponse create_image_variation(image, model, n, response_format, size, user)

Creates a variation of a given image.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.ImagesApi(swagger_client.ApiClient(configuration))
image = 'image_example' # str | 
model = NULL # object | 
n = 56 # int | 
response_format = 'response_format_example' # str | 
size = 'size_example' # str | 
user = 'user_example' # str | 

try:
    # Creates a variation of a given image.
    api_response = api_instance.create_image_variation(image, model, n, response_format, size, user)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling ImagesApi->create_image_variation: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **image** | **str**|  | 
 **model** | [**object**](.md)|  | 
 **n** | **int**|  | 
 **response_format** | **str**|  | 
 **size** | **str**|  | 
 **user** | **str**|  | 

### Return type

[**ImagesResponse**](ImagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

