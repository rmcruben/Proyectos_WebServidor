<?php
require_once("models/movie_model.php");
$pel = new movie_model();

// Insertar 
if (isset($_POST['guardar'])) {
    $nombre = $_POST['nombre'];
    $director = $_POST['director'];
    $img= $_POST['img'];
    $pel->insertar_movies($nombre, $director, $img);
    header("Location: index.php");
}
if (isset($_GET['insertar'])) {
    require_once("views/insert_view.php");
    exit();
}
if (isset($_GET['listar'])) {
    $datos = $pel->get_filmography();
    require_once("views/list_view.php");
    exit();
} 
// Buscar
if (isset($_POST['buscar'])) {
    $nombre = $_POST['nombre_buscar'];
    $datos = $pel->buscar_movies($nombre);
    require_once("views/list_view.php");
    exit();
} 

// Eliminar 
if (isset($_GET['eliminar'])) {
    $id = $_GET['eliminar'];
    $pel->eliminar_movies($id);
    header("Location: index.php");
}

// Editar
if (isset($_GET['editar'])) {
    $id = $_GET['editar'];
    $movieEditar = $pel->get_movies($id);
    require_once("views/edit_movie_view.php");
    exit();
}

// Actualiza
if (isset($_POST['actualizar'])) {
    $id = $_POST['id'];
    $nombre = $_POST['nombre'];
    $tel = $_POST['tel'];
    $pel->actualizar_movies($id, $nombre, $tel);
    header("Location: index.php");
}

// carga la vista principal
require_once("views/home_view.php");
?>
