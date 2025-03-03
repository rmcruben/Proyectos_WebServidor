<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Pelicula</title>
</head>
<body>
    <h2>Editar Pelicula</h2>
    <form action="index.php" method="POST">
        <input type="hidden" name="id" value="<?= $movieEditar['id'] ?>">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<?= $movieEditar['nombre'] ?>" required>
        <br>
        <label>Director:</label>
        <input type="text" name="tel" value="<?= $movieEditar['director'] ?>" required>
        <br>
        <input type="submit" name="actualizar" value="Actualizar">
    </form>
    <br>
    <a href="index.php">Volver</a>
</body>
</html>
