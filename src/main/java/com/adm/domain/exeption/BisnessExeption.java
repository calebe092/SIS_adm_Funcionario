package com.adm.domain.exeption;

public class BisnessExeption extends RuntimeException {

    public BisnessExeption(String messagem) {
        super(messagem);
    }

    public BisnessExeption(Long id) {
        this(String.format("Não existe cadastro com o código %d", id));
    }
}
