package com.rhb.api.models;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * ProfileValue
 */
@Schema(description = "ProfileValue")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-09T11:33:49.249534Z[GMT]")

@XmlAccessorType(XmlAccessType.FIELD)
public class EAIArrProfileValue {

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String applicationReference = null;

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String workflowId = "MG";

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String uUID = null;

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String docCode = null;

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String module = "SUPPCODE";

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String salesId = null;

    @XmlElement(name = "string", namespace = "http://tempuri.org/")
    private String userId = null;

    public EAIArrProfileValue applicationReference(String applicationReference) {
        this.applicationReference = applicationReference;
        return this;
    }

    /**
     * Application Reference
     *
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

    public EAIArrProfileValue workflowId(String workflowId) {
        this.workflowId = workflowId;
        return this;
    }

    /**
     * Workflow Id
     *
     * @return workflowId
     **/
    @Schema(description = "Workflow Id")

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public EAIArrProfileValue uUID(String uUID) {
        this.uUID = uUID;
        return this;
    }

    /**
     * UUID
     *
     * @return uUID
     **/
    @Schema(description = "UUID")

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public EAIArrProfileValue docCode(String docCode) {
        this.docCode = docCode;
        return this;
    }

    /**
     * Doc Code
     *
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

    public EAIArrProfileValue module(String module) {
        this.module = module;
        return this;
    }

    /**
     * Module
     *
     * @return module
     **/
    @Schema(description = "Module")

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public EAIArrProfileValue salesId(String salesId) {
        this.salesId = salesId;
        return this;
    }

    /**
     * Sales Id
     *
     * @return salesId
     **/
    @Schema(description = "Sales Id")

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public EAIArrProfileValue userId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * User Id
     *
     * @return userId
     **/
    @Schema(description = "User Id")

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EAIArrProfileValue eaIArrProfileValue = (EAIArrProfileValue) o;
        return Objects.equals(this.applicationReference, eaIArrProfileValue.applicationReference) &&
                Objects.equals(this.workflowId, eaIArrProfileValue.workflowId) &&
                Objects.equals(this.uUID, eaIArrProfileValue.uUID) &&
                Objects.equals(this.docCode, eaIArrProfileValue.docCode) &&
                Objects.equals(this.module, eaIArrProfileValue.module) &&
                Objects.equals(this.salesId, eaIArrProfileValue.salesId) &&
                Objects.equals(this.userId, eaIArrProfileValue.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationReference, workflowId, uUID, docCode, module, salesId, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EAIArrProfileValue {\n");

        sb.append("    applicationReference: ").append(toIndentedString(applicationReference)).append("\n");
        sb.append("    workflowId: ").append(toIndentedString(workflowId)).append("\n");
        sb.append("    uUID: ").append(toIndentedString(uUID)).append("\n");
        sb.append("    docCode: ").append(toIndentedString(docCode)).append("\n");
        sb.append("    module: ").append(toIndentedString(module)).append("\n");
        sb.append("    salesId: ").append(toIndentedString(salesId)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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
