-- MariaDB structure definition script for schema "http"
-- best import using MariaDB client command "source <path to this file>"

SET CHARACTER SET utf8mb4;
DROP DATABASE IF EXISTS http;
CREATE DATABASE http CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE http;

CREATE TABLE Scope (
	identity BIGINT AUTO_INCREMENT,
	sessionReference CHAR(16) NULL,
	alias CHAR(128) NOT NULL,
	value VARBINARY(64000) NOT NULL,
	PRIMARY KEY (identity),
	UNIQUE KEY (sessionReference, alias)
);
