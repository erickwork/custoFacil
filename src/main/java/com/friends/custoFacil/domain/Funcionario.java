package com.friends.custoFacil.domain;


import com.friends.custoFacil.dto.CadastroFuncionario;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Funcionario")
@Table(name = "Funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String celular;

    public Funcionario(CadastroFuncionario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.celular = dados.celular();
    }
}
