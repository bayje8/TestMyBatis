package com.example.demo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ActorJpa;

@Repository
public interface ActorRepository extends JpaRepository<ActorJpa, Long>{

}
