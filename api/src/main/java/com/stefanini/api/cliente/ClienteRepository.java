package com.stefanini.api.cliente;


import org.springframework.data.jpa.repository.JpaRepository;

//Interface que herda de JpaRepository, com todos os métodos dessa interface
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);
}
