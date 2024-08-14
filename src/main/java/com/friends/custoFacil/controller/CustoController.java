package com.friends.custoFacil.controller;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.dto.AlterarCusto;
import com.friends.custoFacil.dto.cadastroCusto;
import com.friends.custoFacil.repository.CustoRepository;
import com.friends.custoFacil.repository.FuncionarioRepository;
import com.friends.custoFacil.service.custoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custo")
public class CustoController {

    @Autowired
    CustoRepository custoRepository;

    @Autowired
    private custoService custoService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;


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


    @PostMapping("/alterar/{id}")
    public ResponseEntity<?> alterarCusto(@PathVariable Long id, @RequestBody AlterarCusto alteraCusto) {
        try {
            List<String> mudancas = (List<String>) custoService.alteraCusto(id, alteraCusto);
            return ResponseEntity.ok().body(mudancas);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluirCusto(@PathVariable Long id){
        try{
            return custoService.excluirCusto(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarCustoPorFuncionario(@PathVariable Long id){

        try {
            Funcionario funcionario = funcionarioRepository.findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Funcionário não encontrado: " + id));

            List<Custo> listaDeCusto = custoRepository.findCustoByIdFuncionario(id);

            if (listaDeCusto.isEmpty()){
                return ResponseEntity.ok().body("Usuário não possui custo");
            }

            return ResponseEntity.ok().body(listaDeCusto);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}