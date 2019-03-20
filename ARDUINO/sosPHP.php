<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$link = mysqli_connect("127.0.0.1", "root", "", "sos");

if (!$link) {
    echo "Error: Unable to connect to MySQL." . PHP_EOL;
    echo "Debugging errno: " . mysqli_connect_errno() . PHP_EOL;
    echo "Debugging error: " . mysqli_connect_error() . PHP_EOL;
    exit;
}

echo "Success: A proper connection to MySQL was made! The my_db database is great." . PHP_EOL;
echo "Host information: " . mysqli_get_host_info($link) . PHP_EOL;

$serie = $_REQUEST['serie'];

$query = "SELECT * FROM arduino WHERE serie_ard=" . $serie;

if ($result = mysqli_query($link, $query)) {
    while ($row = mysqli_fetch_row($result)) {
        $idarduino = $row[0];
        $serie_ard = $row[1];
        $usuario = $row[2];
        $latitude = $row[3];
        $longitude = $row[4];
    }
    /* free result set */
    mysqli_free_result($result);
}

$query = "SELECT * FROM ocorrencia WHERE nome_ocorrencia='RESIDENCIA'";

if ($result = mysqli_query($link, $query)) {
    while ($row = mysqli_fetch_row($result)) {
        $idocorrencia = $row[0];
        $nome_ocorrencia = $row[1];
    }
    /* free result set */
    mysqli_free_result($result);
}

echo "<h1>" . "DADOS DO ARDUINO" . "</h1>";

echo "<br>ID: " . $idarduino;
echo "<br>SERIE: " . $serie_ard;
echo "<br>USUARIO: " . $usuario;
echo "<br>LATITUDE: " . $latitude;
echo "<br>LONGITUDE: " . $longitude;

echo "<h1>" . "DADOS DA OCORRENCIA" . "</h1>";

echo "<br>ID: " . $idocorrencia;
echo "<br>NOME: " . $nome_ocorrencia;

$query = "INSERT INTO sos (data_sos, hora_sos, usuario, ocorrencia, latitude_sos, longitude_sos, descricao_sos, atendido_sos, vizualizado_sos, cancelar) "
        . "VALUES (?,?,?,?,?,?,?,?,?,?)";
$stmt = mysqli_prepare($link, $query);

mysqli_stmt_bind_param($stmt, "ssssssssss", $v1, $v2, $v3, $v4, $v5, $v6, $v7, $v8, $v9, $v10);

$v1 = date("Y-m-d");
$v2 = date("H:i:s");;
$v3 = $usuario;
$v4 = $idocorrencia;
$v5 = $latitude;
$v6 = $longitude;
$v7 = 'residencia';
$v8 = 0;
$v9 = 0;
$v10 = 0;

/* Execute the statement */
mysqli_stmt_execute($stmt);


mysqli_close($link);