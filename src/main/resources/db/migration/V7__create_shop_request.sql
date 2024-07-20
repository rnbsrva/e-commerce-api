CREATE TABLE shop_request
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255),
    description        VARCHAR(255),
    shop_type          SMALLINT,
    address            VARCHAR(255),
    phone              VARCHAR(255),
    website            VARCHAR(255),
    user_id            BIGINT,
    request_status     VARCHAR(255),
    rejected_reason    VARCHAR(255),
    reviewed_by_id     BIGINT,
    reviewed_at        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_shoprequest PRIMARY KEY (id)
);

ALTER TABLE shop_request
    ADD CONSTRAINT FK_SHOPREQUEST_ON_REVIEWED_BY FOREIGN KEY (reviewed_by_id) REFERENCES users (id);

ALTER TABLE shop_request
    ADD CONSTRAINT FK_SHOPREQUEST_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE shop
    ADD COLUMN shop_request_id BIGINT;

ALTER TABLE shop
    ADD CONSTRAINT FK_SHOP_ON_SHOP_REQUEST FOREIGN KEY (shop_request_id) REFERENCES shop_request (id);