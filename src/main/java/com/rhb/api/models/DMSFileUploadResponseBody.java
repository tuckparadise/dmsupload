package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * Response Model
 */
@Schema(description = "Response Model")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-09T11:04:03.558293Z[GMT]")


public class DMSFileUploadResponseBody {
  @JsonProperty("merchantExportResult")
  private String exportResult = null;

  @JsonProperty("errorCode ")
  private String errorCode = null;

  @JsonProperty("errorMsg")
  private String errorMessage = null;

  public DMSFileUploadResponseBody exportResult(String exportResult) {
    this.exportResult = exportResult;
    return this;
  }

  /**
   * Export Result
   * @return exportResult
   *
   * @param s*/
  @Schema(description = "Export Result")
  
    public String getExportResult(String s) {
    return exportResult;
  }

  public void setExportResult(String exportResult) {
    this.exportResult = exportResult;
  }

  public DMSFileUploadResponseBody errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Error Code 
   * @return errorCode
   **/
  @Schema(description = "Error Code ")
  
    public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public DMSFileUploadResponseBody errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Error Message
   * @return errorMessage
   *
   * @param s*/
  @Schema(description = "Error Message")
  
    public String getErrorMessage(String s) {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DMSFileUploadResponseBody dmSFileUploadResponse = (DMSFileUploadResponseBody) o;
    return Objects.equals(this.exportResult, dmSFileUploadResponse.exportResult) &&
        Objects.equals(this.errorCode, dmSFileUploadResponse.errorCode) &&
        Objects.equals(this.errorMessage, dmSFileUploadResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exportResult, errorCode, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DMSFileUploadResponse {\n");
    
    sb.append("    exportResult: ").append(toIndentedString(exportResult)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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
