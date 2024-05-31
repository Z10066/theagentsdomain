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

class CreateImageRequest(object):
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
        'prompt': 'str',
        'model': 'AnyOfCreateImageRequestModel',
        'n': 'int',
        'quality': 'str',
        'response_format': 'str',
        'size': 'str',
        'style': 'str',
        'user': 'str'
    }

    attribute_map = {
        'prompt': 'prompt',
        'model': 'model',
        'n': 'n',
        'quality': 'quality',
        'response_format': 'response_format',
        'size': 'size',
        'style': 'style',
        'user': 'user'
    }

    def __init__(self, prompt=None, model=None, n=1, quality='standard', response_format='url', size='1024x1024', style='vivid', user=None):  # noqa: E501
        """CreateImageRequest - a model defined in Swagger"""  # noqa: E501
        self._prompt = None
        self._model = None
        self._n = None
        self._quality = None
        self._response_format = None
        self._size = None
        self._style = None
        self._user = None
        self.discriminator = None
        self.prompt = prompt
        if model is not None:
            self.model = model
        if n is not None:
            self.n = n
        if quality is not None:
            self.quality = quality
        if response_format is not None:
            self.response_format = response_format
        if size is not None:
            self.size = size
        if style is not None:
            self.style = style
        if user is not None:
            self.user = user

    @property
    def prompt(self):
        """Gets the prompt of this CreateImageRequest.  # noqa: E501

        A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`.  # noqa: E501

        :return: The prompt of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._prompt

    @prompt.setter
    def prompt(self, prompt):
        """Sets the prompt of this CreateImageRequest.

        A text description of the desired image(s). The maximum length is 1000 characters for `dall-e-2` and 4000 characters for `dall-e-3`.  # noqa: E501

        :param prompt: The prompt of this CreateImageRequest.  # noqa: E501
        :type: str
        """
        if prompt is None:
            raise ValueError("Invalid value for `prompt`, must not be `None`")  # noqa: E501

        self._prompt = prompt

    @property
    def model(self):
        """Gets the model of this CreateImageRequest.  # noqa: E501

        The model to use for image generation.  # noqa: E501

        :return: The model of this CreateImageRequest.  # noqa: E501
        :rtype: AnyOfCreateImageRequestModel
        """
        return self._model

    @model.setter
    def model(self, model):
        """Sets the model of this CreateImageRequest.

        The model to use for image generation.  # noqa: E501

        :param model: The model of this CreateImageRequest.  # noqa: E501
        :type: AnyOfCreateImageRequestModel
        """

        self._model = model

    @property
    def n(self):
        """Gets the n of this CreateImageRequest.  # noqa: E501

        The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.  # noqa: E501

        :return: The n of this CreateImageRequest.  # noqa: E501
        :rtype: int
        """
        return self._n

    @n.setter
    def n(self, n):
        """Sets the n of this CreateImageRequest.

        The number of images to generate. Must be between 1 and 10. For `dall-e-3`, only `n=1` is supported.  # noqa: E501

        :param n: The n of this CreateImageRequest.  # noqa: E501
        :type: int
        """

        self._n = n

    @property
    def quality(self):
        """Gets the quality of this CreateImageRequest.  # noqa: E501

        The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.  # noqa: E501

        :return: The quality of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._quality

    @quality.setter
    def quality(self, quality):
        """Sets the quality of this CreateImageRequest.

        The quality of the image that will be generated. `hd` creates images with finer details and greater consistency across the image. This param is only supported for `dall-e-3`.  # noqa: E501

        :param quality: The quality of this CreateImageRequest.  # noqa: E501
        :type: str
        """
        allowed_values = ["standard", "hd"]  # noqa: E501
        if quality not in allowed_values:
            raise ValueError(
                "Invalid value for `quality` ({0}), must be one of {1}"  # noqa: E501
                .format(quality, allowed_values)
            )

        self._quality = quality

    @property
    def response_format(self):
        """Gets the response_format of this CreateImageRequest.  # noqa: E501

        The format in which the generated images are returned. Must be one of `url` or `b64_json`. URLs are only valid for 60 minutes after the image has been generated.  # noqa: E501

        :return: The response_format of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._response_format

    @response_format.setter
    def response_format(self, response_format):
        """Sets the response_format of this CreateImageRequest.

        The format in which the generated images are returned. Must be one of `url` or `b64_json`. URLs are only valid for 60 minutes after the image has been generated.  # noqa: E501

        :param response_format: The response_format of this CreateImageRequest.  # noqa: E501
        :type: str
        """
        allowed_values = ["url", "b64_json"]  # noqa: E501
        if response_format not in allowed_values:
            raise ValueError(
                "Invalid value for `response_format` ({0}), must be one of {1}"  # noqa: E501
                .format(response_format, allowed_values)
            )

        self._response_format = response_format

    @property
    def size(self):
        """Gets the size of this CreateImageRequest.  # noqa: E501

        The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.  # noqa: E501

        :return: The size of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._size

    @size.setter
    def size(self, size):
        """Sets the size of this CreateImageRequest.

        The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024` for `dall-e-2`. Must be one of `1024x1024`, `1792x1024`, or `1024x1792` for `dall-e-3` models.  # noqa: E501

        :param size: The size of this CreateImageRequest.  # noqa: E501
        :type: str
        """
        allowed_values = ["256x256", "512x512", "1024x1024", "1792x1024", "1024x1792"]  # noqa: E501
        if size not in allowed_values:
            raise ValueError(
                "Invalid value for `size` ({0}), must be one of {1}"  # noqa: E501
                .format(size, allowed_values)
            )

        self._size = size

    @property
    def style(self):
        """Gets the style of this CreateImageRequest.  # noqa: E501

        The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.  # noqa: E501

        :return: The style of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._style

    @style.setter
    def style(self, style):
        """Sets the style of this CreateImageRequest.

        The style of the generated images. Must be one of `vivid` or `natural`. Vivid causes the model to lean towards generating hyper-real and dramatic images. Natural causes the model to produce more natural, less hyper-real looking images. This param is only supported for `dall-e-3`.  # noqa: E501

        :param style: The style of this CreateImageRequest.  # noqa: E501
        :type: str
        """
        allowed_values = ["vivid", "natural"]  # noqa: E501
        if style not in allowed_values:
            raise ValueError(
                "Invalid value for `style` ({0}), must be one of {1}"  # noqa: E501
                .format(style, allowed_values)
            )

        self._style = style

    @property
    def user(self):
        """Gets the user of this CreateImageRequest.  # noqa: E501

        A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).   # noqa: E501

        :return: The user of this CreateImageRequest.  # noqa: E501
        :rtype: str
        """
        return self._user

    @user.setter
    def user(self, user):
        """Sets the user of this CreateImageRequest.

        A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).   # noqa: E501

        :param user: The user of this CreateImageRequest.  # noqa: E501
        :type: str
        """

        self._user = user

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
        if issubclass(CreateImageRequest, dict):
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
        if not isinstance(other, CreateImageRequest):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other