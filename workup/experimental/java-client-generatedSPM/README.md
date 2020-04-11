# swagger-java-client

Service Problem Management
- API version: 3.0.0
  - Build date: 2020-04-06T18:52:03.826Z

## TMF API Reference: TMF656 - Service Problem Management  ### Release : 18.5 - December 2018  Service Problem Management API goal is to provide the ability to manage problems in the Service domain.   ### Resource  - serviceProblem  ### Operations Service Problem Management API performs the following operations on the resource : - Retrieve an entity or a collection of entities depending on filter criteria - Partial update of an entity (including updating rules) - Create an entity (including default values and creation rules) - Delete an entity (for administration purposes) - Manage notification of events


*Automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)*


## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.swagger</groupId>
  <artifactId>swagger-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/swagger-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.EventsSubscriptionApi;

import java.io.File;
import java.util.*;

public class EventsSubscriptionApiExample {

    public static void main(String[] args) {
        
        EventsSubscriptionApi apiInstance = new EventsSubscriptionApi();
        EventSubscriptionInput data = new EventSubscriptionInput(); // EventSubscriptionInput | Data containing the callback endpoint to deliver the information
        try {
            EventSubscription result = apiInstance.registerListener(data);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling EventsSubscriptionApi#registerListener");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*EventsSubscriptionApi* | [**registerListener**](docs/EventsSubscriptionApi.md#registerListener) | **POST** /hub | Register a listener
*EventsSubscriptionApi* | [**unregisterListener**](docs/EventsSubscriptionApi.md#unregisterListener) | **DELETE** /hub/{id} | Unregister a listener
*NotificationListenersClientSideApi* | [**listenToServiceProblemAttributeValueChangeNotification**](docs/NotificationListenersClientSideApi.md#listenToServiceProblemAttributeValueChangeNotification) | **POST** /listener/serviceProblemAttributeValueChangeNotification | Client listener for entity ServiceProblemAttributeValueChangeNotification
*NotificationListenersClientSideApi* | [**listenToServiceProblemCreateNotification**](docs/NotificationListenersClientSideApi.md#listenToServiceProblemCreateNotification) | **POST** /listener/serviceProblemCreateNotification | Client listener for entity ServiceProblemCreateNotification
*NotificationListenersClientSideApi* | [**listenToServiceProblemInformationRequiredNotification**](docs/NotificationListenersClientSideApi.md#listenToServiceProblemInformationRequiredNotification) | **POST** /listener/serviceProblemInformationRequiredNotification | Client listener for entity ServiceProblemInformationRequiredNotification
*NotificationListenersClientSideApi* | [**listenToServiceProblemStateChangeNotification**](docs/NotificationListenersClientSideApi.md#listenToServiceProblemStateChangeNotification) | **POST** /listener/serviceProblemStateChangeNotification | Client listener for entity ServiceProblemStateChangeNotification
*ProblemAcknowledgementApi* | [**createProblemAcknowledgement**](docs/ProblemAcknowledgementApi.md#createProblemAcknowledgement) | **POST** /problemAcknowledgement | Creates a ProblemAcknowledgement
*ProblemGroupApi* | [**createProblemGroup**](docs/ProblemGroupApi.md#createProblemGroup) | **POST** /problemGroup | Creates a ProblemGroup
*ProblemUnacknowledgementApi* | [**createProblemUnacknowledgement**](docs/ProblemUnacknowledgementApi.md#createProblemUnacknowledgement) | **POST** /problemUnacknowledgement | Creates a ProblemUnacknowledgement
*ProblemUngroupApi* | [**createProblemUngroup**](docs/ProblemUngroupApi.md#createProblemUngroup) | **POST** /problemUngroup | Creates a ProblemUngroup
*ServiceProblemApi* | [**createServiceProblem**](docs/ServiceProblemApi.md#createServiceProblem) | **POST** /serviceProblem | Creates a ServiceProblem
*ServiceProblemApi* | [**deleteServiceProblem**](docs/ServiceProblemApi.md#deleteServiceProblem) | **DELETE** /serviceProblem/{id} | Deletes a ServiceProblem
*ServiceProblemApi* | [**listServiceProblem**](docs/ServiceProblemApi.md#listServiceProblem) | **GET** /serviceProblem | List or find ServiceProblem objects
*ServiceProblemApi* | [**patchServiceProblem**](docs/ServiceProblemApi.md#patchServiceProblem) | **PATCH** /serviceProblem/{id} | Updates partially a ServiceProblem
*ServiceProblemApi* | [**retrieveServiceProblem**](docs/ServiceProblemApi.md#retrieveServiceProblem) | **GET** /serviceProblem/{id} | Retrieves a ServiceProblem by ID
*ServiceProblemEventRecordApi* | [**listServiceProblemEventRecord**](docs/ServiceProblemEventRecordApi.md#listServiceProblemEventRecord) | **GET** /serviceProblemEventRecord | List or find ServiceProblemEventRecord objects
*ServiceProblemEventRecordApi* | [**retrieveServiceProblemEventRecord**](docs/ServiceProblemEventRecordApi.md#retrieveServiceProblemEventRecord) | **GET** /serviceProblemEventRecord/{id} | Retrieves a ServiceProblemEventRecord by ID


## Documentation for Models

 - [Any](docs/Any.md)
 - [ChangeRequestRef](docs/ChangeRequestRef.md)
 - [Characteristic](docs/Characteristic.md)
 - [Error](docs/Error.md)
 - [EventRef](docs/EventRef.md)
 - [EventSubscription](docs/EventSubscription.md)
 - [EventSubscriptionInput](docs/EventSubscriptionInput.md)
 - [ImpactPattern](docs/ImpactPattern.md)
 - [Note](docs/Note.md)
 - [Place](docs/Place.md)
 - [ProblemAcknowledgement](docs/ProblemAcknowledgement.md)
 - [ProblemAcknowledgementCreate](docs/ProblemAcknowledgementCreate.md)
 - [ProblemGroup](docs/ProblemGroup.md)
 - [ProblemGroupCreate](docs/ProblemGroupCreate.md)
 - [ProblemUnacknowledgement](docs/ProblemUnacknowledgement.md)
 - [ProblemUnacknowledgementCreate](docs/ProblemUnacknowledgementCreate.md)
 - [ProblemUngroup](docs/ProblemUngroup.md)
 - [ProblemUngroupCreate](docs/ProblemUngroupCreate.md)
 - [RelatedEntityRef](docs/RelatedEntityRef.md)
 - [RelatedParty](docs/RelatedParty.md)
 - [ResourceAlarmRef](docs/ResourceAlarmRef.md)
 - [ResourceRef](docs/ResourceRef.md)
 - [SLAViolationRef](docs/SLAViolationRef.md)
 - [ServiceProblem](docs/ServiceProblem.md)
 - [ServiceProblemAttributeValueChangeEvent](docs/ServiceProblemAttributeValueChangeEvent.md)
 - [ServiceProblemAttributeValueChangeNotification](docs/ServiceProblemAttributeValueChangeNotification.md)
 - [ServiceProblemCreate](docs/ServiceProblemCreate.md)
 - [ServiceProblemCreateEvent](docs/ServiceProblemCreateEvent.md)
 - [ServiceProblemCreateNotification](docs/ServiceProblemCreateNotification.md)
 - [ServiceProblemEventRecord](docs/ServiceProblemEventRecord.md)
 - [ServiceProblemInformationRequiredEvent](docs/ServiceProblemInformationRequiredEvent.md)
 - [ServiceProblemInformationRequiredNotification](docs/ServiceProblemInformationRequiredNotification.md)
 - [ServiceProblemRef](docs/ServiceProblemRef.md)
 - [ServiceProblemStateChangeEvent](docs/ServiceProblemStateChangeEvent.md)
 - [ServiceProblemStateChangeNotification](docs/ServiceProblemStateChangeNotification.md)
 - [ServiceProblemUpdate](docs/ServiceProblemUpdate.md)
 - [ServiceRef](docs/ServiceRef.md)
 - [TrackingRecord](docs/TrackingRecord.md)
 - [TroubleTicketRef](docs/TroubleTicketRef.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


