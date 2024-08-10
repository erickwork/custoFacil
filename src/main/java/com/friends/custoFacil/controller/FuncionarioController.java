package com.friends.custoFacil.controller;


import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.dto.CadastroFuncionario;
import com.friends.custoFacil.dto.retornoFuncionarios;
import com.friends.custoFacil.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ResponseEntity<?> newFuncionario(@RequestBody @Valid CadastroFuncionario dados){
        try {
            funcionarioRepository.save(new Funcionario(dados));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Funcionario adicionado com sucesso");
    }

    @GetMapping
    public List<retornoFuncionarios> getAllFuncionarios(){
        return funcionarioRepository.findAll().stream().map(retornoFuncionarios::new).toList();
    }

    @GetMapping("/{id}")
    public Optional<Funcionario> getFuncionarioId(@PathVariable Long id){
        return funcionarioRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inativarFuncionarioId(@PathVariable Long id){
        try{
            var funcionarioDados = funcionarioRepository.getReferenceById(id);
            funcionarioDados.inativarFuncionario();
            funcionarioRepository.save(funcionarioDados);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Funcionário foi Inativado com sucesso");
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> reativarFuncionarioId(@PathVariable Long id){
        try{
            var funcionarioDados = funcionarioRepository.getReferenceById(id);
            funcionarioDados.reativarFuncionario();
            funcionarioRepository.save(funcionarioDados);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Funcionário foi reativado com sucesso");
    }

}
