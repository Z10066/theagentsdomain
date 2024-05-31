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

class CreateChatCompletionStreamResponseChoices(object):
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
        'delta': 'ChatCompletionStreamResponseDelta',
        'logprobs': 'CreateChatCompletionResponseLogprobs',
        'finish_reason': 'str',
        'index': 'int'
    }

    attribute_map = {
        'delta': 'delta',
        'logprobs': 'logprobs',
        'finish_reason': 'finish_reason',
        'index': 'index'
    }

    def __init__(self, delta=None, logprobs=None, finish_reason=None, index=None):  # noqa: E501
        """CreateChatCompletionStreamResponseChoices - a model defined in Swagger"""  # noqa: E501
        self._delta = None
        self._logprobs = None
        self._finish_reason = None
        self._index = None
        self.discriminator = None
        self.delta = delta
        if logprobs is not None:
            self.logprobs = logprobs
        self.finish_reason = finish_reason
        self.index = index

    @property
    def delta(self):
        """Gets the delta of this CreateChatCompletionStreamResponseChoices.  # noqa: E501


        :return: The delta of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :rtype: ChatCompletionStreamResponseDelta
        """
        return self._delta

    @delta.setter
    def delta(self, delta):
        """Sets the delta of this CreateChatCompletionStreamResponseChoices.


        :param delta: The delta of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :type: ChatCompletionStreamResponseDelta
        """
        if delta is None:
            raise ValueError("Invalid value for `delta`, must not be `None`")  # noqa: E501

        self._delta = delta

    @property
    def logprobs(self):
        """Gets the logprobs of this CreateChatCompletionStreamResponseChoices.  # noqa: E501


        :return: The logprobs of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :rtype: CreateChatCompletionResponseLogprobs
        """
        return self._logprobs

    @logprobs.setter
    def logprobs(self, logprobs):
        """Sets the logprobs of this CreateChatCompletionStreamResponseChoices.


        :param logprobs: The logprobs of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :type: CreateChatCompletionResponseLogprobs
        """

        self._logprobs = logprobs

    @property
    def finish_reason(self):
        """Gets the finish_reason of this CreateChatCompletionStreamResponseChoices.  # noqa: E501

        The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function.   # noqa: E501

        :return: The finish_reason of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :rtype: str
        """
        return self._finish_reason

    @finish_reason.setter
    def finish_reason(self, finish_reason):
        """Sets the finish_reason of this CreateChatCompletionStreamResponseChoices.

        The reason the model stopped generating tokens. This will be `stop` if the model hit a natural stop point or a provided stop sequence, `length` if the maximum number of tokens specified in the request was reached, `content_filter` if content was omitted due to a flag from our content filters, `tool_calls` if the model called a tool, or `function_call` (deprecated) if the model called a function.   # noqa: E501

        :param finish_reason: The finish_reason of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :type: str
        """
        if finish_reason is None:
            raise ValueError("Invalid value for `finish_reason`, must not be `None`")  # noqa: E501
        allowed_values = ["stop", "length", "tool_calls", "content_filter", "function_call"]  # noqa: E501
        if finish_reason not in allowed_values:
            raise ValueError(
                "Invalid value for `finish_reason` ({0}), must be one of {1}"  # noqa: E501
                .format(finish_reason, allowed_values)
            )

        self._finish_reason = finish_reason

    @property
    def index(self):
        """Gets the index of this CreateChatCompletionStreamResponseChoices.  # noqa: E501

        The index of the choice in the list of choices.  # noqa: E501

        :return: The index of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :rtype: int
        """
        return self._index

    @index.setter
    def index(self, index):
        """Sets the index of this CreateChatCompletionStreamResponseChoices.

        The index of the choice in the list of choices.  # noqa: E501

        :param index: The index of this CreateChatCompletionStreamResponseChoices.  # noqa: E501
        :type: int
        """
        if index is None:
            raise ValueError("Invalid value for `index`, must not be `None`")  # noqa: E501

        self._index = index

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
        if issubclass(CreateChatCompletionStreamResponseChoices, dict):
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
        if not isinstance(other, CreateChatCompletionStreamResponseChoices):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other