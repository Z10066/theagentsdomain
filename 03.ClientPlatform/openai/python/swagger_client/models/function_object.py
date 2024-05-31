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

class FunctionObject(object):
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
        'description': 'str',
        'name': 'str',
        'parameters': 'FunctionParameters'
    }

    attribute_map = {
        'description': 'description',
        'name': 'name',
        'parameters': 'parameters'
    }

    def __init__(self, description=None, name=None, parameters=None):  # noqa: E501
        """FunctionObject - a model defined in Swagger"""  # noqa: E501
        self._description = None
        self._name = None
        self._parameters = None
        self.discriminator = None
        if description is not None:
            self.description = description
        self.name = name
        if parameters is not None:
            self.parameters = parameters

    @property
    def description(self):
        """Gets the description of this FunctionObject.  # noqa: E501

        A description of what the function does, used by the model to choose when and how to call the function.  # noqa: E501

        :return: The description of this FunctionObject.  # noqa: E501
        :rtype: str
        """
        return self._description

    @description.setter
    def description(self, description):
        """Sets the description of this FunctionObject.

        A description of what the function does, used by the model to choose when and how to call the function.  # noqa: E501

        :param description: The description of this FunctionObject.  # noqa: E501
        :type: str
        """

        self._description = description

    @property
    def name(self):
        """Gets the name of this FunctionObject.  # noqa: E501

        The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64.  # noqa: E501

        :return: The name of this FunctionObject.  # noqa: E501
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name):
        """Sets the name of this FunctionObject.

        The name of the function to be called. Must be a-z, A-Z, 0-9, or contain underscores and dashes, with a maximum length of 64.  # noqa: E501

        :param name: The name of this FunctionObject.  # noqa: E501
        :type: str
        """
        if name is None:
            raise ValueError("Invalid value for `name`, must not be `None`")  # noqa: E501

        self._name = name

    @property
    def parameters(self):
        """Gets the parameters of this FunctionObject.  # noqa: E501


        :return: The parameters of this FunctionObject.  # noqa: E501
        :rtype: FunctionParameters
        """
        return self._parameters

    @parameters.setter
    def parameters(self, parameters):
        """Sets the parameters of this FunctionObject.


        :param parameters: The parameters of this FunctionObject.  # noqa: E501
        :type: FunctionParameters
        """

        self._parameters = parameters

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
        if issubclass(FunctionObject, dict):
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
        if not isinstance(other, FunctionObject):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
