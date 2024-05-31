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
import { RunToolCallObjectFunction } from './runToolCallObjectFunction';

/**
 * Tool call objects
 */
export interface RunToolCallObject { 
    /**
     * The ID of the tool call. This ID must be referenced when you submit the tool outputs in using the [Submit tool outputs to run](/docs/api-reference/runs/submitToolOutputs) endpoint.
     */
    id: string;
    /**
     * The type of tool call the output is required for. For now, this is always `function`.
     */
    type: RunToolCallObject.TypeEnum;
    _function: RunToolCallObjectFunction;
}
export namespace RunToolCallObject {
    export type TypeEnum = 'function';
    export const TypeEnum = {
        Function: 'function' as TypeEnum
    };
}