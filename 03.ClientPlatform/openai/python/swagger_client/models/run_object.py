# coding: utf-8

"""
    OpenAI API

    The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.  # noqa: E501

    OpenAPI spec version: 2.0.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

import pprint
import re  # noqa: F401

import six

class RunObject(object):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    """
    Attributes:
      swagger_types (dict): The key is attribute name
                            and the value is attribute type.
      attribute_map (dict): The key is attribute name
                            and the value is json key in definition.
    """
    swagger_types = {
        'id': 'str',
        'object': 'str',
        'created_at': 'int',
        'thread_id': 'str',
        'assistant_id': 'str',
        'status': 'str',
        'required_action': 'RunObjectRequiredAction',
        'last_error': 'RunObjectLastError',
        'expires_at': 'int',
        'started_at': 'int',
        'cancelled_at': 'int',
        'failed_at': 'int',
        'completed_at': 'int',
        'incomplete_details': 'RunObjectIncompleteDetails',
        'model': 'str',
        'instructions': 'str',
        'tools': 'list[OneOfRunObjectToolsItems]',
        'metadata': 'object',
        'usage': 'RunCompletionUsage',
        'temperature': 'float',
        'top_p': 'float',
        'max_prompt_tokens': 'int',
        'max_completion_tokens': 'int',
        'truncation_strategy': 'TruncationObject',
        'tool_choice': 'AssistantsApiToolChoiceOption',
        'response_format': 'AssistantsApiResponseFormatOption'
    }

    attribute_map = {
        'id': 'id',
        'object': 'object',
        'created_at': 'created_at',
        'thread_id': 'thread_id',
        'assistant_id': 'assistant_id',
        'status': 'status',
        'required_action': 'required_action',
        'last_error': 'last_error',
        'expires_at': 'expires_at',
        'started_at': 'started_at',
        'cancelled_at': 'cancelled_at',
        'failed_at': 'failed_at',
        'completed_at': 'completed_at',
        'incomplete_details': 'incomplete_details',
        'model': 'model',
        'instructions': 'instructions',
        'tools': 'tools',
        'metadata': 'metadata',
        'usage': 'usage',
        'temperature': 'temperature',
        'top_p': 'top_p',
        'max_prompt_tokens': 'max_prompt_tokens',
        'max_completion_tokens': 'max_completion_tokens',
        'truncation_strategy': 'truncation_strategy',
        'tool_choice': 'tool_choice',
        'response_format': 'response_format'
    }

    def __init__(self, id=None, object=None, created_at=None, thread_id=None, assistant_id=None, status=None, required_action=None, last_error=None, expires_at=None, started_at=None, cancelled_at=None, failed_at=None, completed_at=None, incomplete_details=None, model=None, instructions=None, tools=None, metadata=None, usage=None, temperature=None, top_p=None, max_prompt_tokens=None, max_completion_tokens=None, truncation_strategy=None, tool_choice=None, response_format=None):  # noqa: E501
        """RunObject - a model defined in Swagger"""  # noqa: E501
        self._id = None
        self._object = None
        self._created_at = None
        self._thread_id = None
        self._assistant_id = None
        self._status = None
        self._required_action = None
        self._last_error = None
        self._expires_at = None
        self._started_at = None
        self._cancelled_at = None
        self._failed_at = None
        self._completed_at = None
        self._incomplete_details = None
        self._model = None
        self._instructions = None
        self._tools = None
        self._metadata = None
        self._usage = None
        self._temperature = None
        self._top_p = None
        self._max_prompt_tokens = None
        self._max_completion_tokens = None
        self._truncation_strategy = None
        self._tool_choice = None
        self._response_format = None
        self.discriminator = None
        self.id = id
        self.object = object
        self.created_at = created_at
        self.thread_id = thread_id
        self.assistant_id = assistant_id
        self.status = status
        self.required_action = required_action
        self.last_error = last_error
        self.expires_at = expires_at
        self.started_at = started_at
        self.cancelled_at = cancelled_at
        self.failed_at = failed_at
        self.completed_at = completed_at
        self.incomplete_details = incomplete_details
        self.model = model
        self.instructions = instructions
        self.tools = tools
        self.metadata = metadata
        self.usage = usage
        if temperature is not None:
            self.temperature = temperature
        if top_p is not None:
            self.top_p = top_p
        self.max_prompt_tokens = max_prompt_tokens
        self.max_completion_tokens = max_completion_tokens
        self.truncation_strategy = truncation_strategy
        self.tool_choice = tool_choice
        self.response_format = response_format

    @property
    def id(self):
        """Gets the id of this RunObject.  # noqa: E501

        The identifier, which can be referenced in API endpoints.  # noqa: E501

        :return: The id of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this RunObject.

        The identifier, which can be referenced in API endpoints.  # noqa: E501

        :param id: The id of this RunObject.  # noqa: E501
        :type: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def object(self):
        """Gets the object of this RunObject.  # noqa: E501

        The object type, which is always `thread.run`.  # noqa: E501

        :return: The object of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._object

    @object.setter
    def object(self, object):
        """Sets the object of this RunObject.

        The object type, which is always `thread.run`.  # noqa: E501

        :param object: The object of this RunObject.  # noqa: E501
        :type: str
        """
        if object is None:
            raise ValueError("Invalid value for `object`, must not be `None`")  # noqa: E501
        allowed_values = ["thread.run"]  # noqa: E501
        if object not in allowed_values:
            raise ValueError(
                "Invalid value for `object` ({0}), must be one of {1}"  # noqa: E501
                .format(object, allowed_values)
            )

        self._object = object

    @property
    def created_at(self):
        """Gets the created_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run was created.  # noqa: E501

        :return: The created_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._created_at

    @created_at.setter
    def created_at(self, created_at):
        """Sets the created_at of this RunObject.

        The Unix timestamp (in seconds) for when the run was created.  # noqa: E501

        :param created_at: The created_at of this RunObject.  # noqa: E501
        :type: int
        """
        if created_at is None:
            raise ValueError("Invalid value for `created_at`, must not be `None`")  # noqa: E501

        self._created_at = created_at

    @property
    def thread_id(self):
        """Gets the thread_id of this RunObject.  # noqa: E501

        The ID of the [thread](/docs/api-reference/threads) that was executed on as a part of this run.  # noqa: E501

        :return: The thread_id of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._thread_id

    @thread_id.setter
    def thread_id(self, thread_id):
        """Sets the thread_id of this RunObject.

        The ID of the [thread](/docs/api-reference/threads) that was executed on as a part of this run.  # noqa: E501

        :param thread_id: The thread_id of this RunObject.  # noqa: E501
        :type: str
        """
        if thread_id is None:
            raise ValueError("Invalid value for `thread_id`, must not be `None`")  # noqa: E501

        self._thread_id = thread_id

    @property
    def assistant_id(self):
        """Gets the assistant_id of this RunObject.  # noqa: E501

        The ID of the [assistant](/docs/api-reference/assistants) used for execution of this run.  # noqa: E501

        :return: The assistant_id of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._assistant_id

    @assistant_id.setter
    def assistant_id(self, assistant_id):
        """Sets the assistant_id of this RunObject.

        The ID of the [assistant](/docs/api-reference/assistants) used for execution of this run.  # noqa: E501

        :param assistant_id: The assistant_id of this RunObject.  # noqa: E501
        :type: str
        """
        if assistant_id is None:
            raise ValueError("Invalid value for `assistant_id`, must not be `None`")  # noqa: E501

        self._assistant_id = assistant_id

    @property
    def status(self):
        """Gets the status of this RunObject.  # noqa: E501

        The status of the run, which can be either `queued`, `in_progress`, `requires_action`, `cancelling`, `cancelled`, `failed`, `completed`, `incomplete`, or `expired`.  # noqa: E501

        :return: The status of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._status

    @status.setter
    def status(self, status):
        """Sets the status of this RunObject.

        The status of the run, which can be either `queued`, `in_progress`, `requires_action`, `cancelling`, `cancelled`, `failed`, `completed`, `incomplete`, or `expired`.  # noqa: E501

        :param status: The status of this RunObject.  # noqa: E501
        :type: str
        """
        if status is None:
            raise ValueError("Invalid value for `status`, must not be `None`")  # noqa: E501
        allowed_values = ["queued", "in_progress", "requires_action", "cancelling", "cancelled", "failed", "completed", "incomplete", "expired"]  # noqa: E501
        if status not in allowed_values:
            raise ValueError(
                "Invalid value for `status` ({0}), must be one of {1}"  # noqa: E501
                .format(status, allowed_values)
            )

        self._status = status

    @property
    def required_action(self):
        """Gets the required_action of this RunObject.  # noqa: E501


        :return: The required_action of this RunObject.  # noqa: E501
        :rtype: RunObjectRequiredAction
        """
        return self._required_action

    @required_action.setter
    def required_action(self, required_action):
        """Sets the required_action of this RunObject.


        :param required_action: The required_action of this RunObject.  # noqa: E501
        :type: RunObjectRequiredAction
        """
        if required_action is None:
            raise ValueError("Invalid value for `required_action`, must not be `None`")  # noqa: E501

        self._required_action = required_action

    @property
    def last_error(self):
        """Gets the last_error of this RunObject.  # noqa: E501


        :return: The last_error of this RunObject.  # noqa: E501
        :rtype: RunObjectLastError
        """
        return self._last_error

    @last_error.setter
    def last_error(self, last_error):
        """Sets the last_error of this RunObject.


        :param last_error: The last_error of this RunObject.  # noqa: E501
        :type: RunObjectLastError
        """
        if last_error is None:
            raise ValueError("Invalid value for `last_error`, must not be `None`")  # noqa: E501

        self._last_error = last_error

    @property
    def expires_at(self):
        """Gets the expires_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run will expire.  # noqa: E501

        :return: The expires_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._expires_at

    @expires_at.setter
    def expires_at(self, expires_at):
        """Sets the expires_at of this RunObject.

        The Unix timestamp (in seconds) for when the run will expire.  # noqa: E501

        :param expires_at: The expires_at of this RunObject.  # noqa: E501
        :type: int
        """
        if expires_at is None:
            raise ValueError("Invalid value for `expires_at`, must not be `None`")  # noqa: E501

        self._expires_at = expires_at

    @property
    def started_at(self):
        """Gets the started_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run was started.  # noqa: E501

        :return: The started_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._started_at

    @started_at.setter
    def started_at(self, started_at):
        """Sets the started_at of this RunObject.

        The Unix timestamp (in seconds) for when the run was started.  # noqa: E501

        :param started_at: The started_at of this RunObject.  # noqa: E501
        :type: int
        """
        if started_at is None:
            raise ValueError("Invalid value for `started_at`, must not be `None`")  # noqa: E501

        self._started_at = started_at

    @property
    def cancelled_at(self):
        """Gets the cancelled_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run was cancelled.  # noqa: E501

        :return: The cancelled_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._cancelled_at

    @cancelled_at.setter
    def cancelled_at(self, cancelled_at):
        """Sets the cancelled_at of this RunObject.

        The Unix timestamp (in seconds) for when the run was cancelled.  # noqa: E501

        :param cancelled_at: The cancelled_at of this RunObject.  # noqa: E501
        :type: int
        """
        if cancelled_at is None:
            raise ValueError("Invalid value for `cancelled_at`, must not be `None`")  # noqa: E501

        self._cancelled_at = cancelled_at

    @property
    def failed_at(self):
        """Gets the failed_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run failed.  # noqa: E501

        :return: The failed_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._failed_at

    @failed_at.setter
    def failed_at(self, failed_at):
        """Sets the failed_at of this RunObject.

        The Unix timestamp (in seconds) for when the run failed.  # noqa: E501

        :param failed_at: The failed_at of this RunObject.  # noqa: E501
        :type: int
        """
        if failed_at is None:
            raise ValueError("Invalid value for `failed_at`, must not be `None`")  # noqa: E501

        self._failed_at = failed_at

    @property
    def completed_at(self):
        """Gets the completed_at of this RunObject.  # noqa: E501

        The Unix timestamp (in seconds) for when the run was completed.  # noqa: E501

        :return: The completed_at of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._completed_at

    @completed_at.setter
    def completed_at(self, completed_at):
        """Sets the completed_at of this RunObject.

        The Unix timestamp (in seconds) for when the run was completed.  # noqa: E501

        :param completed_at: The completed_at of this RunObject.  # noqa: E501
        :type: int
        """
        if completed_at is None:
            raise ValueError("Invalid value for `completed_at`, must not be `None`")  # noqa: E501

        self._completed_at = completed_at

    @property
    def incomplete_details(self):
        """Gets the incomplete_details of this RunObject.  # noqa: E501


        :return: The incomplete_details of this RunObject.  # noqa: E501
        :rtype: RunObjectIncompleteDetails
        """
        return self._incomplete_details

    @incomplete_details.setter
    def incomplete_details(self, incomplete_details):
        """Sets the incomplete_details of this RunObject.


        :param incomplete_details: The incomplete_details of this RunObject.  # noqa: E501
        :type: RunObjectIncompleteDetails
        """
        if incomplete_details is None:
            raise ValueError("Invalid value for `incomplete_details`, must not be `None`")  # noqa: E501

        self._incomplete_details = incomplete_details

    @property
    def model(self):
        """Gets the model of this RunObject.  # noqa: E501

        The model that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :return: The model of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._model

    @model.setter
    def model(self, model):
        """Sets the model of this RunObject.

        The model that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :param model: The model of this RunObject.  # noqa: E501
        :type: str
        """
        if model is None:
            raise ValueError("Invalid value for `model`, must not be `None`")  # noqa: E501

        self._model = model

    @property
    def instructions(self):
        """Gets the instructions of this RunObject.  # noqa: E501

        The instructions that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :return: The instructions of this RunObject.  # noqa: E501
        :rtype: str
        """
        return self._instructions

    @instructions.setter
    def instructions(self, instructions):
        """Sets the instructions of this RunObject.

        The instructions that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :param instructions: The instructions of this RunObject.  # noqa: E501
        :type: str
        """
        if instructions is None:
            raise ValueError("Invalid value for `instructions`, must not be `None`")  # noqa: E501

        self._instructions = instructions

    @property
    def tools(self):
        """Gets the tools of this RunObject.  # noqa: E501

        The list of tools that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :return: The tools of this RunObject.  # noqa: E501
        :rtype: list[OneOfRunObjectToolsItems]
        """
        return self._tools

    @tools.setter
    def tools(self, tools):
        """Sets the tools of this RunObject.

        The list of tools that the [assistant](/docs/api-reference/assistants) used for this run.  # noqa: E501

        :param tools: The tools of this RunObject.  # noqa: E501
        :type: list[OneOfRunObjectToolsItems]
        """
        if tools is None:
            raise ValueError("Invalid value for `tools`, must not be `None`")  # noqa: E501

        self._tools = tools

    @property
    def metadata(self):
        """Gets the metadata of this RunObject.  # noqa: E501

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :return: The metadata of this RunObject.  # noqa: E501
        :rtype: object
        """
        return self._metadata

    @metadata.setter
    def metadata(self, metadata):
        """Sets the metadata of this RunObject.

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :param metadata: The metadata of this RunObject.  # noqa: E501
        :type: object
        """
        if metadata is None:
            raise ValueError("Invalid value for `metadata`, must not be `None`")  # noqa: E501

        self._metadata = metadata

    @property
    def usage(self):
        """Gets the usage of this RunObject.  # noqa: E501


        :return: The usage of this RunObject.  # noqa: E501
        :rtype: RunCompletionUsage
        """
        return self._usage

    @usage.setter
    def usage(self, usage):
        """Sets the usage of this RunObject.


        :param usage: The usage of this RunObject.  # noqa: E501
        :type: RunCompletionUsage
        """
        if usage is None:
            raise ValueError("Invalid value for `usage`, must not be `None`")  # noqa: E501

        self._usage = usage

    @property
    def temperature(self):
        """Gets the temperature of this RunObject.  # noqa: E501

        The sampling temperature used for this run. If not set, defaults to 1.  # noqa: E501

        :return: The temperature of this RunObject.  # noqa: E501
        :rtype: float
        """
        return self._temperature

    @temperature.setter
    def temperature(self, temperature):
        """Sets the temperature of this RunObject.

        The sampling temperature used for this run. If not set, defaults to 1.  # noqa: E501

        :param temperature: The temperature of this RunObject.  # noqa: E501
        :type: float
        """

        self._temperature = temperature

    @property
    def top_p(self):
        """Gets the top_p of this RunObject.  # noqa: E501

        The nucleus sampling value used for this run. If not set, defaults to 1.  # noqa: E501

        :return: The top_p of this RunObject.  # noqa: E501
        :rtype: float
        """
        return self._top_p

    @top_p.setter
    def top_p(self, top_p):
        """Sets the top_p of this RunObject.

        The nucleus sampling value used for this run. If not set, defaults to 1.  # noqa: E501

        :param top_p: The top_p of this RunObject.  # noqa: E501
        :type: float
        """

        self._top_p = top_p

    @property
    def max_prompt_tokens(self):
        """Gets the max_prompt_tokens of this RunObject.  # noqa: E501

        The maximum number of prompt tokens specified to have been used over the course of the run.   # noqa: E501

        :return: The max_prompt_tokens of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._max_prompt_tokens

    @max_prompt_tokens.setter
    def max_prompt_tokens(self, max_prompt_tokens):
        """Sets the max_prompt_tokens of this RunObject.

        The maximum number of prompt tokens specified to have been used over the course of the run.   # noqa: E501

        :param max_prompt_tokens: The max_prompt_tokens of this RunObject.  # noqa: E501
        :type: int
        """
        if max_prompt_tokens is None:
            raise ValueError("Invalid value for `max_prompt_tokens`, must not be `None`")  # noqa: E501

        self._max_prompt_tokens = max_prompt_tokens

    @property
    def max_completion_tokens(self):
        """Gets the max_completion_tokens of this RunObject.  # noqa: E501

        The maximum number of completion tokens specified to have been used over the course of the run.   # noqa: E501

        :return: The max_completion_tokens of this RunObject.  # noqa: E501
        :rtype: int
        """
        return self._max_completion_tokens

    @max_completion_tokens.setter
    def max_completion_tokens(self, max_completion_tokens):
        """Sets the max_completion_tokens of this RunObject.

        The maximum number of completion tokens specified to have been used over the course of the run.   # noqa: E501

        :param max_completion_tokens: The max_completion_tokens of this RunObject.  # noqa: E501
        :type: int
        """
        if max_completion_tokens is None:
            raise ValueError("Invalid value for `max_completion_tokens`, must not be `None`")  # noqa: E501

        self._max_completion_tokens = max_completion_tokens

    @property
    def truncation_strategy(self):
        """Gets the truncation_strategy of this RunObject.  # noqa: E501


        :return: The truncation_strategy of this RunObject.  # noqa: E501
        :rtype: TruncationObject
        """
        return self._truncation_strategy

    @truncation_strategy.setter
    def truncation_strategy(self, truncation_strategy):
        """Sets the truncation_strategy of this RunObject.


        :param truncation_strategy: The truncation_strategy of this RunObject.  # noqa: E501
        :type: TruncationObject
        """
        if truncation_strategy is None:
            raise ValueError("Invalid value for `truncation_strategy`, must not be `None`")  # noqa: E501

        self._truncation_strategy = truncation_strategy

    @property
    def tool_choice(self):
        """Gets the tool_choice of this RunObject.  # noqa: E501


        :return: The tool_choice of this RunObject.  # noqa: E501
        :rtype: AssistantsApiToolChoiceOption
        """
        return self._tool_choice

    @tool_choice.setter
    def tool_choice(self, tool_choice):
        """Sets the tool_choice of this RunObject.


        :param tool_choice: The tool_choice of this RunObject.  # noqa: E501
        :type: AssistantsApiToolChoiceOption
        """
        if tool_choice is None:
            raise ValueError("Invalid value for `tool_choice`, must not be `None`")  # noqa: E501

        self._tool_choice = tool_choice

    @property
    def response_format(self):
        """Gets the response_format of this RunObject.  # noqa: E501


        :return: The response_format of this RunObject.  # noqa: E501
        :rtype: AssistantsApiResponseFormatOption
        """
        return self._response_format

    @response_format.setter
    def response_format(self, response_format):
        """Sets the response_format of this RunObject.


        :param response_format: The response_format of this RunObject.  # noqa: E501
        :type: AssistantsApiResponseFormatOption
        """
        if response_format is None:
            raise ValueError("Invalid value for `response_format`, must not be `None`")  # noqa: E501

        self._response_format = response_format

    def to_dict(self):
        """Returns the model properties as a dict"""
        result = {}

        for attr, _ in six.iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value
        if issubclass(RunObject, dict):
            for key, value in self.items():
                result[key] = value

        return result

    def to_str(self):
        """Returns the string representation of the model"""
        return pprint.pformat(self.to_dict())

    def __repr__(self):
        """For `print` and `pprint`"""
        return self.to_str()

    def __eq__(self, other):
        """Returns true if both objects are equal"""
        if not isinstance(other, RunObject):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other