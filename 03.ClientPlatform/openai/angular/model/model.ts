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
 * Describes an OpenAI model offering that can be used with the API.
 */
export interface Model { 
    /**
     * The model identifier, which can be referenced in the API endpoints.
     */
    id: string;
    /**
     * The Unix timestamp (in seconds) when the model was created.
     */
    created: number;
    /**
     * The object type, which is always \"model\".
     */
    object: Model.ObjectEnum;
    /**
     * The organization that owns the model.
     */
    ownedBy: string;
}
export namespace Model {
    export type ObjectEnum = 'model';
    export const ObjectEnum = {
        Model: 'model' as ObjectEnum
    };
}