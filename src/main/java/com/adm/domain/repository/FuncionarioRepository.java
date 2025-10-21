package com.adm.domain.repository;

import com.adm.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
     Optional<Funcionario> findByEmail(String email);
}
