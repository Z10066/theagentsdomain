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

class CreateTranscriptionResponseVerboseJson(object):
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
        'language': 'str',
        'duration': 'str',
        'text': 'str',
        'words': 'list[TranscriptionWord]',
        'segments': 'list[TranscriptionSegment]'
    }

    attribute_map = {
        'language': 'language',
        'duration': 'duration',
        'text': 'text',
        'words': 'words',
        'segments': 'segments'
    }

    def __init__(self, language=None, duration=None, text=None, words=None, segments=None):  # noqa: E501
        """CreateTranscriptionResponseVerboseJson - a model defined in Swagger"""  # noqa: E501
        self._language = None
        self._duration = None
        self._text = None
        self._words = None
        self._segments = None
        self.discriminator = None
        self.language = language
        self.duration = duration
        self.text = text
        if words is not None:
            self.words = words
        if segments is not None:
            self.segments = segments

    @property
    def language(self):
        """Gets the language of this CreateTranscriptionResponseVerboseJson.  # noqa: E501

        The language of the input audio.  # noqa: E501

        :return: The language of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :rtype: str
        """
        return self._language

    @language.setter
    def language(self, language):
        """Sets the language of this CreateTranscriptionResponseVerboseJson.

        The language of the input audio.  # noqa: E501

        :param language: The language of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :type: str
        """
        if language is None:
            raise ValueError("Invalid value for `language`, must not be `None`")  # noqa: E501

        self._language = language

    @property
    def duration(self):
        """Gets the duration of this CreateTranscriptionResponseVerboseJson.  # noqa: E501

        The duration of the input audio.  # noqa: E501

        :return: The duration of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :rtype: str
        """
        return self._duration

    @duration.setter
    def duration(self, duration):
        """Sets the duration of this CreateTranscriptionResponseVerboseJson.

        The duration of the input audio.  # noqa: E501

        :param duration: The duration of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :type: str
        """
        if duration is None:
            raise ValueError("Invalid value for `duration`, must not be `None`")  # noqa: E501

        self._duration = duration

    @property
    def text(self):
        """Gets the text of this CreateTranscriptionResponseVerboseJson.  # noqa: E501

        The transcribed text.  # noqa: E501

        :return: The text of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :rtype: str
        """
        return self._text

    @text.setter
    def text(self, text):
        """Sets the text of this CreateTranscriptionResponseVerboseJson.

        The transcribed text.  # noqa: E501

        :param text: The text of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :type: str
        """
        if text is None:
            raise ValueError("Invalid value for `text`, must not be `None`")  # noqa: E501

        self._text = text

    @property
    def words(self):
        """Gets the words of this CreateTranscriptionResponseVerboseJson.  # noqa: E501

        Extracted words and their corresponding timestamps.  # noqa: E501

        :return: The words of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :rtype: list[TranscriptionWord]
        """
        return self._words

    @words.setter
    def words(self, words):
        """Sets the words of this CreateTranscriptionResponseVerboseJson.

        Extracted words and their corresponding timestamps.  # noqa: E501

        :param words: The words of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :type: list[TranscriptionWord]
        """

        self._words = words

    @property
    def segments(self):
        """Gets the segments of this CreateTranscriptionResponseVerboseJson.  # noqa: E501

        Segments of the transcribed text and their corresponding details.  # noqa: E501

        :return: The segments of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :rtype: list[TranscriptionSegment]
        """
        return self._segments

    @segments.setter
    def segments(self, segments):
        """Sets the segments of this CreateTranscriptionResponseVerboseJson.

        Segments of the transcribed text and their corresponding details.  # noqa: E501

        :param segments: The segments of this CreateTranscriptionResponseVerboseJson.  # noqa: E501
        :type: list[TranscriptionSegment]
        """

        self._segments = segments

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
        if issubclass(CreateTranscriptionResponseVerboseJson, dict):
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
        if not isinstance(other, CreateTranscriptionResponseVerboseJson):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other