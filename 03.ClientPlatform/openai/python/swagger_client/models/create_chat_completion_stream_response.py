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

class CreateChatCompletionStreamResponse(object):
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
        'choices': 'list[CreateChatCompletionStreamResponseChoices]',
        'created': 'int',
        'model': 'str',
        'system_fingerprint': 'str',
        'object': 'str',
        'usage': 'CreateChatCompletionStreamResponseUsage'
    }

    attribute_map = {
        'id': 'id',
        'choices': 'choices',
        'created': 'created',
        'model': 'model',
        'system_fingerprint': 'system_fingerprint',
        'object': 'object',
        'usage': 'usage'
    }

    def __init__(self, id=None, choices=None, created=None, model=None, system_fingerprint=None, object=None, usage=None):  # noqa: E501
        """CreateChatCompletionStreamResponse - a model defined in Swagger"""  # noqa: E501
        self._id = None
        self._choices = None
        self._created = None
        self._model = None
        self._system_fingerprint = None
        self._object = None
        self._usage = None
        self.discriminator = None
        self.id = id
        self.choices = choices
        self.created = created
        self.model = model
        if system_fingerprint is not None:
            self.system_fingerprint = system_fingerprint
        self.object = object
        if usage is not None:
            self.usage = usage

    @property
    def id(self):
        """Gets the id of this CreateChatCompletionStreamResponse.  # noqa: E501

        A unique identifier for the chat completion. Each chunk has the same ID.  # noqa: E501

        :return: The id of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this CreateChatCompletionStreamResponse.

        A unique identifier for the chat completion. Each chunk has the same ID.  # noqa: E501

        :param id: The id of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def choices(self):
        """Gets the choices of this CreateChatCompletionStreamResponse.  # noqa: E501

        A list of chat completion choices. Can contain more than one elements if `n` is greater than 1. Can also be empty for the last chunk if you set `stream_options: {\"include_usage\": true}`.   # noqa: E501

        :return: The choices of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: list[CreateChatCompletionStreamResponseChoices]
        """
        return self._choices

    @choices.setter
    def choices(self, choices):
        """Sets the choices of this CreateChatCompletionStreamResponse.

        A list of chat completion choices. Can contain more than one elements if `n` is greater than 1. Can also be empty for the last chunk if you set `stream_options: {\"include_usage\": true}`.   # noqa: E501

        :param choices: The choices of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: list[CreateChatCompletionStreamResponseChoices]
        """
        if choices is None:
            raise ValueError("Invalid value for `choices`, must not be `None`")  # noqa: E501

        self._choices = choices

    @property
    def created(self):
        """Gets the created of this CreateChatCompletionStreamResponse.  # noqa: E501

        The Unix timestamp (in seconds) of when the chat completion was created. Each chunk has the same timestamp.  # noqa: E501

        :return: The created of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: int
        """
        return self._created

    @created.setter
    def created(self, created):
        """Sets the created of this CreateChatCompletionStreamResponse.

        The Unix timestamp (in seconds) of when the chat completion was created. Each chunk has the same timestamp.  # noqa: E501

        :param created: The created of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: int
        """
        if created is None:
            raise ValueError("Invalid value for `created`, must not be `None`")  # noqa: E501

        self._created = created

    @property
    def model(self):
        """Gets the model of this CreateChatCompletionStreamResponse.  # noqa: E501

        The model to generate the completion.  # noqa: E501

        :return: The model of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: str
        """
        return self._model

    @model.setter
    def model(self, model):
        """Sets the model of this CreateChatCompletionStreamResponse.

        The model to generate the completion.  # noqa: E501

        :param model: The model of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: str
        """
        if model is None:
            raise ValueError("Invalid value for `model`, must not be `None`")  # noqa: E501

        self._model = model

    @property
    def system_fingerprint(self):
        """Gets the system_fingerprint of this CreateChatCompletionStreamResponse.  # noqa: E501

        This fingerprint represents the backend configuration that the model runs with. Can be used in conjunction with the `seed` request parameter to understand when backend changes have been made that might impact determinism.   # noqa: E501

        :return: The system_fingerprint of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: str
        """
        return self._system_fingerprint

    @system_fingerprint.setter
    def system_fingerprint(self, system_fingerprint):
        """Sets the system_fingerprint of this CreateChatCompletionStreamResponse.

        This fingerprint represents the backend configuration that the model runs with. Can be used in conjunction with the `seed` request parameter to understand when backend changes have been made that might impact determinism.   # noqa: E501

        :param system_fingerprint: The system_fingerprint of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: str
        """

        self._system_fingerprint = system_fingerprint

    @property
    def object(self):
        """Gets the object of this CreateChatCompletionStreamResponse.  # noqa: E501

        The object type, which is always `chat.completion.chunk`.  # noqa: E501

        :return: The object of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: str
        """
        return self._object

    @object.setter
    def object(self, object):
        """Sets the object of this CreateChatCompletionStreamResponse.

        The object type, which is always `chat.completion.chunk`.  # noqa: E501

        :param object: The object of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: str
        """
        if object is None:
            raise ValueError("Invalid value for `object`, must not be `None`")  # noqa: E501
        allowed_values = ["chat.completion.chunk"]  # noqa: E501
        if object not in allowed_values:
            raise ValueError(
                "Invalid value for `object` ({0}), must be one of {1}"  # noqa: E501
                .format(object, allowed_values)
            )

        self._object = object

    @property
    def usage(self):
        """Gets the usage of this CreateChatCompletionStreamResponse.  # noqa: E501


        :return: The usage of this CreateChatCompletionStreamResponse.  # noqa: E501
        :rtype: CreateChatCompletionStreamResponseUsage
        """
        return self._usage

    @usage.setter
    def usage(self, usage):
        """Sets the usage of this CreateChatCompletionStreamResponse.


        :param usage: The usage of this CreateChatCompletionStreamResponse.  # noqa: E501
        :type: CreateChatCompletionStreamResponseUsage
        """

        self._usage = usage

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
        if issubclass(CreateChatCompletionStreamResponse, dict):
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
        if not isinstance(other, CreateChatCompletionStreamResponse):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
