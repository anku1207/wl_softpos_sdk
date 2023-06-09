package com.example.wl_softpos_sdk.common.utils;

public class Constants {

    private static final String TAG = "Constants";

    public static class AlertDialogRequestCode {
        public AlertDialogRequestCode() {
            throw new IllegalStateException(TAG);
        }
        public static final int LOG_OUT = 1001;
        public static final int ON_ERROR = 1002;
        public static final int SERVER_ERROR_401 = 401;
        public static final int SERVER_ERROR_403 = 403;
        public static final int SERVER_ERROR_412 = 412;
        public static final int SERVER_ERROR_410 = 410;
        public static final int SERVER_ERROR_500 = 500;
        public static final int NO_INTERNET = 1003;
        public static final int DELETE_TRANSACTION = 1004;
        public static final int DELETE_CUSTOMER = 1005;
        public static final int FILE_TOO_LARGE = 1006;
        public static final int DELETE_NOTIFICATIONS = 1007;
        public static final int DOCUMENT_MISSING = 1008;
        public static final int UPDATE_AVAILABLE = 1009;
        public static final int MOBILE_NO_DIFF = 1010;
        public static final int UPLOAD_CHEQUE = 1011;
        public static final int VERIFY_WITH_AADHAAR = 1012;
        public static final int CHEQUE_UPLOAD_ERROR = 1013;
        public static final int NFC_NOT_SUPPORTED = 1014;
        public static final int NFC_NOT_ENABLE = 1015;
        public static final int NAME_NOT_MACHINING = 1016;

    }

}
