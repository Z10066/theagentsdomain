# swagger_client.AssistantsApi

All URIs are relative to *https://api.openai.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancel_run**](AssistantsApi.md#cancel_run) | **POST** /threads/{thread_id}/runs/{run_id}/cancel | Cancels a run that is &#x60;in_progress&#x60;.
[**create_assistant**](AssistantsApi.md#create_assistant) | **POST** /assistants | Create an assistant with a model and instructions.
[**create_message**](AssistantsApi.md#create_message) | **POST** /threads/{thread_id}/messages | Create a message.
[**create_run**](AssistantsApi.md#create_run) | **POST** /threads/{thread_id}/runs | Create a run.
[**create_thread**](AssistantsApi.md#create_thread) | **POST** /threads | Create a thread.
[**create_thread_and_run**](AssistantsApi.md#create_thread_and_run) | **POST** /threads/runs | Create a thread and run it in one request.
[**delete_assistant**](AssistantsApi.md#delete_assistant) | **DELETE** /assistants/{assistant_id} | Delete an assistant.
[**delete_message**](AssistantsApi.md#delete_message) | **DELETE** /threads/{thread_id}/messages/{message_id} | Deletes a message.
[**delete_thread**](AssistantsApi.md#delete_thread) | **DELETE** /threads/{thread_id} | Delete a thread.
[**get_assistant**](AssistantsApi.md#get_assistant) | **GET** /assistants/{assistant_id} | Retrieves an assistant.
[**get_message**](AssistantsApi.md#get_message) | **GET** /threads/{thread_id}/messages/{message_id} | Retrieve a message.
[**get_run**](AssistantsApi.md#get_run) | **GET** /threads/{thread_id}/runs/{run_id} | Retrieves a run.
[**get_run_step**](AssistantsApi.md#get_run_step) | **GET** /threads/{thread_id}/runs/{run_id}/steps/{step_id} | Retrieves a run step.
[**get_thread**](AssistantsApi.md#get_thread) | **GET** /threads/{thread_id} | Retrieves a thread.
[**list_assistants**](AssistantsApi.md#list_assistants) | **GET** /assistants | Returns a list of assistants.
[**list_messages**](AssistantsApi.md#list_messages) | **GET** /threads/{thread_id}/messages | Returns a list of messages for a given thread.
[**list_run_steps**](AssistantsApi.md#list_run_steps) | **GET** /threads/{thread_id}/runs/{run_id}/steps | Returns a list of run steps belonging to a run.
[**list_runs**](AssistantsApi.md#list_runs) | **GET** /threads/{thread_id}/runs | Returns a list of runs belonging to a thread.
[**modify_assistant**](AssistantsApi.md#modify_assistant) | **POST** /assistants/{assistant_id} | Modifies an assistant.
[**modify_message**](AssistantsApi.md#modify_message) | **POST** /threads/{thread_id}/messages/{message_id} | Modifies a message.
[**modify_run**](AssistantsApi.md#modify_run) | **POST** /threads/{thread_id}/runs/{run_id} | Modifies a run.
[**modify_thread**](AssistantsApi.md#modify_thread) | **POST** /threads/{thread_id} | Modifies a thread.
[**submit_tool_ouputs_to_run**](AssistantsApi.md#submit_tool_ouputs_to_run) | **POST** /threads/{thread_id}/runs/{run_id}/submit_tool_outputs | When a run has the &#x60;status: \&quot;requires_action\&quot;&#x60; and &#x60;required_action.type&#x60; is &#x60;submit_tool_outputs&#x60;, this endpoint can be used to submit the outputs from the tool calls once they&#x27;re all completed. All outputs must be submitted in a single request. 

# **cancel_run**
> RunObject cancel_run(thread_id, run_id)

Cancels a run that is `in_progress`.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread to which this run belongs.
run_id = 'run_id_example' # str | The ID of the run to cancel.

try:
    # Cancels a run that is `in_progress`.
    api_response = api_instance.cancel_run(thread_id, run_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->cancel_run: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread to which this run belongs. | 
 **run_id** | **str**| The ID of the run to cancel. | 

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_assistant**
> AssistantObject create_assistant(body)

Create an assistant with a model and instructions.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateAssistantRequest() # CreateAssistantRequest | 

try:
    # Create an assistant with a model and instructions.
    api_response = api_instance.create_assistant(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->create_assistant: %s\n" % e)
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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_message**
> MessageObject create_message(body, thread_id)

Create a message.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateMessageRequest() # CreateMessageRequest | 
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) to create a message for.

try:
    # Create a message.
    api_response = api_instance.create_message(body, thread_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->create_message: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateMessageRequest**](CreateMessageRequest.md)|  | 
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) to create a message for. | 

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_run**
> RunObject create_run(body, thread_id)

Create a run.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateRunRequest() # CreateRunRequest | 
thread_id = 'thread_id_example' # str | The ID of the thread to run.

try:
    # Create a run.
    api_response = api_instance.create_run(body, thread_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->create_run: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateRunRequest**](CreateRunRequest.md)|  | 
 **thread_id** | **str**| The ID of the thread to run. | 

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_thread**
> ThreadObject create_thread(body=body)

Create a thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateThreadRequest() # CreateThreadRequest |  (optional)

try:
    # Create a thread.
    api_response = api_instance.create_thread(body=body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->create_thread: %s\n" % e)
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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_thread_and_run**
> RunObject create_thread_and_run(body)

Create a thread and run it in one request.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.CreateThreadAndRunRequest() # CreateThreadAndRunRequest | 

try:
    # Create a thread and run it in one request.
    api_response = api_instance.create_thread_and_run(body)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->create_thread_and_run: %s\n" % e)
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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete_assistant**
> DeleteAssistantResponse delete_assistant(assistant_id)

Delete an assistant.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
assistant_id = 'assistant_id_example' # str | The ID of the assistant to delete.

try:
    # Delete an assistant.
    api_response = api_instance.delete_assistant(assistant_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->delete_assistant: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assistant_id** | **str**| The ID of the assistant to delete. | 

### Return type

[**DeleteAssistantResponse**](DeleteAssistantResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete_message**
> DeleteMessageResponse delete_message(thread_id, message_id)

Deletes a message.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread to which this message belongs.
message_id = 'message_id_example' # str | The ID of the message to delete.

try:
    # Deletes a message.
    api_response = api_instance.delete_message(thread_id, message_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->delete_message: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread to which this message belongs. | 
 **message_id** | **str**| The ID of the message to delete. | 

### Return type

[**DeleteMessageResponse**](DeleteMessageResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete_thread**
> DeleteThreadResponse delete_thread(thread_id)

Delete a thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread to delete.

try:
    # Delete a thread.
    api_response = api_instance.delete_thread(thread_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->delete_thread: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread to delete. | 

### Return type

[**DeleteThreadResponse**](DeleteThreadResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_assistant**
> AssistantObject get_assistant(assistant_id)

Retrieves an assistant.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
assistant_id = 'assistant_id_example' # str | The ID of the assistant to retrieve.

try:
    # Retrieves an assistant.
    api_response = api_instance.get_assistant(assistant_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->get_assistant: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **assistant_id** | **str**| The ID of the assistant to retrieve. | 

### Return type

[**AssistantObject**](AssistantObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_message**
> MessageObject get_message(thread_id, message_id)

Retrieve a message.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) to which this message belongs.
message_id = 'message_id_example' # str | The ID of the message to retrieve.

try:
    # Retrieve a message.
    api_response = api_instance.get_message(thread_id, message_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->get_message: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) to which this message belongs. | 
 **message_id** | **str**| The ID of the message to retrieve. | 

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_run**
> RunObject get_run(thread_id, run_id)

Retrieves a run.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) that was run.
run_id = 'run_id_example' # str | The ID of the run to retrieve.

try:
    # Retrieves a run.
    api_response = api_instance.get_run(thread_id, run_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->get_run: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) that was run. | 
 **run_id** | **str**| The ID of the run to retrieve. | 

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_run_step**
> RunStepObject get_run_step(thread_id, run_id, step_id)

Retrieves a run step.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread to which the run and run step belongs.
run_id = 'run_id_example' # str | The ID of the run to which the run step belongs.
step_id = 'step_id_example' # str | The ID of the run step to retrieve.

try:
    # Retrieves a run step.
    api_response = api_instance.get_run_step(thread_id, run_id, step_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->get_run_step: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread to which the run and run step belongs. | 
 **run_id** | **str**| The ID of the run to which the run step belongs. | 
 **step_id** | **str**| The ID of the run step to retrieve. | 

### Return type

[**RunStepObject**](RunStepObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_thread**
> ThreadObject get_thread(thread_id)

Retrieves a thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread to retrieve.

try:
    # Retrieves a thread.
    api_response = api_instance.get_thread(thread_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->get_thread: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread to retrieve. | 

### Return type

[**ThreadObject**](ThreadObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_assistants**
> ListAssistantsResponse list_assistants(limit=limit, order=order, after=after, before=before)

Returns a list of assistants.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
limit = 20 # int | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional) (default to 20)
order = 'desc' # str | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order.  (optional) (default to desc)
after = 'after_example' # str | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list.  (optional)
before = 'before_example' # str | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list.  (optional)

try:
    # Returns a list of assistants.
    api_response = api_instance.list_assistants(limit=limit, order=order, after=after, before=before)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->list_assistants: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **int**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **str**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc]
 **after** | **str**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional] 
 **before** | **str**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional] 

### Return type

[**ListAssistantsResponse**](ListAssistantsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_messages**
> ListMessagesResponse list_messages(thread_id, limit=limit, order=order, after=after, before=before, run_id=run_id)

Returns a list of messages for a given thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) the messages belong to.
limit = 20 # int | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional) (default to 20)
order = 'desc' # str | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order.  (optional) (default to desc)
after = 'after_example' # str | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list.  (optional)
before = 'before_example' # str | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list.  (optional)
run_id = 'run_id_example' # str | Filter messages by the run ID that generated them.  (optional)

try:
    # Returns a list of messages for a given thread.
    api_response = api_instance.list_messages(thread_id, limit=limit, order=order, after=after, before=before, run_id=run_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->list_messages: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) the messages belong to. | 
 **limit** | **int**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **str**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc]
 **after** | **str**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional] 
 **before** | **str**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional] 
 **run_id** | **str**| Filter messages by the run ID that generated them.  | [optional] 

### Return type

[**ListMessagesResponse**](ListMessagesResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_run_steps**
> ListRunStepsResponse list_run_steps(thread_id, run_id, limit=limit, order=order, after=after, before=before)

Returns a list of run steps belonging to a run.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread the run and run steps belong to.
run_id = 'run_id_example' # str | The ID of the run the run steps belong to.
limit = 20 # int | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional) (default to 20)
order = 'desc' # str | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order.  (optional) (default to desc)
after = 'after_example' # str | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list.  (optional)
before = 'before_example' # str | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list.  (optional)

try:
    # Returns a list of run steps belonging to a run.
    api_response = api_instance.list_run_steps(thread_id, run_id, limit=limit, order=order, after=after, before=before)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->list_run_steps: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread the run and run steps belong to. | 
 **run_id** | **str**| The ID of the run the run steps belong to. | 
 **limit** | **int**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **str**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc]
 **after** | **str**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional] 
 **before** | **str**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional] 

### Return type

[**ListRunStepsResponse**](ListRunStepsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **list_runs**
> ListRunsResponse list_runs(thread_id, limit=limit, order=order, after=after, before=before)

Returns a list of runs belonging to a thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
thread_id = 'thread_id_example' # str | The ID of the thread the run belongs to.
limit = 20 # int | A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  (optional) (default to 20)
order = 'desc' # str | Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order.  (optional) (default to desc)
after = 'after_example' # str | A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list.  (optional)
before = 'before_example' # str | A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list.  (optional)

try:
    # Returns a list of runs belonging to a thread.
    api_response = api_instance.list_runs(thread_id, limit=limit, order=order, after=after, before=before)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->list_runs: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **thread_id** | **str**| The ID of the thread the run belongs to. | 
 **limit** | **int**| A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20.  | [optional] [default to 20]
 **order** | **str**| Sort order by the &#x60;created_at&#x60; timestamp of the objects. &#x60;asc&#x60; for ascending order and &#x60;desc&#x60; for descending order.  | [optional] [default to desc]
 **after** | **str**| A cursor for use in pagination. &#x60;after&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after&#x3D;obj_foo in order to fetch the next page of the list.  | [optional] 
 **before** | **str**| A cursor for use in pagination. &#x60;before&#x60; is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before&#x3D;obj_foo in order to fetch the previous page of the list.  | [optional] 

### Return type

[**ListRunsResponse**](ListRunsResponse.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **modify_assistant**
> AssistantObject modify_assistant(body, assistant_id)

Modifies an assistant.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.ModifyAssistantRequest() # ModifyAssistantRequest | 
assistant_id = 'assistant_id_example' # str | The ID of the assistant to modify.

try:
    # Modifies an assistant.
    api_response = api_instance.modify_assistant(body, assistant_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->modify_assistant: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyAssistantRequest**](ModifyAssistantRequest.md)|  | 
 **assistant_id** | **str**| The ID of the assistant to modify. | 

### Return type

[**AssistantObject**](AssistantObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **modify_message**
> MessageObject modify_message(body, thread_id, message_id)

Modifies a message.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.ModifyMessageRequest() # ModifyMessageRequest | 
thread_id = 'thread_id_example' # str | The ID of the thread to which this message belongs.
message_id = 'message_id_example' # str | The ID of the message to modify.

try:
    # Modifies a message.
    api_response = api_instance.modify_message(body, thread_id, message_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->modify_message: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyMessageRequest**](ModifyMessageRequest.md)|  | 
 **thread_id** | **str**| The ID of the thread to which this message belongs. | 
 **message_id** | **str**| The ID of the message to modify. | 

### Return type

[**MessageObject**](MessageObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **modify_run**
> RunObject modify_run(body, thread_id, run_id)

Modifies a run.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.ModifyRunRequest() # ModifyRunRequest | 
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) that was run.
run_id = 'run_id_example' # str | The ID of the run to modify.

try:
    # Modifies a run.
    api_response = api_instance.modify_run(body, thread_id, run_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->modify_run: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyRunRequest**](ModifyRunRequest.md)|  | 
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) that was run. | 
 **run_id** | **str**| The ID of the run to modify. | 

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **modify_thread**
> ThreadObject modify_thread(body, thread_id)

Modifies a thread.

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.ModifyThreadRequest() # ModifyThreadRequest | 
thread_id = 'thread_id_example' # str | The ID of the thread to modify. Only the `metadata` can be modified.

try:
    # Modifies a thread.
    api_response = api_instance.modify_thread(body, thread_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->modify_thread: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ModifyThreadRequest**](ModifyThreadRequest.md)|  | 
 **thread_id** | **str**| The ID of the thread to modify. Only the &#x60;metadata&#x60; can be modified. | 

### Return type

[**ThreadObject**](ThreadObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **submit_tool_ouputs_to_run**
> RunObject submit_tool_ouputs_to_run(body, thread_id, run_id)

When a run has the `status: \"requires_action\"` and `required_action.type` is `submit_tool_outputs`, this endpoint can be used to submit the outputs from the tool calls once they're all completed. All outputs must be submitted in a single request. 

### Example
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint


# create an instance of the API class
api_instance = swagger_client.AssistantsApi(swagger_client.ApiClient(configuration))
body = swagger_client.SubmitToolOutputsRunRequest() # SubmitToolOutputsRunRequest | 
thread_id = 'thread_id_example' # str | The ID of the [thread](/docs/api-reference/threads) to which this run belongs.
run_id = 'run_id_example' # str | The ID of the run that requires the tool output submission.

try:
    # When a run has the `status: \"requires_action\"` and `required_action.type` is `submit_tool_outputs`, this endpoint can be used to submit the outputs from the tool calls once they're all completed. All outputs must be submitted in a single request. 
    api_response = api_instance.submit_tool_ouputs_to_run(body, thread_id, run_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling AssistantsApi->submit_tool_ouputs_to_run: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SubmitToolOutputsRunRequest**](SubmitToolOutputsRunRequest.md)|  | 
 **thread_id** | **str**| The ID of the [thread](/docs/api-reference/threads) to which this run belongs. | 
 **run_id** | **str**| The ID of the run that requires the tool output submission. | 

### Return type

[**RunObject**](RunObject.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

