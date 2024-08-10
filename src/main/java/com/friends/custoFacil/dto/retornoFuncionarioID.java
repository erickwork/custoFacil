package com.friends.custoFacil.dto;

import com.friends.custoFacil.domain.Funcionario;

public record retornoFuncionarioID(String nome, String celular, String status) {

    public retornoFuncionarioID(Funcionario funcionario){
        this(
                funcionario.getNome(),
                funcionario.getCelular(),
                String.valueOf(funcionario.getStatusFuncionario())
        );
    }
}
