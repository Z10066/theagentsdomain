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
import { AssistantObjectToolResourcesFileSearch } from './assistantObjectToolResourcesFileSearch';
import { CreateAssistantRequestToolResourcesCodeInterpreter } from './createAssistantRequestToolResourcesCodeInterpreter';

/**
 * A set of resources that are used by the assistant's tools. The resources are specific to the type of tool. For example, the `code_interpreter` tool requires a list of file IDs, while the `file_search` tool requires a list of vector store IDs. 
 */
export interface CreateThreadAndRunRequestToolResources { 
    codeInterpreter?: CreateAssistantRequestToolResourcesCodeInterpreter;
    fileSearch?: AssistantObjectToolResourcesFileSearch;
}