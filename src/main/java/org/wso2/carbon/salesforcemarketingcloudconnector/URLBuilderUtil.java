/*

* Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com). All Rights Reserved.
*
* This software is the property of WSO2 LLC. and its suppliers, if any.
* Dissemination of any information or reproduction of any material contained
* herein is strictly forbidden, unless permitted by WSO2 in accordance with
* the WSO2 Software License available at: https://wso2.com/licenses/eula/3.2
* For specific language governing the permissions and limitations under
* this license, please see the license as well as any agreement you’ve
* entered into with WSO2 governing the purchase of this software and any
* associated services.
*/
package org.wso2.carbon.salesforcemarketingcloudconnector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseConstants;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.healthcare.integration.common.ehr.EHRConnectException;

import java.util.HashMap;
import java.util.Map;

// Generated on 08-Wed, 01, 2025 20:03:16+0530

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

    @Override
        public void connect(MessageContext messageContext) throws EHRConnectException {
            messageContext.setProperty("_OH_INTERNAL_PARAM_NAME_MAP_", parameterNameMap);
        }
}
