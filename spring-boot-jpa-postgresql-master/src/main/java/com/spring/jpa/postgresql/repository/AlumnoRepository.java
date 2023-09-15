package com.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.postgresql.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
  List<Alumno> findByNombreContaining(String title);
}
