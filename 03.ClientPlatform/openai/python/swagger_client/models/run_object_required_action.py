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

class RunObjectRequiredAction(object):
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
        'type': 'str',
        'submit_tool_outputs': 'RunObjectRequiredActionSubmitToolOutputs'
    }

    attribute_map = {
        'type': 'type',
        'submit_tool_outputs': 'submit_tool_outputs'
    }

    def __init__(self, type=None, submit_tool_outputs=None):  # noqa: E501
        """RunObjectRequiredAction - a model defined in Swagger"""  # noqa: E501
        self._type = None
        self._submit_tool_outputs = None
        self.discriminator = None
        self.type = type
        self.submit_tool_outputs = submit_tool_outputs

    @property
    def type(self):
        """Gets the type of this RunObjectRequiredAction.  # noqa: E501

        For now, this is always `submit_tool_outputs`.  # noqa: E501

        :return: The type of this RunObjectRequiredAction.  # noqa: E501
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """Sets the type of this RunObjectRequiredAction.

        For now, this is always `submit_tool_outputs`.  # noqa: E501

        :param type: The type of this RunObjectRequiredAction.  # noqa: E501
        :type: str
        """
        if type is None:
            raise ValueError("Invalid value for `type`, must not be `None`")  # noqa: E501
        allowed_values = ["submit_tool_outputs"]  # noqa: E501
        if type not in allowed_values:
            raise ValueError(
                "Invalid value for `type` ({0}), must be one of {1}"  # noqa: E501
                .format(type, allowed_values)
            )

        self._type = type

    @property
    def submit_tool_outputs(self):
        """Gets the submit_tool_outputs of this RunObjectRequiredAction.  # noqa: E501


        :return: The submit_tool_outputs of this RunObjectRequiredAction.  # noqa: E501
        :rtype: RunObjectRequiredActionSubmitToolOutputs
        """
        return self._submit_tool_outputs

    @submit_tool_outputs.setter
    def submit_tool_outputs(self, submit_tool_outputs):
        """Sets the submit_tool_outputs of this RunObjectRequiredAction.


        :param submit_tool_outputs: The submit_tool_outputs of this RunObjectRequiredAction.  # noqa: E501
        :type: RunObjectRequiredActionSubmitToolOutputs
        """
        if submit_tool_outputs is None:
            raise ValueError("Invalid value for `submit_tool_outputs`, must not be `None`")  # noqa: E501

        self._submit_tool_outputs = submit_tool_outputs

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
        if issubclass(RunObjectRequiredAction, dict):
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
        if not isinstance(other, RunObjectRequiredAction):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
