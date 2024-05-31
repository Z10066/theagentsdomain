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

class CreateFineTuningJobRequestHyperparameters(object):
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
        'batch_size': 'OneOfCreateFineTuningJobRequestHyperparametersBatchSize',
        'learning_rate_multiplier': 'OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier',
        'n_epochs': 'OneOfCreateFineTuningJobRequestHyperparametersNEpochs'
    }

    attribute_map = {
        'batch_size': 'batch_size',
        'learning_rate_multiplier': 'learning_rate_multiplier',
        'n_epochs': 'n_epochs'
    }

    def __init__(self, batch_size=None, learning_rate_multiplier=None, n_epochs=None):  # noqa: E501
        """CreateFineTuningJobRequestHyperparameters - a model defined in Swagger"""  # noqa: E501
        self._batch_size = None
        self._learning_rate_multiplier = None
        self._n_epochs = None
        self.discriminator = None
        if batch_size is not None:
            self.batch_size = batch_size
        if learning_rate_multiplier is not None:
            self.learning_rate_multiplier = learning_rate_multiplier
        if n_epochs is not None:
            self.n_epochs = n_epochs

    @property
    def batch_size(self):
        """Gets the batch_size of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501

        Number of examples in each batch. A larger batch size means that model parameters are updated less frequently, but with lower variance.   # noqa: E501

        :return: The batch_size of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :rtype: OneOfCreateFineTuningJobRequestHyperparametersBatchSize
        """
        return self._batch_size

    @batch_size.setter
    def batch_size(self, batch_size):
        """Sets the batch_size of this CreateFineTuningJobRequestHyperparameters.

        Number of examples in each batch. A larger batch size means that model parameters are updated less frequently, but with lower variance.   # noqa: E501

        :param batch_size: The batch_size of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :type: OneOfCreateFineTuningJobRequestHyperparametersBatchSize
        """

        self._batch_size = batch_size

    @property
    def learning_rate_multiplier(self):
        """Gets the learning_rate_multiplier of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501

        Scaling factor for the learning rate. A smaller learning rate may be useful to avoid overfitting.   # noqa: E501

        :return: The learning_rate_multiplier of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :rtype: OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier
        """
        return self._learning_rate_multiplier

    @learning_rate_multiplier.setter
    def learning_rate_multiplier(self, learning_rate_multiplier):
        """Sets the learning_rate_multiplier of this CreateFineTuningJobRequestHyperparameters.

        Scaling factor for the learning rate. A smaller learning rate may be useful to avoid overfitting.   # noqa: E501

        :param learning_rate_multiplier: The learning_rate_multiplier of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :type: OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier
        """

        self._learning_rate_multiplier = learning_rate_multiplier

    @property
    def n_epochs(self):
        """Gets the n_epochs of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501

        The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.   # noqa: E501

        :return: The n_epochs of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :rtype: OneOfCreateFineTuningJobRequestHyperparametersNEpochs
        """
        return self._n_epochs

    @n_epochs.setter
    def n_epochs(self, n_epochs):
        """Sets the n_epochs of this CreateFineTuningJobRequestHyperparameters.

        The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.   # noqa: E501

        :param n_epochs: The n_epochs of this CreateFineTuningJobRequestHyperparameters.  # noqa: E501
        :type: OneOfCreateFineTuningJobRequestHyperparametersNEpochs
        """

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
        if issubclass(CreateFineTuningJobRequestHyperparameters, dict):
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
        if not isinstance(other, CreateFineTuningJobRequestHyperparameters):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other