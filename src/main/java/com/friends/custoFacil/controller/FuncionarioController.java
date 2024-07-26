package com.friends.custoFacil.controller;


import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.dto.CadastroFuncionario;
import com.friends.custoFacil.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity<?> newFuncionario(@RequestBody @Valid CadastroFuncionario dados){
        try {
            funcionarioRepository.save(new Funcionario(dados));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Funcionario adicionado com sucesso");
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios(){
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> getFuncionarioId(@PathVariable Long id){
        return funcionarioRepository.findById(id);
    }

}
