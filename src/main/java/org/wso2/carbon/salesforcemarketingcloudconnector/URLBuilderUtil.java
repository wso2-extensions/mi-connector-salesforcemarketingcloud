/*
 *  Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.salesforcemarketingcloudconnector;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * The Utils class contains all the utils methods related to the connector.
 */
public class URLBuilderUtil extends AbstractConnector {

    private static final Log log = LogFactory.getLog(URLBuilderUtil.class);

    public static final Map<String, String> parameterNameMap = new HashMap<String, String>() {{

        put("columns", "columns");
        put("mobileNumber", "mobile_number");
        put("emailId", "emailId");
        put("type", "type");
        put("journeyId", "journeyId");
        put("dEExternalKey", "DE_External_Key");
        put("mobilepushMessageId", "mobilepush_message_id");
        put("etKeyword", "et_keyword");
        put("etLocationId", "et_location_id");
        put("eventDefinitionId", "eventDefinitionId");
        put("dEID", "DE_ID");
        put("xDirectPipe", "x-direct-pipe");
        put("$Pagesize", "$pagesize");
        put("assetId", "assetId");
        put("definitionKey", "definitionKey");
        put("id", "id");
        put("messageKey", "messageKey");
        put("smsMessageId", "sms_message_id");
        put("clientId", "client_id");
        put("etSmsmsgId", "et_smsmsg_id");
        put("resource", "resource");
        put("etSmstokenId", "et_smstoken_id");
        put("etKeywordId", "et_keyword_id");
        put("$OrderBy", "$orderBy");
        put("versionNumber", "versionNumber");
        put("eventDefinitionKey", "eventDefinitionKey");
        put("etSmsTokenId", "et_sms_tokenId");
        put("$Page", "$page");
        put("$Filter", "$filter");
        put("etSmsShortcode", "et_sms_shortcode");
        put("statusId", "statusId");
        put("callbackId", "callbackId");
        put("$Fields", "$fields");
        put("guid", "guid");
        put("step", "step");
        put("subscriptionId", "subscriptionId");
        put("categoryId", "categoryId");
    }};
    private static final String ENCODING = "UTF-8";
    private static final String URL_PATH = "uri.var.urlPath";

    private String operationPath = "";
    private String pathParameters = "";

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        try {
            String ttdAuthHeader = (String) messageContext.getProperty(Constants.PROPERTY_TTD_AUTH);
            if (StringUtils.isBlank(ttdAuthHeader)) {
                String errorMessage = Constants.GENERAL_ERROR_MSG + "TTD-auth header is not set.";
                setErrorPropertiesToMessage(messageContext, Constants.ErrorCodes.INVALID_CONFIG, errorMessage);
                handleException(errorMessage, messageContext);
            }

            String urlPath = getOperationPath();
            if (StringUtils.isNotEmpty(this.pathParameters)) {
                String[] pathParameterList = getPathParameters().split(",");
                for (String pathParameter : pathParameterList) {
                    String paramValue = (String) getParameter(messageContext, pathParameter);
                    if (StringUtils.isNotEmpty(paramValue)) {
                        String encodedParamValue = URLEncoder.encode(paramValue, ENCODING);
                        urlPath = urlPath.replace("{" + pathParameter + "}", encodedParamValue);
                    } else {
                        String errorMessage = Constants.GENERAL_ERROR_MSG + "Mandatory parameter '" + pathParameter + "' is not set.";
                        setErrorPropertiesToMessage(messageContext, Constants.ErrorCodes.INVALID_CONFIG, errorMessage);
                        handleException(errorMessage, messageContext);
                    }
                }
            }

            messageContext.setProperty(URL_PATH, urlPath);
        } catch (UnsupportedEncodingException e) {
            String errorMessage = Constants.GENERAL_ERROR_MSG + "Error occurred while constructing the URL query.";
            setErrorPropertiesToMessage(messageContext, Constants.ErrorCodes.GENERAL_ERROR, errorMessage);
            handleException(errorMessage, messageContext);
        }
    }

    public String getOperationPath() {
        return operationPath;
    }

    public void setOperationPath(String operationPath) {
        this.operationPath = operationPath;
    }

    public String getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(String pathParameters) {
        this.pathParameters = pathParameters;
    }

    /**
     * Sets the error code and error message in message context.
     *
     * @param messageContext Message Context
     * @param errorCode      Error Code
     * @param errorMessage   Error Message
     */
    private void setErrorPropertiesToMessage(MessageContext messageContext, String errorCode, String errorMessage) {
        messageContext.setProperty(Constants.PROPERTY_ERROR_CODE, errorCode);
        messageContext.setProperty(Constants.PROPERTY_ERROR_MESSAGE, errorMessage);
    }
}