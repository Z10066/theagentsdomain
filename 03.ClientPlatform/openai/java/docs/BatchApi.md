# BatchApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelBatch**](BatchApi.md#cancelBatch) | **POST** /batches/{batch_id}/cancel | Cancels an in-progress batch.
[**createBatch**](BatchApi.md#createBatch) | **POST** /batches | Creates and executes a batch from an uploaded file of requests
[**listBatches**](BatchApi.md#listBatches) | **GET** /batches | List your organization&#x27;s batches.
[**retrieveBatch**](BatchApi.md#retrieveBatch) | **GET** /batches/{batch_id} | Retrieves a batch.

<a name="cancelBatch"></a>
# **cancelBatch**
> Batch cancelBatch(batchId)

Cancels an in-progress batch.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.BatchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


BatchApi apiInstance = new BatchApi();
String batchId = "batchId_example"; // String | The ID of the batch to cancel.
try {
    Batch result = apiInstance.cancelBatch(batchId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BatchApi#cancelBatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **batchId** | **String**| The ID of the batch to cancel. |

### Return type

[**Batch**](Batch.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="createBatch"></a>
# **createBatch**
> Batch createBatch(body)

Creates and executes a batch from an uploaded file of requests

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.BatchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


BatchApi apiInstance = new BatchApi();
BatchesBody body = new BatchesBody(); // BatchesBody | 
try {
    Batch result = apiInstance.createBatch(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BatchApi#createBatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**BatchesBody**](BatchesBody.md)|  |

### Return type

[**Batch**](Batch.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="listBatches"></a>
# **listBatches**
> ListBatchesResponse listBatches(after, limit)

List your organization&#x27;s batches.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.BatchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


BatchApi apiInstance = new BatchApi();
String after = "after_example"; // String | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. 
Integer limit = 20; // Integer | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. 
try {
    ListBatchesResponse result = apiInstance.listBatches(after, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BatchApi#listBatches");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **after** | **String**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional]
 **limit** | **Integer**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]

### Return type

[**ListBatchesResponse**](ListBatchesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="retrieveBatch"></a>
# **retrieveBatch**
> Batch retrieveBatch(batchId)

Retrieves a batch.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.BatchApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


BatchApi apiInstance = new BatchApi();
String batchId = "batchId_example"; // String | The ID of the batch to retrieve.
try {
    Batch result = apiInstance.retrieveBatch(batchId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BatchApi#retrieveBatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **batchId** | **String**| The ID of the batch to retrieve. |

### Return type

[**Batch**](Batch.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

