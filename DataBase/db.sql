CREATE Table Invoice;
Use Invoice;

CREATE TABLE `accounts` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `pwd` varchar(255) DEFAULT NULL,
    `role` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `association_table` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `company_id` bigint DEFAULT NULL,
    `type_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKb1kp2xbp3q1r7xb3k599ool9e` (`company_id`),
    KEY `FK1sp1wcjnm8c5ic041iof2fdmr` (`type_id`),
    FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
    FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
);

CREATE TABLE `authority` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `account_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
);


CREATE TABLE `bank_accounts` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `iban` varchar(255) DEFAULT NULL,
    `max_value` double DEFAULT NULL,
    `bank_id` bigint DEFAULT NULL,
    `company_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY  (`bank_id`),
    KEY  (`company_id`),
    FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`),
    FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
);


CREATE TABLE `banks` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `companies`
 (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `vat_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)


CREATE TABLE `document_type` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)



CREATE TABLE`invoices` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `collection_date` date DEFAULT NULL,
    `description` varchar(1000) DEFAULT NULL,
    `expiration_date` date DEFAULT NULL,
    `income` double DEFAULT NULL,
    `invoice_number` varchar(255) DEFAULT NULL,
    `issue_date` date DEFAULT NULL,
    `retention_tax` double DEFAULT NULL,
    `vat` double DEFAULT NULL,
    `advanced` bit(1) DEFAULT NULL,
    `reversed` bit(1) DEFAULT NULL,
    `bank_account` bigint DEFAULT NULL,
    `receiver_id` bigint DEFAULT NULL,
    `sender_id` bigint DEFAULT NULL,
    `type_id` bigint DEFAULT NULL,
    `id_bank_account` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY  (`bank_account`),
    KEY  (`receiver_id`),
    KEY  (`sender_id`),
    KEY  (`type_id`),
    FOREIGN KEY (`bank_account`) REFERENCES `bank_accounts` (`id`),
    FOREIGN KEY (`type_id`) REFERENCES `document_type` (`id`),
    FOREIGN KEY (`sender_id`) REFERENCES `companies` (`id`),
    FOREIGN KEY (`receiver_id`) REFERENCES `companies` (`id`)
);


CREATE TABLE `log` 
(
    `id` int NOT NULL AUTO_INCREMENT,
    `date` datetime(6) DEFAULT NULL,
    `message` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ;


CREATE TABLE `tokenvalidator` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `finish` datetime(6) DEFAULT NULL,
    `start` datetime(6) DEFAULT NULL,
    `token` varchar(255) DEFAULT NULL,
    `account_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY (`account_id`),
    FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
);

CREATE TABLE`type` 
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);