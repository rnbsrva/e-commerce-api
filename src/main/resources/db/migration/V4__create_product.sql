CREATE TABLE category
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255),
    description        VARCHAR(255),
    image              VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255),
    description        VARCHAR(255),
    price              DOUBLE PRECISION,
    image              VARCHAR(255),
    category_id        BIGINT,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

CREATE TABLE category_products
(
    category_id BIGINT NOT NULL references category(id),
    products_id BIGINT NOT NULL references product(id)
);

ALTER TABLE category_products
    ADD CONSTRAINT uc_category_products_products UNIQUE (products_id);

CREATE TABLE shop_product
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created_date       TIMESTAMP WITHOUT TIME ZONE,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    quantity           INTEGER,
    available          BOOLEAN,
    price              DOUBLE PRECISION,
    shop_id            BIGINT,
    product_id         BIGINT,
    CONSTRAINT pk_shopproduct PRIMARY KEY (id)
);

ALTER TABLE shop_product
    ADD CONSTRAINT FK_SHOPPRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE shop_product
    ADD CONSTRAINT FK_SHOPPRODUCT_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shop (id);