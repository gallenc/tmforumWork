# ProblemGroupApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createProblemGroup**](ProblemGroupApi.md#createProblemGroup) | **POST** /problemGroup | Creates a ProblemGroup


<a name="createProblemGroup"></a>
# **createProblemGroup**
> ProblemGroup createProblemGroup(problemGroup)

Creates a ProblemGroup

This operation creates a ProblemGroup entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProblemGroupApi;


ProblemGroupApi apiInstance = new ProblemGroupApi();
ProblemGroupCreate problemGroup = new ProblemGroupCreate(); // ProblemGroupCreate | The ProblemGroup to be created
try {
    ProblemGroup result = apiInstance.createProblemGroup(problemGroup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProblemGroupApi#createProblemGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **problemGroup** | [**ProblemGroupCreate**](ProblemGroupCreate.md)| The ProblemGroup to be created |

### Return type

[**ProblemGroup**](ProblemGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

