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
import io.swagger.client.model.ServiceProblemStateChangeEvent;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * The notification data structure
 */
@ApiModel(description = "The notification data structure")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-06T18:52:03.826Z")
public class ServiceProblemStateChangeNotification {
  @SerializedName("eventId")
  private String eventId = null;

  @SerializedName("eventTime")
  private OffsetDateTime eventTime = null;

  @SerializedName("eventType")
  private String eventType = null;

  @SerializedName("fieldPath")
  private String fieldPath = null;

  @SerializedName("resourcePath")
  private String resourcePath = null;

  @SerializedName("event")
  private ServiceProblemStateChangeEvent event = null;

  public ServiceProblemStateChangeNotification eventId(String eventId) {
    this.eventId = eventId;
    return this;
  }

   /**
   * The identifier of the notification
   * @return eventId
  **/
  @ApiModelProperty(value = "The identifier of the notification")
  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public ServiceProblemStateChangeNotification eventTime(OffsetDateTime eventTime) {
    this.eventTime = eventTime;
    return this;
  }

   /**
   * Time of the event occurrence
   * @return eventTime
  **/
  @ApiModelProperty(value = "Time of the event occurrence")
  public OffsetDateTime getEventTime() {
    return eventTime;
  }

  public void setEventTime(OffsetDateTime eventTime) {
    this.eventTime = eventTime;
  }

  public ServiceProblemStateChangeNotification eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * The type of the notification
   * @return eventType
  **/
  @ApiModelProperty(value = "The type of the notification")
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public ServiceProblemStateChangeNotification fieldPath(String fieldPath) {
    this.fieldPath = fieldPath;
    return this;
  }

   /**
   * The path identifying the object field concerned by this notification
   * @return fieldPath
  **/
  @ApiModelProperty(value = "The path identifying the object field concerned by this notification")
  public String getFieldPath() {
    return fieldPath;
  }

  public void setFieldPath(String fieldPath) {
    this.fieldPath = fieldPath;
  }

  public ServiceProblemStateChangeNotification resourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
    return this;
  }

   /**
   * The path identifying the resource object concerned by this notification
   * @return resourcePath
  **/
  @ApiModelProperty(value = "The path identifying the resource object concerned by this notification")
  public String getResourcePath() {
    return resourcePath;
  }

  public void setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public ServiceProblemStateChangeNotification event(ServiceProblemStateChangeEvent event) {
    this.event = event;
    return this;
  }

   /**
   * The event linked to the involved resource object
   * @return event
  **/
  @ApiModelProperty(value = "The event linked to the involved resource object")
  public ServiceProblemStateChangeEvent getEvent() {
    return event;
  }

  public void setEvent(ServiceProblemStateChangeEvent event) {
    this.event = event;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceProblemStateChangeNotification serviceProblemStateChangeNotification = (ServiceProblemStateChangeNotification) o;
    return Objects.equals(this.eventId, serviceProblemStateChangeNotification.eventId) &&
        Objects.equals(this.eventTime, serviceProblemStateChangeNotification.eventTime) &&
        Objects.equals(this.eventType, serviceProblemStateChangeNotification.eventType) &&
        Objects.equals(this.fieldPath, serviceProblemStateChangeNotification.fieldPath) &&
        Objects.equals(this.resourcePath, serviceProblemStateChangeNotification.resourcePath) &&
        Objects.equals(this.event, serviceProblemStateChangeNotification.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventId, eventTime, eventType, fieldPath, resourcePath, event);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceProblemStateChangeNotification {\n");
    
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    fieldPath: ").append(toIndentedString(fieldPath)).append("\n");
    sb.append("    resourcePath: ").append(toIndentedString(resourcePath)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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

