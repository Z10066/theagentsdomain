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
import { ChatCompletionMessageToolCalls } from './chatCompletionMessageToolCalls';
import { ChatCompletionResponseMessageFunctionCall } from './chatCompletionResponseMessageFunctionCall';

/**
 * A chat completion message generated by the model.
 */
export interface ChatCompletionResponseMessage { 
    /**
     * The contents of the message.
     */
    content: string;
    toolCalls?: ChatCompletionMessageToolCalls;
    /**
     * The role of the author of this message.
     */
    role: ChatCompletionResponseMessage.RoleEnum;
    functionCall?: ChatCompletionResponseMessageFunctionCall;
}
export namespace ChatCompletionResponseMessage {
    export type RoleEnum = 'assistant';
    export const RoleEnum = {
        Assistant: 'assistant' as RoleEnum
    };
}