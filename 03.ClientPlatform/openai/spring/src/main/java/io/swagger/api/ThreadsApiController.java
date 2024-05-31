package io.swagger.api;

import io.swagger.model.CreateMessageRequest;
import io.swagger.model.CreateRunRequest;
import io.swagger.model.CreateThreadAndRunRequest;
import io.swagger.model.CreateThreadRequest;
import io.swagger.model.DeleteMessageResponse;
import io.swagger.model.DeleteThreadResponse;
import io.swagger.model.ListMessagesResponse;
import io.swagger.model.ListRunStepsResponse;
import io.swagger.model.ListRunsResponse;
import io.swagger.model.MessageObject;
import io.swagger.model.ModifyMessageRequest;
import io.swagger.model.ModifyRunRequest;
import io.swagger.model.ModifyThreadRequest;
import io.swagger.model.RunObject;
import io.swagger.model.RunStepObject;
import io.swagger.model.SubmitToolOutputsRunRequest;
import io.swagger.model.ThreadObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")
@RestController
public class ThreadsApiController implements ThreadsApi {

    private static final Logger log = LoggerFactory.getLogger(ThreadsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ThreadsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<RunObject> cancelRun(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to which this run belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run to cancel.", required=true, schema=@Schema()) @PathVariable("run_id") String runId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MessageObject> createMessage(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) to create a message for.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateMessageRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<MessageObject>(objectMapper.readValue("{\n  \"metadata\" : { },\n  \"role\" : \"user\",\n  \"assistant_id\" : \"assistant_id\",\n  \"run_id\" : \"run_id\",\n  \"attachments\" : [ {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  }, {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  } ],\n  \"created_at\" : 0,\n  \"content\" : [ \"\", \"\" ],\n  \"completed_at\" : 6,\n  \"thread_id\" : \"thread_id\",\n  \"id\" : \"id\",\n  \"incomplete_at\" : 1,\n  \"incomplete_details\" : {\n    \"reason\" : \"content_filter\"\n  },\n  \"object\" : \"thread.message\",\n  \"status\" : \"in_progress\"\n}", MessageObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<MessageObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MessageObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunObject> createRun(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to run.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateRunRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ThreadObject> createThread(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody CreateThreadRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ThreadObject>(objectMapper.readValue("{\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"thread\"\n}", ThreadObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ThreadObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ThreadObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunObject> createThreadAndRun(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateThreadAndRunRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteMessageResponse> deleteMessage(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to which this message belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the message to delete.", required=true, schema=@Schema()) @PathVariable("message_id") String messageId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteMessageResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"thread.message.deleted\"\n}", DeleteMessageResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteMessageResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteMessageResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<DeleteThreadResponse> deleteThread(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to delete.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<DeleteThreadResponse>(objectMapper.readValue("{\n  \"deleted\" : true,\n  \"id\" : \"id\",\n  \"object\" : \"thread.deleted\"\n}", DeleteThreadResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<DeleteThreadResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<DeleteThreadResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MessageObject> getMessage(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) to which this message belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the message to retrieve.", required=true, schema=@Schema()) @PathVariable("message_id") String messageId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<MessageObject>(objectMapper.readValue("{\n  \"metadata\" : { },\n  \"role\" : \"user\",\n  \"assistant_id\" : \"assistant_id\",\n  \"run_id\" : \"run_id\",\n  \"attachments\" : [ {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  }, {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  } ],\n  \"created_at\" : 0,\n  \"content\" : [ \"\", \"\" ],\n  \"completed_at\" : 6,\n  \"thread_id\" : \"thread_id\",\n  \"id\" : \"id\",\n  \"incomplete_at\" : 1,\n  \"incomplete_details\" : {\n    \"reason\" : \"content_filter\"\n  },\n  \"object\" : \"thread.message\",\n  \"status\" : \"in_progress\"\n}", MessageObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<MessageObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MessageObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunObject> getRun(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) that was run.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run to retrieve.", required=true, schema=@Schema()) @PathVariable("run_id") String runId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunStepObject> getRunStep(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to which the run and run step belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run to which the run step belongs.", required=true, schema=@Schema()) @PathVariable("run_id") String runId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run step to retrieve.", required=true, schema=@Schema()) @PathVariable("step_id") String stepId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunStepObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 1,\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"run_id\" : \"run_id\",\n  \"usage\" : {\n    \"completion_tokens\" : 2,\n    \"prompt_tokens\" : 7,\n    \"total_tokens\" : 9\n  },\n  \"created_at\" : 0,\n  \"expired_at\" : 6,\n  \"type\" : \"message_creation\",\n  \"step_details\" : \"\",\n  \"completed_at\" : 5,\n  \"thread_id\" : \"thread_id\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"failed_at\" : 5,\n  \"object\" : \"thread.run.step\",\n  \"status\" : \"in_progress\"\n}", RunStepObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunStepObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunStepObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ThreadObject> getThread(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to retrieve.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ThreadObject>(objectMapper.readValue("{\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"thread\"\n}", ThreadObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ThreadObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ThreadObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListMessagesResponse> listMessages(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) the messages belong to.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before,@Parameter(in = ParameterIn.QUERY, description = "Filter messages by the run ID that generated them. " ,schema=@Schema()) @Valid @RequestParam(value = "run_id", required = false) String runId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListMessagesResponse>(objectMapper.readValue("{\n  \"first_id\" : \"msg_abc123\",\n  \"data\" : [ {\n    \"metadata\" : { },\n    \"role\" : \"user\",\n    \"assistant_id\" : \"assistant_id\",\n    \"run_id\" : \"run_id\",\n    \"attachments\" : [ {\n      \"file_id\" : \"file_id\",\n      \"tools\" : [ \"\", \"\" ]\n    }, {\n      \"file_id\" : \"file_id\",\n      \"tools\" : [ \"\", \"\" ]\n    } ],\n    \"created_at\" : 0,\n    \"content\" : [ \"\", \"\" ],\n    \"completed_at\" : 6,\n    \"thread_id\" : \"thread_id\",\n    \"id\" : \"id\",\n    \"incomplete_at\" : 1,\n    \"incomplete_details\" : {\n      \"reason\" : \"content_filter\"\n    },\n    \"object\" : \"thread.message\",\n    \"status\" : \"in_progress\"\n  }, {\n    \"metadata\" : { },\n    \"role\" : \"user\",\n    \"assistant_id\" : \"assistant_id\",\n    \"run_id\" : \"run_id\",\n    \"attachments\" : [ {\n      \"file_id\" : \"file_id\",\n      \"tools\" : [ \"\", \"\" ]\n    }, {\n      \"file_id\" : \"file_id\",\n      \"tools\" : [ \"\", \"\" ]\n    } ],\n    \"created_at\" : 0,\n    \"content\" : [ \"\", \"\" ],\n    \"completed_at\" : 6,\n    \"thread_id\" : \"thread_id\",\n    \"id\" : \"id\",\n    \"incomplete_at\" : 1,\n    \"incomplete_details\" : {\n      \"reason\" : \"content_filter\"\n    },\n    \"object\" : \"thread.message\",\n    \"status\" : \"in_progress\"\n  } ],\n  \"last_id\" : \"msg_abc123\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListMessagesResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListMessagesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListMessagesResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListRunStepsResponse> listRunSteps(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread the run and run steps belong to.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run the run steps belong to.", required=true, schema=@Schema()) @PathVariable("run_id") String runId,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListRunStepsResponse>(objectMapper.readValue("{\n  \"first_id\" : \"step_abc123\",\n  \"data\" : [ {\n    \"cancelled_at\" : 1,\n    \"metadata\" : { },\n    \"assistant_id\" : \"assistant_id\",\n    \"run_id\" : \"run_id\",\n    \"usage\" : {\n      \"completion_tokens\" : 2,\n      \"prompt_tokens\" : 7,\n      \"total_tokens\" : 9\n    },\n    \"created_at\" : 0,\n    \"expired_at\" : 6,\n    \"type\" : \"message_creation\",\n    \"step_details\" : \"\",\n    \"completed_at\" : 5,\n    \"thread_id\" : \"thread_id\",\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"server_error\",\n      \"message\" : \"message\"\n    },\n    \"failed_at\" : 5,\n    \"object\" : \"thread.run.step\",\n    \"status\" : \"in_progress\"\n  }, {\n    \"cancelled_at\" : 1,\n    \"metadata\" : { },\n    \"assistant_id\" : \"assistant_id\",\n    \"run_id\" : \"run_id\",\n    \"usage\" : {\n      \"completion_tokens\" : 2,\n      \"prompt_tokens\" : 7,\n      \"total_tokens\" : 9\n    },\n    \"created_at\" : 0,\n    \"expired_at\" : 6,\n    \"type\" : \"message_creation\",\n    \"step_details\" : \"\",\n    \"completed_at\" : 5,\n    \"thread_id\" : \"thread_id\",\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"server_error\",\n      \"message\" : \"message\"\n    },\n    \"failed_at\" : 5,\n    \"object\" : \"thread.run.step\",\n    \"status\" : \"in_progress\"\n  } ],\n  \"last_id\" : \"step_abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListRunStepsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListRunStepsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListRunStepsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ListRunsResponse> listRuns(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread the run belongs to.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.QUERY, description = "A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 20. " ,schema=@Schema( defaultValue="20")) @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit,@Parameter(in = ParameterIn.QUERY, description = "Sort order by the `created_at` timestamp of the objects. `asc` for ascending order and `desc` for descending order. " ,schema=@Schema(allowableValues={ "asc", "desc" }
, defaultValue="desc")) @Valid @RequestParam(value = "order", required = false, defaultValue="desc") String order,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `after` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include after=obj_foo in order to fetch the next page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "after", required = false) String after,@Parameter(in = ParameterIn.QUERY, description = "A cursor for use in pagination. `before` is an object ID that defines your place in the list. For instance, if you make a list request and receive 100 objects, ending with obj_foo, your subsequent call can include before=obj_foo in order to fetch the previous page of the list. " ,schema=@Schema()) @Valid @RequestParam(value = "before", required = false) String before) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ListRunsResponse>(objectMapper.readValue("{\n  \"first_id\" : \"run_abc123\",\n  \"data\" : [ {\n    \"cancelled_at\" : 5,\n    \"instructions\" : \"instructions\",\n    \"metadata\" : { },\n    \"assistant_id\" : \"assistant_id\",\n    \"required_action\" : {\n      \"submit_tool_outputs\" : {\n        \"tool_calls\" : [ {\n          \"function\" : {\n            \"name\" : \"name\",\n            \"arguments\" : \"arguments\"\n          },\n          \"id\" : \"id\",\n          \"type\" : \"function\"\n        }, {\n          \"function\" : {\n            \"name\" : \"name\",\n            \"arguments\" : \"arguments\"\n          },\n          \"id\" : \"id\",\n          \"type\" : \"function\"\n        } ]\n      },\n      \"type\" : \"submit_tool_outputs\"\n    },\n    \"usage\" : {\n      \"completion_tokens\" : 7,\n      \"prompt_tokens\" : 9,\n      \"total_tokens\" : 3\n    },\n    \"created_at\" : 0,\n    \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n    \"top_p\" : 4.145608029883936,\n    \"max_completion_tokens\" : 256,\n    \"thread_id\" : \"thread_id\",\n    \"expires_at\" : 6,\n    \"response_format\" : \"\",\n    \"temperature\" : 2.027123023002322,\n    \"tool_choice\" : \"\",\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"server_error\",\n      \"message\" : \"message\"\n    },\n    \"incomplete_details\" : {\n      \"reason\" : \"max_completion_tokens\"\n    },\n    \"truncation_strategy\" : {\n      \"last_messages\" : 1,\n      \"type\" : \"auto\"\n    },\n    \"completed_at\" : 2,\n    \"started_at\" : 1,\n    \"failed_at\" : 5,\n    \"max_prompt_tokens\" : 256,\n    \"object\" : \"thread.run\",\n    \"status\" : \"queued\"\n  }, {\n    \"cancelled_at\" : 5,\n    \"instructions\" : \"instructions\",\n    \"metadata\" : { },\n    \"assistant_id\" : \"assistant_id\",\n    \"required_action\" : {\n      \"submit_tool_outputs\" : {\n        \"tool_calls\" : [ {\n          \"function\" : {\n            \"name\" : \"name\",\n            \"arguments\" : \"arguments\"\n          },\n          \"id\" : \"id\",\n          \"type\" : \"function\"\n        }, {\n          \"function\" : {\n            \"name\" : \"name\",\n            \"arguments\" : \"arguments\"\n          },\n          \"id\" : \"id\",\n          \"type\" : \"function\"\n        } ]\n      },\n      \"type\" : \"submit_tool_outputs\"\n    },\n    \"usage\" : {\n      \"completion_tokens\" : 7,\n      \"prompt_tokens\" : 9,\n      \"total_tokens\" : 3\n    },\n    \"created_at\" : 0,\n    \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n    \"top_p\" : 4.145608029883936,\n    \"max_completion_tokens\" : 256,\n    \"thread_id\" : \"thread_id\",\n    \"expires_at\" : 6,\n    \"response_format\" : \"\",\n    \"temperature\" : 2.027123023002322,\n    \"tool_choice\" : \"\",\n    \"model\" : \"model\",\n    \"id\" : \"id\",\n    \"last_error\" : {\n      \"code\" : \"server_error\",\n      \"message\" : \"message\"\n    },\n    \"incomplete_details\" : {\n      \"reason\" : \"max_completion_tokens\"\n    },\n    \"truncation_strategy\" : {\n      \"last_messages\" : 1,\n      \"type\" : \"auto\"\n    },\n    \"completed_at\" : 2,\n    \"started_at\" : 1,\n    \"failed_at\" : 5,\n    \"max_prompt_tokens\" : 256,\n    \"object\" : \"thread.run\",\n    \"status\" : \"queued\"\n  } ],\n  \"last_id\" : \"run_abc456\",\n  \"has_more\" : false,\n  \"object\" : \"list\"\n}", ListRunsResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ListRunsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ListRunsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<MessageObject> modifyMessage(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to which this message belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the message to modify.", required=true, schema=@Schema()) @PathVariable("message_id") String messageId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody ModifyMessageRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<MessageObject>(objectMapper.readValue("{\n  \"metadata\" : { },\n  \"role\" : \"user\",\n  \"assistant_id\" : \"assistant_id\",\n  \"run_id\" : \"run_id\",\n  \"attachments\" : [ {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  }, {\n    \"file_id\" : \"file_id\",\n    \"tools\" : [ \"\", \"\" ]\n  } ],\n  \"created_at\" : 0,\n  \"content\" : [ \"\", \"\" ],\n  \"completed_at\" : 6,\n  \"thread_id\" : \"thread_id\",\n  \"id\" : \"id\",\n  \"incomplete_at\" : 1,\n  \"incomplete_details\" : {\n    \"reason\" : \"content_filter\"\n  },\n  \"object\" : \"thread.message\",\n  \"status\" : \"in_progress\"\n}", MessageObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<MessageObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MessageObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunObject> modifyRun(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) that was run.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run to modify.", required=true, schema=@Schema()) @PathVariable("run_id") String runId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody ModifyRunRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ThreadObject> modifyThread(@Parameter(in = ParameterIn.PATH, description = "The ID of the thread to modify. Only the `metadata` can be modified.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody ModifyThreadRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ThreadObject>(objectMapper.readValue("{\n  \"tool_resources\" : {\n    \"code_interpreter\" : {\n      \"file_ids\" : [ \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\", \"file_ids\" ]\n    },\n    \"file_search\" : {\n      \"vector_store_ids\" : [ \"vector_store_ids\" ]\n    }\n  },\n  \"metadata\" : { },\n  \"created_at\" : 0,\n  \"id\" : \"id\",\n  \"object\" : \"thread\"\n}", ThreadObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ThreadObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ThreadObject>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<RunObject> submitToolOuputsToRun(@Parameter(in = ParameterIn.PATH, description = "The ID of the [thread](/docs/api-reference/threads) to which this run belongs.", required=true, schema=@Schema()) @PathVariable("thread_id") String threadId,@Parameter(in = ParameterIn.PATH, description = "The ID of the run that requires the tool output submission.", required=true, schema=@Schema()) @PathVariable("run_id") String runId,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody SubmitToolOutputsRunRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<RunObject>(objectMapper.readValue("{\n  \"cancelled_at\" : 5,\n  \"instructions\" : \"instructions\",\n  \"metadata\" : { },\n  \"assistant_id\" : \"assistant_id\",\n  \"required_action\" : {\n    \"submit_tool_outputs\" : {\n      \"tool_calls\" : [ {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      }, {\n        \"function\" : {\n          \"name\" : \"name\",\n          \"arguments\" : \"arguments\"\n        },\n        \"id\" : \"id\",\n        \"type\" : \"function\"\n      } ]\n    },\n    \"type\" : \"submit_tool_outputs\"\n  },\n  \"usage\" : {\n    \"completion_tokens\" : 7,\n    \"prompt_tokens\" : 9,\n    \"total_tokens\" : 3\n  },\n  \"created_at\" : 0,\n  \"tools\" : [ \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\", \"\" ],\n  \"top_p\" : 4.145608029883936,\n  \"max_completion_tokens\" : 256,\n  \"thread_id\" : \"thread_id\",\n  \"expires_at\" : 6,\n  \"response_format\" : \"\",\n  \"temperature\" : 2.027123023002322,\n  \"tool_choice\" : \"\",\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"last_error\" : {\n    \"code\" : \"server_error\",\n    \"message\" : \"message\"\n  },\n  \"incomplete_details\" : {\n    \"reason\" : \"max_completion_tokens\"\n  },\n  \"truncation_strategy\" : {\n    \"last_messages\" : 1,\n    \"type\" : \"auto\"\n  },\n  \"completed_at\" : 2,\n  \"started_at\" : 1,\n  \"failed_at\" : 5,\n  \"max_prompt_tokens\" : 256,\n  \"object\" : \"thread.run\",\n  \"status\" : \"queued\"\n}", RunObject.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<RunObject>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<RunObject>(HttpStatus.NOT_IMPLEMENTED);
    }

}
