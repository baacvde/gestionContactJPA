package com.gestionContactJPA;

import com.gestionContactJPA.entity.Contact;
import com.gestionContactJPA.service.ContactService;
import jakarta.servlet.http.HttpServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class Main extends HttpServlet {
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        contactService = new ContactService(); // Assurez-vous que ContactService est correctement implémenté.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            listContacts(request, response);
        } else if (action.equals("/create")) {
            request.getRequestDispatcher("/jsp/createContact.jsp").forward(request, response);
        } else if (action.startsWith("/update")) {
            showUpdateForm(request, response);
        } else if (action.startsWith("/delete")) {
            deleteContact(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if ("/create".equals(action)) {
            createContact(request, response);
        } else if ("/update".equals(action)) {
            updateContact(request, response);
        }
    }

    private void listContacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contact> contacts = contactService.getAllContacts();
        request.setAttribute("contacts", contacts);
        request.getRequestDispatcher("/jsp/listContacts.jsp").forward(request, response);
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        int age = Integer.parseInt(request.getParameter("age"));

        Contact contact = new Contact(nom, prenom, email, telephone, age);
        contactService.addContact(contact);

        response.sendRedirect(request.getContextPath() + "/contacts/");
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_contact"));
        Contact contact = contactService.getContact(id);
        request.setAttribute("contact", contact);
        request.getRequestDispatcher("/jsp/updateContact.jsp").forward(request, response);
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id_contact"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        int age = Integer.parseInt(request.getParameter("age"));

        Contact contact = contactService.getContact(id);
        contact.setNom(nom);
        contact.setPrenom(prenom);
        contact.setEmail(email);
        contact.setTelephone(telephone);
        contact.setAge(age);

        contactService.updateContact(contact);

        response.sendRedirect(request.getContextPath() + "/contacts/");
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id_contact"));
        contactService.deleteContact(id);
        response.sendRedirect(request.getContextPath() + "/contacts/");
    }

    public static class TestContact {
        public static void main(String[] args) {
            // Créer un contact
            Contact contact = new Contact("Dupont", "Jean", "jean.dupont@example.com", "0601020304", 30);

            // Ajouter le contact à la base de données
            ContactService contactService = new ContactService();
            contactService.addContact(contact);
            System.out.println("Contact ajouté avec succès !");

            // Mettre à jour le contact
            contact.setNom("Dupont");
            contact.setPrenom("Jean-Pierre");
            contact.setEmail("jean.pierre.dupont@example.com");
            contact.setTelephone("0605060708");
            contact.setAge(31);  // Changer l'âge, par exemple

            // Appeler la méthode pour mettre à jour le contact dans la base de données
            contactService.updateContact(contact);
            System.out.println("Contact mis à jour avec succès !");




            List<Contact> contacts = contactService.getAllContacts();
            System.out.println("Contacts"+contacts);

        }
    }



}
