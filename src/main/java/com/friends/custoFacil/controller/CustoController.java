package com.friends.custoFacil.controller;

import com.friends.custoFacil.domain.Custo;
import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.dto.AlterarCusto;
import com.friends.custoFacil.dto.cadastroCusto;
import com.friends.custoFacil.enums.StatusPagamento;
import com.friends.custoFacil.repository.CustoRepository;
import com.friends.custoFacil.repository.FuncionarioRepository;
import com.friends.custoFacil.service.custoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Cadastra novo custo",
            description = "Este endpoint permite cadastrar novo custo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado"),
                    @ApiResponse(responseCode = "404", description = "dados inválidos")
            }
    )
    public ResponseEntity<?> novoCusto(@RequestBody @Valid cadastroCusto cadastroCusto) {
        try{
            custoRepository.save(new Custo(cadastroCusto));
            return ResponseEntity.ok().body("Custo adicionado com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Erro ao adicionar o custo: " + e.getMessage());
        }
    }


    @GetMapping
    @Operation(
            summary = "Listar todos os custos",
            description = "Este endpoint listar todos os custo independente do status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Custos encontrados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum custo encontrado")
            }
    )
    public ResponseEntity<?> listarCustos() {
            return ResponseEntity.ok().body(custoRepository.findAll());
    }


    @PostMapping("/alterar/{id}")
    @Transactional
    @Operation(
            summary = "Alterar custo por ID",
            description = "Este endpoint alterar um custo por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Custo alterado"),
                    @ApiResponse(responseCode = "404", description = "Nenhum custo encontrado")
            }
    )
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
    @Operation(
            summary = "Deletar custo por ID",
            description = "Este endpoint deletar um custo por id, desde que ele não esteja concluído",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Custo deletado"),
                    @ApiResponse(responseCode = "404", description = "Nenhum custo encontrado")
            }
    )
    public ResponseEntity<?> excluirCusto(@PathVariable Long id){
        try{
            return custoService.excluirCusto(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/funcionario/{id}")
    @Operation(
            summary = "Listar custo por ID do funcionario",
            description = "Este endpoint listar os custos pelo ID do funcionario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Custos listados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum custo encontrado")
            }
    )
    public ResponseEntity<?> listarCustoPorFuncionario(@PathVariable Long id){

        try {
            Funcionario funcionario = funcionarioRepository.findById(id)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Funcionário não encontrado: " + id));

            List<Custo> listaDeCusto = custoRepository.findCustoByIdFuncionario(id);

            if (listaDeCusto.isEmpty()){
                return ResponseEntity.ok().body("Funcionário não possui custo");
            }

            return ResponseEntity.ok().body(listaDeCusto);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/funcionario/{id}/concluido")
    @Operation(
            summary = "Listar custo concluídos por ID do funcionario",
            description = "Este endpoint listar os custos concluídos pelo ID do funcionario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Custos listados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum custo encontrado")
            }
    )
    public ResponseEntity<?> listarCustoConcluidoFuncionario (@PathVariable Long id){
        try {
            List<Custo> custoConcluido = custoRepository.findCustoByIdFuncionarioAndStatusPagamento(id, StatusPagamento.CONCLUIDO);

            if (custoConcluido.isEmpty()){
                return ResponseEntity.ok().body("Funcionário não possui custo concluído");
            }

            return ResponseEntity.ok().body(custoConcluido);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}