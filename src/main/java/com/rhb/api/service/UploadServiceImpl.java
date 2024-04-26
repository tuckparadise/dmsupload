package com.rhb.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.rhb.api.models.DMSFileUploadReqBody;
import com.rhb.api.models.DMSFileUploadResponseBody;
import com.rhb.api.models.DMSResHeader;
import com.rhb.api.models.DMSResponse;
import com.rhb.api.models.EAIArrProfileValue;
import com.rhb.api.models.EAIDMSReqBody;
import com.rhb.api.models.EAISDMSResponse;
import com.rhb.api.utilities.DmsValidator;
import com.rhb.api.utilities.MyNamespaceMapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class UploadServiceImpl implements UploadService {

    private static final Log LOG = LogFactory.getLog(UploadServiceImpl.class);

    @Autowired
    DmsValidator validator;

    private static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            LOG.info(e.getMessage());
        }
        return null;
    }

    private static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            dbf.setExpandEntityReferences(false);

            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            LOG.info(e.getMessage());
        }
        return null;
    }

    public ResponseEntity<DMSResponse> dmsFileUploadReq(DMSFileUploadReqBody body, String respEmail) {
        DMSResponse response = new DMSResponse();

        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(body);
            json = json.replace("\u00a0", "");
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.readValue(json, DMSFileUploadReqBody.class);

            List<DMSResHeader> validatorResponse = validator.isValidPayload(body, respEmail);
            response.setHeader(validatorResponse);

            if (!response.getHeader().isEmpty()) {
                for (DMSResHeader header : validatorResponse
                ) {
                    if (header.getErrorCode().equals("API1005")) {
                        return ResponseEntity.unprocessableEntity().body(response);
                    } else {
                        return ResponseEntity.unprocessableEntity().body(response);
                    }
                }
            }
            EAIDMSReqBody eaidmsReqBody = new EAIDMSReqBody();
            respEmail = respEmail.length() > 40 ? respEmail.substring(0, 39) : respEmail ;
            EAIArrProfileValue eaiArrProfile = new EAIArrProfileValue();
            eaiArrProfile.setApplicationReference(body.getApplicationRef());
            eaiArrProfile.setWorkflowId("MG");
            eaiArrProfile.setUUID("");
            eaiArrProfile.setDocCode(body.getDocCode());
            eaiArrProfile.setModule("SUPPDOC");
            eaiArrProfile.setSalesId("");
            eaiArrProfile.setUserId(respEmail);
            eaidmsReqBody.setArrProfileValue(eaiArrProfile);
            eaidmsReqBody.setFileContent(body.getFileContent());
            eaidmsReqBody.setStrFileName(body.getStrFileName());
            eaidmsReqBody.setUserID(body.getUserID());
            eaidmsReqBody.setPdFProperties("N");
            eaidmsReqBody.setStrFolderName("RHBRLOS");
            eaidmsReqBody.setStrProfile("RHBRLOS");

            String xml = createXmlString(eaidmsReqBody);

            LOG.info("Request to EAI : " + xml);
            String eaiResponse = callSoapService(xml);
            LOG.info("Result " + eaiResponse);
            response = setResponse(eaiResponse, response);
            if(!response.getHeader().isEmpty())
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            else
                return ResponseEntity.ok(response);
        }
          catch (JsonProcessingException e) {
                LOG.info(e.getMessage());
            }
        return ResponseEntity.ok(response);
    }

        public DMSResponse setResponse(String eaiResponse, DMSResponse response) throws JsonProcessingException {
            if (eaiResponse.isBlank() || eaiResponse.isEmpty()) {
                DMSResHeader resHeader = new DMSResHeader();
                resHeader.setErrorDesc("Back-end system is currently not available");
                resHeader.setErrorTag("");
                resHeader.setErrorMessage("Back-end system is currently not available");
                resHeader.setErrorCode("500");
                List<DMSResHeader> list = new ArrayList<>();
                list.add(resHeader);
                response.setHeader(list);
                return response;
            }
            char quote = '"';
            eaiResponse = eaiResponse.replace("<?xml version=" + quote + "1.0" + quote + " encoding=" + quote + "utf-8" + quote + " standalone=" + quote + "no" + quote + "?>", "").
                    replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", "").
                    replace("<soap:Body>", "").
                    replace(" xmlns=\"http://tempuri.org/\"", "").
                    replace("</soap:Body>", "").
                    replace("</soap:Envelope>", "").trim();
            LOG.info("Response xml" + eaiResponse);

            XmlMapper xmlMapper = new XmlMapper();
            AnnotationIntrospector aiJaxb = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
            xmlMapper.setAnnotationIntrospector(aiJaxb);
            EAISDMSResponse eaiDmsResponse = xmlMapper.readValue(eaiResponse, EAISDMSResponse.class);
            LOG.info(eaiDmsResponse.getErrorCode());
            DMSFileUploadResponseBody dmsFileUploadResponseBody = new DMSFileUploadResponseBody();
            if (eaiDmsResponse.getErrorCode().equals("0")) {
                dmsFileUploadResponseBody.setErrorCode("Success");
            } else if (eaiDmsResponse.getErrorCode().equals("1")) {
                dmsFileUploadResponseBody.setErrorCode("Error");
                dmsFileUploadResponseBody.setErrorMessage(eaiDmsResponse.getErrorMessage());
            }
            if(eaiDmsResponse.getExportResult() != null){
                if (eaiDmsResponse.getExportResult().equals("1")) {
                    dmsFileUploadResponseBody.setExportResult("Success");
                }
            }else {
                dmsFileUploadResponseBody.setExportResult(eaiDmsResponse.getErrorMessage());
            }
            response.setBody(dmsFileUploadResponseBody);
            LOG.info("Final response" + response.getBody());

            return response;
        }

    private String createXmlString(EAIDMSReqBody request) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(EAIDMSReqBody.class);
            Marshaller m = context.createMarshaller();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            m.marshal(request, doc);
            MessageFactory mfactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = mfactory.createMessage();
            SOAPBody soapBody = soapMessage.getSOAPBody();
            soapMessage.getSOAPPart().getEnvelope().addNamespaceDeclaration("tem", "http://tempuri.org/");
            soapBody.addDocument(doc);
            var baos = new ByteArrayOutputStream();
            soapMessage.writeTo(baos);
            xmlString = baos.toString(StandardCharsets.UTF_8);
            LOG.info("xmlString" + xmlString);
            xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xmlString;
        } catch (JAXBException | SOAPException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            LOG.info(e.getMessage());
        }
        return xmlString;
    }
    public String callSoapService(String soapRequest) {
        // Code to make a webservice HTTPs request
        String responseString;
        StringBuilder outputString = new StringBuilder();
        String formattedSOAPResponse = "";
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{

                    new X509TrustManager() {

                        public X509Certificate[] getAcceptedIssuers() {

                            return new X509Certificate[0];
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                            LOG.info("check");
                            if(certs == null )throw new IllegalArgumentException("Certificate is null or empty");
                            if(!authType.equalsIgnoreCase("ECDHE_RSA") ) throw new CertificateException("Certificate is not trust");
                            try {
                                certs[0].checkValidity();
                            } catch (Exception e) {
                                throw new CertificateException("Certificate is not valid or trusted");
                            }
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            if(chain == null)throw new IllegalArgumentException("Certificate is null or empty");
                            if(!authType.equalsIgnoreCase("ECDHE_RSA") ) throw new CertificateException("Certificate is not trust");
                            try {
                                chain[0].checkValidity();
                            } catch (Exception e) {
                                throw new CertificateException("Certificate is not valid or trusted");
                            }
                        }
                    }
            };

// Install the all-trusting trust manager

            SSLContext sc = SSLContext.getInstance("TLSv1.2");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(System.getProperty("dmsurl"));
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buffer = soapRequest.getBytes();
            bout.write(buffer);
            byte[] b = bout.toByteArray();
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            OutputStream out = httpConn.getOutputStream();
            // Write the content of the request to the outputstream of the HTTP Connection.
            out.write(b);
            out.close();
            // Ready with sending the request.
            // Read the response.
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(isr);
            // Write the SOAP message response to a String.
            while ((responseString = in.readLine()) != null) {
                outputString.append(responseString);
            }
            // Write the SOAP message formatted to the console.
            formattedSOAPResponse = formatXML(outputString.toString());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            LOG.info("MalformedURLException");
        }
        return formattedSOAPResponse;
    }


}
