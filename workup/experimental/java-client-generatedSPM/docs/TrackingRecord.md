
# TrackingRecord

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Identifier of the TrackingRecord |  [optional]
**description** | **String** | Describes the action being done, such as: ack, clear |  [optional]
**systemId** | **String** | Describes the system Id from which the action was done |  [optional]
**time** | [**OffsetDateTime**](OffsetDateTime.md) | Describes the time at which the action was done |  [optional]
**user** | **String** | Describes the user doing the action |  [optional]
**extensionInfo** | [**List&lt;Characteristic&gt;**](Characteristic.md) | A generic list of any type of elements. Used for vendor Extensions or loose element encapsulation from other namespaces |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



