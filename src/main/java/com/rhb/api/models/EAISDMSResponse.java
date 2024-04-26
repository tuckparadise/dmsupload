package com.rhb.api.models;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MerchantExportResponse")
public class EAISDMSResponse {

    @XmlElement(name = "MerchantExportResult")
    private String exportResult;

    @XmlElement(name = "error_code")
    private String errorCode;

    @XmlElement(name = "error_msg")
    private String errorMessage;


    @Schema(description = "Export Result")

    public String getExportResult() {
        return exportResult;
    }

    /**
     * Error Code
     * @return errorCode_
     **/
    @Schema(description = "Error Code ")

    public String getErrorCode() {
        return errorCode;
    }


    @Schema(description = "Error Message")

    public String getErrorMessage() {
        return errorMessage;
    }


}
