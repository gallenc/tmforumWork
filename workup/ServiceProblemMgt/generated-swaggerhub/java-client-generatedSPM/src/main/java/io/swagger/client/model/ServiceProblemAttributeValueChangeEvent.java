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


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.ServiceProblem;
import java.io.IOException;

/**
 * The event data structure
 */
@ApiModel(description = "The event data structure")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-06T18:52:03.826Z")
public class ServiceProblemAttributeValueChangeEvent {
  @SerializedName("serviceProblem")
  private ServiceProblem serviceProblem = null;

  public ServiceProblemAttributeValueChangeEvent serviceProblem(ServiceProblem serviceProblem) {
    this.serviceProblem = serviceProblem;
    return this;
  }

   /**
   * The involved resource data for the event
   * @return serviceProblem
  **/
  @ApiModelProperty(value = "The involved resource data for the event")
  public ServiceProblem getServiceProblem() {
    return serviceProblem;
  }

  public void setServiceProblem(ServiceProblem serviceProblem) {
    this.serviceProblem = serviceProblem;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceProblemAttributeValueChangeEvent serviceProblemAttributeValueChangeEvent = (ServiceProblemAttributeValueChangeEvent) o;
    return Objects.equals(this.serviceProblem, serviceProblemAttributeValueChangeEvent.serviceProblem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceProblem);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceProblemAttributeValueChangeEvent {\n");
    
    sb.append("    serviceProblem: ").append(toIndentedString(serviceProblem)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

