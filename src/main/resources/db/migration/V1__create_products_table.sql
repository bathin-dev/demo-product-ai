CREATE TABLE IF NOT EXISTS products (
  id SERIAL PRIMARY KEY,
  product_id VARCHAR(255) UNIQUE,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  vendor VARCHAR(255),
  product_type VARCHAR(255),
  image_url TEXT,
  price DECIMAL(19, 2) NOT NULL
);

CREATE INDEX IF NOT EXISTS products_product_id_idx ON products (product_id);
