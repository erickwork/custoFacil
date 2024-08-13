ALTER TABLE custos
    CHANGE COLUMN categoriaCusto categoria_custo VARCHAR(255),
    CHANGE COLUMN dataCusto data_custo DATE,
    CHANGE COLUMN idFuncionario id_funcionario BIGINT,
    CHANGE COLUMN statusPagamento status_pagamento VARCHAR(255);
