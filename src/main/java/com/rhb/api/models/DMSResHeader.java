package com.rhb.api.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

/**
 * CC Submission Response Header
 */
@Schema(description = "CC Submission Response Header")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-05-26T11:08:28.485469Z[GMT]")


public class DMSResHeader   {

    @JsonProperty("errorCode")
    private String errorCode = null;

    @JsonProperty("errorTag")
    private String errorTag = null;

    @JsonProperty("errorMsg")
    private String errorMessage = null;

    @JsonProperty("errorDesc")
    private String errorDesc = null;

    public DMSResHeader errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Error Code
     * @return errorCode
     **/
    @Schema(required = true, description = "Error Code")
    @NotNull

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DMSResHeader errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Error Message
     * @return errorMessage
     **/
    @Schema(required = true, description = "Error Message")
    @NotNull

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DMSResHeader errorTag(String errorTag) {
        this.errorTag = errorTag;
        return this;
    }

    /**
     * Error Tag
     * @return errorTag
     **/
    @Schema(required = true, description = "Error Tag")
    @NotNull

    public String getErrorTag() {
        return errorTag;
    }

    public void setErrorTag(String errorTag) {
        this.errorTag = errorTag;
    }

    public DMSResHeader errorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
        return this;
    }

    /**
     * Error Desc
     * @return errorDesc
     **/
    @Schema(required = true, description = "Error Desc")
    @NotNull

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DMSResHeader ccSubmissionResponseHeader = (DMSResHeader) o;
        return Objects.equals(this.errorCode, ccSubmissionResponseHeader.errorCode) &&
                Objects.equals(this.errorMessage, ccSubmissionResponseHeader.errorMessage) &&
                Objects.equals(this.errorTag, ccSubmissionResponseHeader.errorTag) &&
                Objects.equals(this.errorDesc, ccSubmissionResponseHeader.errorDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessage, errorTag, errorDesc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DMSResHeader {\n");

        sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
        sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
        sb.append("    errorTag: ").append(toIndentedString(errorTag)).append("\n");
        sb.append("    errorDesc: ").append(toIndentedString(errorDesc)).append("\n");
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
