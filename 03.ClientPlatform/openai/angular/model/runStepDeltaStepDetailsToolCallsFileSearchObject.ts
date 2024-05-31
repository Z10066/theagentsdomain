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

export interface RunStepDeltaStepDetailsToolCallsFileSearchObject { 
    /**
     * The index of the tool call in the tool calls array.
     */
    index: number;
    /**
     * The ID of the tool call object.
     */
    id?: string;
    /**
     * The type of tool call. This is always going to be `file_search` for this type of tool call.
     */
    type: RunStepDeltaStepDetailsToolCallsFileSearchObject.TypeEnum;
    /**
     * For now, this is always going to be an empty object.
     */
    fileSearch: any;
}
export namespace RunStepDeltaStepDetailsToolCallsFileSearchObject {
    export type TypeEnum = 'file_search';
    export const TypeEnum = {
        FileSearch: 'file_search' as TypeEnum
    };
}