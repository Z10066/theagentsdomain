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
from swagger_client.api.images_api import ImagesApi  # noqa: E501
from swagger_client.rest import ApiException


class TestImagesApi(unittest.TestCase):
    """ImagesApi unit test stubs"""

    def setUp(self):
        self.api = ImagesApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_create_image(self):
        """Test case for create_image

        Creates an image given a prompt.  # noqa: E501
        """
        pass

    def test_create_image_edit(self):
        """Test case for create_image_edit

        Creates an edited or extended image given an original image and a prompt.  # noqa: E501
        """
        pass

    def test_create_image_variation(self):
        """Test case for create_image_variation

        Creates a variation of a given image.  # noqa: E501
        """
        pass


if __name__ == '__main__':
    unittest.main()
