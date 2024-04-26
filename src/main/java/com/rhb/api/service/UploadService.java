package com.rhb.api.service;

import com.rhb.api.models.DMSFileUploadReqBody;
import com.rhb.api.models.DMSResponse;
import org.springframework.http.ResponseEntity;

public interface UploadService {
 ResponseEntity<DMSResponse> dmsFileUploadReq (DMSFileUploadReqBody body, String respEmail);

}
