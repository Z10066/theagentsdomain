# coding: utf-8

"""
    OpenAI API

    The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.  # noqa: E501

    OpenAPI spec version: 2.0.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

from __future__ import absolute_import

import unittest

import swagger_client
from swagger_client.api.chat_api import ChatApi  # noqa: E501
from swagger_client.rest import ApiException


class TestChatApi(unittest.TestCase):
    """ChatApi unit test stubs"""

    def setUp(self):
        self.api = ChatApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_create_chat_completion(self):
        """Test case for create_chat_completion

        Creates a model response for the given chat conversation.  # noqa: E501
        """
        pass


if __name__ == '__main__':
    unittest.main()
