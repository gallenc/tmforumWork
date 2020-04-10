# ServiceProblemEventRecordApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**listServiceProblemEventRecord**](ServiceProblemEventRecordApi.md#listServiceProblemEventRecord) | **GET** /serviceProblemEventRecord | List or find ServiceProblemEventRecord objects
[**retrieveServiceProblemEventRecord**](ServiceProblemEventRecordApi.md#retrieveServiceProblemEventRecord) | **GET** /serviceProblemEventRecord/{id} | Retrieves a ServiceProblemEventRecord by ID


<a name="listServiceProblemEventRecord"></a>
# **listServiceProblemEventRecord**
> List&lt;ServiceProblemEventRecord&gt; listServiceProblemEventRecord(fields, offset, limit)

List or find ServiceProblemEventRecord objects

This operation list or find ServiceProblemEventRecord entities

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemEventRecordApi;


ServiceProblemEventRecordApi apiInstance = new ServiceProblemEventRecordApi();
String fields = "fields_example"; // String | Comma-separated properties to be provided in response
Integer offset = 56; // Integer | Requested index for start of resources to be provided in response
Integer limit = 56; // Integer | Requested number of resources to be provided in response
try {
    List<ServiceProblemEventRecord> result = apiInstance.listServiceProblemEventRecord(fields, offset, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemEventRecordApi#listServiceProblemEventRecord");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fields** | **String**| Comma-separated properties to be provided in response | [optional]
 **offset** | **Integer**| Requested index for start of resources to be provided in response | [optional]
 **limit** | **Integer**| Requested number of resources to be provided in response | [optional]

### Return type

[**List&lt;ServiceProblemEventRecord&gt;**](ServiceProblemEventRecord.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="retrieveServiceProblemEventRecord"></a>
# **retrieveServiceProblemEventRecord**
> ServiceProblemEventRecord retrieveServiceProblemEventRecord(id, fields)

Retrieves a ServiceProblemEventRecord by ID

This operation retrieves a ServiceProblemEventRecord entity. Attribute selection is enabled for all first level attributes.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemEventRecordApi;


ServiceProblemEventRecordApi apiInstance = new ServiceProblemEventRecordApi();
String id = "id_example"; // String | Identifier of the ServiceProblemEventRecord
String fields = "fields_example"; // String | Comma-separated properties to provide in response
try {
    ServiceProblemEventRecord result = apiInstance.retrieveServiceProblemEventRecord(id, fields);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemEventRecordApi#retrieveServiceProblemEventRecord");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Identifier of the ServiceProblemEventRecord |
 **fields** | **String**| Comma-separated properties to provide in response | [optional]

### Return type

[**ServiceProblemEventRecord**](ServiceProblemEventRecord.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

