package com.example.SIS_adm_Funcionario.domain.service;

import com.example.SIS_adm_Funcionario.domain.exeption.NegocioExeption;
import com.example.SIS_adm_Funcionario.domain.model.Funcionario;
import com.example.SIS_adm_Funcionario.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistroFuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

public Funcionario salvar(Funcionario funcionario){
    boolean emailEmUso = funcionarioRepository.findByEmail(funcionario.getEmail())
            .filter(p -> !p.equals(funcionario))
            .isPresent();

    if(emailEmUso){
        throw new NegocioExeption("Já existe um funcionario cadastrado com esse e-mail");
    }

    return funcionarioRepository.save(funcionario);
}

public void excluir(Long funcionarioId){
    funcionarioRepository.deleteById(funcionarioId);
}
}
