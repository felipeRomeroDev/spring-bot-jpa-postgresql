package com.spring.jpa.postgresql.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "apellidos")
  private String apellidos;

  @Column(name = "email")
  private String email;

  public Alumno() {

  }

  public Alumno(String nombre, String apellidos, String email) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
	    this.email = email;
	  }


  @Override
  public String toString() {
    return "Alumno [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + "]";
  }

}
