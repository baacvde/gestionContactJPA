<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Modifier un Contact</title>
</head>
<body>
<h1>Modifier un Contact</h1>
<form action="update" method="post">
  <input type="hidden" name="id" value="${contact.id}">
  <label>Nom :</label>
  <input type="text" name="nom" value="${contact.nom}" required><br>
  <label>Prénom :</label>
  <input type="text" name="prenom" value="${contact.prenom}" required><br>
  <label>Email :</label>
  <input type="email" name="email" value="${contact.email}" required><br>
  <label>Téléphone :</label>
  <input type="text" name="telephone" value="${contact.telephone}" required><br>
  <label>Âge :</label>
  <input type="number" name="age" value="${contact.age}" required><br>
  <button type="submit">Mettre à jour</button>
</form>
<a href="..">Retour à la liste</a>
</body>
</html>
