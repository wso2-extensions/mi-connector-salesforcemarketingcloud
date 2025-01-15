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

public class Constants {
    public static final String PROPERTY_ERROR_CODE = "ERROR_CODE";
    public static final String PROPERTY_ERROR_MESSAGE = "ERROR_MESSAGE";
    public static final String GENERAL_ERROR_MSG = "The Trade Desk connector encountered an error: ";

    static class ErrorCodes {
        public static final String INVALID_CONFIG = "701002";
        public static final String GENERAL_ERROR = "701003";
    }

    public static final String PROPERTY_TTD_AUTH = "_TTD_AUTH_HEADER_";
}