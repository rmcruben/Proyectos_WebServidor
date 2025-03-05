<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de peliculas</title>
    <link rel="icon" type="image/x-icon" href="/PeliculasMVC/img/faviPeli.png">
</head>

<body>
    <table border="1">
        <tr>
            <th>Poster</th>
            <th>Titulo</th>
            <th>Director</th>
            <th>Género</th>
            <th>Formatos Disponibles</th>
        </tr>
        <?php if (!empty($datos)): ?>
            <?php foreach ($datos as $dato): ?>
                <tr>
                    <td><img src="<?= htmlspecialchars($dato['img']) ?>" alt="poster" style="height: 200px;"></td>
                    <td><?= htmlspecialchars($dato['nombre']) ?></td>
                    <td><?= htmlspecialchars($dato['director']) ?></td>
                    <td><?= htmlspecialchars($dato['genero']) ?></td>
                    <td><?= htmlspecialchars($dato['formatos']) ?></td>
                    <td><a href="index.php?editar=<?= $dato['id'] ?>">Editar</a><br>
                        <a href="index.php?eliminar=<?= $dato['id'] ?>">Eliminar</a>
                    </td>
                </tr>
            <?php endforeach; ?>
        <?php else: ?>
            <tr>
                <td colspan="3">No hay películas disponibles.</td>
            </tr>
        <?php endif; ?>
    </table>
    <a href="index.php">Volver al inicio</a>

</body>

</html>