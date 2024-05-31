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

class CreateVectorStoreFileBatchRequest(object):
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
        'file_ids': 'list[str]'
    }

    attribute_map = {
        'file_ids': 'file_ids'
    }

    def __init__(self, file_ids=None):  # noqa: E501
        """CreateVectorStoreFileBatchRequest - a model defined in Swagger"""  # noqa: E501
        self._file_ids = None
        self.discriminator = None
        self.file_ids = file_ids

    @property
    def file_ids(self):
        """Gets the file_ids of this CreateVectorStoreFileBatchRequest.  # noqa: E501

        A list of [File](/docs/api-reference/files) IDs that the vector store should use. Useful for tools like `file_search` that can access files.  # noqa: E501

        :return: The file_ids of this CreateVectorStoreFileBatchRequest.  # noqa: E501
        :rtype: list[str]
        """
        return self._file_ids

    @file_ids.setter
    def file_ids(self, file_ids):
        """Sets the file_ids of this CreateVectorStoreFileBatchRequest.

        A list of [File](/docs/api-reference/files) IDs that the vector store should use. Useful for tools like `file_search` that can access files.  # noqa: E501

        :param file_ids: The file_ids of this CreateVectorStoreFileBatchRequest.  # noqa: E501
        :type: list[str]
        """
        if file_ids is None:
            raise ValueError("Invalid value for `file_ids`, must not be `None`")  # noqa: E501

        self._file_ids = file_ids

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
        if issubclass(CreateVectorStoreFileBatchRequest, dict):
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
        if not isinstance(other, CreateVectorStoreFileBatchRequest):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
