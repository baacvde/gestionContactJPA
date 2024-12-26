<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Liste des Contacts</title>
</head>
<body>
<h1>Liste des Contacts</h1>
<a href="contacts/create">Ajouter un contact</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Prénom</th>
    <th>Email</th>
    <th>Téléphone</th>
    <th>Âge</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="contact" items="${contacts}">
    <tr>
      <td>${contact.id}</td>
      <td>${contact.nom}</td>
      <td>${contact.prenom}</td>
      <td>${contact.email}</td>
      <td>${contact.telephone}</td>
      <td>${contact.age}</td>
      <td>
        <a href="contacts/update?id=${contact.id}">Modifier</a>
        <a href="contacts/delete?id=${contact.id}">Supprimer</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
