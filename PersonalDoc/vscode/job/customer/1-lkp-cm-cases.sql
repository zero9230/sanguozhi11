SELECT
    CM_CASES.DISCIPLINE_ID as DISCIPLINE_ID,
    CM_CASES.TID as TID
FROM
    CM_CASES
WHERE
    DISCIPLINE_ID in (
        SELECT
            ID
        FROM
            DISCIPLINE
        WHERE
            DISCIPLINE_NAME in ('GI-EI', 'GI-IF')
    ) -- lookup condition
    -- TID = in_CASE_TID
;
