package com.example.SIS_adm_Funcionario.api.controller;

import com.example.SIS_adm_Funcionario.domain.exeption.NegocioExeption;
import com.example.SIS_adm_Funcionario.domain.model.Funcionario;
import com.example.SIS_adm_Funcionario.domain.repository.FuncionarioRepository;
import com.example.SIS_adm_Funcionario.domain.service.RegistroFuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private RegistroFuncionarioService registroFuncionarioService;


    @GetMapping
    public List<Funcionario> listarFuncionario(){
        return funcionarioRepository.findAll();
    }


    @GetMapping("{funcionarioId}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long funcionarioId){
          return funcionarioRepository.findById(funcionarioId)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus
    public Funcionario adicionarFuncionario(@Valid @RequestBody Funcionario funcionario){
        return registroFuncionarioService.salvar(funcionario);
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long funcionarioId,
                                                            @Valid @RequestBody Funcionario funcionario){
        if (!funcionarioRepository.existsById(funcionarioId)){
            return ResponseEntity.notFound().build();
        }

        funcionario.setId(funcionarioId);
        Funcionario funcionarioAtualizado = registroFuncionarioService.salvar(funcionario);

        return ResponseEntity.ok( funcionarioAtualizado);
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> removerFuncionario (@PathVariable Long funcionarioId){
        if (!funcionarioRepository.existsById(funcionarioId)){
            return ResponseEntity.notFound().build();
        }

        registroFuncionarioService.excluir(funcionarioId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity<String> captura (NegocioExeption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
