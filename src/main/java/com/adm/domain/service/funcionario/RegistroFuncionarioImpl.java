package com.adm.domain.service.funcionario;

import com.adm.domain.exeption.BisnessExeption;
import com.adm.domain.exeption.NegocioExeption;
import com.adm.domain.model.Funcionario;
import com.adm.domain.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistroFuncionarioImpl implements IRegistroFuncionario {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        boolean emailEmUso = funcionarioRepository.findByEmail(funcionario.getEmail())
                .filter(p -> !p.equals(funcionario))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioExeption("Já existe um funcionario cadastrado com esse e-mail");
        }

        return funcionarioRepository.save(funcionario);
    }

    public void excluir(Long funcionarioId) {
        funcionarioRepository.deleteById(funcionarioId);
    }

    @Override
    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long funcionarioId) {
        return Optional.ofNullable(funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new BisnessExeption(funcionarioId)));
    }

}


