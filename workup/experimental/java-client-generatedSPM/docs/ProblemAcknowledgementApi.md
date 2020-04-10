# ProblemAcknowledgementApi

All URIs are relative to *https://serverRoot/tmf-api/serviceProblemManagement/v3/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createProblemAcknowledgement**](ProblemAcknowledgementApi.md#createProblemAcknowledgement) | **POST** /problemAcknowledgement | Creates a ProblemAcknowledgement


<a name="createProblemAcknowledgement"></a>
# **createProblemAcknowledgement**
> ProblemAcknowledgement createProblemAcknowledgement(problemAcknowledgement)

Creates a ProblemAcknowledgement

This operation creates a ProblemAcknowledgement entity.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ProblemAcknowledgementApi;


ProblemAcknowledgementApi apiInstance = new ProblemAcknowledgementApi();
ProblemAcknowledgementCreate problemAcknowledgement = new ProblemAcknowledgementCreate(); // ProblemAcknowledgementCreate | The ProblemAcknowledgement to be created
try {
    ProblemAcknowledgement result = apiInstance.createProblemAcknowledgement(problemAcknowledgement);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProblemAcknowledgementApi#createProblemAcknowledgement");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **problemAcknowledgement** | [**ProblemAcknowledgementCreate**](ProblemAcknowledgementCreate.md)| The ProblemAcknowledgement to be created |

### Return type

[**ProblemAcknowledgement**](ProblemAcknowledgement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;charset=utf-8
 - **Accept**: application/json;charset=utf-8

