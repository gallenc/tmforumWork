# EventsSubscriptionApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**registerListener**](EventsSubscriptionApi.md#registerListener) | **POST** /hub | Register a listener
[**unregisterListener**](EventsSubscriptionApi.md#unregisterListener) | **DELETE** /hub/{id} | Unregister a listener


<a name="registerListener"></a>
# **registerListener**
> EventSubscription registerListener(data)

Register a listener

Sets the communication endpoint address the service instance must use to deliver information about its health state, execution state, failures and metrics.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EventsSubscriptionApi;


EventsSubscriptionApi apiInstance = new EventsSubscriptionApi();
EventSubscriptionInput data = new EventSubscriptionInput(); // EventSubscriptionInput | Data containing the callback endpoint to deliver the information
try {
    EventSubscription result = apiInstance.registerListener(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsSubscriptionApi#registerListener");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**EventSubscriptionInput**](EventSubscriptionInput.md)| Data containing the callback endpoint to deliver the information |

### Return type

[**EventSubscription**](EventSubscription.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="unregisterListener"></a>
# **unregisterListener**
> unregisterListener(id)

Unregister a listener

Resets the communication endpoint address the service instance must use to deliver information about its health state, execution state, failures and metrics.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.EventsSubscriptionApi;


EventsSubscriptionApi apiInstance = new EventsSubscriptionApi();
String id = "id_example"; // String | The id of the registered listener
try {
    apiInstance.unregisterListener(id);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsSubscriptionApi#unregisterListener");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the registered listener |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

