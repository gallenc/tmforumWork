
# ProblemAcknowledgementCreate

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ackProblem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | The problems that were acknowledged, populated in the output to this task |  [optional]
**problem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | The problems to be acknowledged, relevant in the input to this task | 
**trackingRecord** | [**TrackingRecord**](TrackingRecord.md) | A record of the action taken in this acknowledgement |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



