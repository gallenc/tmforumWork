# ServiceProblemApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createServiceProblem**](ServiceProblemApi.md#createServiceProblem) | **POST** /serviceProblem | Creates a ServiceProblem
[**deleteServiceProblem**](ServiceProblemApi.md#deleteServiceProblem) | **DELETE** /serviceProblem/{id} | Deletes a ServiceProblem
[**listServiceProblem**](ServiceProblemApi.md#listServiceProblem) | **GET** /serviceProblem | List or find ServiceProblem objects
[**patchServiceProblem**](ServiceProblemApi.md#patchServiceProblem) | **PATCH** /serviceProblem/{id} | Updates partially a ServiceProblem
[**retrieveServiceProblem**](ServiceProblemApi.md#retrieveServiceProblem) | **GET** /serviceProblem/{id} | Retrieves a ServiceProblem by ID


<a name="createServiceProblem"></a>
# **createServiceProblem**
> ServiceProblem createServiceProblem(serviceProblem)

Creates a ServiceProblem

This operation creates a ServiceProblem entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemApi;


ServiceProblemApi apiInstance = new ServiceProblemApi();
ServiceProblemCreate serviceProblem = new ServiceProblemCreate(); // ServiceProblemCreate | The ServiceProblem to be created
try {
    ServiceProblem result = apiInstance.createServiceProblem(serviceProblem);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemApi#createServiceProblem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceProblem** | [**ServiceProblemCreate**](ServiceProblemCreate.md)| The ServiceProblem to be created |

### Return type

[**ServiceProblem**](ServiceProblem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="deleteServiceProblem"></a>
# **deleteServiceProblem**
> deleteServiceProblem(id)

Deletes a ServiceProblem

This operation deletes a ServiceProblem entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemApi;


ServiceProblemApi apiInstance = new ServiceProblemApi();
String id = "id_example"; // String | Identifier of the ServiceProblem
try {
    apiInstance.deleteServiceProblem(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemApi#deleteServiceProblem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Identifier of the ServiceProblem |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="listServiceProblem"></a>
# **listServiceProblem**
> List&lt;ServiceProblem&gt; listServiceProblem(fields, offset, limit)

List or find ServiceProblem objects

This operation list or find ServiceProblem entities

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemApi;


ServiceProblemApi apiInstance = new ServiceProblemApi();
String fields = "fields_example"; // String | Comma-separated properties to be provided in response
Integer offset = 56; // Integer | Requested index for start of resources to be provided in response
Integer limit = 56; // Integer | Requested number of resources to be provided in response
try {
    List<ServiceProblem> result = apiInstance.listServiceProblem(fields, offset, limit);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemApi#listServiceProblem");
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

[**List&lt;ServiceProblem&gt;**](ServiceProblem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="patchServiceProblem"></a>
# **patchServiceProblem**
> ServiceProblem patchServiceProblem(id, serviceProblem)

Updates partially a ServiceProblem

This operation updates partially a ServiceProblem entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemApi;


ServiceProblemApi apiInstance = new ServiceProblemApi();
String id = "id_example"; // String | Identifier of the ServiceProblem
ServiceProblemUpdate serviceProblem = new ServiceProblemUpdate(); // ServiceProblemUpdate | The ServiceProblem to be updated
try {
    ServiceProblem result = apiInstance.patchServiceProblem(id, serviceProblem);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemApi#patchServiceProblem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Identifier of the ServiceProblem |
 **serviceProblem** | [**ServiceProblemUpdate**](ServiceProblemUpdate.md)| The ServiceProblem to be updated |

### Return type

[**ServiceProblem**](ServiceProblem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

<a name="retrieveServiceProblem"></a>
# **retrieveServiceProblem**
> ServiceProblem retrieveServiceProblem(id, fields)

Retrieves a ServiceProblem by ID

This operation retrieves a ServiceProblem entity. Attribute selection is enabled for all first level attributes.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ServiceProblemApi;


ServiceProblemApi apiInstance = new ServiceProblemApi();
String id = "id_example"; // String | Identifier of the ServiceProblem
String fields = "fields_example"; // String | Comma-separated properties to provide in response
try {
    ServiceProblem result = apiInstance.retrieveServiceProblem(id, fields);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProblemApi#retrieveServiceProblem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| Identifier of the ServiceProblem |
 **fields** | **String**| Comma-separated properties to provide in response | [optional]

### Return type

[**ServiceProblem**](ServiceProblem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

