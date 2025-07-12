<!-------------------------------------------------------------------------------
    Desenvolvimento Web
    PUCPR
    Profa. Cristina V. P. B. Souza
    Agosto/2022
---------------------------------------------------------------------------------->
<!-- menu.php -->

	<!-- Top -->
	<div class="w3-top"> 
    <div class="w3-row w3-white w3-padding" ><!-- Alterado para w3-green -->
        
        <div class="w3-half w3-margin-top w3-wide w3-hide-medium w3-hide-small">
            <div class="w3-right"> 
                <!-- Aqui você pode adicionar elementos adicionais se necessário -->
            </div>
        </div>
    </div>
    <div class="w3-bar w3-green w3-large" style="z-index:-1"> <!-- Alterado para w3-green -->
		<a class="w3-bar-item w3-button w3-hide-medium w3-hide-small w3-green w3-hover-light-green w3-padding-16" href="medListar.php" onclick="w3_show_nav('menuMedico')">Mostrar Menu</a>
        <a class="w3-bar-item w3-button w3-hide-medium w3-hide-small w3-green w3-hover-light-green w3-padding-16" href="javascript:void(0)"  style='margin-left:75%'  onclick="w3_show_none()">Esconder Menu</a>
        
        
    </div>
</div>




	<!-- Sidebar -->
	<div class="w3-sidebar w3-bar-block w3-collapse w3-animate-left" style="z-index:3;width:270px" id="mySidebar" >
		<div class="w3-bar w3-hide-large w3-large">
			<a href="javascript:void(0)" onclick="w3_show_nav('menuMedico')"
			   class="w3-bar-item w3-button w3-theme w3-hover-light-gray w3-padding-16" style="width:50%">Autores</a>

			   
		</div>
		<a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-right w3-xlarge w3-hide-large"
		   title="Close Menu">x</a>
		<div id="menuMedico" class="myMenu">
			<div class="w3-half" style="margin-top:0px">
				<a class="logo" href="."><img class="logo" src='imagens/editora.png' alt=' Editora Ramos 'style="margin-left:70%"></a>
			</div>
			<div class="w3-container">
				<h3 style="text-align:center">Menu Autores</h3>
			</div>
			
			<a class="w3-bar-item w3-button" href="medListar.php" style="text-align:center">Relação de Autores</a>
			<a class="w3-bar-item w3-button" href="medIncluir.php" style="text-align:center">Cadastro de Autores</a>
			


		</div>
		
	</div>


	<script type="text/javascript" src="js/myScriptClinic.js"></script>
	
