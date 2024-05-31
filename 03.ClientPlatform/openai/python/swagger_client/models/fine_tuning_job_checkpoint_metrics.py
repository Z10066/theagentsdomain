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

class FineTuningJobCheckpointMetrics(object):
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
        'step': 'float',
        'train_loss': 'float',
        'train_mean_token_accuracy': 'float',
        'valid_loss': 'float',
        'valid_mean_token_accuracy': 'float',
        'full_valid_loss': 'float',
        'full_valid_mean_token_accuracy': 'float'
    }

    attribute_map = {
        'step': 'step',
        'train_loss': 'train_loss',
        'train_mean_token_accuracy': 'train_mean_token_accuracy',
        'valid_loss': 'valid_loss',
        'valid_mean_token_accuracy': 'valid_mean_token_accuracy',
        'full_valid_loss': 'full_valid_loss',
        'full_valid_mean_token_accuracy': 'full_valid_mean_token_accuracy'
    }

    def __init__(self, step=None, train_loss=None, train_mean_token_accuracy=None, valid_loss=None, valid_mean_token_accuracy=None, full_valid_loss=None, full_valid_mean_token_accuracy=None):  # noqa: E501
        """FineTuningJobCheckpointMetrics - a model defined in Swagger"""  # noqa: E501
        self._step = None
        self._train_loss = None
        self._train_mean_token_accuracy = None
        self._valid_loss = None
        self._valid_mean_token_accuracy = None
        self._full_valid_loss = None
        self._full_valid_mean_token_accuracy = None
        self.discriminator = None
        if step is not None:
            self.step = step
        if train_loss is not None:
            self.train_loss = train_loss
        if train_mean_token_accuracy is not None:
            self.train_mean_token_accuracy = train_mean_token_accuracy
        if valid_loss is not None:
            self.valid_loss = valid_loss
        if valid_mean_token_accuracy is not None:
            self.valid_mean_token_accuracy = valid_mean_token_accuracy
        if full_valid_loss is not None:
            self.full_valid_loss = full_valid_loss
        if full_valid_mean_token_accuracy is not None:
            self.full_valid_mean_token_accuracy = full_valid_mean_token_accuracy

    @property
    def step(self):
        """Gets the step of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The step of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._step

    @step.setter
    def step(self, step):
        """Sets the step of this FineTuningJobCheckpointMetrics.


        :param step: The step of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._step = step

    @property
    def train_loss(self):
        """Gets the train_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The train_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._train_loss

    @train_loss.setter
    def train_loss(self, train_loss):
        """Sets the train_loss of this FineTuningJobCheckpointMetrics.


        :param train_loss: The train_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._train_loss = train_loss

    @property
    def train_mean_token_accuracy(self):
        """Gets the train_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The train_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._train_mean_token_accuracy

    @train_mean_token_accuracy.setter
    def train_mean_token_accuracy(self, train_mean_token_accuracy):
        """Sets the train_mean_token_accuracy of this FineTuningJobCheckpointMetrics.


        :param train_mean_token_accuracy: The train_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._train_mean_token_accuracy = train_mean_token_accuracy

    @property
    def valid_loss(self):
        """Gets the valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._valid_loss

    @valid_loss.setter
    def valid_loss(self, valid_loss):
        """Sets the valid_loss of this FineTuningJobCheckpointMetrics.


        :param valid_loss: The valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._valid_loss = valid_loss

    @property
    def valid_mean_token_accuracy(self):
        """Gets the valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._valid_mean_token_accuracy

    @valid_mean_token_accuracy.setter
    def valid_mean_token_accuracy(self, valid_mean_token_accuracy):
        """Sets the valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.


        :param valid_mean_token_accuracy: The valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._valid_mean_token_accuracy = valid_mean_token_accuracy

    @property
    def full_valid_loss(self):
        """Gets the full_valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The full_valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._full_valid_loss

    @full_valid_loss.setter
    def full_valid_loss(self, full_valid_loss):
        """Sets the full_valid_loss of this FineTuningJobCheckpointMetrics.


        :param full_valid_loss: The full_valid_loss of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._full_valid_loss = full_valid_loss

    @property
    def full_valid_mean_token_accuracy(self):
        """Gets the full_valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501


        :return: The full_valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :rtype: float
        """
        return self._full_valid_mean_token_accuracy

    @full_valid_mean_token_accuracy.setter
    def full_valid_mean_token_accuracy(self, full_valid_mean_token_accuracy):
        """Sets the full_valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.


        :param full_valid_mean_token_accuracy: The full_valid_mean_token_accuracy of this FineTuningJobCheckpointMetrics.  # noqa: E501
        :type: float
        """

        self._full_valid_mean_token_accuracy = full_valid_mean_token_accuracy

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
        if issubclass(FineTuningJobCheckpointMetrics, dict):
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
        if not isinstance(other, FineTuningJobCheckpointMetrics):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other