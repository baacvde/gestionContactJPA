<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Créer un Contact</title>
</head>
<body>
<h1>Créer un Contact</h1>
<form action="create" method="post">
  <label>Nom :</label>
  <input type="text" name="nom" required><br>
  <label>Prénom :</label>
  <input type="text" name="prenom" required><br>
  <label>Email :</label>
  <input type="email" name="email" required><br>
  <label>Téléphone :</label>
  <input type="text" name="telephone" required><br>
  <label>Âge :</label>
  <input type="number" name="age" required><br>
  <button type="submit">Créer</button>
</form>
<a href="..">Retour à la liste</a>
</body>
</html>
