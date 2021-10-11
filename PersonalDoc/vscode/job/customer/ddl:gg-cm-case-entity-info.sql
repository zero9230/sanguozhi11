CREATE TABLE "ERCDBA"."GG_CM_CASE_ENTITY_INFO" (
    "GG_COMMIT_TIMESTAMP" date Not Null,
    "GG_SCN" NUMBER(22, 0) Not Null,
    "GG_SEQ" NUMBER(22, 0) Not Null,
    "GG_RBA" NUMBER(22, 0) Not Null,
    "GG_OP_TYPE" VARCHAR2(20) Not Null,
    "GG_BEFORE_AFTER" VARCHAR2(10) Not Null,
    "GG_TRAIL_SEQ" NUMBER(22, 0) Not Null,
    "GG_TRAIL_RBA" NUMBER(22, 0) Not Null,
    "GG_TXNIND" VARCHAR2(20) Not Null,
    "GG_REPLICAT_NAME" VARCHAR2(8) Not Null,
    "GG_ROWID" VARCHAR2(30) Not Null,
    "CASE_TID" NUMBER NOT NULL ENABLE,
    "ENTITY_TYPE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
    "ENTITY_ID" VARCHAR2(100 BYTE) NOT NULL ENABLE,
    "ENTITY_ROLE" VARCHAR2(100 BYTE) NOT NULL ENABLE,
    "ADJACENCY_ID" NUMBER NOT NULL ENABLE,
    "LINK_REASON" VARCHAR2(200 BYTE),
    "CLASSIFICATION" VARCHAR2(100 BYTE),
    "TIME_CREATED" NUMBER NOT NULL ENABLE,
    "TIME_UPDATED" NUMBER NOT NULL ENABLE,
    "ACCOUNT_TENANCY" VARCHAR2(100 BYTE),
    "LINKED_ENTITY_UUID" VARCHAR2(100 BYTE),
    "IS_LINKED_ENTITY" VARCHAR2(1 BYTE)

);

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."CASE_TID" IS 'Description: ID of corresponding case in CM_CASES
Referencing_column : ERC.CM_CASES.TID';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."ENTITY_TYPE" IS 'Description: TYPE OF ENTITY LINKED TO THE CASE
Referencing_column : ERC.CM_ENTITY_TYPE_CON.TYPE_NAME
Value_List: ACCOUNT,CUSTOMER,PARTY,TRANSACTION
';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."ENTITY_ID" IS 'Description: ID of the entity linked to the case
';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."ENTITY_ROLE" IS 'Description: Role of the entity linked to the case.  This could hold values like PRIMARY, SECONDARY, LINKED...
';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."ADJACENCY_ID" IS 'Description: Tenant/Adjacency to which the entity belongs
Referencing_column : ERC.ADJACENCY.ID';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."LINK_REASON" IS 'Description: This shows on what basis an entity was linked to a case';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."CLASSIFICATION" IS 'Description: Classification on the entity. This could hold values like Suspect, Victim, Non suspect and so on';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."TIME_CREATED" IS 'Epoch timestamp when the record was inserted
Ticket#: SDS-5982
Value_List: N/A
Class: Class 5
Handling: No special handling required
Zone: Restricted PCI Storage Zone';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."TIME_UPDATED" IS 'Epoch timestamp when the record was updated
Ticket#: SDS-5982
Value_List: N/A
Class: Class 5
Handling: No special handling required
Zone: Restricted PCI Storage Zone';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."ACCOUNT_TENANCY" IS 'Definition
----------
Column_Description      : Tenancy of the entity. This could hold values like PayPal.GeoMigrated.PP_CN, PayPal.GeoMigrated.PP_IN and so on.
Referencing_Column_Name : NA
Ticket_Number           : SDS-18127
Value_List              : NA
Data Classification
-------------------
 InfoSec_Class    : Class 4
 InfoSec_Handling : Role Based Access (least privilege)';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."LINKED_ENTITY_UUID" IS 'Definition
----------
Column_Description      : UUID to uniquely identify the given linked entity, this will act as resource identifier for linked entities api
Referencing_Column_Name : NA
Ticket_Number           : SDS-19260
Value_List: NA
Data Classification
-------------------
 InfoSec_Class    : Class 4
 InfoSec_Handling : Role Based Access (least privilege)';

COMMENT ON COLUMN "ERCDBA"."GG_CM_CASE_ENTITY_INFO"."IS_LINKED_ENTITY" IS 'Definition
----------
Column_Description      : A boolean flag denotes whether the given entity is a linked or normal account(customer)
Referencing_Column_Name : NA
Ticket_Number           : SDS-19260
Value_List:
0 - No, this is not a linked entity
1 - Yes, its a linked entity
Data Classification
-------------------
 InfoSec_Class    : Class 4
 InfoSec_Handling : Role Based Access (least privilege)';

COMMENT ON TABLE "ERCDBA"."GG_CM_CASE_ENTITY_INFO" IS 'Description: Table to hold entities linked to a case.
Table_Type: TRANSACTION
Contact_Person: Singh, Pushpinder
Owning_BU: RISK/Compliance
Feature: Simplified Case Management System
Ticket#: SDS-4988
Release: 211-ISO
Init_method: none
Inserts_per_day_init: 0
avg_row_size_bytes:
Selects_per_day: 10000
Inserts_per_day: 40000
Updates_per_day: 5000
Deletes_per_day: 10000
Retention_days: 99999
Purging_Method: Partition
Purging_logic: Partition purging
Class: class 5
Handling: No special handling required
Zone: Confidential Storage Zone';


GRANT
SELECT
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCROAPP";

GRANT
SELECT
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCROAPP_R";

GRANT
SELECT
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "RDONLY_R";

GRANT
UPDATE
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCAPP_R";

GRANT
SELECT
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCAPP_R";

GRANT
INSERT
    ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCAPP_R";

GRANT DELETE ON "ERCDBA"."GG_CM_CASE_ENTITY_INFO" TO "ERCAPP_R";