
# EventRef

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | ID of the event | 
**href** | **String** | event reference | 
**eventTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time the event occurred |  [optional]
**eventType** | **String** | Type of the event |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]
**referredType** | **String** | The actual type of the target instance when needed for disambiguation. |  [optional]



