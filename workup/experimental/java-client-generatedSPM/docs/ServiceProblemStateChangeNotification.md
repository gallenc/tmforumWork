
# ServiceProblemStateChangeNotification

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**eventId** | **String** | The identifier of the notification |  [optional]
**eventTime** | [**OffsetDateTime**](OffsetDateTime.md) | Time of the event occurrence |  [optional]
**eventType** | **String** | The type of the notification |  [optional]
**fieldPath** | **String** | The path identifying the object field concerned by this notification |  [optional]
**resourcePath** | **String** | The path identifying the resource object concerned by this notification |  [optional]
**event** | [**ServiceProblemStateChangeEvent**](ServiceProblemStateChangeEvent.md) | The event linked to the involved resource object |  [optional]



