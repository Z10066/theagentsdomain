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

class ChatCompletionRequestAssistantMessage(object):
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
        'content': 'str',
        'role': 'str',
        'name': 'str',
        'tool_calls': 'ChatCompletionMessageToolCalls',
        'function_call': 'ChatCompletionRequestAssistantMessageFunctionCall'
    }

    attribute_map = {
        'content': 'content',
        'role': 'role',
        'name': 'name',
        'tool_calls': 'tool_calls',
        'function_call': 'function_call'
    }

    def __init__(self, content=None, role=None, name=None, tool_calls=None, function_call=None):  # noqa: E501
        """ChatCompletionRequestAssistantMessage - a model defined in Swagger"""  # noqa: E501
        self._content = None
        self._role = None
        self._name = None
        self._tool_calls = None
        self._function_call = None
        self.discriminator = None
        if content is not None:
            self.content = content
        self.role = role
        if name is not None:
            self.name = name
        if tool_calls is not None:
            self.tool_calls = tool_calls
        if function_call is not None:
            self.function_call = function_call

    @property
    def content(self):
        """Gets the content of this ChatCompletionRequestAssistantMessage.  # noqa: E501

        The contents of the assistant message. Required unless `tool_calls` or `function_call` is specified.   # noqa: E501

        :return: The content of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :rtype: str
        """
        return self._content

    @content.setter
    def content(self, content):
        """Sets the content of this ChatCompletionRequestAssistantMessage.

        The contents of the assistant message. Required unless `tool_calls` or `function_call` is specified.   # noqa: E501

        :param content: The content of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :type: str
        """

        self._content = content

    @property
    def role(self):
        """Gets the role of this ChatCompletionRequestAssistantMessage.  # noqa: E501

        The role of the messages author, in this case `assistant`.  # noqa: E501

        :return: The role of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :rtype: str
        """
        return self._role

    @role.setter
    def role(self, role):
        """Sets the role of this ChatCompletionRequestAssistantMessage.

        The role of the messages author, in this case `assistant`.  # noqa: E501

        :param role: The role of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :type: str
        """
        if role is None:
            raise ValueError("Invalid value for `role`, must not be `None`")  # noqa: E501
        allowed_values = ["assistant"]  # noqa: E501
        if role not in allowed_values:
            raise ValueError(
                "Invalid value for `role` ({0}), must be one of {1}"  # noqa: E501
                .format(role, allowed_values)
            )

        self._role = role

    @property
    def name(self):
        """Gets the name of this ChatCompletionRequestAssistantMessage.  # noqa: E501

        An optional name for the participant. Provides the model information to differentiate between participants of the same role.  # noqa: E501

        :return: The name of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name):
        """Sets the name of this ChatCompletionRequestAssistantMessage.

        An optional name for the participant. Provides the model information to differentiate between participants of the same role.  # noqa: E501

        :param name: The name of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :type: str
        """

        self._name = name

    @property
    def tool_calls(self):
        """Gets the tool_calls of this ChatCompletionRequestAssistantMessage.  # noqa: E501


        :return: The tool_calls of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :rtype: ChatCompletionMessageToolCalls
        """
        return self._tool_calls

    @tool_calls.setter
    def tool_calls(self, tool_calls):
        """Sets the tool_calls of this ChatCompletionRequestAssistantMessage.


        :param tool_calls: The tool_calls of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :type: ChatCompletionMessageToolCalls
        """

        self._tool_calls = tool_calls

    @property
    def function_call(self):
        """Gets the function_call of this ChatCompletionRequestAssistantMessage.  # noqa: E501


        :return: The function_call of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :rtype: ChatCompletionRequestAssistantMessageFunctionCall
        """
        return self._function_call

    @function_call.setter
    def function_call(self, function_call):
        """Sets the function_call of this ChatCompletionRequestAssistantMessage.


        :param function_call: The function_call of this ChatCompletionRequestAssistantMessage.  # noqa: E501
        :type: ChatCompletionRequestAssistantMessageFunctionCall
        """

        self._function_call = function_call

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
        if issubclass(ChatCompletionRequestAssistantMessage, dict):
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
        if not isinstance(other, ChatCompletionRequestAssistantMessage):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
