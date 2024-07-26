CREATE TABLE custos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    idFuncionario BIGINT NOT NULL,
    categoriaCusto varchar(100) NOT NULL,
    valor INT NOT NULL,
    dataCusto DATE NOT NULL,
    statusPagamento varchar(100) NOT NULL,
    FOREIGN KEY (idFuncionario) REFERENCES funcionarios(id)
);
