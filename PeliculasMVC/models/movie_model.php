<?php
class movie_model{
    private $db;
    private $movs;
    public function __construct()
    {
        $this->db=Conectar::conexion();
        $this->movs = array();
    }

    public function get_filmography() {
        $consulta = $this->db->query("SELECT * FROM peliculas");
        while ($filas = $consulta->fetch_assoc()) {
            $this->movs[] = $filas;
        }
        return $this->movs;
    }
    public function insertar_movies($nombre, $director, $img) {
        $sql = "INSERT INTO peliculas (nombre, director, img ) VALUES ('$nombre', '$director','$img')";
        return $this->db->query($sql);
    }

    public function eliminar_movies($id) {
        $sql = "DELETE FROM peliculas WHERE id = $id";
        return $this->db->query($sql);
    }

    public function get_movies($id) {
        $consulta = $this->db->query("SELECT * FROM peliculas WHERE id = $id");
        return $consulta->fetch_assoc();
    }

    public function actualizar_movies($id, $nombre, $director) {
        $sql = "UPDATE peliculas SET nombre = '$nombre', director = '$director' WHERE id = $id";
        return $this->db->query($sql);
    }

    public function buscar_movies($nombre) {
        $consulta = $this->db->query("SELECT * FROM peliculas WHERE nombre LIKE '%$nombre%';");
        while ($filas = $consulta->fetch_assoc()) {
            $this->movs[] = $filas;
        }
        return $this->movs;
    }
}
