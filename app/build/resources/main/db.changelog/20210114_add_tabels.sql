CREATE TABLE  "section"(
id BIGINT ,
name VARCHAR UNIQUE NOT NULL,

PRIMARY KEY(id)
);


CREATE TABLE  "category"(
id BIGINT ,
section_id BIGINT NOT NULL,
name VARCHAR UNIQUE NOT NULL,

PRIMARY KEY(id),
CONSTRAINT fk_section_id
FOREIGN KEY (section_id) REFERENCES section(id)
);


CREATE TABLE  "auction"(
id BIGINT,
category_id BIGINT NOT NULL,
creator_id BIGINT NOT NULL,
title VARCHAR UNIQUE NOT NULL,
description TEXT NOT NULL,
price NUMERIC(10,2) NOT NULL,

PRIMARY KEY(id),
CONSTRAINT fk_category_id
FOREIGN KEY (category_id) REFERENCES category(id)

CONSTRAINT fk_user_id
FOREIGN KEY (creator_id) REFERENCES user(id)
);


CREATE TABLE  "auction_picture"(
id BIGINT ,
auction_id BIGINT NOT NULL,
link VARCHAR  NOT NULL,

PRIMARY KEY(id),
CONSTRAINT fk_auction_id_picture
FOREIGN KEY (auction_id) REFERENCES auction(id)
);


CREATE TABLE  "parameter"(
id BIGINT ,
name VARCHAR UNIQUE NOT NULL,

PRIMARY KEY(id),
);


CREATE TABLE  "auction_parameter"(
id BIGINT ,
auction_id BIGINT NOT NULL,
parameter_id BIGINT NOT NULL
value VARCHAR  NOT NULL,

PRIMARY KEY(id),
CONSTRAINT fk_auction_id_parameter
FOREIGN KEY (auction_id) REFERENCES auction(id)
CONSTRAINT fk_parameter_id 
FOREIGN KEY (parameter_id) REFERENCES parameter(id)
);










