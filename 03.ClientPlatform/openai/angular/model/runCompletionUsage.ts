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
 * Usage statistics related to the run. This value will be `null` if the run is not in a terminal state (i.e. `in_progress`, `queued`, etc.).
 */
export interface RunCompletionUsage { 
    /**
     * Number of completion tokens used over the course of the run.
     */
    completionTokens: number;
    /**
     * Number of prompt tokens used over the course of the run.
     */
    promptTokens: number;
    /**
     * Total number of tokens used (prompt + completion).
     */
    totalTokens: number;
}