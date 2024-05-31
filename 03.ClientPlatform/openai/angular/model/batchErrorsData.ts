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

export interface BatchErrorsData { 
    /**
     * An error code identifying the error type.
     */
    code?: string;
    /**
     * A human-readable message providing more details about the error.
     */
    message?: string;
    /**
     * The name of the parameter that caused the error, if applicable.
     */
    param?: string;
    /**
     * The line number of the input file where the error occurred, if applicable.
     */
    line?: number;
}