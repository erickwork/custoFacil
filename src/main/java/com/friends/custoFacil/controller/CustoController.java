package com.friends.custoFacil.controller;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.dto.cadastroCusto;
import com.friends.custoFacil.repository.CustoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custo")
public class CustoController {

    @Autowired
    CustoRepository custoRepository;

    @PostMapping
    public ResponseEntity<?> novoCusto(@RequestBody @Valid cadastroCusto cadastroCusto) {
        try{
            custoRepository.save(new Custo(cadastroCusto));
            return ResponseEntity.ok().body("Custo adicionado com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Erro ao adicionar o custo: " + e.getMessage());
        }
    }


}
