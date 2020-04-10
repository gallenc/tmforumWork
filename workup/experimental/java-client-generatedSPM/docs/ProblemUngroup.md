
# ProblemUngroup

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of this task resource |  [optional]
**href** | **String** | Reference to this task resource |  [optional]
**childProblem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | List of problems to be ungrouped from a parent problem |  [optional]
**parentProblem** | [**ServiceProblemRef**](ServiceProblemRef.md) | The parent problem from which the problems are to be ungrouped |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



