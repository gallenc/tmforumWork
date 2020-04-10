# ProblemUngroupApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createProblemUngroup**](ProblemUngroupApi.md#createProblemUngroup) | **POST** /problemUngroup | Creates a ProblemUngroup


<a name="createProblemUngroup"></a>
# **createProblemUngroup**
> ProblemUngroup createProblemUngroup(problemUngroup)

Creates a ProblemUngroup

This operation creates a ProblemUngroup entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProblemUngroupApi;


ProblemUngroupApi apiInstance = new ProblemUngroupApi();
ProblemUngroupCreate problemUngroup = new ProblemUngroupCreate(); // ProblemUngroupCreate | The ProblemUngroup to be created
try {
    ProblemUngroup result = apiInstance.createProblemUngroup(problemUngroup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProblemUngroupApi#createProblemUngroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **problemUngroup** | [**ProblemUngroupCreate**](ProblemUngroupCreate.md)| The ProblemUngroup to be created |

### Return type

[**ProblemUngroup**](ProblemUngroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

