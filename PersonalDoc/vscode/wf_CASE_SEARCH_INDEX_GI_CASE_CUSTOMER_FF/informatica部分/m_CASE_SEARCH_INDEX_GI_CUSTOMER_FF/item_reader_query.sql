SELECT
    GG_CASE_AUDIT_LOG.GG_COMMIT_TIMESTAMP,
    TO_NUMBER(
        CONCAT(
            GG_CASE_AUDIT_LOG.GG_TRAIL_SEQ,
            LPAD(GG_CASE_AUDIT_LOG.GG_TRAIL_RBA, 10, 0)
        )
    ) AS GG_RBA,
    GG_CASE_AUDIT_LOG.CASE_TID,
    GG_CASE_AUDIT_LOG.AUDIT_SEQ,
    GG_CASE_AUDIT_LOG.TIME_CREATED,
    GG_CASE_AUDIT_LOG.COMMENTS
FROM
    GG_CASE_AUDIT_LOG
WHERE
    GG_OP_TYPE <> 'DELETE'
    AND GG_BEFORE_AFTER = 'AFTER'
    AND GG_COMMIT_TIMESTAMP >= TO_DATE(:fromDate, 'yyyymmddhh24miss') - 1 / 96
    AND GG_COMMIT_TIMESTAMP <= TO_DATE(:toDate, 'yyyymmddhh24miss')
    AND mod(CASE_TID, :totalPartionCount) = :partionID;