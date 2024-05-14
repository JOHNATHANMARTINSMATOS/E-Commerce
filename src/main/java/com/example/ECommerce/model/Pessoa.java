package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Pessoa")
public class Pessoa extends BaseModel {

    @Column(name="Nome",length = 100,nullable = false)
    private String nome;

    @Column(name = "data_inicio",nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim",nullable = false)
    private LocalDateTime dataFim;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "pessoa_id")
    public List<Contato> contatos;

    @OneToOne(mappedBy = "pessoa")
    private PessoaJuridica pj;

    @OneToOne(mappedBy = "pessoa")
    private PessoaFisica pf;

}
