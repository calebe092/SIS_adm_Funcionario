package com.example.SIS_adm_Funcionario.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Funcionario {



    @NotNull
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @Pattern(regexp = "\\d{9,11}", message = "Você excedeu o limite de numeros !!")
    @Column(nullable = false)
    private String telefone;


    @NotBlank
    @Email(message = "O e-mail deve ser valido")
    @Column(unique = true, nullable = false)
    private String email;


    @CPF(message = "CFP inválido")
    @Column(nullable = false)
    private String cpf;


    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    private Double salario;


    @Size(max = 50)
    @Column(nullable = false)
    private String departamento;


    @Size(max = 90)
    @Column(nullable = false)
    private String cargo;

    public String getEmail() {
        return "";
    }

}

