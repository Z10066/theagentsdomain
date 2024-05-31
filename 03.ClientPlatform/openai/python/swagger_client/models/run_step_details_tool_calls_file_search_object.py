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

class RunStepDetailsToolCallsFileSearchObject(object):
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
        'id': 'str',
        'type': 'str',
        'file_search': 'object'
    }

    attribute_map = {
        'id': 'id',
        'type': 'type',
        'file_search': 'file_search'
    }

    def __init__(self, id=None, type=None, file_search=None):  # noqa: E501
        """RunStepDetailsToolCallsFileSearchObject - a model defined in Swagger"""  # noqa: E501
        self._id = None
        self._type = None
        self._file_search = None
        self.discriminator = None
        self.id = id
        self.type = type
        self.file_search = file_search

    @property
    def id(self):
        """Gets the id of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501

        The ID of the tool call object.  # noqa: E501

        :return: The id of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this RunStepDetailsToolCallsFileSearchObject.

        The ID of the tool call object.  # noqa: E501

        :param id: The id of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :type: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def type(self):
        """Gets the type of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501

        The type of tool call. This is always going to be `file_search` for this type of tool call.  # noqa: E501

        :return: The type of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """Sets the type of this RunStepDetailsToolCallsFileSearchObject.

        The type of tool call. This is always going to be `file_search` for this type of tool call.  # noqa: E501

        :param type: The type of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :type: str
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501
        allowed_values = ["file_search"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"  # noqa: E501
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def file_search(self):
        """Gets the file_search of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501

        For now, this is always going to be an empty object.  # noqa: E501

        :return: The file_search of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :rtype: object
        """
        return self._file_search

    @file_search.setter
    def file_search(self, file_search):
        """Sets the file_search of this RunStepDetailsToolCallsFileSearchObject.

        For now, this is always going to be an empty object.  # noqa: E501

        :param file_search: The file_search of this RunStepDetailsToolCallsFileSearchObject.  # noqa: E501
        :type: object
        """
        if file_search is None:
            raise ValueError("Invalid value for `file_search`, must not be `None`")  # noqa: E501

        self._file_search = file_search

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
        if issubclass(RunStepDetailsToolCallsFileSearchObject, dict):
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
        if not isinstance(other, RunStepDetailsToolCallsFileSearchObject):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
