CREATE TABLE review
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    text               VARCHAR(255),
    rating             INTEGER,
    order_id           BIGINT,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE orders
    ADD COLUMN review_id BIGINT;

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_REVIEW FOREIGN KEY (review_id) REFERENCES review (id);