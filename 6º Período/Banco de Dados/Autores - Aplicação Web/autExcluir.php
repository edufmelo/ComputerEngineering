<!DOCTYPE html>
<!-------------------------------------------------------------------------------
    Desenvolvimento Web
    PUCPR
    Profa. Cristina V. P. B. Souza
    Agosto/2022
---------------------------------------------------------------------------------->
<!-- medExcluir.php -->


<html>
<head>
    <title>Editora </title>
    <link rel="icon" type="image/png" href="imagens/favicon.png"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="css/customize.css">
</head>
<body onload="w3_show_nav('menuAutor')">

<?php require 'geral/menu.php';?>
<?php require 'bd/conectaBD.php'; ?>

<div class="w3-main w3-container" style="margin-left:270px;margin-top:117px;">
    <div class="w3-panel w3-padding-large w3-card-4 w3-light-grey">
        <p class="w3-large">
            <div class="w3-code cssHigh notranslate">
                <?php
                date_default_timezone_set("America/Sao_Paulo");
                $data = date("d/m/Y H:i:s", time());
                echo "<p class='w3-small'>Acesso em: $data</p>";
                ?>

                <?php
                $conn = mysqli_connect($servername, $username, $password, $database);

                if (!$conn) {
                    die("<strong> Falha de conexão: </strong>" . mysqli_connect_error());
                }
                
                mysqli_query($conn,"SET NAMES 'utf8'");
                mysqli_query($conn,'SET character_set_connection=utf8');
                mysqli_query($conn,'SET character_set_client=utf8');
                mysqli_query($conn,'SET character_set_results=utf8');

                $id = $_GET['id'];

                $sql = "SELECT ID_Autor, Dt_Fale, Nome, Nacionalidade, Foto, Dt_Nasc 
                        FROM Autor AS A 
                        INNER JOIN Nacionalidade AS N ON (A.ID_Nacionalidade = N.ID_Nacionalidade) 
                        WHERE ID_Autor = $id;";

                echo "<div class='w3-responsive w3-card-4'>";  
                if ($result = mysqli_query($conn, $sql)) {
                    if (mysqli_num_rows($result) == 1) {
                        $row = mysqli_fetch_assoc($result);
                        
                        // Formatação da Data de Nascimento
                        $dataN = explode('-', $row["Dt_Nasc"]);
                        $nova_data = $dataN[2] . '/' . $dataN[1] . '/' . $dataN[0];

                        // Formatação da Data de Falecimento
                        if (!is_null($row["Dt_Fale"])) {
                            $dataF = explode('-', $row["Dt_Fale"]);
                            $nova_data_fale = $dataF[2] . '/' . $dataF[1] . '/' . $dataF[0];
                        } else {
                            $nova_data_fale = "Não disponível"; // Caso não haja data de falecimento
                        }

                        

                        ?>
                        <div class="w3-container w3-teal">
                            <h2>Exclusão do Autor Cód. = [<?php echo $row['ID_Autor']; ?>]</h2>
                        </div>
                        <form class="w3-container" action="autExcluir_exe.php" method="post" onsubmit="return check(this.form)">
                            <input type="hidden" id="Id" name="Id" value="<?php echo $row['ID_Autor']; ?>">
                            <p>
                            <label class="w3-text-IE"><b>Nome: </b> <?php echo $row['Nome']; ?> </label></p>
                            <p>
                            <label class="w3-text-IE"><b>Data de Nascimento: </b><?php echo $nova_data; ?></label></p>
                            <p>
                            <label class="w3-text-IE"><b>Data de Falecimento: </b><?php echo $nova_data_fale; ?></label></p>
                            <p>
                            <label class="w3-text-IE"><b>Nacionalidade: </b><?php echo $row['Nacionalidade']; ?></label></p>
                            <p>
                            <input type="submit" value="Confirma exclusão?" class="w3-btn w3-red" >
                            <input type="button" value="Cancelar" class="w3-btn w3-theme" onclick="window.location.href='autListar.php'"></p>
                        </form>
                        <?php 
                    } else {
                        echo "<div class='w3-container w3-theme'><h2>Tentativa de exclusão de Autor Inexistente</h2></div><br>";
                    }
                } else {
                    echo "<p style='text-align:center'>Erro ao executar SELECT: " . mysqli_error($conn) . "</p>";
                }
                echo "</div>"; // Fim form
                mysqli_close($conn);
                ?>
            </div>
        </p>
    </div>

    <?php require 'geral/sobre.php';?>
</div>

<?php require 'geral/rodape.php';?>
</body>
</html>
