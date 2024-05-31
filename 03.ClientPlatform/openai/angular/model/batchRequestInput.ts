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

/**
 * The per-line object of the batch input file
 */
export interface BatchRequestInput { 
    /**
     * A developer-provided per-request id that will be used to match outputs to inputs. Must be unique for each request in a batch.
     */
    customId?: string;
    /**
     * The HTTP method to be used for the request. Currently only `POST` is supported.
     */
    method?: BatchRequestInput.MethodEnum;
    /**
     * The OpenAI API relative URL to be used for the request. Currently `/v1/chat/completions`, `/v1/embeddings`, and `/v1/completions` are supported.
     */
    url?: string;
}
export namespace BatchRequestInput {
    export type MethodEnum = 'POST';
    export const MethodEnum = {
        POST: 'POST' as MethodEnum
    };
}