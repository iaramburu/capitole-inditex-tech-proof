DROP SCHEMA IF EXISTS tech_proof CASCADE;
CREATE SCHEMA tech_proof;

--*************************************************
-- PRODUCT
--*************************************************
CREATE TABLE tech_proof.product
(
  id bigserial NOT NULL,
  sequence integer NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tech_proof.product
  OWNER TO tech_proof;

--*************************************************
-- SIZE
--*************************************************
CREATE TABLE tech_proof.size
(
  id bigserial NOT NULL,
  product_id bigint NOT NULL,
  back_soon boolean DEFAULT NULL,
  special boolean DEFAULT NULL,
  CONSTRAINT size_pkey PRIMARY KEY (id),
  CONSTRAINT size_product_id_fkey FOREIGN KEY (product_id) REFERENCES tech_proof.product (id) ON DELETE CASCADE
)
  WITH (
    OIDS=FALSE
    );
ALTER TABLE tech_proof.size
  OWNER TO tech_proof;

-- indices
CREATE INDEX idx_size_product_id ON tech_proof.size (product_id);

--*************************************************
-- STOCK
--*************************************************
CREATE TABLE tech_proof.stock
(
  size_id bigint NOT NULL,
  quantity integer NOT NULL,
  CONSTRAINT stock_size_id_fkey FOREIGN KEY (size_id) REFERENCES tech_proof.size (id) ON DELETE CASCADE
)
  WITH (
    OIDS=FALSE
    );
ALTER TABLE tech_proof.stock
  OWNER TO tech_proof;

-- indices
CREATE INDEX idx_stock_size_id ON tech_proof.stock (size_id);
