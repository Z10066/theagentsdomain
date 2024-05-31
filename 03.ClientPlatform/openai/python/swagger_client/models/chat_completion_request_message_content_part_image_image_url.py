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

class ChatCompletionRequestMessageContentPartImageImageUrl(object):
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
        'url': 'str',
        'detail': 'str'
    }

    attribute_map = {
        'url': 'url',
        'detail': 'detail'
    }

    def __init__(self, url=None, detail='auto'):  # noqa: E501
        """ChatCompletionRequestMessageContentPartImageImageUrl - a model defined in Swagger"""  # noqa: E501
        self._url = None
        self._detail = None
        self.discriminator = None
        self.url = url
        if detail is not None:
            self.detail = detail

    @property
    def url(self):
        """Gets the url of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501

        Either a URL of the image or the base64 encoded image data.  # noqa: E501

        :return: The url of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501
        :rtype: str
        """
        return self._url

    @url.setter
    def url(self, url):
        """Sets the url of this ChatCompletionRequestMessageContentPartImageImageUrl.

        Either a URL of the image or the base64 encoded image data.  # noqa: E501

        :param url: The url of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501
        :type: str
        """
        if url is None:
            raise ValueError("Invalid value for `url`, must not be `None`")  # noqa: E501

        self._url = url

    @property
    def detail(self):
        """Gets the detail of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501

        Specifies the detail level of the image. Learn more in the [Vision guide](/docs/guides/vision/low-or-high-fidelity-image-understanding).  # noqa: E501

        :return: The detail of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501
        :rtype: str
        """
        return self._detail

    @detail.setter
    def detail(self, detail):
        """Sets the detail of this ChatCompletionRequestMessageContentPartImageImageUrl.

        Specifies the detail level of the image. Learn more in the [Vision guide](/docs/guides/vision/low-or-high-fidelity-image-understanding).  # noqa: E501

        :param detail: The detail of this ChatCompletionRequestMessageContentPartImageImageUrl.  # noqa: E501
        :type: str
        """
        allowed_values = ["auto", "low", "high"]  # noqa: E501
        if detail not in allowed_values:
            raise ValueError(
                "Invalid value for `detail` ({0}), must be one of {1}"  # noqa: E501
                .format(detail, allowed_values)
            )

        self._detail = detail

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
        if issubclass(ChatCompletionRequestMessageContentPartImageImageUrl, dict):
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
        if not isinstance(other, ChatCompletionRequestMessageContentPartImageImageUrl):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other