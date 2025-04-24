package com.quyennv.lms.constant;

public class ErrorConstant {

    private ErrorConstant() {}

    /**
     * Write the error code prefixed with 200 below
     * 200
     */
    public static final int SUCCESS = 200000;
    public static final int SUCCESS_200 = 200;

    /**
     * Write the error code prefixed with 400 below
     * 400
     */
    public static final int INVALID_PARAMETERS = 4000001;

    public static final int SYSTEM_CONFIGURATION_NOT_FOUND = 4004208;

    public static final int TRANSACTION_NOT_FOUND = 4001210;

    public static final int TRANSACTION_NOT_EXIST = 4001218;

    public static final int TRANSACTION_MUST_NOT_REFUND = 4001212;

    public static final int TRANSACTION_NOT_SUCCESS = 4001213;

    public static final int TRANSACTION_NOT_ENOUGH_AMOUNT = 4001214;

    public static final int HTTP_CONNECTION_ERROR  = 4009000;

    public static final int NULL_META_DATA_RESPONSE  = 4009001;

    /**
     * Write the error code prefixed with 401 below
     * 401
     */
    public static final int UNAUTHORIZED = 4010001;

    /**
     * Write the error code prefixed with 403 below
     * 403
     */
    public static final int FORBIDDEN_ERROR = 4030001;

    /**
     *  Write the error code prefixed with 404 below
     * 404
     */
    public static final int NOT_FOUND = 4040001;

    /** @Get_VA_number_information*/
    public static final int INVALID_VA_NUMBER = 40010525;

    /**
     * Write the error code prefixed with 500 below
     * 500
     */
    public static final int INTERNAL_SERVER_ERROR = 5001001;

}