package com.friends.custoFacil.domain;


import com.friends.custoFacil.dto.CadastroFuncionario;
import com.friends.custoFacil.enums.StatusFuncionario;
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


    @Column(name = "status")
    @Enumerated (EnumType.STRING)
    private StatusFuncionario statusFuncionario;

    public Funcionario(CadastroFuncionario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.celular = dados.celular();
        this.statusFuncionario = statusFuncionario.ACTIVE;
    }

    public void inativarFuncionario() {
        this.statusFuncionario = statusFuncionario.INACTIVE;
    }

    public void reativarFuncionario() {
        this.statusFuncionario = statusFuncionario.ACTIVE;
    }
}
