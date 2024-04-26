package com.rhb.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

/**
 * EAI Mapping Field &lt;Header Response&gt;
 */
@Schema(description = "EAI Mapping Field <Header Response>")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-12-15T01:00:23.343319Z[GMT]")

public class DmsErrorResponse {
    @JsonProperty("errorCode")
    private String errorCode = null;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorTag() {
        return errorTag;
    }

    public void setErrorTag(String errorTag) {
        this.errorTag = errorTag;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @JsonProperty("errorMessage")
    private String errorMessage = null;

    @JsonProperty("errorTag")
    private String errorTag = null;

    @JsonProperty("errorDesc")
    private String errorDesc = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DmsErrorResponse AFSubmissionResHeader = (DmsErrorResponse) o;
        return Objects.equals(this.errorTag, AFSubmissionResHeader.errorTag) &&
                Objects.equals(this.errorDesc, AFSubmissionResHeader.errorDesc) &&
                Objects.equals(this.errorCode, AFSubmissionResHeader.errorCode) &&
                Objects.equals(this.errorMessage, AFSubmissionResHeader.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorTag, errorDesc, errorCode, errorMessage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AFSubmissionResHeader {\n");

        sb.append("    errorTag: ").append(toIndentedString(errorTag)).append("\n");
        sb.append("    errorDesc: ").append(toIndentedString(errorDesc)).append("\n");
        sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
        sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
