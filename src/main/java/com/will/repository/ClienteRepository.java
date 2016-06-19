package com.will.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
