# swagger_client.AudioApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_speech**](AudioApi.md#create_speech) | **POST** /audio/speech | Generates audio from the input text.
[**create_transcription**](AudioApi.md#create_transcription) | **POST** /audio/transcriptions | Transcribes audio into the input language.
[**create_translation**](AudioApi.md#create_translation) | **POST** /audio/translations | Translates audio into English.

# **create_speech**
> str create_speech(body)

Generates audio from the input text.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AudioApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateSpeechRequest() # CreateSpeechRequest | 

try:
    # Generates audio from the input text.
    api_response = api_instance.create_speech(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AudioApi->create_speech: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateSpeechRequest**](CreateSpeechRequest.md)|  | 

### Return type

**str**

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_transcription**
> InlineResponse200 create_transcription(file, model, language, prompt, response_format, temperature, timestamp_granularities)

Transcribes audio into the input language.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AudioApi(swagger_client.ApiClient(configuration))
file = 'file_example' # str | 
model = NULL # object | 
language = 'language_example' # str | 
prompt = 'prompt_example' # str | 
response_format = 'response_format_example' # str | 
temperature = 1.2 # float | 
timestamp_granularities = ['timestamp_granularities_example'] # list[str] | 

try:
    # Transcribes audio into the input language.
    api_response = api_instance.create_transcription(file, model, language, prompt, response_format, temperature, timestamp_granularities)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AudioApi->create_transcription: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **str**|  | 
 **model** | [**object**](.md)|  | 
 **language** | **str**|  | 
 **prompt** | **str**|  | 
 **response_format** | **str**|  | 
 **temperature** | **float**|  | 
 **timestamp_granularities** | [**list[str]**](str.md)|  | 

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_translation**
> InlineResponse2001 create_translation(file, model, prompt, response_format, temperature)

Translates audio into English.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AudioApi(swagger_client.ApiClient(configuration))
file = 'file_example' # str | 
model = NULL # object | 
prompt = 'prompt_example' # str | 
response_format = 'response_format_example' # str | 
temperature = 1.2 # float | 

try:
    # Translates audio into English.
    api_response = api_instance.create_translation(file, model, prompt, response_format, temperature)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AudioApi->create_translation: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **str**|  | 
 **model** | [**object**](.md)|  | 
 **prompt** | **str**|  | 
 **response_format** | **str**|  | 
 **temperature** | **float**|  | 

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

