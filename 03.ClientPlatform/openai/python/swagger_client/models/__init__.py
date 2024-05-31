# coding: utf-8

# flake8: noqa
"""
    OpenAI API

    The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.  # noqa: E501

    OpenAPI spec version: 2.0.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

from __future__ import absolute_import

# import models into model package
from swagger_client.models.any_of_create_assistant_request_model import AnyOfCreateAssistantRequestModel
from swagger_client.models.any_of_create_chat_completion_request_model import AnyOfCreateChatCompletionRequestModel
from swagger_client.models.any_of_create_completion_request_model import AnyOfCreateCompletionRequestModel
from swagger_client.models.any_of_create_embedding_request_model import AnyOfCreateEmbeddingRequestModel
from swagger_client.models.any_of_create_fine_tuning_job_request_model import AnyOfCreateFineTuningJobRequestModel
from swagger_client.models.any_of_create_image_edit_request_model import AnyOfCreateImageEditRequestModel
from swagger_client.models.any_of_create_image_request_model import AnyOfCreateImageRequestModel
from swagger_client.models.any_of_create_image_variation_request_model import AnyOfCreateImageVariationRequestModel
from swagger_client.models.any_of_create_moderation_request_model import AnyOfCreateModerationRequestModel
from swagger_client.models.any_of_create_run_request_model import AnyOfCreateRunRequestModel
from swagger_client.models.any_of_create_speech_request_model import AnyOfCreateSpeechRequestModel
from swagger_client.models.any_of_create_thread_and_run_request_model import AnyOfCreateThreadAndRunRequestModel
from swagger_client.models.any_of_create_transcription_request_model import AnyOfCreateTranscriptionRequestModel
from swagger_client.models.any_of_create_translation_request_model import AnyOfCreateTranslationRequestModel
from swagger_client.models.any_of_modify_assistant_request_model import AnyOfModifyAssistantRequestModel
from swagger_client.models.assistant_object import AssistantObject
from swagger_client.models.assistant_object_tool_resources import AssistantObjectToolResources
from swagger_client.models.assistant_object_tool_resources_code_interpreter import AssistantObjectToolResourcesCodeInterpreter
from swagger_client.models.assistant_object_tool_resources_file_search import AssistantObjectToolResourcesFileSearch
from swagger_client.models.assistant_stream_event import AssistantStreamEvent
from swagger_client.models.assistant_tools_code import AssistantToolsCode
from swagger_client.models.assistant_tools_file_search import AssistantToolsFileSearch
from swagger_client.models.assistant_tools_function import AssistantToolsFunction
from swagger_client.models.assistants_api_response_format import AssistantsApiResponseFormat
from swagger_client.models.assistants_api_response_format_option import AssistantsApiResponseFormatOption
from swagger_client.models.assistants_api_tool_choice_option import AssistantsApiToolChoiceOption
from swagger_client.models.assistants_named_tool_choice import AssistantsNamedToolChoice
from swagger_client.models.batch import Batch
from swagger_client.models.batch_errors import BatchErrors
from swagger_client.models.batch_errors_data import BatchErrorsData
from swagger_client.models.batch_request_counts import BatchRequestCounts
from swagger_client.models.batch_request_input import BatchRequestInput
from swagger_client.models.batch_request_output import BatchRequestOutput
from swagger_client.models.batch_request_output_error import BatchRequestOutputError
from swagger_client.models.batch_request_output_response import BatchRequestOutputResponse
from swagger_client.models.batches_body import BatchesBody
from swagger_client.models.chat_completion_function_call_option import ChatCompletionFunctionCallOption
from swagger_client.models.chat_completion_functions import ChatCompletionFunctions
from swagger_client.models.chat_completion_message_tool_call import ChatCompletionMessageToolCall
from swagger_client.models.chat_completion_message_tool_call_chunk import ChatCompletionMessageToolCallChunk
from swagger_client.models.chat_completion_message_tool_call_chunk_function import ChatCompletionMessageToolCallChunkFunction
from swagger_client.models.chat_completion_message_tool_call_function import ChatCompletionMessageToolCallFunction
from swagger_client.models.chat_completion_message_tool_calls import ChatCompletionMessageToolCalls
from swagger_client.models.chat_completion_named_tool_choice import ChatCompletionNamedToolChoice
from swagger_client.models.chat_completion_named_tool_choice_function import ChatCompletionNamedToolChoiceFunction
from swagger_client.models.chat_completion_request_assistant_message import ChatCompletionRequestAssistantMessage
from swagger_client.models.chat_completion_request_assistant_message_function_call import ChatCompletionRequestAssistantMessageFunctionCall
from swagger_client.models.chat_completion_request_function_message import ChatCompletionRequestFunctionMessage
from swagger_client.models.chat_completion_request_message import ChatCompletionRequestMessage
from swagger_client.models.chat_completion_request_message_content_part import ChatCompletionRequestMessageContentPart
from swagger_client.models.chat_completion_request_message_content_part_image import ChatCompletionRequestMessageContentPartImage
from swagger_client.models.chat_completion_request_message_content_part_image_image_url import ChatCompletionRequestMessageContentPartImageImageUrl
from swagger_client.models.chat_completion_request_message_content_part_text import ChatCompletionRequestMessageContentPartText
from swagger_client.models.chat_completion_request_system_message import ChatCompletionRequestSystemMessage
from swagger_client.models.chat_completion_request_tool_message import ChatCompletionRequestToolMessage
from swagger_client.models.chat_completion_request_user_message import ChatCompletionRequestUserMessage
from swagger_client.models.chat_completion_response_message import ChatCompletionResponseMessage
from swagger_client.models.chat_completion_response_message_function_call import ChatCompletionResponseMessageFunctionCall
from swagger_client.models.chat_completion_role import ChatCompletionRole
from swagger_client.models.chat_completion_stream_options import ChatCompletionStreamOptions
from swagger_client.models.chat_completion_stream_response_delta import ChatCompletionStreamResponseDelta
from swagger_client.models.chat_completion_stream_response_delta_function_call import ChatCompletionStreamResponseDeltaFunctionCall
from swagger_client.models.chat_completion_token_logprob import ChatCompletionTokenLogprob
from swagger_client.models.chat_completion_token_logprob_top_logprobs import ChatCompletionTokenLogprobTopLogprobs
from swagger_client.models.chat_completion_tool import ChatCompletionTool
from swagger_client.models.chat_completion_tool_choice_option import ChatCompletionToolChoiceOption
from swagger_client.models.completion_usage import CompletionUsage
from swagger_client.models.create_assistant_request import CreateAssistantRequest
from swagger_client.models.create_assistant_request_tool_resources import CreateAssistantRequestToolResources
from swagger_client.models.create_assistant_request_tool_resources_code_interpreter import CreateAssistantRequestToolResourcesCodeInterpreter
from swagger_client.models.create_assistant_request_tool_resources_file_search import CreateAssistantRequestToolResourcesFileSearch
from swagger_client.models.create_assistant_request_tool_resources_file_search_vector_stores import CreateAssistantRequestToolResourcesFileSearchVectorStores
from swagger_client.models.create_chat_completion_function_response import CreateChatCompletionFunctionResponse
from swagger_client.models.create_chat_completion_function_response_choices import CreateChatCompletionFunctionResponseChoices
from swagger_client.models.create_chat_completion_image_response import CreateChatCompletionImageResponse
from swagger_client.models.create_chat_completion_request import CreateChatCompletionRequest
from swagger_client.models.create_chat_completion_request_response_format import CreateChatCompletionRequestResponseFormat
from swagger_client.models.create_chat_completion_response import CreateChatCompletionResponse
from swagger_client.models.create_chat_completion_response_choices import CreateChatCompletionResponseChoices
from swagger_client.models.create_chat_completion_response_logprobs import CreateChatCompletionResponseLogprobs
from swagger_client.models.create_chat_completion_stream_response import CreateChatCompletionStreamResponse
from swagger_client.models.create_chat_completion_stream_response_choices import CreateChatCompletionStreamResponseChoices
from swagger_client.models.create_chat_completion_stream_response_usage import CreateChatCompletionStreamResponseUsage
from swagger_client.models.create_completion_request import CreateCompletionRequest
from swagger_client.models.create_completion_response import CreateCompletionResponse
from swagger_client.models.create_completion_response_choices import CreateCompletionResponseChoices
from swagger_client.models.create_completion_response_logprobs import CreateCompletionResponseLogprobs
from swagger_client.models.create_embedding_request import CreateEmbeddingRequest
from swagger_client.models.create_embedding_response import CreateEmbeddingResponse
from swagger_client.models.create_embedding_response_usage import CreateEmbeddingResponseUsage
from swagger_client.models.create_file_request import CreateFileRequest
from swagger_client.models.create_fine_tuning_job_request import CreateFineTuningJobRequest
from swagger_client.models.create_fine_tuning_job_request_hyperparameters import CreateFineTuningJobRequestHyperparameters
from swagger_client.models.create_fine_tuning_job_request_integrations import CreateFineTuningJobRequestIntegrations
from swagger_client.models.create_fine_tuning_job_request_wandb import CreateFineTuningJobRequestWandb
from swagger_client.models.create_image_edit_request import CreateImageEditRequest
from swagger_client.models.create_image_request import CreateImageRequest
from swagger_client.models.create_image_variation_request import CreateImageVariationRequest
from swagger_client.models.create_message_request import CreateMessageRequest
from swagger_client.models.create_moderation_request import CreateModerationRequest
from swagger_client.models.create_moderation_response import CreateModerationResponse
from swagger_client.models.create_moderation_response_categories import CreateModerationResponseCategories
from swagger_client.models.create_moderation_response_category_scores import CreateModerationResponseCategoryScores
from swagger_client.models.create_moderation_response_results import CreateModerationResponseResults
from swagger_client.models.create_run_request import CreateRunRequest
from swagger_client.models.create_speech_request import CreateSpeechRequest
from swagger_client.models.create_thread_and_run_request import CreateThreadAndRunRequest
from swagger_client.models.create_thread_and_run_request_tool_resources import CreateThreadAndRunRequestToolResources
from swagger_client.models.create_thread_request import CreateThreadRequest
from swagger_client.models.create_thread_request_tool_resources import CreateThreadRequestToolResources
from swagger_client.models.create_thread_request_tool_resources_file_search import CreateThreadRequestToolResourcesFileSearch
from swagger_client.models.create_transcription_request import CreateTranscriptionRequest
from swagger_client.models.create_transcription_response_json import CreateTranscriptionResponseJson
from swagger_client.models.create_transcription_response_verbose_json import CreateTranscriptionResponseVerboseJson
from swagger_client.models.create_translation_request import CreateTranslationRequest
from swagger_client.models.create_translation_response_json import CreateTranslationResponseJson
from swagger_client.models.create_translation_response_verbose_json import CreateTranslationResponseVerboseJson
from swagger_client.models.create_vector_store_file_batch_request import CreateVectorStoreFileBatchRequest
from swagger_client.models.create_vector_store_file_request import CreateVectorStoreFileRequest
from swagger_client.models.create_vector_store_request import CreateVectorStoreRequest
from swagger_client.models.delete_assistant_response import DeleteAssistantResponse
from swagger_client.models.delete_file_response import DeleteFileResponse
from swagger_client.models.delete_message_response import DeleteMessageResponse
from swagger_client.models.delete_model_response import DeleteModelResponse
from swagger_client.models.delete_thread_response import DeleteThreadResponse
from swagger_client.models.delete_vector_store_file_response import DeleteVectorStoreFileResponse
from swagger_client.models.delete_vector_store_response import DeleteVectorStoreResponse
from swagger_client.models.done_event import DoneEvent
from swagger_client.models.embedding import Embedding
from swagger_client.models.error import Error
from swagger_client.models.error_event import ErrorEvent
from swagger_client.models.error_response import ErrorResponse
from swagger_client.models.fine_tuning_integration import FineTuningIntegration
from swagger_client.models.fine_tuning_job import FineTuningJob
from swagger_client.models.fine_tuning_job_checkpoint import FineTuningJobCheckpoint
from swagger_client.models.fine_tuning_job_checkpoint_metrics import FineTuningJobCheckpointMetrics
from swagger_client.models.fine_tuning_job_error import FineTuningJobError
from swagger_client.models.fine_tuning_job_event import FineTuningJobEvent
from swagger_client.models.fine_tuning_job_hyperparameters import FineTuningJobHyperparameters
from swagger_client.models.function_object import FunctionObject
from swagger_client.models.function_parameters import FunctionParameters
from swagger_client.models.image import Image
from swagger_client.models.images_response import ImagesResponse
from swagger_client.models.inline_response200 import InlineResponse200
from swagger_client.models.inline_response2001 import InlineResponse2001
from swagger_client.models.list_assistants_response import ListAssistantsResponse
from swagger_client.models.list_batches_response import ListBatchesResponse
from swagger_client.models.list_files_response import ListFilesResponse
from swagger_client.models.list_fine_tuning_job_checkpoints_response import ListFineTuningJobCheckpointsResponse
from swagger_client.models.list_fine_tuning_job_events_response import ListFineTuningJobEventsResponse
from swagger_client.models.list_messages_response import ListMessagesResponse
from swagger_client.models.list_models_response import ListModelsResponse
from swagger_client.models.list_paginated_fine_tuning_jobs_response import ListPaginatedFineTuningJobsResponse
from swagger_client.models.list_run_steps_response import ListRunStepsResponse
from swagger_client.models.list_runs_response import ListRunsResponse
from swagger_client.models.list_threads_response import ListThreadsResponse
from swagger_client.models.list_vector_store_files_response import ListVectorStoreFilesResponse
from swagger_client.models.list_vector_stores_response import ListVectorStoresResponse
from swagger_client.models.message_content_image_file_object import MessageContentImageFileObject
from swagger_client.models.message_content_image_file_object_image_file import MessageContentImageFileObjectImageFile
from swagger_client.models.message_content_image_url_object import MessageContentImageUrlObject
from swagger_client.models.message_content_image_url_object_image_url import MessageContentImageUrlObjectImageUrl
from swagger_client.models.message_content_text_annotations_file_citation_object import MessageContentTextAnnotationsFileCitationObject
from swagger_client.models.message_content_text_annotations_file_citation_object_file_citation import MessageContentTextAnnotationsFileCitationObjectFileCitation
from swagger_client.models.message_content_text_annotations_file_path_object import MessageContentTextAnnotationsFilePathObject
from swagger_client.models.message_content_text_annotations_file_path_object_file_path import MessageContentTextAnnotationsFilePathObjectFilePath
from swagger_client.models.message_content_text_object import MessageContentTextObject
from swagger_client.models.message_content_text_object_text import MessageContentTextObjectText
from swagger_client.models.message_delta_content_image_file_object import MessageDeltaContentImageFileObject
from swagger_client.models.message_delta_content_image_file_object_image_file import MessageDeltaContentImageFileObjectImageFile
from swagger_client.models.message_delta_content_image_url_object import MessageDeltaContentImageUrlObject
from swagger_client.models.message_delta_content_image_url_object_image_url import MessageDeltaContentImageUrlObjectImageUrl
from swagger_client.models.message_delta_content_text_annotations_file_citation_object import MessageDeltaContentTextAnnotationsFileCitationObject
from swagger_client.models.message_delta_content_text_annotations_file_citation_object_file_citation import MessageDeltaContentTextAnnotationsFileCitationObjectFileCitation
from swagger_client.models.message_delta_content_text_annotations_file_path_object import MessageDeltaContentTextAnnotationsFilePathObject
from swagger_client.models.message_delta_content_text_annotations_file_path_object_file_path import MessageDeltaContentTextAnnotationsFilePathObjectFilePath
from swagger_client.models.message_delta_content_text_object import MessageDeltaContentTextObject
from swagger_client.models.message_delta_content_text_object_text import MessageDeltaContentTextObjectText
from swagger_client.models.message_delta_object import MessageDeltaObject
from swagger_client.models.message_delta_object_delta import MessageDeltaObjectDelta
from swagger_client.models.message_object import MessageObject
from swagger_client.models.message_object_attachments import MessageObjectAttachments
from swagger_client.models.message_object_incomplete_details import MessageObjectIncompleteDetails
from swagger_client.models.message_request_content_text_object import MessageRequestContentTextObject
from swagger_client.models.message_stream_event import MessageStreamEvent
from swagger_client.models.model import Model
from swagger_client.models.modify_assistant_request import ModifyAssistantRequest
from swagger_client.models.modify_assistant_request_tool_resources import ModifyAssistantRequestToolResources
from swagger_client.models.modify_assistant_request_tool_resources_code_interpreter import ModifyAssistantRequestToolResourcesCodeInterpreter
from swagger_client.models.modify_assistant_request_tool_resources_file_search import ModifyAssistantRequestToolResourcesFileSearch
from swagger_client.models.modify_message_request import ModifyMessageRequest
from swagger_client.models.modify_run_request import ModifyRunRequest
from swagger_client.models.modify_thread_request import ModifyThreadRequest
from swagger_client.models.one_of_assistant_object_tools_items import OneOfAssistantObjectToolsItems
from swagger_client.models.one_of_chat_completion_request_user_message_content import OneOfChatCompletionRequestUserMessageContent
from swagger_client.models.one_of_create_assistant_request_tools_items import OneOfCreateAssistantRequestToolsItems
from swagger_client.models.one_of_create_chat_completion_request_function_call import OneOfCreateChatCompletionRequestFunctionCall
from swagger_client.models.one_of_create_chat_completion_request_stop import OneOfCreateChatCompletionRequestStop
from swagger_client.models.one_of_create_completion_request_prompt import OneOfCreateCompletionRequestPrompt
from swagger_client.models.one_of_create_completion_request_stop import OneOfCreateCompletionRequestStop
from swagger_client.models.one_of_create_embedding_request_input import OneOfCreateEmbeddingRequestInput
from swagger_client.models.one_of_create_fine_tuning_job_request_hyperparameters_batch_size import OneOfCreateFineTuningJobRequestHyperparametersBatchSize
from swagger_client.models.one_of_create_fine_tuning_job_request_hyperparameters_learning_rate_multiplier import OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier
from swagger_client.models.one_of_create_fine_tuning_job_request_hyperparameters_n_epochs import OneOfCreateFineTuningJobRequestHyperparametersNEpochs
from swagger_client.models.one_of_create_fine_tuning_job_request_integrations_type import OneOfCreateFineTuningJobRequestIntegrationsType
from swagger_client.models.one_of_create_message_request_content import OneOfCreateMessageRequestContent
from swagger_client.models.one_of_create_moderation_request_input import OneOfCreateModerationRequestInput
from swagger_client.models.one_of_create_run_request_tools_items import OneOfCreateRunRequestToolsItems
from swagger_client.models.one_of_create_thread_and_run_request_tools_items import OneOfCreateThreadAndRunRequestToolsItems
from swagger_client.models.one_of_fine_tuning_job_hyperparameters_n_epochs import OneOfFineTuningJobHyperparametersNEpochs
from swagger_client.models.one_of_fine_tuning_job_integrations_items import OneOfFineTuningJobIntegrationsItems
from swagger_client.models.one_of_message_content_text_object_text_annotations_items import OneOfMessageContentTextObjectTextAnnotationsItems
from swagger_client.models.one_of_message_delta_content_text_object_text_annotations_items import OneOfMessageDeltaContentTextObjectTextAnnotationsItems
from swagger_client.models.one_of_message_delta_object_delta_content_items import OneOfMessageDeltaObjectDeltaContentItems
from swagger_client.models.one_of_message_object_attachments_tools_items import OneOfMessageObjectAttachmentsToolsItems
from swagger_client.models.one_of_message_object_content_items import OneOfMessageObjectContentItems
from swagger_client.models.one_of_modify_assistant_request_tools_items import OneOfModifyAssistantRequestToolsItems
from swagger_client.models.one_of_run_object_tools_items import OneOfRunObjectToolsItems
from swagger_client.models.one_of_run_step_delta_object_delta_step_details import OneOfRunStepDeltaObjectDeltaStepDetails
from swagger_client.models.one_of_run_step_delta_step_details_tool_calls_code_object_code_interpreter_outputs_items import OneOfRunStepDeltaStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems
from swagger_client.models.one_of_run_step_delta_step_details_tool_calls_object_tool_calls_items import OneOfRunStepDeltaStepDetailsToolCallsObjectToolCallsItems
from swagger_client.models.one_of_run_step_details_tool_calls_code_object_code_interpreter_outputs_items import OneOfRunStepDetailsToolCallsCodeObjectCodeInterpreterOutputsItems
from swagger_client.models.one_of_run_step_details_tool_calls_object_tool_calls_items import OneOfRunStepDetailsToolCallsObjectToolCallsItems
from swagger_client.models.one_of_run_step_object_step_details import OneOfRunStepObjectStepDetails
from swagger_client.models.open_ai_file import OpenAIFile
from swagger_client.models.run_completion_usage import RunCompletionUsage
from swagger_client.models.run_object import RunObject
from swagger_client.models.run_object_incomplete_details import RunObjectIncompleteDetails
from swagger_client.models.run_object_last_error import RunObjectLastError
from swagger_client.models.run_object_required_action import RunObjectRequiredAction
from swagger_client.models.run_object_required_action_submit_tool_outputs import RunObjectRequiredActionSubmitToolOutputs
from swagger_client.models.run_step_completion_usage import RunStepCompletionUsage
from swagger_client.models.run_step_delta_object import RunStepDeltaObject
from swagger_client.models.run_step_delta_object_delta import RunStepDeltaObjectDelta
from swagger_client.models.run_step_delta_step_details_message_creation_object import RunStepDeltaStepDetailsMessageCreationObject
from swagger_client.models.run_step_delta_step_details_message_creation_object_message_creation import RunStepDeltaStepDetailsMessageCreationObjectMessageCreation
from swagger_client.models.run_step_delta_step_details_tool_calls_code_object import RunStepDeltaStepDetailsToolCallsCodeObject
from swagger_client.models.run_step_delta_step_details_tool_calls_code_object_code_interpreter import RunStepDeltaStepDetailsToolCallsCodeObjectCodeInterpreter
from swagger_client.models.run_step_delta_step_details_tool_calls_code_output_image_object import RunStepDeltaStepDetailsToolCallsCodeOutputImageObject
from swagger_client.models.run_step_delta_step_details_tool_calls_code_output_image_object_image import RunStepDeltaStepDetailsToolCallsCodeOutputImageObjectImage
from swagger_client.models.run_step_delta_step_details_tool_calls_code_output_logs_object import RunStepDeltaStepDetailsToolCallsCodeOutputLogsObject
from swagger_client.models.run_step_delta_step_details_tool_calls_file_search_object import RunStepDeltaStepDetailsToolCallsFileSearchObject
from swagger_client.models.run_step_delta_step_details_tool_calls_function_object import RunStepDeltaStepDetailsToolCallsFunctionObject
from swagger_client.models.run_step_delta_step_details_tool_calls_function_object_function import RunStepDeltaStepDetailsToolCallsFunctionObjectFunction
from swagger_client.models.run_step_delta_step_details_tool_calls_object import RunStepDeltaStepDetailsToolCallsObject
from swagger_client.models.run_step_details_message_creation_object import RunStepDetailsMessageCreationObject
from swagger_client.models.run_step_details_message_creation_object_message_creation import RunStepDetailsMessageCreationObjectMessageCreation
from swagger_client.models.run_step_details_tool_calls_code_object import RunStepDetailsToolCallsCodeObject
from swagger_client.models.run_step_details_tool_calls_code_object_code_interpreter import RunStepDetailsToolCallsCodeObjectCodeInterpreter
from swagger_client.models.run_step_details_tool_calls_code_output_image_object import RunStepDetailsToolCallsCodeOutputImageObject
from swagger_client.models.run_step_details_tool_calls_code_output_image_object_image import RunStepDetailsToolCallsCodeOutputImageObjectImage
from swagger_client.models.run_step_details_tool_calls_code_output_logs_object import RunStepDetailsToolCallsCodeOutputLogsObject
from swagger_client.models.run_step_details_tool_calls_file_search_object import RunStepDetailsToolCallsFileSearchObject
from swagger_client.models.run_step_details_tool_calls_function_object import RunStepDetailsToolCallsFunctionObject
from swagger_client.models.run_step_details_tool_calls_function_object_function import RunStepDetailsToolCallsFunctionObjectFunction
from swagger_client.models.run_step_details_tool_calls_object import RunStepDetailsToolCallsObject
from swagger_client.models.run_step_object import RunStepObject
from swagger_client.models.run_step_object_last_error import RunStepObjectLastError
from swagger_client.models.run_step_stream_event import RunStepStreamEvent
from swagger_client.models.run_stream_event import RunStreamEvent
from swagger_client.models.run_tool_call_object import RunToolCallObject
from swagger_client.models.run_tool_call_object_function import RunToolCallObjectFunction
from swagger_client.models.submit_tool_outputs_run_request import SubmitToolOutputsRunRequest
from swagger_client.models.submit_tool_outputs_run_request_tool_outputs import SubmitToolOutputsRunRequestToolOutputs
from swagger_client.models.thread_object import ThreadObject
from swagger_client.models.thread_object_tool_resources import ThreadObjectToolResources
from swagger_client.models.thread_object_tool_resources_file_search import ThreadObjectToolResourcesFileSearch
from swagger_client.models.thread_stream_event import ThreadStreamEvent
from swagger_client.models.transcription_segment import TranscriptionSegment
from swagger_client.models.transcription_word import TranscriptionWord
from swagger_client.models.truncation_object import TruncationObject
from swagger_client.models.update_vector_store_request import UpdateVectorStoreRequest
from swagger_client.models.vector_store_expiration_after import VectorStoreExpirationAfter
from swagger_client.models.vector_store_file_batch_object import VectorStoreFileBatchObject
from swagger_client.models.vector_store_file_batch_object_file_counts import VectorStoreFileBatchObjectFileCounts
from swagger_client.models.vector_store_file_object import VectorStoreFileObject
from swagger_client.models.vector_store_file_object_last_error import VectorStoreFileObjectLastError
from swagger_client.models.vector_store_object import VectorStoreObject
from swagger_client.models.vector_store_object_file_counts import VectorStoreObjectFileCounts
