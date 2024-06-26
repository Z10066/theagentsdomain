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
import { RunStepDetailsMessageCreationObjectMessageCreation } from './runStepDetailsMessageCreationObjectMessageCreation';

/**
 * Details of the message creation by the run step.
 */
export interface RunStepDetailsMessageCreationObject { 
    /**
     * Always `message_creation`.
     */
    type: RunStepDetailsMessageCreationObject.TypeEnum;
    messageCreation: RunStepDetailsMessageCreationObjectMessageCreation;
}
export namespace RunStepDetailsMessageCreationObject {
    export type TypeEnum = 'message_creation';
    export const TypeEnum = {
        MessageCreation: 'message_creation' as TypeEnum
    };
}