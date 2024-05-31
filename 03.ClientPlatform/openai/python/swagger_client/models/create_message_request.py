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

class CreateMessageRequest(object):
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
        'role': 'str',
        'content': 'OneOfCreateMessageRequestContent',
        'attachments': 'list[MessageObjectAttachments]',
        'metadata': 'object'
    }

    attribute_map = {
        'role': 'role',
        'content': 'content',
        'attachments': 'attachments',
        'metadata': 'metadata'
    }

    def __init__(self, role=None, content=None, attachments=None, metadata=None):  # noqa: E501
        """CreateMessageRequest - a model defined in Swagger"""  # noqa: E501
        self._role = None
        self._content = None
        self._attachments = None
        self._metadata = None
        self.discriminator = None
        self.role = role
        self.content = content
        if attachments is not None:
            self.attachments = attachments
        if metadata is not None:
            self.metadata = metadata

    @property
    def role(self):
        """Gets the role of this CreateMessageRequest.  # noqa: E501

        The role of the entity that is creating the message. Allowed values include: - `user`: Indicates the message is sent by an actual user and should be used in most cases to represent user-generated messages. - `assistant`: Indicates the message is generated by the assistant. Use this value to insert messages from the assistant into the conversation.   # noqa: E501

        :return: The role of this CreateMessageRequest.  # noqa: E501
        :rtype: str
        """
        return self._role

    @role.setter
    def role(self, role):
        """Sets the role of this CreateMessageRequest.

        The role of the entity that is creating the message. Allowed values include: - `user`: Indicates the message is sent by an actual user and should be used in most cases to represent user-generated messages. - `assistant`: Indicates the message is generated by the assistant. Use this value to insert messages from the assistant into the conversation.   # noqa: E501

        :param role: The role of this CreateMessageRequest.  # noqa: E501
        :type: str
        """
        if role is None:
            raise ValueError("Invalid value for `role`, must not be `None`")  # noqa: E501
        allowed_values = ["user", "assistant"]  # noqa: E501
        if role not in allowed_values:
            raise ValueError(
                "Invalid value for `role` ({0}), must be one of {1}"  # noqa: E501
                .format(role, allowed_values)
            )

        self._role = role

    @property
    def content(self):
        """Gets the content of this CreateMessageRequest.  # noqa: E501


        :return: The content of this CreateMessageRequest.  # noqa: E501
        :rtype: OneOfCreateMessageRequestContent
        """
        return self._content

    @content.setter
    def content(self, content):
        """Sets the content of this CreateMessageRequest.


        :param content: The content of this CreateMessageRequest.  # noqa: E501
        :type: OneOfCreateMessageRequestContent
        """
        if content is None:
            raise ValueError("Invalid value for `content`, must not be `None`")  # noqa: E501

        self._content = content

    @property
    def attachments(self):
        """Gets the attachments of this CreateMessageRequest.  # noqa: E501

        A list of files attached to the message, and the tools they should be added to.  # noqa: E501

        :return: The attachments of this CreateMessageRequest.  # noqa: E501
        :rtype: list[MessageObjectAttachments]
        """
        return self._attachments

    @attachments.setter
    def attachments(self, attachments):
        """Sets the attachments of this CreateMessageRequest.

        A list of files attached to the message, and the tools they should be added to.  # noqa: E501

        :param attachments: The attachments of this CreateMessageRequest.  # noqa: E501
        :type: list[MessageObjectAttachments]
        """

        self._attachments = attachments

    @property
    def metadata(self):
        """Gets the metadata of this CreateMessageRequest.  # noqa: E501

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :return: The metadata of this CreateMessageRequest.  # noqa: E501
        :rtype: object
        """
        return self._metadata

    @metadata.setter
    def metadata(self, metadata):
        """Sets the metadata of this CreateMessageRequest.

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :param metadata: The metadata of this CreateMessageRequest.  # noqa: E501
        :type: object
        """

        self._metadata = metadata

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
        if issubclass(CreateMessageRequest, dict):
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
        if not isinstance(other, CreateMessageRequest):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
