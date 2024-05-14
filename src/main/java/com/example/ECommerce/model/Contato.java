package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "Contato")

public class Contato extends BaseModel {

    @Column(name = "data_inicio",nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim",nullable = false)
    private LocalDateTime dataFim;

    @ManyToOne // Muitos contatos para uma pessoa
    @JoinColumn(name = "pessoa_id") // Chave estrangeira para pessoa
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="pessoa_id")
    private List<Email> emails = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="pessoa_id")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="pessoa_id")
    private List<Telefone> telefones = new ArrayList<>();


}
