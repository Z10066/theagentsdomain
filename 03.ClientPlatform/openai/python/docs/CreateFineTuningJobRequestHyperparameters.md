# CreateFineTuningJobRequestHyperparameters

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**batch_size** | **OneOfCreateFineTuningJobRequestHyperparametersBatchSize** | Number of examples in each batch. A larger batch size means that model parameters are updated less frequently, but with lower variance.  | [optional] 
**learning_rate_multiplier** | **OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier** | Scaling factor for the learning rate. A smaller learning rate may be useful to avoid overfitting.  | [optional] 
**n_epochs** | **OneOfCreateFineTuningJobRequestHyperparametersNEpochs** | The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.  | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)

