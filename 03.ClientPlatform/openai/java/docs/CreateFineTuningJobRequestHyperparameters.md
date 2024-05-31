# CreateFineTuningJobRequestHyperparameters

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**batchSize** | **OneOfCreateFineTuningJobRequestHyperparametersBatchSize** | Number of examples in each batch. A larger batch size means that model parameters are updated less frequently, but with lower variance.  |  [optional]
**learningRateMultiplier** | **OneOfCreateFineTuningJobRequestHyperparametersLearningRateMultiplier** | Scaling factor for the learning rate. A smaller learning rate may be useful to avoid overfitting.  |  [optional]
**nEpochs** | **OneOfCreateFineTuningJobRequestHyperparametersNEpochs** | The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.  |  [optional]
