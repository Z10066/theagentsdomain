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
 * Details on why the run is incomplete. Will be `null` if the run is not incomplete.
 */
export interface RunObjectIncompleteDetails { 
    /**
     * The reason why the run is incomplete. This will point to which specific token limit was reached over the course of the run.
     */
    reason?: RunObjectIncompleteDetails.ReasonEnum;
}
export namespace RunObjectIncompleteDetails {
    export type ReasonEnum = 'max_completion_tokens' | 'max_prompt_tokens';
    export const ReasonEnum = {
        CompletionTokens: 'max_completion_tokens' as ReasonEnum,
        PromptTokens: 'max_prompt_tokens' as ReasonEnum
    };
}