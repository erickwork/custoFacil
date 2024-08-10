package com.friends.custoFacil.dto;

import com.friends.custoFacil.domain.Funcionario;

public record retornoFuncionarios(String nome, String status) {

    public retornoFuncionarios(Funcionario funcionario) {
        this(
                funcionario.getNome(),
                String.valueOf(funcionario.getStatusFuncionario())
        );
    }
}
