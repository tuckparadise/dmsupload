package com.rhb.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DMSResponse {

    public List<DMSResHeader> getHeader() {
        return header;
    }

    public void setHeader(List<DMSResHeader> header) {
        this.header = header;
    }

    public DMSFileUploadResponseBody getBody() {
        return body;
    }

    public void setBody(DMSFileUploadResponseBody body) {
        this.body = body;
    }

    @JsonProperty("header")
    private List<DMSResHeader> header = null;

    @JsonProperty("Body")
    private DMSFileUploadResponseBody body = null;
}
