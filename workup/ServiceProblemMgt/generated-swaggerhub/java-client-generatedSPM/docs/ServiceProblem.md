
# ServiceProblem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Identifier of the service problem |  [optional]
**href** | **String** | Reference to the Service Problem |  [optional]
**affectedNumberOfServices** | **Integer** | Number of affected services |  [optional]
**category** | **String** | Classifier for the problem. Settable. For example, this is used for distinguish the category of problem originator in [role].[category] format. Example: serviceProvider.declarer, supplier.originated, system.originated |  [optional]
**correlationId** | **String** | Additional identifier coming from an external system |  [optional]
**description** | **String** | Free form text describing the Service Problem |  [optional]
**impactImportanceFactor** | **String** | Impact Importance is characterized by an Impact Importance Factor: overall importance of the impact of all the affected services, e.g. 0 (zero impact) to 100 (worst impact). The Impact Importance is a calculated field which is set by the OSS determining the impact. |  [optional]
**originatingSystem** | **String** | Indicates where the problem was generated |  [optional]
**priority** | **Integer** | An indication varying from 1 (highest) to 10 (lowest) of how important it is for the service provider to correct the Service Problem. |  [optional]
**problemEscalation** | **String** | Indicates if this service problem has been escalated or not. Possible values are 0 to 10. A value of zero means no escalation. The meanings of values 1-10 are to be determined by the user of the interface, but they show increasing levels of escalation. |  [optional]
**reason** | **String** | Free text or optionally structured text. It can be Unknown. |  [optional]
**resolutionDate** | [**OffsetDateTime**](OffsetDateTime.md) | Time the problem was resolved |  [optional]
**status** | **String** | The current status of the service problem. Possible values are Submitted, Rejected, Acknowledged, In Progress [Held, Pending], Resolved, Closed, and Cancelled. |  [optional]
**statusChangeDate** | [**OffsetDateTime**](OffsetDateTime.md) | Time the problem was last status changed |  [optional]
**statusChangeReason** | **String** | The reason of state change |  [optional]
**timeChanged** | [**OffsetDateTime**](OffsetDateTime.md) | Time the problem was last changed |  [optional]
**timeRaised** | [**OffsetDateTime**](OffsetDateTime.md) | Time the problem was raised |  [optional]
**affectedLocation** | [**List&lt;Place&gt;**](Place.md) | A list of the locations affected by the problem. At least one of affectedResource, affectedService or affectedLocation should be present. |  [optional]
**affectedResource** | [**List&lt;ResourceRef&gt;**](ResourceRef.md) | A list of the resources affected by the problem. At least one of affectedResource, affectedService or affectedLocation should be present. |  [optional]
**affectedService** | [**List&lt;ServiceRef&gt;**](ServiceRef.md) | List of affected services. At least one of affectedResource, affectedService or affectedLocation should be present. |  [optional]
**associatedSLAViolation** | [**List&lt;SLAViolationRef&gt;**](SLAViolationRef.md) | A List of SLA violations associated with this problem. |  [optional]
**associatedTroubleTicket** | [**List&lt;TroubleTicketRef&gt;**](TroubleTicketRef.md) | A list of trouble tickets associated with this problem. |  [optional]
**comment** | [**List&lt;Note&gt;**](Note.md) | A list of comments or notes made on the problem |  [optional]
**extensionInfo** | [**List&lt;Characteristic&gt;**](Characteristic.md) | A generic list of any type of elements. Used for vendor Extensions or loose element encapsulation from other namespaces |  [optional]
**firstAlert** | [**RelatedEntityRef**](RelatedEntityRef.md) | Indicates what first alerted the system to the problem. It is not the root cause of the Service Problem. Examples: Threshold crossing alert |  [optional]
**impactPatterns** | [**ImpactPattern**](ImpactPattern.md) | Define the patterns of impact (optional)- e.g. other service characteristics- Used when defining impact through another pattern than the predefined attributes. |  [optional]
**originatorParty** | [**RelatedParty**](RelatedParty.md) | Individual or organization that created the problem |  [optional]
**parentProblem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | The parent problem to which this problem is attached. |  [optional]
**relatedEvent** | [**List&lt;EventRef&gt;**](EventRef.md) | List of events associated to this problem |  [optional]
**relatedObject** | [**List&lt;RelatedEntityRef&gt;**](RelatedEntityRef.md) | List of objects associated to this problem |  [optional]
**relatedParty** | [**List&lt;RelatedParty&gt;**](RelatedParty.md) | List of parties or party roles playing a role within the service problem |  [optional]
**responsibleParty** | [**RelatedParty**](RelatedParty.md) | Individual or organization responsible for handling this problem |  [optional]
**rootCauseResource** | [**List&lt;ResourceRef&gt;**](ResourceRef.md) | Resource(s) that are associated to the underlying service problems that are the Root Cause of this one if any (used only if applicable). |  [optional]
**rootCauseService** | [**List&lt;ServiceRef&gt;**](ServiceRef.md) | Service(s) that are associated to the underlying service problems that are the Root Cause of this one if any (used only if applicable) |  [optional]
**trackingRecord** | [**List&lt;TrackingRecord&gt;**](TrackingRecord.md) | List of tracking records that allow the tracking of modifications on the problem.The tracking records should not be embedded in the problem to allow retrieving the problem without the tracking records |  [optional]
**underlyingAlarm** | [**List&lt;ResourceAlarmRef&gt;**](ResourceAlarmRef.md) | A list of alarms underlying this problem. |  [optional]
**underlyingProblem** | [**List&lt;ServiceProblemRef&gt;**](ServiceProblemRef.md) | A list of underlying problems. Relevant only if this problem is derived from other problems. |  [optional]
**baseType** | **String** | When sub-classing, this defines the super-class |  [optional]
**schemaLocation** | **String** | A URI to a JSON-Schema file that defines additional attributes and relationships |  [optional]
**type** | **String** | When sub-classing, this defines the sub-class entity name |  [optional]



