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
import { MessageDeltaContentImageFileObject } from './messageDeltaContentImageFileObject';
import { MessageDeltaContentImageUrlObject } from './messageDeltaContentImageUrlObject';
import { MessageDeltaContentTextObject } from './messageDeltaContentTextObject';

/**
 * The delta containing the fields that have changed on the Message.
 */
export interface MessageDeltaObjectDelta { 
    /**
     * The entity that produced the message. One of `user` or `assistant`.
     */
    role?: MessageDeltaObjectDelta.RoleEnum;
    /**
     * The content of the message in array of text and/or images.
     */
    content?: Array<MessageDeltaContentImageFileObject | MessageDeltaContentTextObject | MessageDeltaContentImageUrlObject>;
}
export namespace MessageDeltaObjectDelta {
    export type RoleEnum = 'user' | 'assistant';
    export const RoleEnum = {
        User: 'user' as RoleEnum,
        Assistant: 'assistant' as RoleEnum
    };
}