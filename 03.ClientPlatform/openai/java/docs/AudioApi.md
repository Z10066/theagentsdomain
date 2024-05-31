# AudioApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSpeech**](AudioApi.md#createSpeech) | **POST** /audio/speech | Generates audio from the input text.
[**createTranscription**](AudioApi.md#createTranscription) | **POST** /audio/transcriptions | Transcribes audio into the input language.
[**createTranslation**](AudioApi.md#createTranslation) | **POST** /audio/translations | Translates audio into English.

<a name="createSpeech"></a>
# **createSpeech**
> File createSpeech(body)

Generates audio from the input text.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AudioApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AudioApi apiInstance = new AudioApi();
CreateSpeechRequest body = new CreateSpeechRequest(); // CreateSpeechRequest | 
try {
    File result = apiInstance.createSpeech(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AudioApi#createSpeech");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateSpeechRequest**](CreateSpeechRequest.md)|  |

### Return type

[**File**](File.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="createTranscription"></a>
# **createTranscription**
> InlineResponse200 createTranscription(file, model, language, prompt, responseFormat, temperature, timestampGranularities)

Transcribes audio into the input language.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AudioApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AudioApi apiInstance = new AudioApi();
File file = new File("file_example"); // File | 
Object model = null; // Object | 
String language = "language_example"; // String | 
String prompt = "prompt_example"; // String | 
String responseFormat = "responseFormat_example"; // String | 
BigDecimal temperature = new BigDecimal(); // BigDecimal | 
List<String> timestampGranularities = Arrays.asList("timestampGranularities_example"); // List<String> | 
try {
    InlineResponse200 result = apiInstance.createTranscription(file, model, language, prompt, responseFormat, temperature, timestampGranularities);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AudioApi#createTranscription");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**|  |
 **model** | [**Object**](.md)|  |
 **language** | **String**|  |
 **prompt** | **String**|  |
 **responseFormat** | **String**|  | [enum: json, text, srt, verbose_json, vtt]
 **temperature** | **BigDecimal**|  |
 **timestampGranularities** | [**List&lt;String&gt;**](String.md)|  | [enum: word, segment]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="createTranslation"></a>
# **createTranslation**
> InlineResponse2001 createTranslation(file, model, prompt, responseFormat, temperature)

Translates audio into English.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AudioApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AudioApi apiInstance = new AudioApi();
File file = new File("file_example"); // File | 
Object model = null; // Object | 
String prompt = "prompt_example"; // String | 
String responseFormat = "responseFormat_example"; // String | 
BigDecimal temperature = new BigDecimal(); // BigDecimal | 
try {
    InlineResponse2001 result = apiInstance.createTranslation(file, model, prompt, responseFormat, temperature);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AudioApi#createTranslation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**|  |
 **model** | [**Object**](.md)|  |
 **prompt** | **String**|  |
 **responseFormat** | **String**|  |
 **temperature** | **BigDecimal**|  |

### Return type

[**InlineResponse2001**](InlineResponse2001.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

