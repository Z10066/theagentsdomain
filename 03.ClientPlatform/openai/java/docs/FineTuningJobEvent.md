# FineTuningJobEvent

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** |  | 
**createdAt** | **Integer** |  | 
**level** | [**LevelEnum**](#LevelEnum) |  | 
**message** | **String** |  | 
**object** | [**ObjectEnum**](#ObjectEnum) |  | 

<a name="LevelEnum"></a>
## Enum: LevelEnum
Name | Value
---- | -----
INFO | &quot;info&quot;
WARN | &quot;warn&quot;
ERROR | &quot;error&quot;

<a name="ObjectEnum"></a>
## Enum: ObjectEnum
Name | Value
---- | -----
FINE_TUNING_JOB_EVENT | &quot;fine_tuning.job.event&quot;
