package com.friends.custoFacil.controller;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.dto.cadastroCusto;
import com.friends.custoFacil.repository.CustoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custo")
public class CustoController {

    @Autowired
    CustoRepository custoRepository;

    @PostMapping
    @Transactional // Diz que é uma transação
    public ResponseEntity<?> novoCusto(@RequestBody @Valid cadastroCusto cadastroCusto) {
        try{
            custoRepository.save(new Custo(cadastroCusto));
            return ResponseEntity.ok().body("Custo adicionado com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Erro ao adicionar o custo: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> listarCustos() {
            return ResponseEntity.ok().body(custoRepository.findAll());
    }


}