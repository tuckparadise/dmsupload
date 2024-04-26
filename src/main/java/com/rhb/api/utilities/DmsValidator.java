package com.rhb.api.utilities;

import com.rhb.api.models.DMSFileUploadReqBody;
import com.rhb.api.models.DMSResHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

//GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
import org.apache.commons.lang3.StringUtils;

@Component
public class DmsValidator {


    private static final Log LOG = LogFactory.getLog(DmsValidator.class);

    String blankErrorCode = "blankErrorCode";
    String strApplicationRef= "applicationRef";
    String strFileName = "strFileName";
    String blankCheckErrorCode = "blankCheckErrorCode";
    String blankErrorDesc = "blankErrorDesc";
    String strDocCode = "docCode";
    String lengthErrorDesc = "lengthErrorDesc";
    String lengthErrorCode = "lengthErrorCode";
    String invalidLengthErrorCode = "invalidLengthErrorCode";
    String strUserID= "userID";

    public List<DMSResHeader> isValidPayload(DMSFileUploadReqBody body, String respEmail) {
        List<DMSResHeader> list = new ArrayList<>();

        if 
        (
            Boolean.TRUE.equals
            (
                validateFileContent(body, list) &&
                validateStrFileName(body, list) &&
                validateApplicationReference(body.getApplicationRef(), list) &&
                isValidRespEmail(list, respEmail) &&
                validateDocCode(body.getDocCode(), list) && 
                validateUserID(body.getUserID(), list)
            )
        )
            return list;
        return list;
    }
    
    public boolean isValidRespEmail(List<DMSResHeader> list, String respEmail) {
        if (respEmail == null || respEmail.isEmpty() || respEmail.isBlank()) {
            DMSResHeader messageList = new DMSResHeader();
            messageList.setErrorCode(System.getProperty(blankErrorCode));
            messageList.setErrorMessage(System.getProperty(blankCheckErrorCode));
            messageList.setErrorTag("respEmail");
            messageList.setErrorDesc(System.getProperty(blankErrorDesc));            
            list.add(messageList);
        }
        return true;
    }


