package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Table(name="Pessoa_Juridica")
public class PessoaJuridica extends BaseModel {

    @Column(name="CNPJ", length = 14, nullable = false)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id",referencedColumnName = "id")
    private Pessoa pessoa;


}