package com.rhb.api;
import com.rhb.api.models.DMSFileUploadReqBody;
import com.rhb.api.models.DMSResHeader;
import com.rhb.api.service.UploadService;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DmsFileUploadTest {

    List<DMSResHeader> expectedList = new ArrayList<>();

    @Autowired
    UploadService service;
    public void populatePropertyValues() {
        System.setProperty("flashErrorList", "");
        System.setProperty("pfStatusUrl", "http://172.30.84.240:8083/APIFlash");
        System.setProperty("invalidApplication", "Application not found");
        System.setProperty("invalidApplicationCode", "API1012");
        System.setProperty("blankErrorCode", "API1002");
        System.setProperty("dateFormatErrorCode", "API1008");
        System.setProperty("lengthErrorCode", "API1004");
        System.setProperty("invalidErrorCode", "API1005");
        System.setProperty("invalidTypeErrorCode", "API1003");
        System.setProperty("specialCharacterErrorCode", "API1006");
        System.setProperty("invalidValueErrorCode", "API1011");

        System.setProperty("blankCheckErrorCode", "Mandatory field cannot be blank");
        System.setProperty("invalidDataTypeErrorCode", "Incorrect field data type");
        System.setProperty("invalidLengthErrorCode", "Invalid data field length");
        System.setProperty("invalidFieldValueErrorCode", "Invalid field value");
        System.setProperty("invalidSpecialCharacterErrorCode", "Invalid Characters");
        System.setProperty("invalidDateErrorCode", "Invalid Date Value");
        System.setProperty("dateMismatchErrorCode", "Invalid File Format");
        System.setProperty("fileFormatErrorCode", "API1013");
        System.setProperty("invalidFileFormat", "Invalid File Format");

        System.setProperty("docCodeList","01,02,03,04,05,06,07,08,09,10,11,12,12,13,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,101,102,103,105,106,108,115,200,201,202,203,204,205,207,208,209,301,302,303,304,305,306,307,401,402,403,501,502,503,504,505,506,507,A001,A002,A010,A015,A020,A030,A040,A050,A060,A070,A080,A090,A100,AFDOC001,AFSD601,AML01,AML02,AML03,ASNBDOC001,ASNBDOC002,ASNBDOC003,ASNBDOC004,ASNBDOC005,ASNBDOC006,ASNBDOC007,ASNBDOC008,ASNBDOC009,ASNBDOC010,ASNBDOC011,ASNBDOC011,ASNBDOC012,ASNBDOC013,ASNBDOC014,ASNBDOC015,ASNBDOC016,ASNBDOC017,ASNBDOC018,ASNBDOC019,ASNBDOC020,ASNBDOC021,ASNBDOC022,ASNBDOC022,ASNBDOC022A,ASNBDOC023,ASNBDOC024,ASNBDOC025,ASNBDOC026,ASNBDOC027,ASNBDOC028,ASNBDOC029,ASNBDOC030,ASNBDOC031,ASNBDOC032,ASNBDOC033,ASNBDOC034,ASNBDOC035,CCDOC001,CCDOC002,CCDOC003,CCDOC005,CCDOC007,CCDOC012,CCDOC030,CCDOC031,CCDOC032,CCDOC033,CCDOC034,CCDOC035,CCDOC036,CCDOC041,CF03,CPP01,CPP02,CPP03,CPP04,CPP05,CPP06,CPP07,DEDOC01,DEDOC02,DEDOC03,DEDOC04,DEDOC05,DEDOC06,DEDOC07,DEDOC08,DEDOC09,DOCO9,DOCOD1,DOCOD2,DOCOD3,DOCOD4,DOCOD5,DOCOD6,DOCOD7,DOCOD8,DOCODFD1,DOCODFD2,DOCSM1,DOCSM2,DOCSM3,DOCSMF3,EBITDA,EPF1,HPSUPDOC1,HPSUPDOC10,HPSUPDOC11,HPSUPDOC12,HPSUPDOC13,HPSUPDOC14,HPSUPDOC15,HPSUPDOC16,HPSUPDOC17,HPSUPDOC18,HPSUPDOC19,HPSUPDOC2,HPSUPDOC20,HPSUPDOC21,HPSUPDOC22,HPSUPDOC24,HPSUPDOC3,HPSUPDOC4,HPSUPDOC5,HPSUPDOC6,HPSUPDOC7,HPSUPDOC8,HPSUPDOC9,M01,M02,M03,M04,M05,M06,M07,M08,M09,M10,M100,M11,M12,M13,M14,M15,M16,M17,M18,M19,M20,M21,M22,M23,M24,M25,M26,M26A,M27,M28,M29,M30,M31,M32,M33,M34,M35,M36,M37,M38,M39,M40,M41,M42,M43,M44,M45,M46,M47,M48,M50,M51,M55,M56,M57,M60,M61,M62,M63,M64,M65,M66,M67,M68,M69,M70,M71,M72,M73,M74,M99,MGDOC001,ODDOC2,ODRVDOC01,ODRVDOC02,ODRVDOC03,PFDOC001,PFDOC002,PFDOC003,PFDOC004,PFDOC005,PFDOC006,PFDOC007,PFDOC008,PFDOC009,PFDOC010,PFDOC011,PFDOC012,PFDOC013,PFDOC014,PFDOC015,PFDOC016,PFDOC017,PFDOC018,PFDOC019,PFDOC020,PFDOC021,PFDOC022,PFDOC023,PFDOC024,PFDOC025,PFDOC026,PFDOC027,PFDOC028,PFDOC029,PFDOC030,PFDOC031,PFDOC032,PFDOC033,PFDOC034,PFDOC035,PFDOC036,PFDOC037,PFDOC038,PFDOC039,PFDOC040,PFDOC041,PFDOC042,PFDOC043,PFDOC044,PFDOC045,PL004,PL005,PL006,PL007,PL008,PL009,PL012,PL013,PL014,PL015,PNB001,PNB002,RLSTF01,RRDOC01,RRDOC02,RRDOC03,RRDOC04,RRDOC05,SOLAD,SOLNP,STF01,STF02,SYS01,SYS02,SYS03,SYS04,SYS05,SYS06,SYS08,test");
        System.setProperty("fileFormatList","tif,tiff,png,jpg,jpeg,gif,bmp,pdf,doc,docx,xls,xlsx");

        System.setProperty("dmsurl","https://dms-uat.my.intranet.rhbgroup.com/WSSDK/WSSDK.asmx?WSDL");
    }
    public DMSFileUploadReqBody populateReqBodyValues(){
        DMSFileUploadReqBody dmsFileUploadReqBody=new DMSFileUploadReqBody();
        dmsFileUploadReqBody.setFileContent("MVfOH2rexj8U92rdT3rway4+ugiJp8qBxvrY2Ldf+9xhiseHDTAUGyxy3gG8lqHqi4s41i9crWyP5ULN01ZCR2zAek+BNdUi7SF8XUfGiXX6t6s9GuURBIN26tiHs2ycVWiviESpfwxkkEG29qjIwkeiR0ZiIxyhPNTT2YbhdF3+JLf2lnSYMsB2+PPSclgfcNIS8cWeMq5LOI7695xERclsdlkzk/T4SHD0QohE9ZGWXDbTWrMHL0dGIpOQRVY1ERWmx0L61mFwTFOkBVOcm8UIba0s33KPafD03ifJFJEm7mMQvRwuCX4Gm45NJak+ukufJIJFN9GgW7/nzll7WHRnsflXOvN9peWPqZmnFIN9hs+j4YBFsghU1D4TciRFQ/xA2zLUagShbVrvV4fhwCTFaXj9V2Hatz9MC5wAeC139Q3k69zokAYyrCchOjjXaog09lkE5hf7XcnQkuWob5aucyDCM2YMIq0Skt+XYR8Snxzm9BwhkwwPNjArQa2QRyr45Zhe9L8yGz0ZoeaVD5ysHAaPMhrCwyYuixLOlf2JOfHyGzSZ4Ez7aLqc5til5Jrz/112Nbqy5YMMS15QbZsFYoA/OIt2T3YK3VRcsVJ/ofJuc0sfjtiGR7LyDUQJdtyj7I5+XSJGqzQOycYGJBLI2Qt10H0NPh4AVF2yKKjbKzVBE8HoiOfhlGENZbwzV06gS/WW1nVuzEhYGEPUgpVUD4lFaGGYLwh9SUD4qbPOP7XE5sCq5VUWZGPU61E84ekxb08f8R7QgCrL+S7mfs/bvykydPgAzf5FTr7e8ug5a2kAjYHrlSpH9Q1dvfPuW5BTWVLZCjJfApn0fmhn88mbFeY4aY689Bf0bWGWegZO5jM9");
        dmsFileUploadReqBody.setStrFileName("sample.pdf");
        dmsFileUploadReqBody.setDocCode("HPSUPDOC5");
        dmsFileUploadReqBody.setApplicationRef("13100119723");

       return dmsFileUploadReqBody;
    }

    @DisplayName("FileContent field is null - throws error: [Mandatory field (fileContent) cannot be blank]")
    @Test
    void invalidFileContentNull() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorTag("fileContent");
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();
        body.setFileContent("");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("StrFileName field is null - throws error: [Mandatory field (strFileName) cannot be blank]")
    @Test
    void invalidStrFileNameNull() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorTag("strFileName");
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();
        body.setStrFileName("");
        Assertions.assertEquals(messageList, Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("strFileName field with invalid characters- throws error: [Invalid Characters]")
    @Test
    void invalidStrFileName() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("specialCharacterErrorCode"));
        messageList.setErrorTag("strFileName");
        messageList.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
        messageList.setErrorDesc("Provide the file name with file type separated by dot");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();

        body.setStrFileName("<>%^&.bmp");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("strFileName invalid length - throws error: [Invalid data field length]")
    @Test
    void invalidStrFileNameLength() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("lengthErrorCode"));
        messageList.setErrorTag("strFileName");
        messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
        messageList.setErrorDesc("Maximum of 100 characters are allowed");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();

        body.setStrFileName("ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127.bmp");

        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("strFileName with invalid format - throws error: [Invalid File Format]")
    @Test
    void invalidStrFileNameValue() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("fileFormatErrorCode"));
        messageList.setErrorTag("strFileName");
        messageList.setErrorMessage(System.getProperty("invalidFileFormat"));
        messageList.setErrorDesc("The file format/type is other than the allowed list");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();

        body.setStrFileName("filename3.ppp");

        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }


    @DisplayName("DocCode field is null - throws error: [Mandatory field (docCode) cannot be blank]")
    @Test
    void invalidDocCodeNull() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorTag("docCode");
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
         DMSFileUploadReqBody body = populateReqBodyValues();
        body.setDocCode("");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }



    @DisplayName("docCode invalid length - throws error: [Invalid data field length]")
    @Test
    void invalidDocCodeLength() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("lengthErrorCode"));
        messageList.setErrorTag("docCode");
        messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
        messageList.setErrorDesc("Maximum of 256 characters are allowed");
        expectedList.add(messageList);
         DMSFileUploadReqBody body = populateReqBodyValues();
        body.setDocCode("ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("docCode with invalid value - throws error: [Invalid Field Value]")
    @Test
    void invalidDocCodeValue() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("invalidErrorCode"));
        messageList.setErrorTag("docCode");
        messageList.setErrorMessage(System.getProperty("invalidFieldValueErrorCode"));
        messageList.setErrorDesc("Input data value does not exist/mismatch with the valid value");
        expectedList.add(messageList);
         DMSFileUploadReqBody body = populateReqBodyValues();
        body.setDocCode("9999");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

    @DisplayName("ApplicationRef field is null - throws error: [Mandatory field (applicationRef) cannot be blank]")
    @Test
    void invalidApplicationRefNull() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("blankErrorCode"));
        messageList.setErrorTag("applicationRef");
        messageList.setErrorMessage(System.getProperty("blankCheckErrorCode"));
        messageList.setErrorDesc("Provide the correct value for this mandatory field");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();
        body.setApplicationRef("");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).  getHeader().get(0));
    }



    @DisplayName("applicationRef invalid length - throws error: [Invalid data field length]")
    @Test
    void invalidApplicationRefLength() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("lengthErrorCode"));
        messageList.setErrorTag("applicationRef");
        messageList.setErrorMessage(System.getProperty("invalidLengthErrorCode"));
        messageList.setErrorDesc("Maximum of 256 characters are allowed");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();
        body.setApplicationRef("ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127ahahgshsjajkashjashj1712878712762677272091901887127");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).  getHeader().get(0));
    }

    @DisplayName("applicationRef field with invalid characters- throws error: [Invalid Characters]")
    @Test
    void invalidApplicationRefType() {
        DMSResHeader messageList = new DMSResHeader ();
 populatePropertyValues();
        messageList.setErrorCode(System.getProperty("specialCharacterErrorCode"));
        messageList.setErrorTag("applicationRef");
        messageList.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
        messageList.setErrorDesc("Only alphabets & numbers are allowed");
        expectedList.add(messageList);
        DMSFileUploadReqBody body = populateReqBodyValues();
        body.setApplicationRef("@#$$");
        Assertions.assertEquals(expectedList.get(0), Objects.requireNonNull(service.dmsFileUploadReq(body, "abc@gmail.com").getBody()).getHeader().get(0));
    }

}
