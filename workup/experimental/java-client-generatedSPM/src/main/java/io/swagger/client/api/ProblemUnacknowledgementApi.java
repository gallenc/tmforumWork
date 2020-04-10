/*
 * Service Problem Management
 * ## TMF API Reference: TMF656 - Service Problem Management  ### Release : 18.5 - December 2018  Service Problem Management API goal is to provide the ability to manage problems in the Service domain.   ### Resource  - serviceProblem  ### Operations Service Problem Management API performs the following operations on the resource : - Retrieve an entity or a collection of entities depending on filter criteria - Partial update of an entity (including updating rules) - Create an entity (including default values and creation rules) - Delete an entity (for administration purposes) - Manage notification of events
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.Error;
import io.swagger.client.model.ProblemUnacknowledgement;
import io.swagger.client.model.ProblemUnacknowledgementCreate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemUnacknowledgementApi {
    private ApiClient apiClient;

    public ProblemUnacknowledgementApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ProblemUnacknowledgementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for createProblemUnacknowledgement
     * @param problemUnacknowledgement The ProblemUnacknowledgement to be created (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call createProblemUnacknowledgementCall(ProblemUnacknowledgementCreate problemUnacknowledgement, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = problemUnacknowledgement;

        // create path and map variables
        String localVarPath = "/problemUnacknowledgement";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json;charset=utf-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json;charset=utf-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call createProblemUnacknowledgementValidateBeforeCall(ProblemUnacknowledgementCreate problemUnacknowledgement, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'problemUnacknowledgement' is set
        if (problemUnacknowledgement == null) {
            throw new ApiException("Missing the required parameter 'problemUnacknowledgement' when calling createProblemUnacknowledgement(Async)");
        }
        

        com.squareup.okhttp.Call call = createProblemUnacknowledgementCall(problemUnacknowledgement, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Creates a ProblemUnacknowledgement
     * This operation creates a ProblemUnacknowledgement entity.
     * @param problemUnacknowledgement The ProblemUnacknowledgement to be created (required)
     * @return ProblemUnacknowledgement
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ProblemUnacknowledgement createProblemUnacknowledgement(ProblemUnacknowledgementCreate problemUnacknowledgement) throws ApiException {
        ApiResponse<ProblemUnacknowledgement> resp = createProblemUnacknowledgementWithHttpInfo(problemUnacknowledgement);
        return resp.getData();
    }

    /**
     * Creates a ProblemUnacknowledgement
     * This operation creates a ProblemUnacknowledgement entity.
     * @param problemUnacknowledgement The ProblemUnacknowledgement to be created (required)
     * @return ApiResponse&lt;ProblemUnacknowledgement&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ProblemUnacknowledgement> createProblemUnacknowledgementWithHttpInfo(ProblemUnacknowledgementCreate problemUnacknowledgement) throws ApiException {
        com.squareup.okhttp.Call call = createProblemUnacknowledgementValidateBeforeCall(problemUnacknowledgement, null, null);
        Type localVarReturnType = new TypeToken<ProblemUnacknowledgement>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Creates a ProblemUnacknowledgement (asynchronously)
     * This operation creates a ProblemUnacknowledgement entity.
     * @param problemUnacknowledgement The ProblemUnacknowledgement to be created (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call createProblemUnacknowledgementAsync(ProblemUnacknowledgementCreate problemUnacknowledgement, final ApiCallback<ProblemUnacknowledgement> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = createProblemUnacknowledgementValidateBeforeCall(problemUnacknowledgement, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ProblemUnacknowledgement>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
