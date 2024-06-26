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

class Batch(object):
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
        'object': 'str',
        'endpoint': 'str',
        'errors': 'BatchErrors',
        'input_file_id': 'str',
        'completion_window': 'str',
        'status': 'str',
        'output_file_id': 'str',
        'error_file_id': 'str',
        'created_at': 'int',
        'in_progress_at': 'int',
        'expires_at': 'int',
        'finalizing_at': 'int',
        'completed_at': 'int',
        'failed_at': 'int',
        'expired_at': 'int',
        'cancelling_at': 'int',
        'cancelled_at': 'int',
        'request_counts': 'BatchRequestCounts',
        'metadata': 'object'
    }

    attribute_map = {
        'id': 'id',
        'object': 'object',
        'endpoint': 'endpoint',
        'errors': 'errors',
        'input_file_id': 'input_file_id',
        'completion_window': 'completion_window',
        'status': 'status',
        'output_file_id': 'output_file_id',
        'error_file_id': 'error_file_id',
        'created_at': 'created_at',
        'in_progress_at': 'in_progress_at',
        'expires_at': 'expires_at',
        'finalizing_at': 'finalizing_at',
        'completed_at': 'completed_at',
        'failed_at': 'failed_at',
        'expired_at': 'expired_at',
        'cancelling_at': 'cancelling_at',
        'cancelled_at': 'cancelled_at',
        'request_counts': 'request_counts',
        'metadata': 'metadata'
    }

    def __init__(self, id=None, object=None, endpoint=None, errors=None, input_file_id=None, completion_window=None, status=None, output_file_id=None, error_file_id=None, created_at=None, in_progress_at=None, expires_at=None, finalizing_at=None, completed_at=None, failed_at=None, expired_at=None, cancelling_at=None, cancelled_at=None, request_counts=None, metadata=None):  # noqa: E501
        """Batch - a model defined in Swagger"""  # noqa: E501
        self._id = None
        self._object = None
        self._endpoint = None
        self._errors = None
        self._input_file_id = None
        self._completion_window = None
        self._status = None
        self._output_file_id = None
        self._error_file_id = None
        self._created_at = None
        self._in_progress_at = None
        self._expires_at = None
        self._finalizing_at = None
        self._completed_at = None
        self._failed_at = None
        self._expired_at = None
        self._cancelling_at = None
        self._cancelled_at = None
        self._request_counts = None
        self._metadata = None
        self.discriminator = None
        self.id = id
        self.object = object
        self.endpoint = endpoint
        if errors is not None:
            self.errors = errors
        self.input_file_id = input_file_id
        self.completion_window = completion_window
        self.status = status
        if output_file_id is not None:
            self.output_file_id = output_file_id
        if error_file_id is not None:
            self.error_file_id = error_file_id
        self.created_at = created_at
        if in_progress_at is not None:
            self.in_progress_at = in_progress_at
        if expires_at is not None:
            self.expires_at = expires_at
        if finalizing_at is not None:
            self.finalizing_at = finalizing_at
        if completed_at is not None:
            self.completed_at = completed_at
        if failed_at is not None:
            self.failed_at = failed_at
        if expired_at is not None:
            self.expired_at = expired_at
        if cancelling_at is not None:
            self.cancelling_at = cancelling_at
        if cancelled_at is not None:
            self.cancelled_at = cancelled_at
        if request_counts is not None:
            self.request_counts = request_counts
        if metadata is not None:
            self.metadata = metadata

    @property
    def id(self):
        """Gets the id of this Batch.  # noqa: E501


        :return: The id of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id):
        """Sets the id of this Batch.


        :param id: The id of this Batch.  # noqa: E501
        :type: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def object(self):
        """Gets the object of this Batch.  # noqa: E501

        The object type, which is always `batch`.  # noqa: E501

        :return: The object of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._object

    @object.setter
    def object(self, object):
        """Sets the object of this Batch.

        The object type, which is always `batch`.  # noqa: E501

        :param object: The object of this Batch.  # noqa: E501
        :type: str
        """
        if object is None:
            raise ValueError("Invalid value for `object`, must not be `None`")  # noqa: E501
        allowed_values = ["batch"]  # noqa: E501
        if object not in allowed_values:
            raise ValueError(
                "Invalid value for `object` ({0}), must be one of {1}"  # noqa: E501
                .format(object, allowed_values)
            )

        self._object = object

    @property
    def endpoint(self):
        """Gets the endpoint of this Batch.  # noqa: E501

        The OpenAI API endpoint used by the batch.  # noqa: E501

        :return: The endpoint of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._endpoint

    @endpoint.setter
    def endpoint(self, endpoint):
        """Sets the endpoint of this Batch.

        The OpenAI API endpoint used by the batch.  # noqa: E501

        :param endpoint: The endpoint of this Batch.  # noqa: E501
        :type: str
        """
        if endpoint is None:
            raise ValueError("Invalid value for `endpoint`, must not be `None`")  # noqa: E501

        self._endpoint = endpoint

    @property
    def errors(self):
        """Gets the errors of this Batch.  # noqa: E501


        :return: The errors of this Batch.  # noqa: E501
        :rtype: BatchErrors
        """
        return self._errors

    @errors.setter
    def errors(self, errors):
        """Sets the errors of this Batch.


        :param errors: The errors of this Batch.  # noqa: E501
        :type: BatchErrors
        """

        self._errors = errors

    @property
    def input_file_id(self):
        """Gets the input_file_id of this Batch.  # noqa: E501

        The ID of the input file for the batch.  # noqa: E501

        :return: The input_file_id of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._input_file_id

    @input_file_id.setter
    def input_file_id(self, input_file_id):
        """Sets the input_file_id of this Batch.

        The ID of the input file for the batch.  # noqa: E501

        :param input_file_id: The input_file_id of this Batch.  # noqa: E501
        :type: str
        """
        if input_file_id is None:
            raise ValueError("Invalid value for `input_file_id`, must not be `None`")  # noqa: E501

        self._input_file_id = input_file_id

    @property
    def completion_window(self):
        """Gets the completion_window of this Batch.  # noqa: E501

        The time frame within which the batch should be processed.  # noqa: E501

        :return: The completion_window of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._completion_window

    @completion_window.setter
    def completion_window(self, completion_window):
        """Sets the completion_window of this Batch.

        The time frame within which the batch should be processed.  # noqa: E501

        :param completion_window: The completion_window of this Batch.  # noqa: E501
        :type: str
        """
        if completion_window is None:
            raise ValueError("Invalid value for `completion_window`, must not be `None`")  # noqa: E501

        self._completion_window = completion_window

    @property
    def status(self):
        """Gets the status of this Batch.  # noqa: E501

        The current status of the batch.  # noqa: E501

        :return: The status of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._status

    @status.setter
    def status(self, status):
        """Sets the status of this Batch.

        The current status of the batch.  # noqa: E501

        :param status: The status of this Batch.  # noqa: E501
        :type: str
        """
        if status is None:
            raise ValueError("Invalid value for `status`, must not be `None`")  # noqa: E501
        allowed_values = ["validating", "failed", "in_progress", "finalizing", "completed", "expired", "cancelling", "cancelled"]  # noqa: E501
        if status not in allowed_values:
            raise ValueError(
                "Invalid value for `status` ({0}), must be one of {1}"  # noqa: E501
                .format(status, allowed_values)
            )

        self._status = status

    @property
    def output_file_id(self):
        """Gets the output_file_id of this Batch.  # noqa: E501

        The ID of the file containing the outputs of successfully executed requests.  # noqa: E501

        :return: The output_file_id of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._output_file_id

    @output_file_id.setter
    def output_file_id(self, output_file_id):
        """Sets the output_file_id of this Batch.

        The ID of the file containing the outputs of successfully executed requests.  # noqa: E501

        :param output_file_id: The output_file_id of this Batch.  # noqa: E501
        :type: str
        """

        self._output_file_id = output_file_id

    @property
    def error_file_id(self):
        """Gets the error_file_id of this Batch.  # noqa: E501

        The ID of the file containing the outputs of requests with errors.  # noqa: E501

        :return: The error_file_id of this Batch.  # noqa: E501
        :rtype: str
        """
        return self._error_file_id

    @error_file_id.setter
    def error_file_id(self, error_file_id):
        """Sets the error_file_id of this Batch.

        The ID of the file containing the outputs of requests with errors.  # noqa: E501

        :param error_file_id: The error_file_id of this Batch.  # noqa: E501
        :type: str
        """

        self._error_file_id = error_file_id

    @property
    def created_at(self):
        """Gets the created_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch was created.  # noqa: E501

        :return: The created_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._created_at

    @created_at.setter
    def created_at(self, created_at):
        """Sets the created_at of this Batch.

        The Unix timestamp (in seconds) for when the batch was created.  # noqa: E501

        :param created_at: The created_at of this Batch.  # noqa: E501
        :type: int
        """
        if created_at is None:
            raise ValueError("Invalid value for `created_at`, must not be `None`")  # noqa: E501

        self._created_at = created_at

    @property
    def in_progress_at(self):
        """Gets the in_progress_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch started processing.  # noqa: E501

        :return: The in_progress_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._in_progress_at

    @in_progress_at.setter
    def in_progress_at(self, in_progress_at):
        """Sets the in_progress_at of this Batch.

        The Unix timestamp (in seconds) for when the batch started processing.  # noqa: E501

        :param in_progress_at: The in_progress_at of this Batch.  # noqa: E501
        :type: int
        """

        self._in_progress_at = in_progress_at

    @property
    def expires_at(self):
        """Gets the expires_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch will expire.  # noqa: E501

        :return: The expires_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._expires_at

    @expires_at.setter
    def expires_at(self, expires_at):
        """Sets the expires_at of this Batch.

        The Unix timestamp (in seconds) for when the batch will expire.  # noqa: E501

        :param expires_at: The expires_at of this Batch.  # noqa: E501
        :type: int
        """

        self._expires_at = expires_at

    @property
    def finalizing_at(self):
        """Gets the finalizing_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch started finalizing.  # noqa: E501

        :return: The finalizing_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._finalizing_at

    @finalizing_at.setter
    def finalizing_at(self, finalizing_at):
        """Sets the finalizing_at of this Batch.

        The Unix timestamp (in seconds) for when the batch started finalizing.  # noqa: E501

        :param finalizing_at: The finalizing_at of this Batch.  # noqa: E501
        :type: int
        """

        self._finalizing_at = finalizing_at

    @property
    def completed_at(self):
        """Gets the completed_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch was completed.  # noqa: E501

        :return: The completed_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._completed_at

    @completed_at.setter
    def completed_at(self, completed_at):
        """Sets the completed_at of this Batch.

        The Unix timestamp (in seconds) for when the batch was completed.  # noqa: E501

        :param completed_at: The completed_at of this Batch.  # noqa: E501
        :type: int
        """

        self._completed_at = completed_at

    @property
    def failed_at(self):
        """Gets the failed_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch failed.  # noqa: E501

        :return: The failed_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._failed_at

    @failed_at.setter
    def failed_at(self, failed_at):
        """Sets the failed_at of this Batch.

        The Unix timestamp (in seconds) for when the batch failed.  # noqa: E501

        :param failed_at: The failed_at of this Batch.  # noqa: E501
        :type: int
        """

        self._failed_at = failed_at

    @property
    def expired_at(self):
        """Gets the expired_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch expired.  # noqa: E501

        :return: The expired_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._expired_at

    @expired_at.setter
    def expired_at(self, expired_at):
        """Sets the expired_at of this Batch.

        The Unix timestamp (in seconds) for when the batch expired.  # noqa: E501

        :param expired_at: The expired_at of this Batch.  # noqa: E501
        :type: int
        """

        self._expired_at = expired_at

    @property
    def cancelling_at(self):
        """Gets the cancelling_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch started cancelling.  # noqa: E501

        :return: The cancelling_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._cancelling_at

    @cancelling_at.setter
    def cancelling_at(self, cancelling_at):
        """Sets the cancelling_at of this Batch.

        The Unix timestamp (in seconds) for when the batch started cancelling.  # noqa: E501

        :param cancelling_at: The cancelling_at of this Batch.  # noqa: E501
        :type: int
        """

        self._cancelling_at = cancelling_at

    @property
    def cancelled_at(self):
        """Gets the cancelled_at of this Batch.  # noqa: E501

        The Unix timestamp (in seconds) for when the batch was cancelled.  # noqa: E501

        :return: The cancelled_at of this Batch.  # noqa: E501
        :rtype: int
        """
        return self._cancelled_at

    @cancelled_at.setter
    def cancelled_at(self, cancelled_at):
        """Sets the cancelled_at of this Batch.

        The Unix timestamp (in seconds) for when the batch was cancelled.  # noqa: E501

        :param cancelled_at: The cancelled_at of this Batch.  # noqa: E501
        :type: int
        """

        self._cancelled_at = cancelled_at

    @property
    def request_counts(self):
        """Gets the request_counts of this Batch.  # noqa: E501


        :return: The request_counts of this Batch.  # noqa: E501
        :rtype: BatchRequestCounts
        """
        return self._request_counts

    @request_counts.setter
    def request_counts(self, request_counts):
        """Sets the request_counts of this Batch.


        :param request_counts: The request_counts of this Batch.  # noqa: E501
        :type: BatchRequestCounts
        """

        self._request_counts = request_counts

    @property
    def metadata(self):
        """Gets the metadata of this Batch.  # noqa: E501

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :return: The metadata of this Batch.  # noqa: E501
        :rtype: object
        """
        return self._metadata

    @metadata.setter
    def metadata(self, metadata):
        """Sets the metadata of this Batch.

        Set of 16 key-value pairs that can be attached to an object. This can be useful for storing additional information about the object in a structured format. Keys can be a maximum of 64 characters long and values can be a maxium of 512 characters long.   # noqa: E501

        :param metadata: The metadata of this Batch.  # noqa: E501
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
        if issubclass(Batch, dict):
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
        if not isinstance(other, Batch):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other
