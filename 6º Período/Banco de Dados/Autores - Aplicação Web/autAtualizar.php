<!DOCTYPE html>
<!-------------------------------------------------------------------------------
    Desenvolvimento Web
    PUCPR
    Profa. Cristina V. P. B. Souza
    Agosto/2022
---------------------------------------------------------------------------------->
<!-- autAtualizar.php -->

<html>
	<head>
		<title>Autoria</title>
		<link rel="icon" type="image/png" href="imagens/favicon.png"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="css/customize.css">
	</head>
<body onload="w3_show_nav('menuAutor')" >
	<!-- Inclui MENU.PHP  -->
	<?php require 'geral/menu.php'; ?>
	<?php require 'bd/conectaBD.php'; ?>

	<div class="w3-main w3-container" style="margin-left:270px;margin-top:117px;">
		<div class="w3-panel w3-padding-large w3-card-4 w3-light-grey">
			<p class="w3-large">
			<div class="w3-code cssHigh notranslate">
				<!-- Acesso em:-->
				<?php

				date_default_timezone_set("America/Sao_Paulo");
				$data = date("d/m/Y H:i:s", time());
				echo "<p class='w3-small' > ";
				echo "Acesso em: ";
				echo $data;
				echo "</p> "
				?>

				<!-- Acesso ao BD-->
				<?php		
				$id = $_GET['id'];

				// Cria conexão
				$conn = mysqli_connect($servername, $username, $password, $database);

				// Verifica conexão
				if (!$conn) {
					die("<strong> Falha de conexão: </strong>" . mysqli_connect_error());
				}
				// Configura para trabalhar com caracteres acentuados do português	 
				mysqli_query($conn, "SET NAMES 'utf8'");
				mysqli_query($conn, 'SET character_set_connection=utf8');
				mysqli_query($conn, 'SET character_set_client=utf8');
				mysqli_query($conn, 'SET character_set_results=utf8');
				
				// Faz Select na Base de Dados
				$sql = "SELECT ID_Autor, Nome, Dt_Fale, Dt_Nasc, ID_Nacionalidade, Foto FROM Autor WHERE ID_Autor = $id";

				//Inicio DIV form
				echo "<div class='w3-responsive w3-card-4'>";
				if ($result = mysqli_query($conn, $sql)) {
					if(mysqli_num_rows($result) == 1){
						$row = mysqli_fetch_assoc($result);
						
						$nacionalidade = $row['ID_Nacionalidade'];
						$id_autor     = $row['ID_Autor'];
						$nome          = $row['Nome'];
						$dataFale           = $row['Dt_Fale'];
						$dataNasc      = $row['Dt_Nasc'];
						$foto          = $row['Foto'];
									
						// Faz Select na Base de Dados
						$sqlG = "SELECT ID_Nacionalidade, Nacionalidade FROM Nacionalidade";
							
						$optionsEspec = array();
						
						if ($result = mysqli_query($conn, $sqlG)) {
							while ($row = mysqli_fetch_assoc($result)) {
								$selected = "";
								if ($row['ID_Nacionalidade'] == $nacionalidade)
									$selected = "selected";
								array_push($optionsEspec, "\t\t\t<option " . $selected . " value='". $row["ID_Nacionalidade"]."'>".$row["Nacionalidade"]."</option>\n");
							}
						}

						?>
						<div class="w3-container w3-teal">
							<h2>Altere os dados do Autor. = [<?php echo $id_autor;echo ' '; echo $nome; ?>]</h2>
						</div>
						<form class="w3-container" action="autAtualizar_exe.php" method="post" enctype="multipart/form-data">
						<table class='w3-table-all'>
						<tr>
							<td style="width:50%;">
							<p>
							<input type="hidden" id="Id" name="Id" value="<?php echo $id_autor; ?>">
							<p>
							<label class="w3-text-IE"><b>Nome</b></label>
							<input class="w3-input w3-border w3-light-grey " name="Nome" type="text" pattern="[a-zA-Z\u00C0-\u00FF ]{10,100}$"
									title="Nome entre 10 e 100 letras." value="<?php echo $nome; ?>" required></p>
							<p>
							<label class="w3-text-IE"><b>Data de Nascimento</b></label>
							<input class="w3-input w3-border w3-light-grey " name="DataNasc" type="date"
									placeholder="dd/mm/aaaa" title="dd/mm/aaaa"
									title="Formato: dd/mm/aaaa" value="<?php echo $dataNasc; ?>"></p>
							<p>
							<label class="w3-text-IE"><b>Data de Falecimento</b></label>
							<input class="w3-input w3-border w3-light-grey " name="DataFale" type="date"
									placeholder="dd/mm/aaaa" title="dd/mm/aaaa"
									title="Formato: dd/mm/aaaa" value="<?php echo $dataFale; ?>"></p>
							
							<p><label class="w3-text-IE"><b>Nacionalidade</b>*</label>
							<select name="Nacionalidade" id="Especialidade" class="w3-input w3-border w3-light-grey " required>

							<?php
								foreach($optionsEspec as $key => $value){
									echo $value;
								}
							?>
							</select>
							</p>
						
							</td>
							<td>
												
							<p style="text-align:center"><label class="w3-text-IE" ><b>Minha Imagem para Identificação: </b></label></p>
							<?php
							if ($foto) {?>
								<p style="text-align:center">
									<img id="imagemSelecionada" class="w3-circle w3-margin-top" src="data:image/png;base64,<?= base64_encode($foto); ?>" />
								</p> 
								<?php
							} else {
								?>
								<p style="text-align:center">
									<img id="imagemSelecionada" class="w3-circle w3-margin-top" src="imagens/user.png" />
								</p>
								<?php
							}
							?>
							<p style="text-align:center"><label class="w3-btn w3-green">Selecione uma Imagem
							<input type="hidden" name="MAX_FILE_SIZE" value="16777215" />
							<input type="file" id="Imagem" name="Imagem" accept="imagem/*" onchange="validaImagem(this);" /></label>
							</p>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center">
							<p>
							<input type="submit" value="Alterar" class="w3-btn w3-green" >
							<input type="button" value="Cancelar" class="w3-btn w3-red" onclick="window.location.href='autListar.php'"></p>
						</tr>
						</table>
						<br>
						</form>
								<?php
					}else{?>
								<div class="w3-container w3-green">
								<h2>Autor inexistente</h2>
								</div>
								<br>
							<?php
							}
				} else {
					echo "<p style='text-align:center'>Erro executando UPDATE: " . mysqli_error($conn) . "</p>";
				}
				echo "</div>"; //Fim form
				mysqli_close($conn);  //Encerra conexao com o BD
				?>
			</div>
			</p>
		</div>

	<?php require 'geral/sobre.php';?>
	<!-- FIM PRINCIPAL -->
	</div>
	<!-- Inclui RODAPE.PHP  -->
	<?php require 'geral/rodape.php';?>

</body>
</html>
