package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * Profile Value
 */
@Schema(description = "Profile Value")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-09T11:04:03.558293Z[GMT]")


public class ArrProfileValue   {
  @JsonProperty("applicationReference")
  private String applicationReference = null;

  @JsonProperty("docCode")
  private String docCode = null;

  public ArrProfileValue applicationReference(String applicationReference) {
    this.applicationReference = applicationReference;
    return this;
  }

  /**
   * Application Reference
   * @return applicationReference
   **/
  @Schema(required = true, description = "Application Reference")
      @NotNull

    public String getApplicationReference() {
    return applicationReference;
  }

  public void setApplicationReference(String applicationReference) {
    this.applicationReference = applicationReference;
  }

  public ArrProfileValue docCode(String docCode) {
    this.docCode = docCode;
    return this;
  }

  /**
   * Doc Code
   * @return docCode
   **/
  @Schema(required = true, description = "Doc Code")
      @NotNull

    public String getDocCode() {
    return docCode;
  }

  public void setDocCode(String docCode) {
    this.docCode = docCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArrProfileValue arrProfileValue = (ArrProfileValue) o;
    return Objects.equals(this.applicationReference, arrProfileValue.applicationReference) &&
        Objects.equals(this.docCode, arrProfileValue.docCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationReference, docCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArrProfileValue {\n");
    
    sb.append("    applicationReference: ").append(toIndentedString(applicationReference)).append("\n");
    sb.append("    docCode: ").append(toIndentedString(docCode)).append("\n");
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
