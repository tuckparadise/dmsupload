package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * DMS File Upload Request Body
 */
@Schema(description = "DMS File Upload Request Body")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-09T11:04:03.558293Z[GMT]")


public class DMSFileUploadReqBody   {
  @JsonProperty("fileContent")
  private String fileContent = null;

  @JsonProperty("strFileName")
  private String strFileName = null;

  @JsonProperty("docCode")
  private  String docCode= null;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  @JsonProperty("userID")
  private  String userID= null;

  public String getDocCode() {
    return docCode;
  }

  public void setDocCode(String docCode) {
    this.docCode = docCode;
  }

  public String getApplicationRef() {
    return applicationRef;
  }

  public void setApplicationRef(String applicationRef) {
    this.applicationRef = applicationRef;
  }

  @JsonProperty("applicationRef")
private String applicationRef= null;

  public DMSFileUploadReqBody fileContent(String fileContent) {
    this.fileContent = fileContent;
    return this;
  }

  /**
   * File Content
   * @return fileContent
   **/
  @Schema(required = true, description = "File Content")
      @NotNull

    public String getFileContent() {
    return fileContent;
  }

  public void setFileContent(String fileContent) {
    this.fileContent = fileContent;
  }

  public DMSFileUploadReqBody strFileName(String strFileName) {
    this.strFileName = strFileName;
    return this;
  }

  /**
   * File Name
   * @return strFileName
   **/
  @Schema(required = true, description = "File Name")
      @NotNull

    public String getStrFileName() {
    return strFileName;
  }

  public void setStrFileName(String strFileName) {
    this.strFileName = strFileName;
  }




  /**
   * ProfileValue
   * @return arrProfileValue
   **/


  @Override
  public boolean equals(java.lang.Object o) 
  {
    if (this == o) 
    {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DMSFileUploadReqBody dmSFileUploadReqBody = (DMSFileUploadReqBody) o;
    
    return Objects.equals(this.fileContent, dmSFileUploadReqBody.fileContent) &&
            Objects.equals(this.strFileName, dmSFileUploadReqBody.strFileName) &&
            Objects.equals(this.userID, dmSFileUploadReqBody.userID) 
            ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileContent, strFileName, userID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DMSFileUploadReqBody {\n");
    
    sb.append("    fileContent: ").append(toIndentedString(fileContent)).append("\n");
    sb.append("    strFileName: ").append(toIndentedString(strFileName)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    docCode: ").append(toIndentedString(docCode)).append("\n");
    sb.append("    applicationRef: ").append(toIndentedString(applicationRef)).append("\n");
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
