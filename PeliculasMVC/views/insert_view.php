<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Insertar Pelicula</title>
    <link rel="icon" type="image/x-icon" href="/PeliculasMVC/img/faviPeli.png">
</head>

<body>
    <h2>Insertar Pelicula</h2>
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

        <label>Genero: </label>
        <?php foreach ($genres as $indice => $genre): ?>
            <input type="radio" id="genero_<?php echo $indice; ?>" name="genero" value="<?= $genre['nombre'] ?>" required>
            <label for="genero_<?php echo $indice; ?>"><?= $genre['nombre'] ?></label>
            <br>
        <?php endforeach; ?>

        <label>Formatos disponibles:</label><br>
        <input type="checkbox" name="formatos[]" value="DVD"> DVD<br>
        <input type="checkbox" name="formatos[]" value="Blu-Ray"> Blu-Ray<br>
        <input type="checkbox" name="formatos[]" value="Digital"> Digital<br>
        <input type="submit" name="guardar" value="Guardar">
    </form>
    <br>
    <a href="index.php">Volver</a>
</body>

</html>