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

class ChatCompletionTokenLogprob(object):
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
        'token': 'str',
        'logprob': 'float',
        'bytes': 'list[int]',
        'top_logprobs': 'list[ChatCompletionTokenLogprobTopLogprobs]'
    }

    attribute_map = {
        'token': 'token',
        'logprob': 'logprob',
        'bytes': 'bytes',
        'top_logprobs': 'top_logprobs'
    }

    def __init__(self, token=None, logprob=None, bytes=None, top_logprobs=None):  # noqa: E501
        """ChatCompletionTokenLogprob - a model defined in Swagger"""  # noqa: E501
        self._token = None
        self._logprob = None
        self._bytes = None
        self._top_logprobs = None
        self.discriminator = None
        self.token = token
        self.logprob = logprob
        self.bytes = bytes
        self.top_logprobs = top_logprobs

    @property
    def token(self):
        """Gets the token of this ChatCompletionTokenLogprob.  # noqa: E501

        The token.  # noqa: E501

        :return: The token of this ChatCompletionTokenLogprob.  # noqa: E501
        :rtype: str
        """
        return self._token

    @token.setter
    def token(self, token):
        """Sets the token of this ChatCompletionTokenLogprob.

        The token.  # noqa: E501

        :param token: The token of this ChatCompletionTokenLogprob.  # noqa: E501
        :type: str
        """
        if token is None:
            raise ValueError("Invalid value for `token`, must not be `None`")  # noqa: E501

        self._token = token

    @property
    def logprob(self):
        """Gets the logprob of this ChatCompletionTokenLogprob.  # noqa: E501

        The log probability of this token, if it is within the top 20 most likely tokens. Otherwise, the value `-9999.0` is used to signify that the token is very unlikely.  # noqa: E501

        :return: The logprob of this ChatCompletionTokenLogprob.  # noqa: E501
        :rtype: float
        """
        return self._logprob

    @logprob.setter
    def logprob(self, logprob):
        """Sets the logprob of this ChatCompletionTokenLogprob.

        The log probability of this token, if it is within the top 20 most likely tokens. Otherwise, the value `-9999.0` is used to signify that the token is very unlikely.  # noqa: E501

        :param logprob: The logprob of this ChatCompletionTokenLogprob.  # noqa: E501
        :type: float
        """
        if logprob is None:
            raise ValueError("Invalid value for `logprob`, must not be `None`")  # noqa: E501

        self._logprob = logprob

    @property
    def bytes(self):
        """Gets the bytes of this ChatCompletionTokenLogprob.  # noqa: E501

        A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters are represented by multiple tokens and their byte representations must be combined to generate the correct text representation. Can be `null` if there is no bytes representation for the token.  # noqa: E501

        :return: The bytes of this ChatCompletionTokenLogprob.  # noqa: E501
        :rtype: list[int]
        """
        return self._bytes

    @bytes.setter
    def bytes(self, bytes):
        """Sets the bytes of this ChatCompletionTokenLogprob.

        A list of integers representing the UTF-8 bytes representation of the token. Useful in instances where characters are represented by multiple tokens and their byte representations must be combined to generate the correct text representation. Can be `null` if there is no bytes representation for the token.  # noqa: E501

        :param bytes: The bytes of this ChatCompletionTokenLogprob.  # noqa: E501
        :type: list[int]
        """
        if bytes is None:
            raise ValueError("Invalid value for `bytes`, must not be `None`")  # noqa: E501

        self._bytes = bytes

    @property
    def top_logprobs(self):
        """Gets the top_logprobs of this ChatCompletionTokenLogprob.  # noqa: E501

        List of the most likely tokens and their log probability, at this token position. In rare cases, there may be fewer than the number of requested `top_logprobs` returned.  # noqa: E501

        :return: The top_logprobs of this ChatCompletionTokenLogprob.  # noqa: E501
        :rtype: list[ChatCompletionTokenLogprobTopLogprobs]
        """
        return self._top_logprobs

    @top_logprobs.setter
    def top_logprobs(self, top_logprobs):
        """Sets the top_logprobs of this ChatCompletionTokenLogprob.

        List of the most likely tokens and their log probability, at this token position. In rare cases, there may be fewer than the number of requested `top_logprobs` returned.  # noqa: E501

        :param top_logprobs: The top_logprobs of this ChatCompletionTokenLogprob.  # noqa: E501
        :type: list[ChatCompletionTokenLogprobTopLogprobs]
        """
        if top_logprobs is None:
            raise ValueError("Invalid value for `top_logprobs`, must not be `None`")  # noqa: E501

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
        if issubclass(ChatCompletionTokenLogprob, dict):
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
        if not isinstance(other, ChatCompletionTokenLogprob):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
