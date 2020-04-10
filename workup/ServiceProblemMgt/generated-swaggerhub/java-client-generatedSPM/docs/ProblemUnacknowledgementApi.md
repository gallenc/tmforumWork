# ProblemUnacknowledgementApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createProblemUnacknowledgement**](ProblemUnacknowledgementApi.md#createProblemUnacknowledgement) | **POST** /problemUnacknowledgement | Creates a ProblemUnacknowledgement


<a name="createProblemUnacknowledgement"></a>
# **createProblemUnacknowledgement**
> ProblemUnacknowledgement createProblemUnacknowledgement(problemUnacknowledgement)

Creates a ProblemUnacknowledgement

This operation creates a ProblemUnacknowledgement entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProblemUnacknowledgementApi;


ProblemUnacknowledgementApi apiInstance = new ProblemUnacknowledgementApi();
ProblemUnacknowledgementCreate problemUnacknowledgement = new ProblemUnacknowledgementCreate(); // ProblemUnacknowledgementCreate | The ProblemUnacknowledgement to be created
try {
    ProblemUnacknowledgement result = apiInstance.createProblemUnacknowledgement(problemUnacknowledgement);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProblemUnacknowledgementApi#createProblemUnacknowledgement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **problemUnacknowledgement** | [**ProblemUnacknowledgementCreate**](ProblemUnacknowledgementCreate.md)| The ProblemUnacknowledgement to be created |

### Return type

[**ProblemUnacknowledgement**](ProblemUnacknowledgement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

