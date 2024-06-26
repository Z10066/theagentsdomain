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

class ThreadObjectToolResourcesFileSearch(object):
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
        'vector_store_ids': 'list[str]'
    }

    attribute_map = {
        'vector_store_ids': 'vector_store_ids'
    }

    def __init__(self, vector_store_ids=None):  # noqa: E501
        """ThreadObjectToolResourcesFileSearch - a model defined in Swagger"""  # noqa: E501
        self._vector_store_ids = None
        self.discriminator = None
        if vector_store_ids is not None:
            self.vector_store_ids = vector_store_ids

    @property
    def vector_store_ids(self):
        """Gets the vector_store_ids of this ThreadObjectToolResourcesFileSearch.  # noqa: E501

        The [vector store](/docs/api-reference/vector-stores/object) attached to this thread. There can be a maximum of 1 vector store attached to the thread.   # noqa: E501

        :return: The vector_store_ids of this ThreadObjectToolResourcesFileSearch.  # noqa: E501
        :rtype: list[str]
        """
        return self._vector_store_ids

    @vector_store_ids.setter
    def vector_store_ids(self, vector_store_ids):
        """Sets the vector_store_ids of this ThreadObjectToolResourcesFileSearch.

        The [vector store](/docs/api-reference/vector-stores/object) attached to this thread. There can be a maximum of 1 vector store attached to the thread.   # noqa: E501

        :param vector_store_ids: The vector_store_ids of this ThreadObjectToolResourcesFileSearch.  # noqa: E501
        :type: list[str]
        """

        self._vector_store_ids = vector_store_ids

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
        if issubclass(ThreadObjectToolResourcesFileSearch, dict):
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
        if not isinstance(other, ThreadObjectToolResourcesFileSearch):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
