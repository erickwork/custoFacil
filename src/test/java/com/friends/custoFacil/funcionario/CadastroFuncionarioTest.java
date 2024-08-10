package com.friends.custoFacil.funcionario;


import com.friends.custoFacil.dto.CadastroFuncionario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CadastroFuncionarioTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testCadastroFuncionarioValido() {
        CadastroFuncionario funcionario = new CadastroFuncionario("João Silva", "joao.silva@email.com", "12345678901");

        Set<ConstraintViolation<CadastroFuncionario>> violations = validator.validate(funcionario);

        assertThat(violations).isEmpty();
    }

    @Test
    public void testCadastroFuncionarioNomeVazio() {
        CadastroFuncionario funcionario = new CadastroFuncionario("", "joao.silva@email.com", "12345678901");

        Set<ConstraintViolation<CadastroFuncionario>> violations = validator.validate(funcionario);

        assertThat(violations).isNotEmpty();
    }

    @Test
    public void testCadastroFuncionarioEmailInvalido() {
        CadastroFuncionario funcionario = new CadastroFuncionario("João Silva", "emailinvalido", "12345678901");

        Set<ConstraintViolation<CadastroFuncionario>> violations = validator.validate(funcionario);

        assertThat(violations).isNotEmpty();
    }

    @Test
    public void testCadastroFuncionarioCelularInvalido() {
        CadastroFuncionario funcionario = new CadastroFuncionario("João Silva", "joao.silva@email.com", "12345");

        Set<ConstraintViolation<CadastroFuncionario>> violations = validator.validate(funcionario);

        assertThat(violations).isNotEmpty();
    }

}
