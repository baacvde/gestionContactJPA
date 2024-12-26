package com.gestionContactJPA.service;

import com.gestionContactJPA.dao.ContactDAO;
import com.gestionContactJPA.entity.Contact;

import java.util.List;

public class ContactService {
    private ContactDAO contactDAO;

    public ContactService() {
        contactDAO = new ContactDAO();
    }

    // Ajouter un nouveau contact
    public void addContact(Contact contact) {
        if (contact.getId() != null) {
            throw new IllegalArgumentException("Un nouveau contact ne doit pas avoir d'ID défini.");
        }
        contactDAO.saveContact(contact);
    }

    // Récupérer un contact par son ID
    public Contact getContact(Long id_contact) {
        Contact contact = contactDAO.getContact(id_contact);
        if (contact == null) {
            throw new RuntimeException("Contact avec ID " + id_contact + " non trouvé.");
        }
        return contact;
    }

    // Récupérer tous les contacts
    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    // Supprimer un contact
    public void deleteContact(Long id_contact) {
        if (contactDAO.getContact(id_contact) == null) {
            throw new RuntimeException("Impossible de supprimer. Contact avec ID " + id_contact + " non trouvé.");
        }
        contactDAO.deleteContact(id_contact);
    }

    // Mettre à jour un contact existant
    public void updateContact(Contact contact) {
        if (contact.getId() == null || contactDAO.getContact(contact.getId()) == null) {
            throw new RuntimeException("Impossible de mettre à jour. Contact avec ID " + contact.getId() + " non trouvé.");
        }
        contactDAO.updateContact(contact); // Différencier de saveContact pour plus de clarté
    }
}
