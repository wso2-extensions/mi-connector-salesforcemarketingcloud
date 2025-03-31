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

package org.wso2.carbon.salesforce.marketingcloud.connector;

import org.apache.commons.lang3.StringUtils;
import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;

import java.util.LinkedHashSet;
import java.util.Set;

public class PayloadBuilder extends AbstractConnector {

    public static final String INLINE = "INLINE";
    public static final String INPUT_STRUCTURE = "inputStructure";
    public static final String PAYLOAD = "payload";
    public static final String NORMALIZED_PARAMETERS = "normalized.parameters";
    private String payloadParameters = "";
    private String numericParameters = "";
    private String jsonParameters = "";

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {

        String inputStructureValue = (String) getParameter(messageContext, INPUT_STRUCTURE);
        if (StringUtils.isNotEmpty(inputStructureValue)
                && INLINE.equalsIgnoreCase(inputStructureValue)) {

            String inlinePayload = (String) getParameter(messageContext, PAYLOAD);
            if (StringUtils.isNotEmpty(inlinePayload)) {
                messageContext.setProperty(NORMALIZED_PARAMETERS, inlinePayload);
            }
            return;
        }

        Set<String> allParams = parseCSV(payloadParameters);
        Set<String> numericSet = parseCSV(numericParameters);
        Set<String> jsonSet = parseCSV(jsonParameters);

        StringBuilder jsonBuilder = new StringBuilder("{");
        boolean firstProperty = true;

        for (String paramName : allParams) {
            if (PAYLOAD.equals(paramName) || INPUT_STRUCTURE.equals(paramName)) {
                continue;
            }
            String paramValue = (String) getParameter(messageContext, paramName);
            if (StringUtils.isNotEmpty(paramValue)) {
                if (!firstProperty) {
                    jsonBuilder.append(",");
                } else {
                    firstProperty = false;
                }

                jsonBuilder.append("\"").append(paramName).append("\":");

                if (jsonSet.contains(paramName)) {
                    paramValue = removeEnclosingSingleQuotes(paramValue);
                    jsonBuilder.append(paramValue);
                } else if (numericSet.contains(paramName)) {
                    jsonBuilder.append(paramValue);
                } else {
                    jsonBuilder.append("\"").append(paramValue).append("\"");
                }
            }
        }

        jsonBuilder.append("}");
        messageContext.setProperty(NORMALIZED_PARAMETERS, jsonBuilder.toString());
    }

    /**
     * Utility to parse a comma-separated string of parameter names into a Set, trimming each
     * and ignoring empty entries.
     */
    private Set<String> parseCSV(String csv) {
        Set<String> results = new LinkedHashSet<>();
        if (StringUtils.isNotBlank(csv)) {
            String[] tokens = csv.split(",");
            for (String token : tokens) {
                String trimmed = token.trim();
                if (StringUtils.isNotEmpty(trimmed)) {
                    results.add(trimmed);
                }
            }
        }
        return results;
    }

    private String removeEnclosingSingleQuotes(String value) {
        String trimmed = value.trim();
        if (trimmed.length() >= 2 && trimmed.startsWith("'") && trimmed.endsWith("'")) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return value;
    }

    public String getPayloadParameters() {
        return payloadParameters;
    }

    public void setPayloadParameters(String payloadParameters) {
        this.payloadParameters = payloadParameters;
    }

    public String getNumericParameters() {
        return numericParameters;
    }

    public void setNumericParameters(String numericParameters) {
        this.numericParameters = numericParameters;
    }

    public String getJsonParameters() {
        return jsonParameters;
    }

    public void setJsonParameters(String jsonParameters) {
        this.jsonParameters = jsonParameters;
    }
}
