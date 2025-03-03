<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>La Filmoteca</title>
</head>
<body>
    <a href="index.php?listar"><h1>Ver peliculas</h1></a><br>

    <a href="index.php?insertar"><h1>Insertar peliculas</h1></a><br>
    
    <h2>Buscar Película</h2>
    <form action="index.php" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre_buscar">
        <br>
        <input type="submit" name="buscar" value="Buscar">
    </form>

</body>
</html>

