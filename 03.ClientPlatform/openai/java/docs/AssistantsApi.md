# AssistantsApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancelRun**](AssistantsApi.md#cancelRun) | **POST** /threads/{thread_id}/runs/{run_id}/cancel | Cancels a run that is &#x60;in_progress&#x60;.
[**createAssistant**](AssistantsApi.md#createAssistant) | **POST** /assistants | Create an assistant with a model and instructions.
[**createMessage**](AssistantsApi.md#createMessage) | **POST** /threads/{thread_id}/messages | Create a message.
[**createRun**](AssistantsApi.md#createRun) | **POST** /threads/{thread_id}/runs | Create a run.
[**createThread**](AssistantsApi.md#createThread) | **POST** /threads | Create a thread.
[**createThreadAndRun**](AssistantsApi.md#createThreadAndRun) | **POST** /threads/runs | Create a thread and run it in one request.
[**deleteAssistant**](AssistantsApi.md#deleteAssistant) | **DELETE** /assistants/{assistant_id} | Delete an assistant.
[**deleteMessage**](AssistantsApi.md#deleteMessage) | **DELETE** /threads/{thread_id}/messages/{message_id} | Deletes a message.
[**deleteThread**](AssistantsApi.md#deleteThread) | **DELETE** /threads/{thread_id} | Delete a thread.
[**getAssistant**](AssistantsApi.md#getAssistant) | **GET** /assistants/{assistant_id} | Retrieves an assistant.
[**getMessage**](AssistantsApi.md#getMessage) | **GET** /threads/{thread_id}/messages/{message_id} | Retrieve a message.
[**getRun**](AssistantsApi.md#getRun) | **GET** /threads/{thread_id}/runs/{run_id} | Retrieves a run.
[**getRunStep**](AssistantsApi.md#getRunStep) | **GET** /threads/{thread_id}/runs/{run_id}/steps/{step_id} | Retrieves a run step.
[**getThread**](AssistantsApi.md#getThread) | **GET** /threads/{thread_id} | Retrieves a thread.
[**listAssistants**](AssistantsApi.md#listAssistants) | **GET** /assistants | Returns a list of assistants.
[**listMessages**](AssistantsApi.md#listMessages) | **GET** /threads/{thread_id}/messages | Returns a list of messages for a given thread.
[**listRunSteps**](AssistantsApi.md#listRunSteps) | **GET** /threads/{thread_id}/runs/{run_id}/steps | Returns a list of run steps belonging to a run.
[**listRuns**](AssistantsApi.md#listRuns) | **GET** /threads/{thread_id}/runs | Returns a list of runs belonging to a thread.
[**modifyAssistant**](AssistantsApi.md#modifyAssistant) | **POST** /assistants/{assistant_id} | Modifies an assistant.
[**modifyMessage**](AssistantsApi.md#modifyMessage) | **POST** /threads/{thread_id}/messages/{message_id} | Modifies a message.
[**modifyRun**](AssistantsApi.md#modifyRun) | **POST** /threads/{thread_id}/runs/{run_id} | Modifies a run.
[**modifyThread**](AssistantsApi.md#modifyThread) | **POST** /threads/{thread_id} | Modifies a thread.
[**submitToolOuputsToRun**](AssistantsApi.md#submitToolOuputsToRun) | **POST** /threads/{thread_id}/runs/{run_id}/submit_tool_outputs | When a run has the &#x60;status: \&quot;requires_action\&quot;&#x60; and &#x60;required_action.type&#x60; is &#x60;submit_tool_outputs&#x60;, this endpoint can be used to submit the outputs from the tool calls once they&#x27;re all completed. All outputs must be submitted in a single request. 

<a name="cancelRun"></a>
# **cancelRun**
> RunObject cancelRun(threadId, runId)

Cancels a run that is &#x60;in_progress&#x60;.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread to which this run belongs.
String runId = "runId_example"; // String | The ID of the run to cancel.
try {
    RunObject result = apiInstance.cancelRun(threadId, runId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#cancelRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread to which this run belongs. |
 **runId** | **String**| The ID of the run to cancel. |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="createAssistant"></a>
# **createAssistant**
> AssistantObject createAssistant(body)

Create an assistant with a model and instructions.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
CreateAssistantRequest body = new CreateAssistantRequest(); // CreateAssistantRequest | 
try {
    AssistantObject result = apiInstance.createAssistant(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#createAssistant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateAssistantRequest**](CreateAssistantRequest.md)|  |

### Return type

[**AssistantObject**](AssistantObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createMessage"></a>
# **createMessage**
> MessageObject createMessage(body, threadId)

Create a message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
CreateMessageRequest body = new CreateMessageRequest(); // CreateMessageRequest | 
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) to create a message for.
try {
    MessageObject result = apiInstance.createMessage(body, threadId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#createMessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateMessageRequest**](CreateMessageRequest.md)|  |
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) to create a message for. |

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createRun"></a>
# **createRun**
> RunObject createRun(body, threadId)

Create a run.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
CreateRunRequest body = new CreateRunRequest(); // CreateRunRequest | 
String threadId = "threadId_example"; // String | The ID of the thread to run.
try {
    RunObject result = apiInstance.createRun(body, threadId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#createRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateRunRequest**](CreateRunRequest.md)|  |
 **threadId** | **String**| The ID of the thread to run. |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createThread"></a>
# **createThread**
> ThreadObject createThread(body)

Create a thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
CreateThreadRequest body = new CreateThreadRequest(); // CreateThreadRequest | 
try {
    ThreadObject result = apiInstance.createThread(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#createThread");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateThreadRequest**](CreateThreadRequest.md)|  | [optional]

### Return type

[**ThreadObject**](ThreadObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createThreadAndRun"></a>
# **createThreadAndRun**
> RunObject createThreadAndRun(body)

Create a thread and run it in one request.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
CreateThreadAndRunRequest body = new CreateThreadAndRunRequest(); // CreateThreadAndRunRequest | 
try {
    RunObject result = apiInstance.createThreadAndRun(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#createThreadAndRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateThreadAndRunRequest**](CreateThreadAndRunRequest.md)|  |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteAssistant"></a>
# **deleteAssistant**
> DeleteAssistantResponse deleteAssistant(assistantId)

Delete an assistant.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String assistantId = "assistantId_example"; // String | The ID of the assistant to delete.
try {
    DeleteAssistantResponse result = apiInstance.deleteAssistant(assistantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#deleteAssistant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assistantId** | **String**| The ID of the assistant to delete. |

### Return type

[**DeleteAssistantResponse**](DeleteAssistantResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteMessage"></a>
# **deleteMessage**
> DeleteMessageResponse deleteMessage(threadId, messageId)

Deletes a message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread to which this message belongs.
String messageId = "messageId_example"; // String | The ID of the message to delete.
try {
    DeleteMessageResponse result = apiInstance.deleteMessage(threadId, messageId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#deleteMessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread to which this message belongs. |
 **messageId** | **String**| The ID of the message to delete. |

### Return type

[**DeleteMessageResponse**](DeleteMessageResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteThread"></a>
# **deleteThread**
> DeleteThreadResponse deleteThread(threadId)

Delete a thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread to delete.
try {
    DeleteThreadResponse result = apiInstance.deleteThread(threadId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#deleteThread");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread to delete. |

### Return type

[**DeleteThreadResponse**](DeleteThreadResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAssistant"></a>
# **getAssistant**
> AssistantObject getAssistant(assistantId)

Retrieves an assistant.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String assistantId = "assistantId_example"; // String | The ID of the assistant to retrieve.
try {
    AssistantObject result = apiInstance.getAssistant(assistantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#getAssistant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assistantId** | **String**| The ID of the assistant to retrieve. |

### Return type

[**AssistantObject**](AssistantObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getMessage"></a>
# **getMessage**
> MessageObject getMessage(threadId, messageId)

Retrieve a message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) to which this message belongs.
String messageId = "messageId_example"; // String | The ID of the message to retrieve.
try {
    MessageObject result = apiInstance.getMessage(threadId, messageId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#getMessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) to which this message belongs. |
 **messageId** | **String**| The ID of the message to retrieve. |

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getRun"></a>
# **getRun**
> RunObject getRun(threadId, runId)

Retrieves a run.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) that was run.
String runId = "runId_example"; // String | The ID of the run to retrieve.
try {
    RunObject result = apiInstance.getRun(threadId, runId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#getRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) that was run. |
 **runId** | **String**| The ID of the run to retrieve. |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getRunStep"></a>
# **getRunStep**
> RunStepObject getRunStep(threadId, runId, stepId)

Retrieves a run step.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread to which the run and run step belongs.
String runId = "runId_example"; // String | The ID of the run to which the run step belongs.
String stepId = "stepId_example"; // String | The ID of the run step to retrieve.
try {
    RunStepObject result = apiInstance.getRunStep(threadId, runId, stepId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#getRunStep");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread to which the run and run step belongs. |
 **runId** | **String**| The ID of the run to which the run step belongs. |
 **stepId** | **String**| The ID of the run step to retrieve. |

### Return type

[**RunStepObject**](RunStepObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getThread"></a>
# **getThread**
> ThreadObject getThread(threadId)

Retrieves a thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread to retrieve.
try {
    ThreadObject result = apiInstance.getThread(threadId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#getThread");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread to retrieve. |

### Return type

[**ThreadObject**](ThreadObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAssistants"></a>
# **listAssistants**
> ListAssistantsResponse listAssistants(limit, order, after, before)

Returns a list of assistants.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
Integer limit = 20; // Integer | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. 
String order = "desc"; // String | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. 
String after = "after_example"; // String | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. 
String before = "before_example"; // String | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. 
try {
    ListAssistantsResponse result = apiInstance.listAssistants(limit, order, after, before);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#listAssistants");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **Integer**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **String**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc] [enum: asc, desc]
 **after** | **String**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional]
 **before** | **String**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional]

### Return type

[**ListAssistantsResponse**](ListAssistantsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listMessages"></a>
# **listMessages**
> ListMessagesResponse listMessages(threadId, limit, order, after, before, runId)

Returns a list of messages for a given thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) the messages belong to.
Integer limit = 20; // Integer | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. 
String order = "desc"; // String | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. 
String after = "after_example"; // String | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. 
String before = "before_example"; // String | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. 
String runId = "runId_example"; // String | Filter messages by the run ID that generated them. 
try {
    ListMessagesResponse result = apiInstance.listMessages(threadId, limit, order, after, before, runId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#listMessages");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) the messages belong to. |
 **limit** | **Integer**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **String**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc] [enum: asc, desc]
 **after** | **String**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional]
 **before** | **String**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional]
 **runId** | **String**| Filter messages by the run ID that generated them.  | [optional]

### Return type

[**ListMessagesResponse**](ListMessagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listRunSteps"></a>
# **listRunSteps**
> ListRunStepsResponse listRunSteps(threadId, runId, limit, order, after, before)

Returns a list of run steps belonging to a run.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread the run and run steps belong to.
String runId = "runId_example"; // String | The ID of the run the run steps belong to.
Integer limit = 20; // Integer | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. 
String order = "desc"; // String | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. 
String after = "after_example"; // String | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. 
String before = "before_example"; // String | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. 
try {
    ListRunStepsResponse result = apiInstance.listRunSteps(threadId, runId, limit, order, after, before);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#listRunSteps");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread the run and run steps belong to. |
 **runId** | **String**| The ID of the run the run steps belong to. |
 **limit** | **Integer**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **String**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc] [enum: asc, desc]
 **after** | **String**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional]
 **before** | **String**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional]

### Return type

[**ListRunStepsResponse**](ListRunStepsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listRuns"></a>
# **listRuns**
> ListRunsResponse listRuns(threadId, limit, order, after, before)

Returns a list of runs belonging to a thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
String threadId = "threadId_example"; // String | The ID of the thread the run belongs to.
Integer limit = 20; // Integer | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. 
String order = "desc"; // String | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. 
String after = "after_example"; // String | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. 
String before = "before_example"; // String | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. 
try {
    ListRunsResponse result = apiInstance.listRuns(threadId, limit, order, after, before);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#listRuns");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **threadId** | **String**| The ID of the thread the run belongs to. |
 **limit** | **Integer**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **String**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc] [enum: asc, desc]
 **after** | **String**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional]
 **before** | **String**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional]

### Return type

[**ListRunsResponse**](ListRunsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyAssistant"></a>
# **modifyAssistant**
> AssistantObject modifyAssistant(body, assistantId)

Modifies an assistant.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
ModifyAssistantRequest body = new ModifyAssistantRequest(); // ModifyAssistantRequest | 
String assistantId = "assistantId_example"; // String | The ID of the assistant to modify.
try {
    AssistantObject result = apiInstance.modifyAssistant(body, assistantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#modifyAssistant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyAssistantRequest**](ModifyAssistantRequest.md)|  |
 **assistantId** | **String**| The ID of the assistant to modify. |

### Return type

[**AssistantObject**](AssistantObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="modifyMessage"></a>
# **modifyMessage**
> MessageObject modifyMessage(body, threadId, messageId)

Modifies a message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
ModifyMessageRequest body = new ModifyMessageRequest(); // ModifyMessageRequest | 
String threadId = "threadId_example"; // String | The ID of the thread to which this message belongs.
String messageId = "messageId_example"; // String | The ID of the message to modify.
try {
    MessageObject result = apiInstance.modifyMessage(body, threadId, messageId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#modifyMessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyMessageRequest**](ModifyMessageRequest.md)|  |
 **threadId** | **String**| The ID of the thread to which this message belongs. |
 **messageId** | **String**| The ID of the message to modify. |

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="modifyRun"></a>
# **modifyRun**
> RunObject modifyRun(body, threadId, runId)

Modifies a run.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
ModifyRunRequest body = new ModifyRunRequest(); // ModifyRunRequest | 
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) that was run.
String runId = "runId_example"; // String | The ID of the run to modify.
try {
    RunObject result = apiInstance.modifyRun(body, threadId, runId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#modifyRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyRunRequest**](ModifyRunRequest.md)|  |
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) that was run. |
 **runId** | **String**| The ID of the run to modify. |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="modifyThread"></a>
# **modifyThread**
> ThreadObject modifyThread(body, threadId)

Modifies a thread.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
ModifyThreadRequest body = new ModifyThreadRequest(); // ModifyThreadRequest | 
String threadId = "threadId_example"; // String | The ID of the thread to modify. Only the `metadata` can be modified.
try {
    ThreadObject result = apiInstance.modifyThread(body, threadId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#modifyThread");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyThreadRequest**](ModifyThreadRequest.md)|  |
 **threadId** | **String**| The ID of the thread to modify. Only the &#x60;metadata&#x60; can be modified. |

### Return type

[**ThreadObject**](ThreadObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="submitToolOuputsToRun"></a>
# **submitToolOuputsToRun**
> RunObject submitToolOuputsToRun(body, threadId, runId)

When a run has the &#x60;status: \&quot;requires_action\&quot;&#x60; and &#x60;required_action.type&#x60; is &#x60;submit_tool_outputs&#x60;, this endpoint can be used to submit the outputs from the tool calls once they&#x27;re all completed. All outputs must be submitted in a single request. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AssistantsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AssistantsApi apiInstance = new AssistantsApi();
SubmitToolOutputsRunRequest body = new SubmitToolOutputsRunRequest(); // SubmitToolOutputsRunRequest | 
String threadId = "threadId_example"; // String | The ID of the [thread](/docs/api-reference/threads) to which this run belongs.
String runId = "runId_example"; // String | The ID of the run that requires the tool output submission.
try {
    RunObject result = apiInstance.submitToolOuputsToRun(body, threadId, runId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AssistantsApi#submitToolOuputsToRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SubmitToolOutputsRunRequest**](SubmitToolOutputsRunRequest.md)|  |
 **threadId** | **String**| The ID of the [thread](/docs/api-reference/threads) to which this run belongs. |
 **runId** | **String**| The ID of the run that requires the tool output submission. |

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

