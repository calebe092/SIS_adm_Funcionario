package com.adm.api.controller;

import com.adm.domain.exeption.NegocioExeption;
import com.adm.domain.model.Funcionario;
import com.adm.domain.repository.FuncionarioRepository;
import com.adm.domain.service.funcionario.IRegistroFuncionario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private IRegistroFuncionario registroFuncionarioService;


    @GetMapping
    public List<Funcionario> listarFuncionario() {
        return registroFuncionarioService.listarFuncionario();
    }


    @GetMapping("{funcionarioId}")
    public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable Long funcionarioId) {
        return registroFuncionarioService.buscarPorId(funcionarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus
    public Funcionario adicionarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return registroFuncionarioService.salvar(funcionario);
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long funcionarioId,
                                                            @Valid @RequestBody Funcionario funcionario) {
        if (!funcionarioRepository.existsById(funcionarioId)) {
            return ResponseEntity.notFound().build();
        }

        funcionario.setId(funcionarioId);
        Funcionario funcionarioAtualizado = registroFuncionarioService.salvar(funcionario);

        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> removerFuncionario(@PathVariable Long funcionarioId) {
        if (!funcionarioRepository.existsById(funcionarioId)) {
            return ResponseEntity.notFound().build();
        }

        registroFuncionarioService.excluir(funcionarioId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity<String> captura(NegocioExeption e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
