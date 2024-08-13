package com.friends.custoFacil.domain;


import com.friends.custoFacil.dto.cadastroCusto;
import com.friends.custoFacil.enums.CategoriaCusto;
import com.friends.custoFacil.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Custo")
@Table(name = "Custos")
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_funcionario")
    private Long idFuncionario;
    @Column(name = "categoria_custo")
    @Enumerated(EnumType.STRING)
    private CategoriaCusto categoriaCusto;
    @Column(name = "valor")
    private int valor;
    @Column(name = "data_custo")
    private Date dataCusto;
    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    public Custo(cadastroCusto cadastroCusto) {
        this.idFuncionario = cadastroCusto.idFuncionario();
        this.categoriaCusto = cadastroCusto.categoriaCusto();
        this.valor = cadastroCusto.valor();
        this.dataCusto = cadastroCusto.dataCusto();
        this.statusPagamento = cadastroCusto.statusCusto();
    }
}
