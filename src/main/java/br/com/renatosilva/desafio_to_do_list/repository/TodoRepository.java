package br.com.renatosilva.desafio_to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renatosilva.desafio_to_do_list.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
