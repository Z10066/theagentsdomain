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

class ListRunsResponse(object):
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
        'object': 'str',
        'data': 'list[RunObject]',
        'first_id': 'str',
        'last_id': 'str',
        'has_more': 'bool'
    }

    attribute_map = {
        'object': 'object',
        'data': 'data',
        'first_id': 'first_id',
        'last_id': 'last_id',
        'has_more': 'has_more'
    }

    def __init__(self, object=None, data=None, first_id=None, last_id=None, has_more=None):  # noqa: E501
        """ListRunsResponse - a model defined in Swagger"""  # noqa: E501
        self._object = None
        self._data = None
        self._first_id = None
        self._last_id = None
        self._has_more = None
        self.discriminator = None
        self.object = object
        self.data = data
        self.first_id = first_id
        self.last_id = last_id
        self.has_more = has_more

    @property
    def object(self):
        """Gets the object of this ListRunsResponse.  # noqa: E501


        :return: The object of this ListRunsResponse.  # noqa: E501
        :rtype: str
        """
        return self._object

    @object.setter
    def object(self, object):
        """Sets the object of this ListRunsResponse.


        :param object: The object of this ListRunsResponse.  # noqa: E501
        :type: str
        """
        if object is None:
            raise ValueError("Invalid value for `object`, must not be `None`")  # noqa: E501

        self._object = object

    @property
    def data(self):
        """Gets the data of this ListRunsResponse.  # noqa: E501


        :return: The data of this ListRunsResponse.  # noqa: E501
        :rtype: list[RunObject]
        """
        return self._data

    @data.setter
    def data(self, data):
        """Sets the data of this ListRunsResponse.


        :param data: The data of this ListRunsResponse.  # noqa: E501
        :type: list[RunObject]
        """
        if data is None:
            raise ValueError("Invalid value for `data`, must not be `None`")  # noqa: E501

        self._data = data

    @property
    def first_id(self):
        """Gets the first_id of this ListRunsResponse.  # noqa: E501


        :return: The first_id of this ListRunsResponse.  # noqa: E501
        :rtype: str
        """
        return self._first_id

    @first_id.setter
    def first_id(self, first_id):
        """Sets the first_id of this ListRunsResponse.


        :param first_id: The first_id of this ListRunsResponse.  # noqa: E501
        :type: str
        """
        if first_id is None:
            raise ValueError("Invalid value for `first_id`, must not be `None`")  # noqa: E501

        self._first_id = first_id

    @property
    def last_id(self):
        """Gets the last_id of this ListRunsResponse.  # noqa: E501


        :return: The last_id of this ListRunsResponse.  # noqa: E501
        :rtype: str
        """
        return self._last_id

    @last_id.setter
    def last_id(self, last_id):
        """Sets the last_id of this ListRunsResponse.


        :param last_id: The last_id of this ListRunsResponse.  # noqa: E501
        :type: str
        """
        if last_id is None:
            raise ValueError("Invalid value for `last_id`, must not be `None`")  # noqa: E501

        self._last_id = last_id

    @property
    def has_more(self):
        """Gets the has_more of this ListRunsResponse.  # noqa: E501


        :return: The has_more of this ListRunsResponse.  # noqa: E501
        :rtype: bool
        """
        return self._has_more

    @has_more.setter
    def has_more(self, has_more):
        """Sets the has_more of this ListRunsResponse.


        :param has_more: The has_more of this ListRunsResponse.  # noqa: E501
        :type: bool
        """
        if has_more is None:
            raise ValueError("Invalid value for `has_more`, must not be `None`")  # noqa: E501

        self._has_more = has_more

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
        if issubclass(ListRunsResponse, dict):
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
        if not isinstance(other, ListRunsResponse):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
