create table USER
(
    ID           INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_BDD253C4_A441_471B_A809_A7CE6118A317" auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);

