package com.friends.custoFacil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroFuncionario(

        @NotBlank(message = "O nome nao pode ser vazio ou nulo")
        String nome,

        @Email(message = "Email invalido")
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "Tamanho de celular invalido")
        String celular)

        //Status como padrão é definido como Ativo
{
}
