package com.friends.custoFacil.repository;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustoRepository extends JpaRepository<Custo, Long> {

    List<Custo> findCustoByIdFuncionario(Long idFuncionario);

    List<Custo> findCustoByIdFuncionarioAndStatusPagamento(Long idFuncionario, StatusPagamento statusPagamento);
}
