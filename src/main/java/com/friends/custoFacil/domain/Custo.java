package com.friends.custoFacil.domain;


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

    private Long idFuncionario;
    private CategoriaCusto categoriaCusto;
    private int valor;
    private Date dataCusto;
    private StatusPagamento statusPagamento;

}
