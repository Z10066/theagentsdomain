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

export interface CreateVectorStoreFileRequest { 
    /**
     * A [File](/docs/api-reference/files) ID that the vector store should use. Useful for tools like `file_search` that can access files.
     */
    fileId: string;
}