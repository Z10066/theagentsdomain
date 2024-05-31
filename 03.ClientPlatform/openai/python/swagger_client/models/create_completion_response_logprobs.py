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

class CreateCompletionResponseLogprobs(object):
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
        'text_offset': 'list[int]',
        'token_logprobs': 'list[float]',
        'tokens': 'list[str]',
        'top_logprobs': 'list[dict(str, float)]'
    }

    attribute_map = {
        'text_offset': 'text_offset',
        'token_logprobs': 'token_logprobs',
        'tokens': 'tokens',
        'top_logprobs': 'top_logprobs'
    }

    def __init__(self, text_offset=None, token_logprobs=None, tokens=None, top_logprobs=None):  # noqa: E501
        """CreateCompletionResponseLogprobs - a model defined in Swagger"""  # noqa: E501
        self._text_offset = None
        self._token_logprobs = None
        self._tokens = None
        self._top_logprobs = None
        self.discriminator = None
        if text_offset is not None:
            self.text_offset = text_offset
        if token_logprobs is not None:
            self.token_logprobs = token_logprobs
        if tokens is not None:
            self.tokens = tokens
        if top_logprobs is not None:
            self.top_logprobs = top_logprobs

    @property
    def text_offset(self):
        """Gets the text_offset of this CreateCompletionResponseLogprobs.  # noqa: E501


        :return: The text_offset of this CreateCompletionResponseLogprobs.  # noqa: E501
        :rtype: list[int]
        """
        return self._text_offset

    @text_offset.setter
    def text_offset(self, text_offset):
        """Sets the text_offset of this CreateCompletionResponseLogprobs.


        :param text_offset: The text_offset of this CreateCompletionResponseLogprobs.  # noqa: E501
        :type: list[int]
        """

        self._text_offset = text_offset

    @property
    def token_logprobs(self):
        """Gets the token_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501


        :return: The token_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501
        :rtype: list[float]
        """
        return self._token_logprobs

    @token_logprobs.setter
    def token_logprobs(self, token_logprobs):
        """Sets the token_logprobs of this CreateCompletionResponseLogprobs.


        :param token_logprobs: The token_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501
        :type: list[float]
        """

        self._token_logprobs = token_logprobs

    @property
    def tokens(self):
        """Gets the tokens of this CreateCompletionResponseLogprobs.  # noqa: E501


        :return: The tokens of this CreateCompletionResponseLogprobs.  # noqa: E501
        :rtype: list[str]
        """
        return self._tokens

    @tokens.setter
    def tokens(self, tokens):
        """Sets the tokens of this CreateCompletionResponseLogprobs.


        :param tokens: The tokens of this CreateCompletionResponseLogprobs.  # noqa: E501
        :type: list[str]
        """

        self._tokens = tokens

    @property
    def top_logprobs(self):
        """Gets the top_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501


        :return: The top_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501
        :rtype: list[dict(str, float)]
        """
        return self._top_logprobs

    @top_logprobs.setter
    def top_logprobs(self, top_logprobs):
        """Sets the top_logprobs of this CreateCompletionResponseLogprobs.


        :param top_logprobs: The top_logprobs of this CreateCompletionResponseLogprobs.  # noqa: E501
        :type: list[dict(str, float)]
        """

        self._top_logprobs = top_logprobs

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
        if issubclass(CreateCompletionResponseLogprobs, dict):
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
        if not isinstance(other, CreateCompletionResponseLogprobs):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
