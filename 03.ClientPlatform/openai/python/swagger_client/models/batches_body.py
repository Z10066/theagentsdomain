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

class BatchesBody(object):
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
        'input_file_id': 'str',
        'endpoint': 'str',
        'completion_window': 'str',
        'metadata': 'dict(str, str)'
    }

    attribute_map = {
        'input_file_id': 'input_file_id',
        'endpoint': 'endpoint',
        'completion_window': 'completion_window',
        'metadata': 'metadata'
    }

    def __init__(self, input_file_id=None, endpoint=None, completion_window=None, metadata=None):  # noqa: E501
        """BatchesBody - a model defined in Swagger"""  # noqa: E501
        self._input_file_id = None
        self._endpoint = None
        self._completion_window = None
        self._metadata = None
        self.discriminator = None
        self.input_file_id = input_file_id
        self.endpoint = endpoint
        self.completion_window = completion_window
        if metadata is not None:
            self.metadata = metadata

    @property
    def input_file_id(self):
        """Gets the input_file_id of this BatchesBody.  # noqa: E501

        The ID of an uploaded file that contains requests for the new batch.  See [upload file](/docs/api-reference/files/create) for how to upload a file.  Your input file must be formatted as a [JSONL file](/docs/api-reference/batch/requestInput), and must be uploaded with the purpose `batch`. The file can contain up to 50,000 requests, and can be up to 100 MB in size.   # noqa: E501

        :return: The input_file_id of this BatchesBody.  # noqa: E501
        :rtype: str
        """
        return self._input_file_id

    @input_file_id.setter
    def input_file_id(self, input_file_id):
        """Sets the input_file_id of this BatchesBody.

        The ID of an uploaded file that contains requests for the new batch.  See [upload file](/docs/api-reference/files/create) for how to upload a file.  Your input file must be formatted as a [JSONL file](/docs/api-reference/batch/requestInput), and must be uploaded with the purpose `batch`. The file can contain up to 50,000 requests, and can be up to 100 MB in size.   # noqa: E501

        :param input_file_id: The input_file_id of this BatchesBody.  # noqa: E501
        :type: str
        """
        if input_file_id is None:
            raise ValueError("Invalid value for `input_file_id`, must not be `None`")  # noqa: E501

        self._input_file_id = input_file_id

    @property
    def endpoint(self):
        """Gets the endpoint of this BatchesBody.  # noqa: E501

        The endpoint to be used for all requests in the batch. Currently `/v1/chat/completions`, `/v1/embeddings`, and `/v1/completions` are supported. Note that `/v1/embeddings` batches are also restricted to a maximum of 50,000 embedding inputs across all requests in the batch.  # noqa: E501

        :return: The endpoint of this BatchesBody.  # noqa: E501
        :rtype: str
        """
        return self._endpoint

    @endpoint.setter
    def endpoint(self, endpoint):
        """Sets the endpoint of this BatchesBody.

        The endpoint to be used for all requests in the batch. Currently `/v1/chat/completions`, `/v1/embeddings`, and `/v1/completions` are supported. Note that `/v1/embeddings` batches are also restricted to a maximum of 50,000 embedding inputs across all requests in the batch.  # noqa: E501

        :param endpoint: The endpoint of this BatchesBody.  # noqa: E501
        :type: str
        """
        if endpoint is None:
            raise ValueError("Invalid value for `endpoint`, must not be `None`")  # noqa: E501
        allowed_values = ["/v1/chat/completions", "/v1/embeddings", "/v1/completions"]  # noqa: E501
        if endpoint not in allowed_values:
            raise ValueError(
                "Invalid value for `endpoint` ({0}), must be one of {1}"  # noqa: E501
                .format(endpoint, allowed_values)
            )

        self._endpoint = endpoint

    @property
    def completion_window(self):
        """Gets the completion_window of this BatchesBody.  # noqa: E501

        The time frame within which the batch should be processed. Currently only `24h` is supported.  # noqa: E501

        :return: The completion_window of this BatchesBody.  # noqa: E501
        :rtype: str
        """
        return self._completion_window

    @completion_window.setter
    def completion_window(self, completion_window):
        """Sets the completion_window of this BatchesBody.

        The time frame within which the batch should be processed. Currently only `24h` is supported.  # noqa: E501

        :param completion_window: The completion_window of this BatchesBody.  # noqa: E501
        :type: str
        """
        if completion_window is None:
            raise ValueError("Invalid value for `completion_window`, must not be `None`")  # noqa: E501
        allowed_values = ["24h"]  # noqa: E501
        if completion_window not in allowed_values:
            raise ValueError(
                "Invalid value for `completion_window` ({0}), must be one of {1}"  # noqa: E501
                .format(completion_window, allowed_values)
            )

        self._completion_window = completion_window

    @property
    def metadata(self):
        """Gets the metadata of this BatchesBody.  # noqa: E501

        Optional custom metadata for the batch.  # noqa: E501

        :return: The metadata of this BatchesBody.  # noqa: E501
        :rtype: dict(str, str)
        """
        return self._metadata

    @metadata.setter
    def metadata(self, metadata):
        """Sets the metadata of this BatchesBody.

        Optional custom metadata for the batch.  # noqa: E501

        :param metadata: The metadata of this BatchesBody.  # noqa: E501
        :type: dict(str, str)
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
        if issubclass(BatchesBody, dict):
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
        if not isinstance(other, BatchesBody):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
