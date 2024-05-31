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

class ChatCompletionRequestToolMessage(object):
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
        'role': 'str',
        'content': 'str',
        'tool_call_id': 'str'
    }

    attribute_map = {
        'role': 'role',
        'content': 'content',
        'tool_call_id': 'tool_call_id'
    }

    def __init__(self, role=None, content=None, tool_call_id=None):  # noqa: E501
        """ChatCompletionRequestToolMessage - a model defined in Swagger"""  # noqa: E501
        self._role = None
        self._content = None
        self._tool_call_id = None
        self.discriminator = None
        self.role = role
        self.content = content
        self.tool_call_id = tool_call_id

    @property
    def role(self):
        """Gets the role of this ChatCompletionRequestToolMessage.  # noqa: E501

        The role of the messages author, in this case `tool`.  # noqa: E501

        :return: The role of this ChatCompletionRequestToolMessage.  # noqa: E501
        :rtype: str
        """
        return self._role

    @role.setter
    def role(self, role):
        """Sets the role of this ChatCompletionRequestToolMessage.

        The role of the messages author, in this case `tool`.  # noqa: E501

        :param role: The role of this ChatCompletionRequestToolMessage.  # noqa: E501
        :type: str
        """
        if role is None:
            raise ValueError("Invalid value for `role`, must not be `None`")  # noqa: E501
        allowed_values = ["tool"]  # noqa: E501
        if role not in allowed_values:
            raise ValueError(
                "Invalid value for `role` ({0}), must be one of {1}"  # noqa: E501
                .format(role, allowed_values)
            )

        self._role = role

    @property
    def content(self):
        """Gets the content of this ChatCompletionRequestToolMessage.  # noqa: E501

        The contents of the tool message.  # noqa: E501

        :return: The content of this ChatCompletionRequestToolMessage.  # noqa: E501
        :rtype: str
        """
        return self._content

    @content.setter
    def content(self, content):
        """Sets the content of this ChatCompletionRequestToolMessage.

        The contents of the tool message.  # noqa: E501

        :param content: The content of this ChatCompletionRequestToolMessage.  # noqa: E501
        :type: str
        """
        if content is None:
            raise ValueError("Invalid value for `content`, must not be `None`")  # noqa: E501

        self._content = content

    @property
    def tool_call_id(self):
        """Gets the tool_call_id of this ChatCompletionRequestToolMessage.  # noqa: E501

        Tool call that this message is responding to.  # noqa: E501

        :return: The tool_call_id of this ChatCompletionRequestToolMessage.  # noqa: E501
        :rtype: str
        """
        return self._tool_call_id

    @tool_call_id.setter
    def tool_call_id(self, tool_call_id):
        """Sets the tool_call_id of this ChatCompletionRequestToolMessage.

        Tool call that this message is responding to.  # noqa: E501

        :param tool_call_id: The tool_call_id of this ChatCompletionRequestToolMessage.  # noqa: E501
        :type: str
        """
        if tool_call_id is None:
            raise ValueError("Invalid value for `tool_call_id`, must not be `None`")  # noqa: E501

        self._tool_call_id = tool_call_id

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
        if issubclass(ChatCompletionRequestToolMessage, dict):
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
        if not isinstance(other, ChatCompletionRequestToolMessage):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other