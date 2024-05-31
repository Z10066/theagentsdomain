/**
 * OpenAI API
 * The OpenAI REST API. Please see https://platform.openai.com/docs/api-reference for more details.
 *
 * OpenAPI spec version: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { MessageContentTextAnnotationsFileCitationObject } from './messageContentTextAnnotationsFileCitationObject';
import { MessageContentTextAnnotationsFilePathObject } from './messageContentTextAnnotationsFilePathObject';

export interface MessageContentTextObjectText { 
    /**
     * The data that makes up the text.
     */
    value: string;
    annotations: Array<MessageContentTextAnnotationsFileCitationObject | MessageContentTextAnnotationsFilePathObject>;
}