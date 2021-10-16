SELECT
    WUSER.TIME_CREATED as TIME_CREATED,
    WUSER.FIRST_NAME as FIRST_NAME,
    WUSER.LAST_NAME as LAST_NAME,
    WUSER.FLAGS as FLAGS,
    WUSER.LAST_WEB_ACCESS as LAST_WEB_ACCESS,
    WUSER.DAY_PHONE as DAY_PHONE,
    WUSER.NIGHT_PHONE as NIGHT_PHONE,
    WUSER.ADDRESS_ID as ADDRESS_ID,
    WUSER.CYCLE_NUMBER as CYCLE_NUMBER,
    WUSER.SERVER_CURRENT_TRANSACTION as SERVER_CURRENT_TRANSACTION,
    WUSER.CREDIT_CHARGED as CREDIT_CHARGED,
    WUSER.CYCLE_LAST_UPDATED as CYCLE_LAST_UPDATED,
    WUSER.CONFIRM_ERROR_COUNT as CONFIRM_ERROR_COUNT,
    WUSER.TRANSACTION_LIMIT as TRANSACTION_LIMIT,
    WUSER.DEFAULT_CC as DEFAULT_CC,
    WUSER.DEFAULT_ACH as DEFAULT_ACH,
    WUSER.USER_GROUP as USER_GROUP,
    WUSER.PRIMARY_EMAIL as PRIMARY_EMAIL,
    WUSER.TOTAL_AMOUNT_SENT as TOTAL_AMOUNT_SENT,
    WUSER.AUTHENTICATIONS as AUTHENTICATIONS,
    WUSER.RECEIVING_PREFS as RECEIVING_PREFS,
    WUSER.CC_AMOUNT_RECEIVED as CC_AMOUNT_RECEIVED,
    WUSER.LAST_LOGIN_IP as LAST_LOGIN_IP,
    WUSER.CITIZENSHIP as CITIZENSHIP,
    WUSER.FRAUD_SCORE as FRAUD_SCORE,
    WUSER.LIFETIME_REVENUE as LIFETIME_REVENUE,
    WUSER.SUBPRIME as SUBPRIME,
    WUSER.FLAGS2 as FLAGS2,
    WUSER.AUTOSWEEP_TARGET as AUTOSWEEP_TARGET,
    WUSER.TIME_CLOSED as TIME_CLOSED,
    WUSER.WITHDRAW_BALANCE as WITHDRAW_BALANCE,
    WUSER.PRIMARY_EMAIL_NAME as PRIMARY_EMAIL_NAME,
    WUSER.AFS_TYPE as AFS_TYPE,
    WUSER.AFS_ID as AFS_ID,
    WUSER.AAC_ERROR_COUNT as AAC_ERROR_COUNT,
    WUSER.FLAGS3 as FLAGS3,
    WUSER.VOLUME_COMPLETED as VOLUME_COMPLETED,
    WUSER.VOLUME_COMPLETED_PLUS_PENDING as VOLUME_COMPLETED_PLUS_PENDING,
    WUSER.LANGUAGE as LANGUAGE,
    WUSER.PRIMARY_RESIDENCE as PRIMARY_RESIDENCE,
    WUSER.VALIDATION_TRIES as VALIDATION_TRIES,
    WUSER.FLAGS4 as FLAGS4,
    WUSER.AMOUNT_ACH_DEPOSITED as AMOUNT_ACH_DEPOSITED,
    WUSER.CONTACT_SCORE_OVERRIDE as CONTACT_SCORE_OVERRIDE,
    WUSER.CLONE_STATE as CLONE_STATE,
    WUSER.CCRISK_CUTOFF_GROUP as CCRISK_CUTOFF_GROUP,
    WUSER.AMOUNT_SENT_FROM_BALANCE as AMOUNT_SENT_FROM_BALANCE,
    WUSER.AMOUNT_SENT_FROM_UNCONFIRMED as AMOUNT_SENT_FROM_UNCONFIRMED,
    WUSER.TIMEZONE as TIMEZONE,
    WUSER.PRIMARY_CURRENCY_CODE as PRIMARY_CURRENCY_CODE,
    WUSER.SEND_LIMIT_CORRECTOR as SEND_LIMIT_CORRECTOR,
    WUSER.WITHDRAW_LIMIT_CORRECTOR as WITHDRAW_LIMIT_CORRECTOR,
    WUSER.WITHDRAW_BALANCE_CUMULATIVE as WITHDRAW_BALANCE_CUMULATIVE,
    WUSER.WITHDRAW_CORRECTOR_CUMULATIVE as WITHDRAW_CORRECTOR_CUMULATIVE,
    WUSER.LEGAL_ENTITY as LEGAL_ENTITY,
    WUSER.PRIMARY_SESSION_NUMBER as PRIMARY_SESSION_NUMBER,
    WUSER.BIRTH_DATE as BIRTH_DATE,
    WUSER.WORLD_SELLER_TIER as WORLD_SELLER_TIER,
    WUSER.TIME_ROW_UPDATED as TIME_ROW_UPDATED,
    WUSER.FIRST_NAME_UPPER as FIRST_NAME_UPPER,
    WUSER.LAST_NAME_UPPER as LAST_NAME_UPPER,
    WUSER.UPDATE_VERSION as UPDATE_VERSION,
    WUSER.MAO_UPDATE_VERSION as MAO_UPDATE_VERSION,
    WUSER.UPDATE_VERSION2 as UPDATE_VERSION2,
    WUSER.ACCOUNT_NUMBER as ACCOUNT_NUMBER
FROM
    WUSER
WHERE
    ACCOUNT_NUMBER = ? ENTITY_ID ?