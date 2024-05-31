# CreateFileRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**file** | [**File**](File.md) | The File object (not file name) to be uploaded.  | 
**purpose** | [**PurposeEnum**](#PurposeEnum) | The intended purpose of the uploaded file.  Use \&quot;assistants\&quot; for [Assistants](/docs/api-reference/assistants) and [Message](/docs/api-reference/messages) files, \&quot;vision\&quot; for Assistants image file inputs, \&quot;batch\&quot; for [Batch API](/docs/guides/batch), and \&quot;fine-tune\&quot; for [Fine-tuning](/docs/api-reference/fine-tuning).  | 

<a name="PurposeEnum"></a>
## Enum: PurposeEnum
Name | Value
---- | -----
ASSISTANTS | &quot;assistants&quot;
BATCH | &quot;batch&quot;
FINE_TUNE | &quot;fine-tune&quot;
VISION | &quot;vision&quot;
