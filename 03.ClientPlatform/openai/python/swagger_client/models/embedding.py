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

class Embedding(object):
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
        'index': 'int',
        'embedding': 'list[float]',
        'object': 'str'
    }

    attribute_map = {
        'index': 'index',
        'embedding': 'embedding',
        'object': 'object'
    }

    def __init__(self, index=None, embedding=None, object=None):  # noqa: E501
        """Embedding - a model defined in Swagger"""  # noqa: E501
        self._index = None
        self._embedding = None
        self._object = None
        self.discriminator = None
        self.index = index
        self.embedding = embedding
        self.object = object

    @property
    def index(self):
        """Gets the index of this Embedding.  # noqa: E501

        The index of the embedding in the list of embeddings.  # noqa: E501

        :return: The index of this Embedding.  # noqa: E501
        :rtype: int
        """
        return self._index

    @index.setter
    def index(self, index):
        """Sets the index of this Embedding.

        The index of the embedding in the list of embeddings.  # noqa: E501

        :param index: The index of this Embedding.  # noqa: E501
        :type: int
        """
        if index is None:
            raise ValueError("Invalid value for `index`, must not be `None`")  # noqa: E501

        self._index = index

    @property
    def embedding(self):
        """Gets the embedding of this Embedding.  # noqa: E501

        The embedding vector, which is a list of floats. The length of vector depends on the model as listed in the [embedding guide](/docs/guides/embeddings).   # noqa: E501

        :return: The embedding of this Embedding.  # noqa: E501
        :rtype: list[float]
        """
        return self._embedding

    @embedding.setter
    def embedding(self, embedding):
        """Sets the embedding of this Embedding.

        The embedding vector, which is a list of floats. The length of vector depends on the model as listed in the [embedding guide](/docs/guides/embeddings).   # noqa: E501

        :param embedding: The embedding of this Embedding.  # noqa: E501
        :type: list[float]
        """
        if embedding is None:
            raise ValueError("Invalid value for `embedding`, must not be `None`")  # noqa: E501

        self._embedding = embedding

    @property
    def object(self):
        """Gets the object of this Embedding.  # noqa: E501

        The object type, which is always \"embedding\".  # noqa: E501

        :return: The object of this Embedding.  # noqa: E501
        :rtype: str
        """
        return self._object

    @object.setter
    def object(self, object):
        """Sets the object of this Embedding.

        The object type, which is always \"embedding\".  # noqa: E501

        :param object: The object of this Embedding.  # noqa: E501
        :type: str
        """
        if object is None:
            raise ValueError("Invalid value for `object`, must not be `None`")  # noqa: E501
        allowed_values = ["embedding"]  # noqa: E501
        if object not in allowed_values:
            raise ValueError(
                "Invalid value for `object` ({0}), must be one of {1}"  # noqa: E501
                .format(object, allowed_values)
            )

        self._object = object

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
        if issubclass(Embedding, dict):
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
        if not isinstance(other, Embedding):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
