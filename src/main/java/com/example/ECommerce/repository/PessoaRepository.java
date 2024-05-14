package com.example.ECommerce.repository;

import com.example.ECommerce.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa,Integer>{
}
