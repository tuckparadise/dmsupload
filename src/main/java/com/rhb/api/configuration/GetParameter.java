package com.rhb.api.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

public class GetParameter {

    private static final Log LOG = LogFactory.getLog(GetParameter.class);
    String profile = System.getenv("spring.profiles.active");
    String awsRoleArn = "AWS_ROLE_ARN";
    String tokenFile = "AWS_WEB_IDENTITY_TOKEN_FILE";
    String config = "/config/";
    String flash = "/flash/";

    public JSONObject getParamAsJson(String paramName) {
        String awsArnRole = System.getenv(awsRoleArn);
        Path webIdentityTokenFilePath = Paths.get(System.getenv(tokenFile));

        Region region = Region.AP_SOUTHEAST_1;
        AwsCredentialsProvider credentials = WebIdentityTokenFileCredentialsProvider.builder()
                .roleArn(awsArnRole)
                .webIdentityTokenFile(webIdentityTokenFilePath)
                .build();

        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(credentials)
                .region(region)
                .build();

        try {

            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(config + profile + flash + paramName)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);

            String paramResponse = parameterResponse.parameter().value();
            LOG.info(paramName + " value : " + paramResponse);
            return new JSONObject(paramResponse);
        } catch (SsmException | JSONException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public String getParamAsString(String paramName) {
        String awsArnRole = System.getenv(awsRoleArn);
        Path webIdentityTokenFilePath = Paths.get(System.getenv(tokenFile));

        Region region = Region.AP_SOUTHEAST_1;
        AwsCredentialsProvider credentials = WebIdentityTokenFileCredentialsProvider.builder()
                .roleArn(awsArnRole)
                .webIdentityTokenFile(webIdentityTokenFilePath)
                .build();

        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(credentials)
                .region(region)
                .build();

        try {
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(config + profile + flash + paramName)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);

            String paramResponse = parameterResponse.parameter().value();
            LOG.info(paramName + " : value is : " + paramResponse);
            return paramResponse;
        } catch (SsmException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    public List<String> getParam(String paramName) {

        String awsArnRole = System.getenv(awsRoleArn);
        Path webIdentityTokenFilePath = Paths.get(System.getenv(tokenFile));

        Region region = Region.AP_SOUTHEAST_1;
        AwsCredentialsProvider credentials = WebIdentityTokenFileCredentialsProvider.builder()
                .roleArn(awsArnRole)
                .webIdentityTokenFile(webIdentityTokenFilePath)
                .build();

        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(credentials)
                .region(region)
                .build();

        try {

            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(config + profile + flash + paramName)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);

            String paramResponse = parameterResponse.parameter().value();
            LOG.info(paramName + " value is : " + paramResponse);
            return Arrays.asList(paramResponse.split(","));
        } catch (SsmException e) {
            LOG.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}