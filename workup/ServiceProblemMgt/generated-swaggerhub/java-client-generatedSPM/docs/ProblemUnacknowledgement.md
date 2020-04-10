
# ProblemUnacknowledgement

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of this task resource |  [optional]
**href** | **String** | Reference to this task resource |  [optional]
**problem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | The problems to be unacknowledged, relevant in the input to this task |  [optional]
**trackingRecord** | [**TrackingRecord**](TrackingRecord.md) | A record of the action taken in this acknowledgement |  [optional]
**unackProblem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | The problems that were unacknowledged, populated in the output to this task |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



