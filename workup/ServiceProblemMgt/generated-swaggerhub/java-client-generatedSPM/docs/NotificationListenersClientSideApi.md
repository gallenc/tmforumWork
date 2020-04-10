# NotificationListenersClientSideApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**listenToServiceProblemAttributeValueChangeNotification**](NotificationListenersClientSideApi.md#listenToServiceProblemAttributeValueChangeNotification) | **POST** /listener/serviceProblemAttributeValueChangeNotification | Client listener for entity ServiceProblemAttributeValueChangeNotification
[**listenToServiceProblemCreateNotification**](NotificationListenersClientSideApi.md#listenToServiceProblemCreateNotification) | **POST** /listener/serviceProblemCreateNotification | Client listener for entity ServiceProblemCreateNotification
[**listenToServiceProblemInformationRequiredNotification**](NotificationListenersClientSideApi.md#listenToServiceProblemInformationRequiredNotification) | **POST** /listener/serviceProblemInformationRequiredNotification | Client listener for entity ServiceProblemInformationRequiredNotification
[**listenToServiceProblemStateChangeNotification**](NotificationListenersClientSideApi.md#listenToServiceProblemStateChangeNotification) | **POST** /listener/serviceProblemStateChangeNotification | Client listener for entity ServiceProblemStateChangeNotification


<a name="listenToServiceProblemAttributeValueChangeNotification"></a>
# **listenToServiceProblemAttributeValueChangeNotification**
> EventSubscription listenToServiceProblemAttributeValueChangeNotification(data)

Client listener for entity ServiceProblemAttributeValueChangeNotification

Example of a client listener for receiving the notification ServiceProblemAttributeValueChangeNotification

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationListenersClientSideApi;


NotificationListenersClientSideApi apiInstance = new NotificationListenersClientSideApi();
ServiceProblemAttributeValueChangeNotification data = new ServiceProblemAttributeValueChangeNotification(); // ServiceProblemAttributeValueChangeNotification | The event data
try {
    EventSubscription result = apiInstance.listenToServiceProblemAttributeValueChangeNotification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationListenersClientSideApi#listenToServiceProblemAttributeValueChangeNotification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**ServiceProblemAttributeValueChangeNotification**](ServiceProblemAttributeValueChangeNotification.md)| The event data |

### Return type

[**EventSubscription**](EventSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="listenToServiceProblemCreateNotification"></a>
# **listenToServiceProblemCreateNotification**
> EventSubscription listenToServiceProblemCreateNotification(data)

Client listener for entity ServiceProblemCreateNotification

Example of a client listener for receiving the notification ServiceProblemCreateNotification

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationListenersClientSideApi;


NotificationListenersClientSideApi apiInstance = new NotificationListenersClientSideApi();
ServiceProblemCreateNotification data = new ServiceProblemCreateNotification(); // ServiceProblemCreateNotification | The event data
try {
    EventSubscription result = apiInstance.listenToServiceProblemCreateNotification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationListenersClientSideApi#listenToServiceProblemCreateNotification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**ServiceProblemCreateNotification**](ServiceProblemCreateNotification.md)| The event data |

### Return type

[**EventSubscription**](EventSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="listenToServiceProblemInformationRequiredNotification"></a>
# **listenToServiceProblemInformationRequiredNotification**
> EventSubscription listenToServiceProblemInformationRequiredNotification(data)

Client listener for entity ServiceProblemInformationRequiredNotification

Example of a client listener for receiving the notification ServiceProblemInformationRequiredNotification

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationListenersClientSideApi;


NotificationListenersClientSideApi apiInstance = new NotificationListenersClientSideApi();
ServiceProblemInformationRequiredNotification data = new ServiceProblemInformationRequiredNotification(); // ServiceProblemInformationRequiredNotification | The event data
try {
    EventSubscription result = apiInstance.listenToServiceProblemInformationRequiredNotification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationListenersClientSideApi#listenToServiceProblemInformationRequiredNotification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**ServiceProblemInformationRequiredNotification**](ServiceProblemInformationRequiredNotification.md)| The event data |

### Return type

[**EventSubscription**](EventSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="listenToServiceProblemStateChangeNotification"></a>
# **listenToServiceProblemStateChangeNotification**
> EventSubscription listenToServiceProblemStateChangeNotification(data)

Client listener for entity ServiceProblemStateChangeNotification

Example of a client listener for receiving the notification ServiceProblemStateChangeNotification

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.NotificationListenersClientSideApi;


NotificationListenersClientSideApi apiInstance = new NotificationListenersClientSideApi();
ServiceProblemStateChangeNotification data = new ServiceProblemStateChangeNotification(); // ServiceProblemStateChangeNotification | The event data
try {
    EventSubscription result = apiInstance.listenToServiceProblemStateChangeNotification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationListenersClientSideApi#listenToServiceProblemStateChangeNotification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**ServiceProblemStateChangeNotification**](ServiceProblemStateChangeNotification.md)| The event data |

### Return type

[**EventSubscription**](EventSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

