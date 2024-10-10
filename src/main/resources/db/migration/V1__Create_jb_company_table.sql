CREATE TABLE jb_company (
                            company_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            company_no VARCHAR(10) NOT NULL,
                            company_name VARCHAR(50) NOT NULL,
                            lng FLOAT,
                            lat FLOAT
);