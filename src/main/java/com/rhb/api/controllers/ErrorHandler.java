package com.rhb.api.controllers;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.rhb.api.models.DMSResHeader;
import com.rhb.api.models.DMSResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ErrorHandler {

    private static final Log LOG = LogFactory.getLog(ErrorHandler.class);
    String invalidRequestErrorDesc= "invalidRequestErrorDesc";
    String invalidReqCode= "invalidReqCode";
    String invalidReqMsg= "invalidReqMsg";

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAllUncaughtException(
            NullPointerException exception,
            WebRequest request) {
        exception.printStackTrace();
        DMSResponse response = new DMSResponse();
        DMSResHeader header = new DMSResHeader();
        List<DMSResHeader> list = new ArrayList<>();
        header.setErrorDesc(System.getProperty(invalidRequestErrorDesc));
        header.setErrorCode(System.getProperty(invalidReqCode));
        header.setErrorMessage(System.getProperty(invalidReqMsg));
        header.setErrorTag("Request Payload");
        list.add(header);
        response.setHeader(list);
        Gson gson = new Gson();
        return gson.toJson(response);
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        DMSResponse response = new DMSResponse();
        Gson gson = new Gson();
        List<DMSResHeader> list = new ArrayList<>();
        exception.printStackTrace();
        DMSResHeader messageList = new DMSResHeader();
        if(exception.contains(UnrecognizedPropertyException.class) ||
                exception.getLocalizedMessage().contains("JsonParseException") ||
                exception.getLocalizedMessage().contains("JsonMappingException") ||
                exception.getLocalizedMessage().contains("HttpMessageNotReadableException") ||
                exception.getLocalizedMessage().contains("expected a value")){
            messageList.setErrorCode(System.getProperty(invalidReqCode));
            messageList.setErrorMessage(System.getProperty(invalidReqMsg));
            messageList.setErrorTag("Json Format Error");
            messageList.setErrorDesc(System.getProperty(invalidRequestErrorDesc));
            list.add(messageList);
            response.setHeader(list);
            return gson.toJson(response);
        }
        if (exception.contains(UnrecognizedPropertyException.class)) {
            messageList.setErrorCode(System.getProperty(invalidReqCode));
            messageList.setErrorMessage(System.getProperty(invalidReqMsg));
            messageList.setErrorTag("Request Payload");
            messageList.setErrorDesc(System.getProperty(invalidRequestErrorDesc));
            list.add(messageList);
            response.setHeader(list);

            return gson.toJson(response);
        }


        String[] errorArray = exception.getLocalizedMessage().split("->");
        LOG.info(errorArray.length);
        if (errorArray.length > 0) {
            String error = errorArray[errorArray.length - 1];
            String[] errorArr = error.split("\"");
            if (errorArr.length > 1) {
                messageList.setErrorMessage(System.getProperty("invalidDataTypeErrorCode"));
                messageList.setErrorCode(System.getProperty("dataTypeErrorCode"));
                messageList.setErrorDesc(System.getProperty("datatypeErrorDesc"));
                messageList.setErrorTag(errorArr[1]);
            }
            else {
                messageList.setErrorCode(System.getProperty(invalidReqCode));
                messageList.setErrorMessage(System.getProperty(invalidReqMsg));
                messageList.setErrorTag("Json Format Error");
                messageList.setErrorDesc(System.getProperty(invalidRequestErrorDesc));
            }
        }

        list.add(messageList);
        response.setHeader(list);

        return gson.toJson(response);
    }
}
