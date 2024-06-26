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

class CreateModerationRequest(object):
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
        'input': 'OneOfCreateModerationRequestInput',
        'model': 'AnyOfCreateModerationRequestModel'
    }

    attribute_map = {
        'input': 'input',
        'model': 'model'
    }

    def __init__(self, input=None, model=None):  # noqa: E501
        """CreateModerationRequest - a model defined in Swagger"""  # noqa: E501
        self._input = None
        self._model = None
        self.discriminator = None
        self.input = input
        if model is not None:
            self.model = model

    @property
    def input(self):
        """Gets the input of this CreateModerationRequest.  # noqa: E501

        The input text to classify  # noqa: E501

        :return: The input of this CreateModerationRequest.  # noqa: E501
        :rtype: OneOfCreateModerationRequestInput
        """
        return self._input

    @input.setter
    def input(self, input):
        """Sets the input of this CreateModerationRequest.

        The input text to classify  # noqa: E501

        :param input: The input of this CreateModerationRequest.  # noqa: E501
        :type: OneOfCreateModerationRequestInput
        """
        if input is None:
            raise ValueError("Invalid value for `input`, must not be `None`")  # noqa: E501

        self._input = input

    @property
    def model(self):
        """Gets the model of this CreateModerationRequest.  # noqa: E501

        Two content moderations models are available: `text-moderation-stable` and `text-moderation-latest`.  The default is `text-moderation-latest` which will be automatically upgraded over time. This ensures you are always using our most accurate model. If you use `text-moderation-stable`, we will provide advanced notice before updating the model. Accuracy of `text-moderation-stable` may be slightly lower than for `text-moderation-latest`.   # noqa: E501

        :return: The model of this CreateModerationRequest.  # noqa: E501
        :rtype: AnyOfCreateModerationRequestModel
        """
        return self._model

    @model.setter
    def model(self, model):
        """Sets the model of this CreateModerationRequest.

        Two content moderations models are available: `text-moderation-stable` and `text-moderation-latest`.  The default is `text-moderation-latest` which will be automatically upgraded over time. This ensures you are always using our most accurate model. If you use `text-moderation-stable`, we will provide advanced notice before updating the model. Accuracy of `text-moderation-stable` may be slightly lower than for `text-moderation-latest`.   # noqa: E501

        :param model: The model of this CreateModerationRequest.  # noqa: E501
        :type: AnyOfCreateModerationRequestModel
        """

        self._model = model

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
        if issubclass(CreateModerationRequest, dict):
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
        if not isinstance(other, CreateModerationRequest):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
