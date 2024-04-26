package com.rhb.api.models;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Flash Req body
 */
@Schema(description = "Flash Req body")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-09T11:33:49.249534Z[GMT]")

@XmlRootElement(name = "MerchantExport", namespace = "http://tempuri.org/")
@XmlAccessorType(XmlAccessType.FIELD)
public class EAIDMSReqBody {

    @XmlElement(name = "FileContent", namespace = "http://tempuri.org/")
    private String fileContent = null;

    @XmlElement(name = "strFileName", namespace = "http://tempuri.org/")
    private String strFileName = null;

    @XmlElement(name = "strProfile", namespace = "http://tempuri.org/")
    private String strProfile = "RHBRLOS";

    @XmlElement(name = "strFolderName", namespace = "http://tempuri.org/")
    private String strFolderName = "RHBRLOS";

    @XmlElement(name = "arrProfileValue", namespace = "http://tempuri.org/")
    private EAIArrProfileValue arrProfileValue = null;

    @XmlElement(name = "userID", namespace = "http://tempuri.org/")
    private String userID = null;

    @XmlElement(name = "PDFProperties", namespace = "http://tempuri.org/")
    private String pdFProperties = "N";

    public EAIDMSReqBody fileContent(String fileContent) {
        this.fileContent = fileContent;
        return this;
    }

    /**
     * FileContent
     *
     * @return fileContent
     **/
    @Schema(required = true, description = "FileContent")
    @NotNull

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public EAIDMSReqBody strFileName(String strFileName) {
        this.strFileName = strFileName;
        return this;
    }

    /**
     * FileName
     *
     * @return strFileName
     **/
    @Schema(required = true, description = "FileName")
    @NotNull

    public String getStrFileName() {
        return strFileName;
    }

    public void setStrFileName(String strFileName) {
        this.strFileName = strFileName;
    }

    public EAIDMSReqBody strProfile(String strProfile) {
        this.strProfile = strProfile;
        return this;
    }

    /**
     * Profile
     *
     * @return strProfile
     **/
    @Schema(description = "Profile")

    public String getStrProfile() {
        return strProfile;
    }

    public void setStrProfile(String strProfile) {
        this.strProfile = strProfile;
    }

    public EAIDMSReqBody strFolderName(String strFolderName) {
        this.strFolderName = strFolderName;
        return this;
    }

    /**
     * Folder Name
     *
     * @return strFolderName
     **/
    @Schema(description = "Folder Name")

    public String getStrFolderName() {
        return strFolderName;
    }

    public void setStrFolderName(String strFolderName) {
        this.strFolderName = strFolderName;
    }

    public EAIDMSReqBody arrProfileValue(EAIArrProfileValue arrProfileValue) {
        this.arrProfileValue = arrProfileValue;
        return this;
    }


    /**
     * ProfileValue
     *
     * @return arrProfileValue
     **/
    @Schema(description = "ProfileValue")
    @Valid
    public EAIArrProfileValue getArrProfileValue() {
        return arrProfileValue;
    }

    public void setArrProfileValue(EAIArrProfileValue arrProfileValue) {
        this.arrProfileValue = arrProfileValue;
    }

    public EAIDMSReqBody userID(String userID) {
        this.userID = userID;
        return this;
    }

    /**
     * userID
     *
     * @return userID
     **/
    @Schema(description = "userID")

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public EAIDMSReqBody pdFProperties(String pdFProperties) {
        this.pdFProperties = pdFProperties;
        return this;
    }

    /**
     * PDFProperties
     *
     * @return pdFProperties
     **/
    @Schema(description = "PDFProperties")

    public String getPdFProperties() {
        return pdFProperties;
    }

    public void setPdFProperties(String pdFProperties) {
        this.pdFProperties = pdFProperties;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EAIDMSReqBody eaIDMSReqBody = (EAIDMSReqBody) o;
        return Objects.equals(this.fileContent, eaIDMSReqBody.fileContent) &&
                Objects.equals(this.strFileName, eaIDMSReqBody.strFileName) &&
                Objects.equals(this.strProfile, eaIDMSReqBody.strProfile) &&
                Objects.equals(this.strFolderName, eaIDMSReqBody.strFolderName) &&
                Objects.equals(this.arrProfileValue, eaIDMSReqBody.arrProfileValue) &&
                Objects.equals(this.userID, eaIDMSReqBody.userID) &&
                Objects.equals(this.pdFProperties, eaIDMSReqBody.pdFProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileContent, strFileName, strProfile, strFolderName, arrProfileValue, userID, pdFProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EAIDMSReqBody {\n");

        sb.append("    fileContent: ").append(toIndentedString(fileContent)).append("\n");
        sb.append("    strFileName: ").append(toIndentedString(strFileName)).append("\n");
        sb.append("    strProfile: ").append(toIndentedString(strProfile)).append("\n");
        sb.append("    strFolderName: ").append(toIndentedString(strFolderName)).append("\n");
        sb.append("    arrProfileValue: ").append(toIndentedString(arrProfileValue)).append("\n");
        sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
        sb.append("    pdFProperties: ").append(toIndentedString(pdFProperties)).append("\n");
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
