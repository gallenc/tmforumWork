
# ServiceProblemEventRecord

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Identifier of the service problem event record. |  [optional]
**href** | **String** | reference to this resource |  [optional]
**eventTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time at which the event occurred |  [optional]
**eventType** | **String** | Type of the recorded event |  [optional]
**recordTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time at which the record was created |  [optional]
**notification** | [**Any**](Any.md) | A notification from the possible notifications for Service Problem (such as creation, status change, information required, change) |  [optional]
**serviceProblem** | [**ServiceProblemRef**](ServiceProblemRef.md) | The service problem to which this record applies |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



