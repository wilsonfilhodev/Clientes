package com.will.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
