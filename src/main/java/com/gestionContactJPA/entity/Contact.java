package com.gestionContactJPA.entity;

import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contact;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int age;

    // Constructeur par défaut
    public Contact() {}

    // Constructeur avec tous les champs sauf id
    public Contact(String nom, String prenom, String email, String telephone, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }

    // Getters et Setters
    public Long getId() {
        return id_contact;
    }

    public void setId(Long id_contact) {
        this.id_contact = id_contact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id_contact +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", age=" + age +
                '}';
    }
}
