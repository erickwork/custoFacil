package com.friends.custoFacil.dto;

import com.friends.custoFacil.enums.CategoriaCusto;
import com.friends.custoFacil.enums.StatusPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record cadastroCusto(
        @NotNull
        Long idFuncionario,

        @NotNull
        CategoriaCusto categoriaCusto,

        @NotNull
        int valor,

        @NotNull
        Date dataCusto,

        @NotNull
        StatusPagamento statusCusto) {

}

