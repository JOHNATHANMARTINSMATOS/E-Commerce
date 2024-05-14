package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Telefone")

public class Telefone extends BaseModel {

    @Column(name = "DDD",length = 3,nullable = false)
    private String ddd;

    @Column(name = "Numero",length = 9,nullable = false)
    private String numero;

    @Column(name = "data_inicio",nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim",nullable = false)
    private LocalDateTime dataFim;


    @ManyToOne // Muitos telefones para um contato
    @JoinColumn(name = "contato_id") // Chave estrangeira para contato
    private Contato contato;
}
