package com.friends.custoFacil.dto;

import com.friends.custoFacil.enums.CategoriaCusto;
import com.friends.custoFacil.enums.StatusPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AlterarCusto {

    @Enumerated(EnumType.STRING)
    private CategoriaCusto categoriaCusto;
    private int valor;
    private Date dataCusto;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusCusto;

}
