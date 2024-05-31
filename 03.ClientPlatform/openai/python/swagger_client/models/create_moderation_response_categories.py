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

class CreateModerationResponseCategories(object):
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
        'hate': 'bool',
        'hatethreatening': 'bool',
        'harassment': 'bool',
        'harassmentthreatening': 'bool',
        'self_harm': 'bool',
        'self_harmintent': 'bool',
        'self_harminstructions': 'bool',
        'sexual': 'bool',
        'sexualminors': 'bool',
        'violence': 'bool',
        'violencegraphic': 'bool'
    }

    attribute_map = {
        'hate': 'hate',
        'hatethreatening': 'hate/threatening',
        'harassment': 'harassment',
        'harassmentthreatening': 'harassment/threatening',
        'self_harm': 'self-harm',
        'self_harmintent': 'self-harm/intent',
        'self_harminstructions': 'self-harm/instructions',
        'sexual': 'sexual',
        'sexualminors': 'sexual/minors',
        'violence': 'violence',
        'violencegraphic': 'violence/graphic'
    }

    def __init__(self, hate=None, hatethreatening=None, harassment=None, harassmentthreatening=None, self_harm=None, self_harmintent=None, self_harminstructions=None, sexual=None, sexualminors=None, violence=None, violencegraphic=None):  # noqa: E501
        """CreateModerationResponseCategories - a model defined in Swagger"""  # noqa: E501
        self._hate = None
        self._hatethreatening = None
        self._harassment = None
        self._harassmentthreatening = None
        self._self_harm = None
        self._self_harmintent = None
        self._self_harminstructions = None
        self._sexual = None
        self._sexualminors = None
        self._violence = None
        self._violencegraphic = None
        self.discriminator = None
        self.hate = hate
        self.hatethreatening = hatethreatening
        self.harassment = harassment
        self.harassmentthreatening = harassmentthreatening
        self.self_harm = self_harm
        self.self_harmintent = self_harmintent
        self.self_harminstructions = self_harminstructions
        self.sexual = sexual
        self.sexualminors = sexualminors
        self.violence = violence
        self.violencegraphic = violencegraphic

    @property
    def hate(self):
        """Gets the hate of this CreateModerationResponseCategories.  # noqa: E501

        Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.  # noqa: E501

        :return: The hate of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._hate

    @hate.setter
    def hate(self, hate):
        """Sets the hate of this CreateModerationResponseCategories.

        Content that expresses, incites, or promotes hate based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste. Hateful content aimed at non-protected groups (e.g., chess players) is harassment.  # noqa: E501

        :param hate: The hate of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if hate is None:
            raise ValueError("Invalid value for `hate`, must not be `None`")  # noqa: E501

        self._hate = hate

    @property
    def hatethreatening(self):
        """Gets the hatethreatening of this CreateModerationResponseCategories.  # noqa: E501

        Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.  # noqa: E501

        :return: The hatethreatening of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._hatethreatening

    @hatethreatening.setter
    def hatethreatening(self, hatethreatening):
        """Sets the hatethreatening of this CreateModerationResponseCategories.

        Hateful content that also includes violence or serious harm towards the targeted group based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.  # noqa: E501

        :param hatethreatening: The hatethreatening of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if hatethreatening is None:
            raise ValueError("Invalid value for `hatethreatening`, must not be `None`")  # noqa: E501

        self._hatethreatening = hatethreatening

    @property
    def harassment(self):
        """Gets the harassment of this CreateModerationResponseCategories.  # noqa: E501

        Content that expresses, incites, or promotes harassing language towards any target.  # noqa: E501

        :return: The harassment of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._harassment

    @harassment.setter
    def harassment(self, harassment):
        """Sets the harassment of this CreateModerationResponseCategories.

        Content that expresses, incites, or promotes harassing language towards any target.  # noqa: E501

        :param harassment: The harassment of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if harassment is None:
            raise ValueError("Invalid value for `harassment`, must not be `None`")  # noqa: E501

        self._harassment = harassment

    @property
    def harassmentthreatening(self):
        """Gets the harassmentthreatening of this CreateModerationResponseCategories.  # noqa: E501

        Harassment content that also includes violence or serious harm towards any target.  # noqa: E501

        :return: The harassmentthreatening of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._harassmentthreatening

    @harassmentthreatening.setter
    def harassmentthreatening(self, harassmentthreatening):
        """Sets the harassmentthreatening of this CreateModerationResponseCategories.

        Harassment content that also includes violence or serious harm towards any target.  # noqa: E501

        :param harassmentthreatening: The harassmentthreatening of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if harassmentthreatening is None:
            raise ValueError("Invalid value for `harassmentthreatening`, must not be `None`")  # noqa: E501

        self._harassmentthreatening = harassmentthreatening

    @property
    def self_harm(self):
        """Gets the self_harm of this CreateModerationResponseCategories.  # noqa: E501

        Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.  # noqa: E501

        :return: The self_harm of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._self_harm

    @self_harm.setter
    def self_harm(self, self_harm):
        """Sets the self_harm of this CreateModerationResponseCategories.

        Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.  # noqa: E501

        :param self_harm: The self_harm of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if self_harm is None:
            raise ValueError("Invalid value for `self_harm`, must not be `None`")  # noqa: E501

        self._self_harm = self_harm

    @property
    def self_harmintent(self):
        """Gets the self_harmintent of this CreateModerationResponseCategories.  # noqa: E501

        Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.  # noqa: E501

        :return: The self_harmintent of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._self_harmintent

    @self_harmintent.setter
    def self_harmintent(self, self_harmintent):
        """Sets the self_harmintent of this CreateModerationResponseCategories.

        Content where the speaker expresses that they are engaging or intend to engage in acts of self-harm, such as suicide, cutting, and eating disorders.  # noqa: E501

        :param self_harmintent: The self_harmintent of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if self_harmintent is None:
            raise ValueError("Invalid value for `self_harmintent`, must not be `None`")  # noqa: E501

        self._self_harmintent = self_harmintent

    @property
    def self_harminstructions(self):
        """Gets the self_harminstructions of this CreateModerationResponseCategories.  # noqa: E501

        Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.  # noqa: E501

        :return: The self_harminstructions of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._self_harminstructions

    @self_harminstructions.setter
    def self_harminstructions(self, self_harminstructions):
        """Sets the self_harminstructions of this CreateModerationResponseCategories.

        Content that encourages performing acts of self-harm, such as suicide, cutting, and eating disorders, or that gives instructions or advice on how to commit such acts.  # noqa: E501

        :param self_harminstructions: The self_harminstructions of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if self_harminstructions is None:
            raise ValueError("Invalid value for `self_harminstructions`, must not be `None`")  # noqa: E501

        self._self_harminstructions = self_harminstructions

    @property
    def sexual(self):
        """Gets the sexual of this CreateModerationResponseCategories.  # noqa: E501

        Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).  # noqa: E501

        :return: The sexual of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._sexual

    @sexual.setter
    def sexual(self, sexual):
        """Sets the sexual of this CreateModerationResponseCategories.

        Content meant to arouse sexual excitement, such as the description of sexual activity, or that promotes sexual services (excluding sex education and wellness).  # noqa: E501

        :param sexual: The sexual of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if sexual is None:
            raise ValueError("Invalid value for `sexual`, must not be `None`")  # noqa: E501

        self._sexual = sexual

    @property
    def sexualminors(self):
        """Gets the sexualminors of this CreateModerationResponseCategories.  # noqa: E501

        Sexual content that includes an individual who is under 18 years old.  # noqa: E501

        :return: The sexualminors of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._sexualminors

    @sexualminors.setter
    def sexualminors(self, sexualminors):
        """Sets the sexualminors of this CreateModerationResponseCategories.

        Sexual content that includes an individual who is under 18 years old.  # noqa: E501

        :param sexualminors: The sexualminors of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if sexualminors is None:
            raise ValueError("Invalid value for `sexualminors`, must not be `None`")  # noqa: E501

        self._sexualminors = sexualminors

    @property
    def violence(self):
        """Gets the violence of this CreateModerationResponseCategories.  # noqa: E501

        Content that depicts death, violence, or physical injury.  # noqa: E501

        :return: The violence of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._violence

    @violence.setter
    def violence(self, violence):
        """Sets the violence of this CreateModerationResponseCategories.

        Content that depicts death, violence, or physical injury.  # noqa: E501

        :param violence: The violence of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if violence is None:
            raise ValueError("Invalid value for `violence`, must not be `None`")  # noqa: E501

        self._violence = violence

    @property
    def violencegraphic(self):
        """Gets the violencegraphic of this CreateModerationResponseCategories.  # noqa: E501

        Content that depicts death, violence, or physical injury in graphic detail.  # noqa: E501

        :return: The violencegraphic of this CreateModerationResponseCategories.  # noqa: E501
        :rtype: bool
        """
        return self._violencegraphic

    @violencegraphic.setter
    def violencegraphic(self, violencegraphic):
        """Sets the violencegraphic of this CreateModerationResponseCategories.

        Content that depicts death, violence, or physical injury in graphic detail.  # noqa: E501

        :param violencegraphic: The violencegraphic of this CreateModerationResponseCategories.  # noqa: E501
        :type: bool
        """
        if violencegraphic is None:
            raise ValueError("Invalid value for `violencegraphic`, must not be `None`")  # noqa: E501

        self._violencegraphic = violencegraphic

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
        if issubclass(CreateModerationResponseCategories, dict):
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
        if not isinstance(other, CreateModerationResponseCategories):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """Returns true if both objects are not equal"""
        return not self == other