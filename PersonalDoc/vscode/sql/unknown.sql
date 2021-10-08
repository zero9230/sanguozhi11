select
    c.*
from
    (
        select
            LTRIM(RTRIM(TO_CHAR(a.CASE_TID))) || '_refCom_' || LTRIM(RTRIM(TO_CHAR(a.SAR_SEQ))) as ID,
            a.CASE_TID as CASE_TID,
            CASE
                WHEN RTRIM(
                    LTRIM(
                        OREPLACE(
                            OREPLACE(a.ADDITIONAL_DATA, CHR(10), ' '),
                            CHR(13),
                            ' '
                        )
                    )
                ) = '#' THEN null
                else RTRIM(
                    LTRIM(
                        OREPLACE(
                            OREPLACE(a.ADDITIONAL_DATA, CHR(10), ' '),
                            CHR(13),
                            ' '
                        )
                    )
                )
            END as OVALUE,
            'ReferralComment' as OTYPE,
            LTRIM(
                RTRIM(
                    CASE
                        WHEN (
                            TO_DATE(TO_CHAR(a.TIME_CREATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    18 for 2
                            )
                        ) > 0 THEN (
                            TO_DATE(TO_CHAR(a.TIME_CREATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(a.TIME_CREATED)
                                from
                                    18 for 2
                            )
                        )
                        ELSE null
                    end
                )
            ) as TIME_CREATED,
            LTRIM(
                RTRIM(
                    CASE
                        WHEN (
                            TO_DATE(TO_CHAR(a.TIME_UPDATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    18 for 2
                            )
                        ) > 0 THEN (
                            TO_DATE(TO_CHAR(a.TIME_UPDATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(a.TIME_UPDATED)
                                from
                                    18 for 2
                            )
                        )
                        ELSE null
                    end
                )
            ) as TIME_UPDATED
        from
            PP_COMPLIANCE_ODS_VIEWS.CM_SAR_CASE_DATA a
        union
        select
            LTRIM(RTRIM(TO_CHAR(b.CASE_TID))) || '_refCom_' || LTRIM(RTRIM(TO_CHAR(b.CM_DD_CASE_DATA_ID))) as ID,
            b.CASE_TID as CASE_TID,
            CASE
                WHEN RTRIM(
                    LTRIM(
                        OREPLACE(
                            OREPLACE(b.ADDITIONAL_CASE_DATA, CHR(10), ' '),
                            CHR(13),
                            ' '
                        )
                    )
                ) = '#' THEN null
                else RTRIM(
                    LTRIM(
                        OREPLACE(
                            OREPLACE(b.ADDITIONAL_CASE_DATA, CHR(10), ' '),
                            CHR(13),
                            ' '
                        )
                    )
                )
            END as OVALUE,
            'ReferralComment' as OTYPE,
            LTRIM(
                RTRIM(
                    CASE
                        WHEN (
                            TO_DATE(TO_CHAR(b.TIME_CREATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    18 for 2
                            )
                        ) > 0 THEN (
                            TO_DATE(TO_CHAR(b.TIME_CREATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(b.TIME_CREATED)
                                from
                                    18 for 2
                            )
                        )
                        ELSE null
                    end
                )
            ) as TIME_CREATED,
            LTRIM(
                RTRIM(
                    CASE
                        WHEN (
                            TO_DATE(TO_CHAR(b.TIME_UPDATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    18 for 2
                            )
                        ) > 0 THEN (
                            TO_DATE(TO_CHAR(b.TIME_UPDATED), 'YYYY-MM-DD HH24:MI:SS') - TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
                        ) * 24 * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    12 for 2
                            )
                        ) * 60 * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    15 for 2
                            )
                        ) * 60 + to_number(
                            substring(
                                to_char(b.TIME_UPDATED)
                                from
                                    18 for 2
                            )
                        )
                        ELSE null
                    end
                )
            ) as TIME_UPDATED
        from
            PP_COMPLIANCE_ODS_VIEWS.CM_DD_CASE_DATA b
    ) c
where
    c.case_tid in (
        select
            tid
        from
            pp_scratch.cdd_missing
    )