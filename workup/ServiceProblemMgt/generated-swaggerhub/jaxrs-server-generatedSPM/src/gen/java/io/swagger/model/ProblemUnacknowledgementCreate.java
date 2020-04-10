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
import io.swagger.model.ServiceProblemRef;
import io.swagger.model.TrackingRecord;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Task resource that requests unacknowledgement of problems, rolling back the status of the problems from Acknowledged to Submitted. Skipped properties: id,href
 */
@ApiModel(description = "Task resource that requests unacknowledgement of problems, rolling back the status of the problems from Acknowledged to Submitted. Skipped properties: id,href")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemUnacknowledgementCreate   {
  @JsonProperty("problem")
  private List<ServiceProblemRef> problem = new ArrayList<ServiceProblemRef>();

  @JsonProperty("trackingRecord")
  private TrackingRecord trackingRecord = null;

  @JsonProperty("unackProblem")
  private List<ServiceProblemRef> unackProblem = null;

  @JsonProperty("@baseType")
  private String baseType = null;

  @JsonProperty("@schemaLocation")
  private String schemaLocation = null;

  @JsonProperty("@type")
  private String type = null;

  public ProblemUnacknowledgementCreate problem(List<ServiceProblemRef> problem) {
    this.problem = problem;
    return this;
  }

  public ProblemUnacknowledgementCreate addProblemItem(ServiceProblemRef problemItem) {
    this.problem.add(problemItem);
    return this;
  }

  /**
   * The problems to be unacknowledged, relevant in the input to this task
   * @return problem
   **/
  @JsonProperty("problem")
  @ApiModelProperty(required = true, value = "The problems to be unacknowledged, relevant in the input to this task")
  @NotNull
 @Size(min=1)  public List<ServiceProblemRef> getProblem() {
    return problem;
  }

  public void setProblem(List<ServiceProblemRef> problem) {
    this.problem = problem;
  }

  public ProblemUnacknowledgementCreate trackingRecord(TrackingRecord trackingRecord) {
    this.trackingRecord = trackingRecord;
    return this;
  }

  /**
   * A record of the action taken in this acknowledgement
   * @return trackingRecord
   **/
  @JsonProperty("trackingRecord")
  @ApiModelProperty(value = "A record of the action taken in this acknowledgement")
  public TrackingRecord getTrackingRecord() {
    return trackingRecord;
  }

  public void setTrackingRecord(TrackingRecord trackingRecord) {
    this.trackingRecord = trackingRecord;
  }

  public ProblemUnacknowledgementCreate unackProblem(List<ServiceProblemRef> unackProblem) {
    this.unackProblem = unackProblem;
    return this;
  }

  public ProblemUnacknowledgementCreate addUnackProblemItem(ServiceProblemRef unackProblemItem) {
    if (this.unackProblem == null) {
      this.unackProblem = new ArrayList<ServiceProblemRef>();
    }
    this.unackProblem.add(unackProblemItem);
    return this;
  }

  /**
   * The problems that were unacknowledged, populated in the output to this task
   * @return unackProblem
   **/
  @JsonProperty("unackProblem")
  @ApiModelProperty(value = "The problems that were unacknowledged, populated in the output to this task")
  public List<ServiceProblemRef> getUnackProblem() {
    return unackProblem;
  }

  public void setUnackProblem(List<ServiceProblemRef> unackProblem) {
    this.unackProblem = unackProblem;
  }

  public ProblemUnacknowledgementCreate baseType(String baseType) {
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

  public ProblemUnacknowledgementCreate schemaLocation(String schemaLocation) {
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

  public ProblemUnacknowledgementCreate type(String type) {
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
    ProblemUnacknowledgementCreate problemUnacknowledgementCreate = (ProblemUnacknowledgementCreate) o;
    return Objects.equals(this.problem, problemUnacknowledgementCreate.problem) &&
        Objects.equals(this.trackingRecord, problemUnacknowledgementCreate.trackingRecord) &&
        Objects.equals(this.unackProblem, problemUnacknowledgementCreate.unackProblem) &&
        Objects.equals(this.baseType, problemUnacknowledgementCreate.baseType) &&
        Objects.equals(this.schemaLocation, problemUnacknowledgementCreate.schemaLocation) &&
        Objects.equals(this.type, problemUnacknowledgementCreate.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(problem, trackingRecord, unackProblem, baseType, schemaLocation, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemUnacknowledgementCreate {\n");
    
    sb.append("    problem: ").append(toIndentedString(problem)).append("\n");
    sb.append("    trackingRecord: ").append(toIndentedString(trackingRecord)).append("\n");
    sb.append("    unackProblem: ").append(toIndentedString(unackProblem)).append("\n");
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

