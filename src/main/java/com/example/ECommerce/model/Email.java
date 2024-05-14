package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Email")
public class Email extends BaseModel {

    @Column(name = "Endereco_Email",nullable = false)
    private String enderecoDeEmail;

    @Column(name = "data_inicio",nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim",nullable = false)
    private LocalDateTime dataFim;

    @ManyToOne // Muitos telefones para um contato
    @JoinColumn(name = "contato_id") // Chave estrangeira para contato
    private Contato contato;
}