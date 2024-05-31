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

class FineTuningJobHyperparameters(object):
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
        'n_epochs': 'OneOfFineTuningJobHyperparametersNEpochs'
    }

    attribute_map = {
        'n_epochs': 'n_epochs'
    }

    def __init__(self, n_epochs=None):  # noqa: E501
        """FineTuningJobHyperparameters - a model defined in Swagger"""  # noqa: E501
        self._n_epochs = None
        self.discriminator = None
        self.n_epochs = n_epochs

    @property
    def n_epochs(self):
        """Gets the n_epochs of this FineTuningJobHyperparameters.  # noqa: E501

        The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset. \"auto\" decides the optimal number of epochs based on the size of the dataset. If setting the number manually, we support any number between 1 and 50 epochs.  # noqa: E501

        :return: The n_epochs of this FineTuningJobHyperparameters.  # noqa: E501
        :rtype: OneOfFineTuningJobHyperparametersNEpochs
        """
        return self._n_epochs

    @n_epochs.setter
    def n_epochs(self, n_epochs):
        """Sets the n_epochs of this FineTuningJobHyperparameters.

        The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset. \"auto\" decides the optimal number of epochs based on the size of the dataset. If setting the number manually, we support any number between 1 and 50 epochs.  # noqa: E501

        :param n_epochs: The n_epochs of this FineTuningJobHyperparameters.  # noqa: E501
        :type: OneOfFineTuningJobHyperparametersNEpochs
        """
        if n_epochs is None:
            raise ValueError("Invalid value for `n_epochs`, must not be `None`")  # noqa: E501

        self._n_epochs = n_epochs

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
        if issubclass(FineTuningJobHyperparameters, dict):
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
        if not isinstance(other, FineTuningJobHyperparameters):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other