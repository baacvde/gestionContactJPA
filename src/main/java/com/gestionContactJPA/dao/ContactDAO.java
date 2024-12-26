package com.gestionContactJPA.dao;

import com.gestionContactJPA.entity.Contact;
import com.gestionContactJPA.config.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ContactDAO {
    private final EntityManagerFactory entityManagerFactory;

    public ContactDAO() {
        entityManagerFactory = HibernateUtil.getEntityManagerFactory(); // Cette méthode doit vous retourner un EntityManagerFactory.
    }

    // Ajouter un contact
    public void saveContact(Contact contact) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // Initialisation de l'EntityManager
            entityManager = entityManagerFactory.createEntityManager();

            // Démarrage de la transaction
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Persistance de l'entité
            entityManager.persist(contact);

            // Validation de la transaction
            transaction.commit();
            System.out.println("Contact ajouté avec succès.");
        } catch (Exception e) {
            // Gestion de l'exception
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Erreur lors de l'ajout du contact : " + e.getMessage());
            throw e;
        } finally {
            // Fermeture de l'EntityManager
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    // Mettre à jour un contact existant
    public void updateContact(Contact contact) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Trouver le contact existant par son ID
            Contact existingContact = entityManager.find(Contact.class, contact.getId());

            if (existingContact != null) {
                // Mettre à jour les informations du contact existant
                existingContact.setNom(contact.getNom());
                existingContact.setPrenom(contact.getPrenom());
                existingContact.setEmail(contact.getEmail());
                existingContact.setTelephone(contact.getTelephone());
                existingContact.setAge(contact.getAge());

                // Le `merge` va mettre à jour l'entité avec les nouvelles valeurs
                entityManager.merge(existingContact);
            } else {
                System.out.println("Contact avec l'ID " + contact.getId() + " non trouvé !");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            System.err.println("Erreur lors de la mise à jour du contact : " + e.getMessage());
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    // Récupérer un contact par son ID
    public Contact getContact(Long id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = null;
        Contact contact = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            contact = entityManager.find(Contact.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.err.println("Erreur lors de la récupération du contact : " + e.getMessage());
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return contact;
    }

    // Récupérer tous les contacts
    public List<Contact> getAllContacts() {
        EntityTransaction transaction = null;
        EntityManager entityManager = null;
        List<Contact> contacts = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            contacts = entityManager.createQuery("from Contact", Contact.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.err.println("Erreur lors de la récupération des contacts : " + e.getMessage());
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return contacts;
    }

    // Supprimer un contact par son ID
    public void deleteContact(Long id) {
        EntityTransaction transaction = null;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            Contact contact = entityManager.find(Contact.class, id);
            if (contact != null) {
                entityManager.remove(contact);
            } else {
                System.err.println("Le contact avec l'ID " + id + " n'existe pas.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            System.err.println("Erreur lors de la suppression du contact : " + e.getMessage());
            throw e;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
