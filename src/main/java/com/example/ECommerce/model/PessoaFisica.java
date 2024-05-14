package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "PessoaFisica")
public class PessoaFisica extends BaseModel {

    @Column(name="CPF",length = 11,nullable = false)
    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id",referencedColumnName = "id")
    private Pessoa pessoa;

}
