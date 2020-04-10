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
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Task resource that requests Service Problems to be grouped together into a parent and set of children Skipped properties: id,href
 */
@ApiModel(description = "Task resource that requests Service Problems to be grouped together into a parent and set of children Skipped properties: id,href")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-04-06T18:51:18.270Z")
public class ProblemGroupCreate   {
  @JsonProperty("childProblem")
  private List<ServiceProblemRef> childProblem = new ArrayList<ServiceProblemRef>();

  @JsonProperty("parentProblem")
  private ServiceProblemRef parentProblem = null;

  @JsonProperty("@baseType")
  private String baseType = null;

  @JsonProperty("@schemaLocation")
  private String schemaLocation = null;

  @JsonProperty("@type")
  private String type = null;

  public ProblemGroupCreate childProblem(List<ServiceProblemRef> childProblem) {
    this.childProblem = childProblem;
    return this;
  }

  public ProblemGroupCreate addChildProblemItem(ServiceProblemRef childProblemItem) {
    this.childProblem.add(childProblemItem);
    return this;
  }

  /**
   * List of problems to be grouped under a parent problem
   * @return childProblem
   **/
  @JsonProperty("childProblem")
  @ApiModelProperty(required = true, value = "List of problems to be grouped under a parent problem")
  @NotNull
 @Size(min=1)  public List<ServiceProblemRef> getChildProblem() {
    return childProblem;
  }

  public void setChildProblem(List<ServiceProblemRef> childProblem) {
    this.childProblem = childProblem;
  }

  public ProblemGroupCreate parentProblem(ServiceProblemRef parentProblem) {
    this.parentProblem = parentProblem;
    return this;
  }

  /**
   * The parent problem to which the problems are to be grouped
   * @return parentProblem
   **/
  @JsonProperty("parentProblem")
  @ApiModelProperty(required = true, value = "The parent problem to which the problems are to be grouped")
  @NotNull
  public ServiceProblemRef getParentProblem() {
    return parentProblem;
  }

  public void setParentProblem(ServiceProblemRef parentProblem) {
    this.parentProblem = parentProblem;
  }

  public ProblemGroupCreate baseType(String baseType) {
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

  public ProblemGroupCreate schemaLocation(String schemaLocation) {
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

  public ProblemGroupCreate type(String type) {
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
    ProblemGroupCreate problemGroupCreate = (ProblemGroupCreate) o;
    return Objects.equals(this.childProblem, problemGroupCreate.childProblem) &&
        Objects.equals(this.parentProblem, problemGroupCreate.parentProblem) &&
        Objects.equals(this.baseType, problemGroupCreate.baseType) &&
        Objects.equals(this.schemaLocation, problemGroupCreate.schemaLocation) &&
        Objects.equals(this.type, problemGroupCreate.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(childProblem, parentProblem, baseType, schemaLocation, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemGroupCreate {\n");
    
    sb.append("    childProblem: ").append(toIndentedString(childProblem)).append("\n");
    sb.append("    parentProblem: ").append(toIndentedString(parentProblem)).append("\n");
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

