package com.spring.jpa.postgresql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jpa.postgresql.model.Alumno;
import com.spring.jpa.postgresql.repository.AlumnoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AlumnoController {

	@Autowired
	AlumnoRepository alumnoRepository;

	@GetMapping("/alumnos")
	public ResponseEntity<List<Alumno>> getAllAlumnos(@RequestParam(required = false) String nombre) {
		try {
			List<Alumno> alumnos = new ArrayList<Alumno>();

			if (nombre == null)
				alumnoRepository.findAll().forEach(alumnos::add);
			else
				alumnoRepository.findByNombreContaining(nombre).forEach(alumnos::add);

			if (alumnos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alumnos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> getAlumnoById(@PathVariable("id") long id) {
		Optional<Alumno> alumnoData = alumnoRepository.findById(id);

		if (alumnoData.isPresent()) {
			return new ResponseEntity<>(alumnoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/alumnos")
	public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) {
		try {
			Alumno _alumno = alumnoRepository
					.save(new Alumno(alumno.getNombre(), alumno.getApellidos(), alumno.getEmail()));
			return new ResponseEntity<>(_alumno, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> updateAlumno(@PathVariable("id") long id, @RequestBody Alumno alumno) {
		Optional<Alumno> alumnoData = alumnoRepository.findById(id);

		if (alumnoData.isPresent()) {
			Alumno _alumno = alumnoData.get();
			_alumno.setNombre(alumno.getNombre());
			_alumno.setApellidos(alumno.getApellidos());
			_alumno.setEmail(alumno.getEmail());
			return new ResponseEntity<>(alumnoRepository.save(_alumno), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<HttpStatus> deleteAlumno(@PathVariable("id") long id) {
		try {
			alumnoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/alumnos")
	public ResponseEntity<HttpStatus> deleteAllAlumnos() {
		try {
			alumnoRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	


}
