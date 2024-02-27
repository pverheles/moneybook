ALTER TABLE envelope ADD COLUMN category_id int8 NOT NULL;
ALTER TABLE envelope ADD CONSTRAINT envelope_category_id_pk FOREIGN KEY (category_id) REFERENCES category(id);
