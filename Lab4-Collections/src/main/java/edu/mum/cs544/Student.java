package edu.mum.cs544;

import javax.persistence.*;

@Entity
public class Student {
  @Id
  private Integer studentId;
  private String name;
  private String email;
  private String password;

  public Student() {
  }

  public Student(Integer id, String name, String email, String password) {
    this.studentId = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public Integer getId() {
    return studentId;
  }

  public void setId(Integer id) {
    this.studentId = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}