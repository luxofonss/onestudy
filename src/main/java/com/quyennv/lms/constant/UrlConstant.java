package com.quyennv.lms.constant;

public class UrlConstant {

    private UrlConstant() {}

    public static final String HEALTH_CHECK_URL = "/actuator/health";
    public static final String BASIC_MERCHANT_PORTAL_REFUND_URL = "/partners/merchant-portal";

    public static final String USER_MAKER = "maker";
    public static final String USER_CHECKER = "checker";

    public static final String ACTION_CREATE = "create";
    public static final String ACTION_LIST = "/list";
    public static final String ACTION_DETAIL = "/detail";
    public static final String ACTION_ACTION = "action";
    public static final String ACTION_CREATE_TRANS = "create-transaction";
    public static final String SELECT_DATA_BY_CUSTOM = "select-data-by";
    public static final String SELECT_COUNT_DATA_BY_CUSTOM = "select-count-data-by";
    public static final String UPDATE_DATA_BY = "update-data-by";
    public static final String TRANSACTION = "/transaction";
    public static final String EXPORT = "/export";
    public static final String SAMPLE_POST = "/sample/post";
    public static final String PAYMENT_GENERATE_QR = "/payment/generate-qr";
    public static final String PAYMENT_FETCH_QR_INFORMATION = "/payment/fetch-qr-information";
    public static final String PAYMENT_TRANSACTION_GET_TRANSACTION = "/payment-transaction/get-transaction";

    public static final String ACTION_SEARCH = "/search";

    public static final String QR_GENERATE = "/qr/generate";

    public static final String SEARCH_TRANSACTION = "/partners/tcb-portal/v2/transactions/search";

    public enum TransactionLogClientServiceApi {
        GET_TRANSACTION("internal/transaction-history/v1/transactions/"),
        GET_LIST_TRANSACTION("internal/transaction-history/v1/transaction-filters"),
        ;

        private String value;

        private TransactionLogClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum OpsPortalClientServiceApi {
        GET_TOKEN_INFO("tcb-int/v1/tpay/token/info"),
        GET_STAFF_TCB_PORTAL("tcb-int/v1/tpay/user"),
        ;

        private String value;

        private OpsPortalClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum OpsMakerCheckerClientServiceApi {
        GET_MAKER_CHECKER_LIST("/ops/checker/list"),
        MAKE_INIT_REQUEST("/ops/maker/create"),
        MAKE_UPDATE_REQUEST("/ops/maker/update"),
        GET_APPROVER_LIST("/ops/v1/approvers"),
        GET_REQUEST_LIST("/ops/maker/request"),
        MAKER_ACTION("/ops/maker/action"),
        CHECKER_ACTION("/ops/checker/action"),

        MAKER_SEARCH_ACTION("/ops/maker/request/search"),
        GET_REQUEST_LIST_BY_IDS("/ops/maker/request/list"),
        ;

        private String value;

        private OpsMakerCheckerClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum OpsT24ConnectorClientServiceApi {
        INIT_ONE_SHOT("/payment/bank-transfer/init/one-shot"),
        ;

        private String value;

        private OpsT24ConnectorClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TCBConnectorClientApi {

        CREATE_MERCHANT("/tpay/tcb/callout/merchant-onboard"),
        INIT_REFUND("/tpay/tcb/callout/request-refund"),
        UPDATE_ACCOUNT("/tpay/tcb/callout/update-merchant"),
        UPDATE_MASTER_ACCOUNT("tpay/tcb/callout/update-master-account"),
        CREATE_SOFTPOS_MERCHANT("tcb-softpos-connector-callout/callout/merchant-registration"),
        UPDATE_CARDTID_SOFTPOS_MERCHANT("tcb-softpos-connector-callout/callout/update-tid")
        ;

        private String value;
        TCBConnectorClientApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum MerchantPortalClientServiceApi {
        GET_STAFF_MERCHANT_SITE("tcb-mcsite-int/v1/tpay/staff"),
        GET_TOKEN_MERCHANT_INFO("tcb-mcsite-int/v1/tpay/staff/current"),
        GET_SOFTPOS_STAFF("tcb-mcsite-int/v1/softpos/staff"),
        ;

        private String value;

        MerchantPortalClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum MerchantPlatformClientServiceApi {
        GET_MERCHANT_DETAIL("tcb-int/v1/softpos/merchant/%s/store/%s"),
        SEND_FILE("tcb-mcsite-int/v1/tpay/terminal/%s/contract"),
        GET_MERCHANT_INFO("tcb-int/v1/softpos/merchant/%s"),
        ;

        private String value;

        MerchantPlatformClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TcbPaymentMerchantConfigClientServiceApi {
        UPLOAD_DOCUMENT("tcb-payment-merchant-config/api/v1/manager/upload-document"),
        GET_LIST_DEVICE("api/v1/ops/device/list"),
        GET_DEVICE_DETAIL("api/v1/ops/device/detail"),
        EXPORT_LIST_DEVICE("api/v1/ops/device/export"),
        ;

        private String value;

        TcbPaymentMerchantConfigClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TpayClientServiceApi {
        CALL_BACK_TRANSACTION("saas-gateway/refund/callback"),
        ;

        private String value;

        TpayClientServiceApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TSConnectorClientApi {
        RETRY_ACCOUNTING_FEE("auto-settlement/retry"),

        RETRY_FEE_TRANSACTION("job/fee/transaction/retry"),

        RETRY_FEE_SUM_SETTLEMENT("job/fee/sum/settlement/retry"),

        RETRY_SEND_EMAIL_AUTO_SETTLEMENT("email/auto-settlement/retry"),
        PUSH_NOTI_SLACK("slack-noti/push"),
        ;

        private String value;

        TSConnectorClientApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TpayInquiryConnectorApi {
        TRANSACTION_INQUIRY_REFUND("inquiry/refund"),
        TRANSACTION_INQUIRY_PAYMENT("inquiry/payment"),
        ;

        private String value;

        TpayInquiryConnectorApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TcbPaymentTransactionApi {
        TRANSACTION_SEARCH("/transactions/search"),
        TRANSACTION_DETAIL("/transactions/get"),
        GET_RECEIPT_TRANSACTION("/transactions/receipt/get"),
        ;

        private String value;

        TcbPaymentTransactionApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public enum TcbPaymentSelfOnboardApi {
        GET_LIST_GROUP_STEP_INFO("self-onboard/v1/group-step/info"),
        ;

        private String value;

        TcbPaymentSelfOnboardApi(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}