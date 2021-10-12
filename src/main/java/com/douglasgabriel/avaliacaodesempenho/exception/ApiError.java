package com.douglasgabriel.avaliacaodesempenho.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ApiError {

    private final int codigoErro;

    private final String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime timestamp;

    private final String mensagemErro;

    private final List<String> listaErros;
}
