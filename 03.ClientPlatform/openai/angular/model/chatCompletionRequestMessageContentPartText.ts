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

export interface ChatCompletionRequestMessageContentPartText { 
    /**
     * The type of the content part.
     */
    type: ChatCompletionRequestMessageContentPartText.TypeEnum;
    /**
     * The text content.
     */
    text: string;
}
export namespace ChatCompletionRequestMessageContentPartText {
    export type TypeEnum = 'text';
    export const TypeEnum = {
        Text: 'text' as TypeEnum
    };
}