    public boolean validateDocCode(String docCode, List<DMSResHeader> list) {

        //if (docCode == null || docCode.isBlank() || docCode.isEmpty()) {
        //GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
        if (StringUtils.isBlank(docCode)) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(blankErrorCode));
            dmsResHeader.setErrorTag(strDocCode);
            dmsResHeader.setErrorMessage(System.getProperty(blankCheckErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(dmsResHeader);
        } else if (docCode.length() > 200) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(lengthErrorCode));
            dmsResHeader.setErrorTag(strDocCode);
            dmsResHeader.setErrorMessage(System.getProperty(invalidLengthErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(lengthErrorDesc));
            list.add(dmsResHeader);
        } else if (!Arrays.asList(System.getProperty("docCodeList").split(",")).contains(docCode)) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty("invalidErrorCode"));
            dmsResHeader.setErrorTag(strDocCode);
            dmsResHeader.setErrorMessage(System.getProperty("invalidFieldValueErrorCode"));
            dmsResHeader.setErrorDesc(System.getProperty("invalidValueErrorDesc"));
            list.add(dmsResHeader);
        }
        return true;
    }

    public boolean validateFileContent(DMSFileUploadReqBody dmsFileUploadReqBody, List<DMSResHeader> list) {        
        //if ( dmsFileUploadReqBody.getFileContent().isEmpty() || dmsFileUploadReqBody.getFileContent().isBlank()) {
        //GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
        if (StringUtils.isBlank(dmsFileUploadReqBody.getFileContent())) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(blankErrorCode));
            dmsResHeader.setErrorTag("fileContent");
            dmsResHeader.setErrorMessage(System.getProperty(blankCheckErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(dmsResHeader);
        }
        return true;

    }

    public boolean validateStrFileName(DMSFileUploadReqBody dmsFileUploadReqBody, List<DMSResHeader> list) {
        String strPattern = "[/\\\\:*?\"<>|]";
        //if (dmsFileUploadReqBody.getStrFileName().isBlank() || dmsFileUploadReqBody.getStrFileName().isEmpty()) {
        //GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
        if (StringUtils.isBlank(dmsFileUploadReqBody.getStrFileName())) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(blankErrorCode));
            dmsResHeader.setErrorTag(strFileName);
            dmsResHeader.setErrorMessage(System.getProperty(blankCheckErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(dmsResHeader);
        } else if (!dmsFileUploadReqBody.getStrFileName().isBlank() && !dmsFileUploadReqBody.getStrFileName().isEmpty()) {
            Pattern p = Pattern.compile(strPattern);
            Matcher m = p.matcher(dmsFileUploadReqBody.getStrFileName());
            if (!dmsFileUploadReqBody.getStrFileName().matches("^[^.]+\\.[^.]+$")) {
                DMSResHeader dmsResHeader = new DMSResHeader();
                dmsResHeader.setErrorCode(System.getProperty("invalidFormatErrorCode"));
                dmsResHeader.setErrorTag(strFileName);
                dmsResHeader.setErrorMessage(System.getProperty("dateMismatchErrorCode"));
                dmsResHeader.setErrorDesc(System.getProperty("invalidFormatErrorDesc"));
                list.add(dmsResHeader);
            } else if (m.find()) {
                DMSResHeader dmsResHeader = new DMSResHeader();
                dmsResHeader.setErrorCode(System.getProperty("specialCharacterErrorCode"));
                dmsResHeader.setErrorTag(strFileName);
                dmsResHeader.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
                dmsResHeader.setErrorDesc(System.getProperty("invalidCharacterErrorDesc"));
                list.add(dmsResHeader);
            } else {
                String[] fileFormat = dmsFileUploadReqBody.getStrFileName().split("\\.");
                LOG.info("file format" + fileFormat[1]);
                if (dmsFileUploadReqBody.getStrFileName().length() > 100) {
                    DMSResHeader dmsResHeader = new DMSResHeader();
                    dmsResHeader.setErrorCode(System.getProperty(lengthErrorCode));
                    dmsResHeader.setErrorTag(strFileName);
                    dmsResHeader.setErrorMessage(System.getProperty(invalidLengthErrorCode));
                    dmsResHeader.setErrorDesc(System.getProperty(lengthErrorDesc));
                    list.add(dmsResHeader);
                } else if (!Arrays.asList(System.getProperty("fileFormatList").split(",")).contains(fileFormat[1])) {
                    DMSResHeader dmsResHeader = new DMSResHeader();
                    dmsResHeader.setErrorCode(System.getProperty("fileFormatErrorCode"));
                    dmsResHeader.setErrorTag(strFileName);
                    dmsResHeader.setErrorMessage(System.getProperty("invalidFileFormat"));
                    dmsResHeader.setErrorDesc(System.getProperty("invalidFileFormatErrorDesc"));
                    list.add(dmsResHeader);
                }
            }
        }
        return true;
    }

    public boolean validateApplicationReference(String applicationRef, List<DMSResHeader> list) {
        //if (applicationRef == null || applicationRef.isEmpty() || applicationRef.isBlank()) {
        //GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
        if (StringUtils.isBlank(applicationRef)) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(blankErrorCode));
            dmsResHeader.setErrorTag(strApplicationRef);
            dmsResHeader.setErrorMessage(System.getProperty(blankCheckErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(dmsResHeader);
        } else if (!applicationRef.matches("^[a-zA-Z0-9]+$")) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty("specialCharacterErrorCode"));
            dmsResHeader.setErrorTag(strApplicationRef);
            dmsResHeader.setErrorMessage(System.getProperty("invalidSpecialCharacterErrorCode"));
            dmsResHeader.setErrorDesc(System.getProperty("invalidCharacterErrorDesc"));
            list.add(dmsResHeader);
        } else if (applicationRef.length() > 200) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(lengthErrorCode));
            dmsResHeader.setErrorTag(strApplicationRef);
            dmsResHeader.setErrorMessage(System.getProperty(invalidLengthErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(lengthErrorDesc));
            list.add(dmsResHeader);
        }
        return true;
    }

    public boolean validateUserID(String userID, List<DMSResHeader> list) {
        //GDTAPI-2902: use StringUtils.isEmpty to check for null value in payload for AF ms 
        if (StringUtils.isBlank(userID)) {
        //if (userID == null || userID.isEmpty() || userID.isBlank()) {
            DMSResHeader dmsResHeader = new DMSResHeader();
            dmsResHeader.setErrorCode(System.getProperty(blankErrorCode));
            dmsResHeader.setErrorTag(strUserID);
            dmsResHeader.setErrorMessage(System.getProperty(blankCheckErrorCode));
            dmsResHeader.setErrorDesc(System.getProperty(blankErrorDesc));
            list.add(dmsResHeader);
        } 
        return true;
    }
}
