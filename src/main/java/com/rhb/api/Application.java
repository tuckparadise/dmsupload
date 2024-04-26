package com.rhb.api;

import com.rhb.api.configuration.GetParameter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableOpenApi
@EnableWebMvc
@ComponentScan(basePackages = {"com.rhb.api", "com.rhb.api.controllers", "io.swagger.configuration"})
public class Application {

    String dataTypeErrorCode;
    String invalidErrorCode;
    String blankErrorCode;
    String lengthErrorCode;
    String invalidFormatErrorCode;
    String specialCharacterErrorCode;
    String fileFormatErrorCode;
    String invalidReqCode;
    String blankCheckErrorCode;
    String invalidDataTypeErrorCode;
    String invalidLengthErrorCode;
    String invalidFieldValueErrorCode;
    String invalidSpecialCharacterErrorCode;
    String dateMismatchErrorCode;
    String invalidFileFormat;
    String invalidReqMsg;
    String invalidFormatErrorDesc;
    String invalidRequestErrorDesc;
    String datatypeErrorDesc;
    String blankErrorDesc;
    String lengthErrorDesc;
    String invalidValueErrorDesc;
    String invalidCharacterErrorDesc;
    String invalidFileFormatErrorDesc;

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
        new Application().populateValues();
    }

    public void populateErrorResponseValues() {
        GetParameter parameter = new GetParameter();
        // error code
        fileFormatErrorCode = parameter.getParamAsString("fileFormat.Code");
        System.setProperty("fileFormatErrorCode", fileFormatErrorCode);
        dataTypeErrorCode = parameter.getParamAsString("datatype");
        System.setProperty("dataTypeErrorCode", dataTypeErrorCode);
        invalidErrorCode = parameter.getParamAsString("invalid.code");
        System.setProperty("invalidErrorCode", invalidErrorCode);
        blankErrorCode = parameter.getParamAsString("blank.code");
        System.setProperty("blankErrorCode", blankErrorCode);
        lengthErrorCode = parameter.getParamAsString("length.code");
        System.setProperty("lengthErrorCode", lengthErrorCode);
        invalidFormatErrorCode = parameter.getParamAsString("invalid.format");
        System.setProperty("invalidFormatErrorCode", invalidFormatErrorCode);
        specialCharacterErrorCode = parameter.getParamAsString("invalid.specialCharacterCode");
        System.setProperty("specialCharacterErrorCode", specialCharacterErrorCode);
        invalidReqCode = parameter.getParamAsString("invalidReqCode");
        System.setProperty("invalidReqCode", String.valueOf(invalidReqCode));

        //error message
        invalidFileFormat = parameter.getParamAsString("invalid.fileFormat");
        System.setProperty("invalidFileFormat", invalidFileFormat);
        blankCheckErrorCode = parameter.getParamAsString("blankCheck");
        System.setProperty("blankCheckErrorCode", blankCheckErrorCode);
        invalidDataTypeErrorCode = parameter.getParamAsString("invalid.dataTypeCheck");
        System.setProperty("invalidDataTypeErrorCode", invalidDataTypeErrorCode);
        invalidLengthErrorCode = parameter.getParamAsString("invalid.lengthCheck");
        System.setProperty("invalidLengthErrorCode", invalidLengthErrorCode);
        invalidFieldValueErrorCode = parameter.getParamAsString("invalid.fieldValue");
        System.setProperty("invalidFieldValueErrorCode", invalidFieldValueErrorCode);
        invalidSpecialCharacterErrorCode = parameter.getParamAsString("invalid.specialCharacter");
        System.setProperty("invalidSpecialCharacterErrorCode", invalidSpecialCharacterErrorCode);
        dateMismatchErrorCode = parameter.getParamAsString("date.mismatch");
        System.setProperty("dateMismatchErrorCode", dateMismatchErrorCode);
        invalidReqMsg = parameter.getParamAsString("invalidReqMsg");
        System.setProperty("invalidReqMsg", invalidReqMsg);

        //error description
        invalidFormatErrorDesc = parameter.getParamAsString("invalidFormatErrorDesc");
        System.setProperty("invalidFormatErrorDesc", invalidFormatErrorDesc);
        invalidRequestErrorDesc = parameter.getParamAsString("invalidRequestErrorDesc");
        System.setProperty("invalidRequestErrorDesc", invalidRequestErrorDesc);
        datatypeErrorDesc = parameter.getParamAsString("datatypeErrorDesc");
        System.setProperty("datatypeErrorDesc", datatypeErrorDesc);
        blankErrorDesc = parameter.getParamAsString("blankErrorDesc");
        System.setProperty("blankErrorDesc", blankErrorDesc);
        lengthErrorDesc = parameter.getParamAsString("lengthErrorDesc");
        System.setProperty("lengthErrorDesc", lengthErrorDesc);
        invalidValueErrorDesc = parameter.getParamAsString("invalidValueErrorDesc");
        System.setProperty("invalidValueErrorDesc", invalidValueErrorDesc);
        invalidCharacterErrorDesc = parameter.getParamAsString("invalidCharacterErrorDesc");
        System.setProperty("invalidCharacterErrorDesc", invalidCharacterErrorDesc);
        invalidFileFormatErrorDesc = parameter.getParamAsString("invalidFileFormatErrorDesc");
        System.setProperty("invalidFileFormatErrorDesc", invalidFileFormatErrorDesc);

    }

    public void populateValues() {
        GetParameter parameter = new GetParameter();
        populateErrorResponseValues();
        String dmsurl = parameter.getParamAsString("dmsurl");
        System.setProperty("dmsurl", (dmsurl));

        String fileFormatList = parameter.getParamAsString("fileFormatList");
        System.setProperty("fileFormatList", (fileFormatList));

        String docCodeList = parameter.getParamAsString("docCodeList");
        System.setProperty("docCodeList", (docCodeList));

    }
}