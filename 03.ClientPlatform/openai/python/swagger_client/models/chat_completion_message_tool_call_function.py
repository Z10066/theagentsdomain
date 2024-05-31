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

class ChatCompletionMessageToolCallFunction(object):
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
        'name': 'str',
        'arguments': 'str'
    }

    attribute_map = {
        'name': 'name',
        'arguments': 'arguments'
    }

    def __init__(self, name=None, arguments=None):  # noqa: E501
        """ChatCompletionMessageToolCallFunction - a model defined in Swagger"""  # noqa: E501
        self._name = None
        self._arguments = None
        self.discriminator = None
        self.name = name
        self.arguments = arguments

    @property
    def name(self):
        """Gets the name of this ChatCompletionMessageToolCallFunction.  # noqa: E501

        The name of the function to call.  # noqa: E501

        :return: The name of this ChatCompletionMessageToolCallFunction.  # noqa: E501
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name):
        """Sets the name of this ChatCompletionMessageToolCallFunction.

        The name of the function to call.  # noqa: E501

        :param name: The name of this ChatCompletionMessageToolCallFunction.  # noqa: E501
        :type: str
        """
        if name is None:
            raise ValueError("Invalid value for `name`, must not be `None`")  # noqa: E501

        self._name = name

    @property
    def arguments(self):
        """Gets the arguments of this ChatCompletionMessageToolCallFunction.  # noqa: E501

        The arguments to call the function with, as generated by the model in JSON format. Note that the model does not always generate valid JSON, and may hallucinate parameters not defined by your function schema. Validate the arguments in your code before calling your function.  # noqa: E501

        :return: The arguments of this ChatCompletionMessageToolCallFunction.  # noqa: E501
        :rtype: str
        """
        return self._arguments

    @arguments.setter
    def arguments(self, arguments):
        """Sets the arguments of this ChatCompletionMessageToolCallFunction.

        The arguments to call the function with, as generated by the model in JSON format. Note that the model does not always generate valid JSON, and may hallucinate parameters not defined by your function schema. Validate the arguments in your code before calling your function.  # noqa: E501

        :param arguments: The arguments of this ChatCompletionMessageToolCallFunction.  # noqa: E501
        :type: str
        """
        if arguments is None:
            raise ValueError("Invalid value for `arguments`, must not be `None`")  # noqa: E501

        self._arguments = arguments

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
        if issubclass(ChatCompletionMessageToolCallFunction, dict):
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
        if not isinstance(other, ChatCompletionMessageToolCallFunction):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
