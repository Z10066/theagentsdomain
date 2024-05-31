# ImagesApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createImage**](ImagesApi.md#createImage) | **POST** /images/generations | Creates an image given a prompt.
[**createImageEdit**](ImagesApi.md#createImageEdit) | **POST** /images/edits | Creates an edited or extended image given an original image and a prompt.
[**createImageVariation**](ImagesApi.md#createImageVariation) | **POST** /images/variations | Creates a variation of a given image.

<a name="createImage"></a>
# **createImage**
> ImagesResponse createImage(body)

Creates an image given a prompt.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ImagesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ImagesApi apiInstance = new ImagesApi();
CreateImageRequest body = new CreateImageRequest(); // CreateImageRequest | 
try {
    ImagesResponse result = apiInstance.createImage(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImagesApi#createImage");
    e.printStackTrace();
}
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

<a name="createImageEdit"></a>
# **createImageEdit**
> ImagesResponse createImageEdit(image, prompt, mask, model, n, size, responseFormat, user)

Creates an edited or extended image given an original image and a prompt.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ImagesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ImagesApi apiInstance = new ImagesApi();
File image = new File("image_example"); // File | 
String prompt = "prompt_example"; // String | 
File mask = new File("mask_example"); // File | 
Object model = null; // Object | 
Integer n = 56; // Integer | 
String size = "size_example"; // String | 
String responseFormat = "responseFormat_example"; // String | 
String user = "user_example"; // String | 
try {
    ImagesResponse result = apiInstance.createImageEdit(image, prompt, mask, model, n, size, responseFormat, user);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImagesApi#createImageEdit");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **image** | **File**|  |
 **prompt** | **String**|  |
 **mask** | **File**|  |
 **model** | [**Object**](.md)|  |
 **n** | **Integer**|  | [enum: 10, 1]
 **size** | **String**|  | [enum: 256x256, 512x512, 1024x1024]
 **responseFormat** | **String**|  | [enum: url, b64_json]
 **user** | **String**|  |

### Return type

[**ImagesResponse**](ImagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="createImageVariation"></a>
# **createImageVariation**
> ImagesResponse createImageVariation(image, model, n, responseFormat, size, user)

Creates a variation of a given image.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ImagesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ImagesApi apiInstance = new ImagesApi();
File image = new File("image_example"); // File | 
Object model = null; // Object | 
Integer n = 56; // Integer | 
String responseFormat = "responseFormat_example"; // String | 
String size = "size_example"; // String | 
String user = "user_example"; // String | 
try {
    ImagesResponse result = apiInstance.createImageVariation(image, model, n, responseFormat, size, user);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ImagesApi#createImageVariation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **image** | **File**|  |
 **model** | [**Object**](.md)|  |
 **n** | **Integer**|  | [enum: 10, 1]
 **responseFormat** | **String**|  | [enum: url, b64_json]
 **size** | **String**|  | [enum: 256x256, 512x512, 1024x1024]
 **user** | **String**|  |

### Return type

[**ImagesResponse**](ImagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

