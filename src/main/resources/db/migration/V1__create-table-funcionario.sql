CREATE TABLE funcionarios (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(255) NOT NULL,
                             email VARCHAR(255) unique ,
                             celular VARCHAR(20) NOT NULL unique
);
