<!DOCTYPE html>
<!-------------------------------------------------------------------------------
    Desenvolvimento Web
    PUCPR
    Profa. Cristina V. P. B. Souza
    Agosto/2022
---------------------------------------------------------------------------------->
<!-- autListar.php -->

<html>
<head>
    <title>Autores Cadastrados</title>
    <link rel="icon" type="image/png" href="imagens/favicon.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="css/customize.css">
</head>
<body onload="w3_show_nav('menuAutor')">
<?php require 'geral/menu.php'; ?>
<?php require 'bd/conectaBD.php'; ?>

<div class="w3-main w3-container" style="margin-left:270px;margin-top:117px;">
    <div class="w3-panel w3-padding-large w3-card-4 w3-light-grey">
        <div class="w3-container w3-teal">
            <h2 style="display: inline;">Listagem de Autores</h2>
            <?php
                // Verifica a ordenação atual e define o texto do botão
                $isSortedByDate = isset($_GET['orderBy']) && $_GET['orderBy'] === 'dt_nasc';
                $buttonText = $isSortedByDate ? "Ordenar por Nome" : "Ordenar por Data de Nascimento";
                $newOrder = $isSortedByDate ? "nome" : "dt_nasc"; // Define o novo parâmetro de ordenação
            ?>
            <a href="autListar.php?orderBy=<?php echo $newOrder; ?>" class="w3-button" style="border: 1px solid #aaa; color: #333; float: right; margin-top: 6px; background-color: white; margin-bottom:6px;"><?php echo $buttonText; ?></a>
        </div>

        <?php
            // Cria conexão
            $conn = mysqli_connect($servername, $username, $password, $database);
            
            // Verifica conexão 
            if (!$conn) {
                die("Falha na conexão com o Banco de Dados: " . mysqli_connect_error());
            }
            
            // Configura para trabalhar com caracteres acentuados do português
            mysqli_query($conn,"SET NAMES 'utf8'");
            mysqli_query($conn,'SET character_set_connection=utf8');
            mysqli_query($conn,'SET character_set_client=utf8');
            mysqli_query($conn,'SET character_set_results=utf8');

            // Define a ordenação padrão
            $orderBy = $isSortedByDate ? 'Dt_Nasc' : 'Nome'; // Padrão é por Nome
            
            // Faz Select na Base de Dados
            $sql = "SELECT ID_Autor, Dt_Fale, Nome, Nacionalidade, Foto, Dt_Nasc 
                    FROM Autor AS A 
                    INNER JOIN Nacionalidade AS N ON (A.ID_Nacionalidade = N.ID_Nacionalidade) 
                    ORDER BY $orderBy";
            echo "<div class='w3-responsive w3-card-4'>";
            if ($result = mysqli_query($conn, $sql)) {
                echo "<table class='w3-table-all'>";
                echo "  <tr>";
                echo "    <th width='7%'>Código</th>";
                echo "    <th width='14%'>Autor</th>";
                echo "    <th width='14%'>Nacionalidade</th>";
                echo "    <th width='18%'>Imagem</th>";
                echo "    <th width='15%'>Nascimento</th>";
                echo "    <th width='10%'>Falecimento</th>";
                echo "    <th width='8%'>Idade</th>";
                echo "    <th width='7%'> </th>";
                echo "    <th width='7%'> </th>";
                echo "  </tr>";
                if (mysqli_num_rows($result) > 0) {
                    // Apresenta cada linha da tabela
                    while ($row = mysqli_fetch_assoc($result)) {
                        $data = $row['Dt_Nasc'];
                        list($ano, $mes, $dia) = explode('-', $data);
                        $nova_data = $dia . '/' . $mes . '/' . $ano;
                        // data atual
                        $hoje = mktime(0, 0, 0, date('m'), date('d'), date('Y'));
                        // Descobre a unix timestamp da data de nascimento do autor
                        $nascimento = mktime( 0, 0, 0, $mes, $dia, $ano);
                        // cálculo
                        $idade = floor((((($hoje - $nascimento) / 60) / 60) / 24) / 365.25);
                        $cod = $row["ID_Autor"];
                        echo "<tr>";
                        echo "<td>" . $cod . "</td>";
                        echo "<td>" . $row["Nome"] . "</td>";
                        echo "<td>" . $row["Nacionalidade"] . "</td>";

                        // Exibir imagem
                        echo "<td>";
                        if ($row['Foto']) {
                            echo "<img id='imagemSelecionada' class='w3-circle w3-margin-top' src='data:image/png;base64," . base64_encode($row['Foto']) . "' />";
                        } else {
                            echo "<img id='imagemSelecionada' class='w3-circle w3-margin-top' src='imagens/user.png' />";
                        }
                        echo "</td><td>" . $nova_data . "</td>";

                        
                        $data_falecimento = $row["Dt_Fale"];
                        if ($data_falecimento) {
                            list($ano_f, $mes_f, $dia_f) = explode('-', $data_falecimento);
                            $nova_data_falecimento = $dia_f . '/' . $mes_f . '/' . $ano_f;

                           
                            $falecimento = mktime(0, 0, 0, $mes_f, $dia_f, $ano_f);
                            $idade = floor((((($falecimento - $nascimento) / 60) / 60) / 24) / 365.25);
                        } else {
                            $nova_data_falecimento = "--------------"; 
                            $idade = floor((((($hoje - $nascimento) / 60) / 60) / 24) / 365.25);
                        }

                        echo "<td>" . $nova_data_falecimento . "</td>";
                        echo "<td>" . $idade . " </td>";
            ?>              
                        <td>       
                            <a href='autAtualizar.php?id=<?php echo $cod; ?>'><img src='imagens/Edit.png' title='Editar Autor' width='32'></a>
                        </td>
                        <td>
                            <a href='autExcluir.php?id=<?php echo $cod; ?>'><img src='imagens/Delete.png' title='Excluir Autor' width='32'></a>
                        </td>
                        </tr>
            <?php
                    }
                }
                echo "</table>";
                echo "</div>";
            } else {
                echo "Erro executando SELECT: " . mysqli_error($conn);
            }

            mysqli_close($conn);
        ?>
    </div>
    
    <?php require 'geral/sobre.php'; ?>
    <?php require 'geral/rodape.php'; ?>
</body>
</html>
