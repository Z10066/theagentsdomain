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
 * The last error associated with this run. Will be `null` if there are no errors.
 */
export interface RunObjectLastError { 
    /**
     * One of `server_error`, `rate_limit_exceeded`, or `invalid_prompt`.
     */
    code: RunObjectLastError.CodeEnum;
    /**
     * A human-readable description of the error.
     */
    message: string;
}
export namespace RunObjectLastError {
    export type CodeEnum = 'server_error' | 'rate_limit_exceeded' | 'invalid_prompt';
    export const CodeEnum = {
        ServerError: 'server_error' as CodeEnum,
        RateLimitExceeded: 'rate_limit_exceeded' as CodeEnum,
        InvalidPrompt: 'invalid_prompt' as CodeEnum
    };
}