package com.friends.custoFacil.controller;


import com.friends.custoFacil.domain.Funcionario;
import com.friends.custoFacil.dto.CadastroFuncionario;
import com.friends.custoFacil.dto.retornoFuncionarioID;
import com.friends.custoFacil.dto.retornoFuncionarios;
import com.friends.custoFacil.enums.StatusFuncionario;
import com.friends.custoFacil.enums.StatusPagamento;
import com.friends.custoFacil.repository.CustoRepository;
import com.friends.custoFacil.repository.FuncionarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;
    StatusFuncionario statusFuncionario;
    @Autowired
    private CustoRepository custoRepository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Cadastra novo funcionario",
            description = "Este endpoint permite criar um novo funcionario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionario criado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Dados inválidos")
            }
    )
    public ResponseEntity<?> newFuncionario(@RequestBody @Valid CadastroFuncionario dados){
        try {
            funcionarioRepository.save(new Funcionario(dados));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Funcionario adicionado com sucesso");
    }

    @GetMapping
    @Operation(
            summary = "Busca todos funcionario",
            description = "Este endpoint permite busca todos os funcionarios independente do status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionarios encontrados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum funcionario foi encontrado")
            }
    )
    public List<retornoFuncionarios> getAllFuncionarios(){
        return funcionarioRepository.findAll().stream().map(retornoFuncionarios::new).toList();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca um funcionario por ID",
            description = "Este endpoint permite busca um funcionario pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Nenhum funcionario foi encontrado")
            }
    )
    public ResponseEntity<?> getFuncionarioId(@PathVariable Long id){
        try {
            if(funcionarioRepository.findById(id).isPresent()){
                var funcionario = funcionarioRepository.findById(id).stream().map(retornoFuncionarioID::new);
                return ResponseEntity.ok().body(funcionario);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Código inválido!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Deletar um funcionario por ID",
            description = "Este endpoint permite deletar um funcionario pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionario deletado"),
                    @ApiResponse(responseCode = "404", description = "Nenhum funcionario foi encontrado")
            }
    )
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
    @Operation(
            summary = "Alterar um funcionario por ID",
            description = "Este endpoint permite alterar um funcionario pelo seu ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionario alterado"),
                    @ApiResponse(responseCode = "404", description = "Nenhum funcionario foi encontrado")
            }
    )
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


    @GetMapping("/ativos")
    @Operation(
            summary = "Busca todos funcionario ativos",
            description = "Este endpoint permite busca todos os funcionarios ativos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionarios encontrados"),
                    @ApiResponse(responseCode = "404", description = "Nenhum funcionario foi encontrado")
            }
    )
    public List<Funcionario> getFuncionariosAtivos(){
        return funcionarioRepository.findByStatusFuncionario(statusFuncionario.ACTIVE);
    }


    @GetMapping("/{id}/{idPagamento}")
    public ResponseEntity<?> listarCustoFuncionarioStatus (@PathVariable Long id, @PathVariable Long idPagamento){
        return ResponseEntity.ok().body(custoRepository.findCustoByIdFuncionarioAndStatusPagamento(id, StatusPagamento.fromId(idPagamento)));
    }


}
