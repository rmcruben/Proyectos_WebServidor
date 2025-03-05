<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>La Filmoteca</title>
    <link rel="icon" type="image/x-icon" href="/PeliculasMVC/img/faviPeli.png">
</head>

<body>
    <a href="index.php?listar">
        <h1>Ver peliculas</h1>
    </a><br>

    <a href="index.php?insertar">
        <h1>Insertar peliculas</h1>
    </a><br>

    <h2>Buscar Película Por Nombre</h2>
    <form action="index.php" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre_buscar">
        <br>
        <input type="submit" name="buscar" value="Buscar">
    </form><br>
    <h2>Buscar Película Por Género</h2>
    <form action="index.php?buscarGenero" method="POST">
        <?php foreach ($genres as $indice => $genre): ?>
            <input type="radio" id="genero_buscar_<?php echo $indice; ?>" name="genero_buscar" value="<?= $genre['nombre'] ?>" required>
            <label for="genero_buscar_<?php echo $indice; ?>"><?= $genre['nombre'] ?></label>
            <br>
        <?php endforeach; ?>
        <input type="submit" name="buscarGenero" value="Buscar">
    </form><br>

</body>

</html>