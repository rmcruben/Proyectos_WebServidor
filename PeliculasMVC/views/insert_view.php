<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Insertar Pelicula</title>
</head>
<body>
    <h2>Editar Pelicula</h2>
    <form action="index.php" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre" required>
        <br>
        <label>Director:</label>
        <input type="text" name="director" required>
        <br>
        <label>Ruta imagen poster:</label>
        <input type="text" name="img" required>
        <br>
        <input type="submit" name="guardar" value="Guardar">
    </form>
    <br>
    <a href="index.php">Volver</a>
</body>
</html>