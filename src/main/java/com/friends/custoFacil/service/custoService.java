package com.friends.custoFacil.service;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.dto.AlterarCusto;
import com.friends.custoFacil.repository.CustoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class custoService {

    @Autowired
    CustoRepository custoRepository;

    public List<?> alteraCusto(Long id, AlterarCusto alteraCusto) {

        List<String> mudancas = new ArrayList<>();

        Custo custo = custoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Custo não encontrado: " + id));

        if (alteraCusto.getCategoriaCusto() != null){
            custo.setCategoriaCusto(alteraCusto.getCategoriaCusto());
            mudancas.add("Mudanca de categoria");
        }
        if (alteraCusto.getValor() > 0){
            custo.setValor(alteraCusto.getValor());
            mudancas.add("Mudanca de valor");
        }
        if (alteraCusto.getDataCusto() != null){
            custo.setDataCusto(alteraCusto.getDataCusto());
            mudancas.add("Mudanca de data");
        }
        if (alteraCusto.getStatusCusto() != null){
            custo.setStatusPagamento(alteraCusto.getStatusCusto());
            mudancas.add("Mudanca de status");
        }

        custoRepository.save(custo);
        return mudancas;

    }

}
