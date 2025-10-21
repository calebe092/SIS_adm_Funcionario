package com.adm.domain.service.funcionario;

import com.adm.domain.model.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IRegistroFuncionario {

    Funcionario salvar(Funcionario funcionario);

    void excluir(Long funcionarioId);

    List<Funcionario> listarFuncionario();

    Optional<Funcionario> buscarPorId(Long funcionarioId);
}