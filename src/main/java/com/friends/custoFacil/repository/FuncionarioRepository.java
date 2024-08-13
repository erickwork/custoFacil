package com.friends.custoFacil.repository;

import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.enums.StatusFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByStatusFuncionario(StatusFuncionario status);
}
