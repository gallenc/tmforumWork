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


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Any;
import io.swagger.model.ServiceProblemRef;
import java.util.Date;
import javax.validation.constraints.*;

/**
 * A record of an event (related to a service problem) received from another system
 */
@ApiModel(description = "A record of an event (related to a service problem) received from another system")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ServiceProblemEventRecord   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("href")
  private String href = null;

  @JsonProperty("eventTime")
  private Date eventTime = null;

  @JsonProperty("eventType")
  private String eventType = null;

  @JsonProperty("recordTime")
  private Date recordTime = null;

  @JsonProperty("notification")
  private Any notification = null;

  @JsonProperty("serviceProblem")
  private ServiceProblemRef serviceProblem = null;

  @JsonProperty("@baseType")
  private String baseType = null;

  @JsonProperty("@schemaLocation")
  private String schemaLocation = null;

  @JsonProperty("@type")
  private String type = null;

  public ServiceProblemEventRecord id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the service problem event record.
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "Identifier of the service problem event record.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServiceProblemEventRecord href(String href) {
    this.href = href;
    return this;
  }

  /**
   * reference to this resource
   * @return href
   **/
  @JsonProperty("href")
  @ApiModelProperty(value = "reference to this resource")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public ServiceProblemEventRecord eventTime(Date eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  /**
   * Time at which the event occurred
   * @return eventTime
   **/
  @JsonProperty("eventTime")
  @ApiModelProperty(value = "Time at which the event occurred")
  public Date getEventTime() {
    return eventTime;
  }

  public void setEventTime(Date eventTime) {
    this.eventTime = eventTime;
  }

  public ServiceProblemEventRecord eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

  /**
   * Type of the recorded event
   * @return eventType
   **/
  @JsonProperty("eventType")
  @ApiModelProperty(value = "Type of the recorded event")
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public ServiceProblemEventRecord recordTime(Date recordTime) {
    this.recordTime = recordTime;
    return this;
  }

  /**
   * Time at which the record was created
   * @return recordTime
   **/
  @JsonProperty("recordTime")
  @ApiModelProperty(value = "Time at which the record was created")
  public Date getRecordTime() {
    return recordTime;
  }

  public void setRecordTime(Date recordTime) {
    this.recordTime = recordTime;
  }

  public ServiceProblemEventRecord notification(Any notification) {
    this.notification = notification;
    return this;
  }

  /**
   * A notification from the possible notifications for Service Problem (such as creation, status change, information required, change)
   * @return notification
   **/
  @JsonProperty("notification")
  @ApiModelProperty(value = "A notification from the possible notifications for Service Problem (such as creation, status change, information required, change)")
  public Any getNotification() {
    return notification;
  }

  public void setNotification(Any notification) {
    this.notification = notification;
  }

  public ServiceProblemEventRecord serviceProblem(ServiceProblemRef serviceProblem) {
    this.serviceProblem = serviceProblem;
    return this;
  }

  /**
   * The service problem to which this record applies
   * @return serviceProblem
   **/
  @JsonProperty("serviceProblem")
  @ApiModelProperty(value = "The service problem to which this record applies")
  public ServiceProblemRef getServiceProblem() {
    return serviceProblem;
  }

  public void setServiceProblem(ServiceProblemRef serviceProblem) {
    this.serviceProblem = serviceProblem;
  }

  public ServiceProblemEventRecord baseType(String baseType) {
    this.baseType = baseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class
   * @return baseType
   **/
  @JsonProperty("@baseType")
  @ApiModelProperty(value = "When sub-classing, this defines the super-class")
  public String getBaseType() {
    return baseType;
  }

  public void setBaseType(String baseType) {
    this.baseType = baseType;
  }

  public ServiceProblemEventRecord schemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return schemaLocation
   **/
  @JsonProperty("@schemaLocation")
  @ApiModelProperty(value = "A URI to a JSON-Schema file that defines additional attributes and relationships")
  public String getSchemaLocation() {
    return schemaLocation;
  }

  public void setSchemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
  }

  public ServiceProblemEventRecord type(String type) {
    this.type = type;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class entity name
   * @return type
   **/
  @JsonProperty("@type")
  @ApiModelProperty(value = "When sub-classing, this defines the sub-class entity name")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceProblemEventRecord serviceProblemEventRecord = (ServiceProblemEventRecord) o;
    return Objects.equals(this.id, serviceProblemEventRecord.id) &&
        Objects.equals(this.href, serviceProblemEventRecord.href) &&
        Objects.equals(this.eventTime, serviceProblemEventRecord.eventTime) &&
        Objects.equals(this.eventType, serviceProblemEventRecord.eventType) &&
        Objects.equals(this.recordTime, serviceProblemEventRecord.recordTime) &&
        Objects.equals(this.notification, serviceProblemEventRecord.notification) &&
        Objects.equals(this.serviceProblem, serviceProblemEventRecord.serviceProblem) &&
        Objects.equals(this.baseType, serviceProblemEventRecord.baseType) &&
        Objects.equals(this.schemaLocation, serviceProblemEventRecord.schemaLocation) &&
        Objects.equals(this.type, serviceProblemEventRecord.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, eventTime, eventType, recordTime, notification, serviceProblem, baseType, schemaLocation, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceProblemEventRecord {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    recordTime: ").append(toIndentedString(recordTime)).append("\n");
    sb.append("    notification: ").append(toIndentedString(notification)).append("\n");
    sb.append("    serviceProblem: ").append(toIndentedString(serviceProblem)).append("\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

