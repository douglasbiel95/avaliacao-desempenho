package com.douglasgabriel.avaliacaodesempenho.users.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(nullable = false)
    private final String nome;

    @Column(nullable = false, unique = true)
    private final String nomeUsuario;

    @Column(nullable = false, unique = true)
    private final String email;

    @Column(nullable = false)
    private final String senha;


}
