<!-------------------------------------------------------------------------------
    Desenvolvimento Web
    PUCPR
    Profa. Cristina V. P. B. Souza
    Agosto/2022
---------------------------------------------------------------------------------->
<!-- menu.php -->

	<!-- Top -->
	<div class="w3-top"> 
    <div class="w3-row w3-white w3-padding" >
        
        <div class="w3-half w3-margin-top w3-wide w3-hide-medium w3-hide-small">
            <div class="w3-right"> 
               
            </div>
        </div>
    </div>
    <div class="w3-bar w3-teal w3-large" style="z-index:-1"> 
		<a class="w3-bar-item w3-button w3-hide-medium w3-hide-small w3-teal w3-hover-light-green w3-padding-16" href="autListar.php" onclick="w3_show_nav('menuAutor')">Mostrar Menu</a>
        <a class="w3-bar-item w3-button w3-hide-medium w3-hide-small w3-teal w3-hover-light-green w3-padding-16" href="javascript:void(0)"  style='margin-left:75%'  onclick="w3_show_none()">Esconder Menu</a>
        
        
    </div>
</div>




	<!-- Sidebar -->
	<div class="w3-sidebar w3-bar-block w3-collapse w3-animate-left w3-light-gray " style="z-index:3;width:270px;" id="mySidebar" >
		<div class="w3-bar w3-hide-large w3-large">
			<a href="javascript:void(0)" onclick="w3_show_nav('menuAutor')"
			   class="w3-bar-item w3-button w3-theme w3-hover-light-gray w3-padding-16" style="width:50%">Autores</a>

			   
		</div>
		<a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-right w3-xlarge w3-hide-large"
		   title="Close Menu">x</a>
		<div id="menuAutor" class="myMenu">
			<div class="w3-half" style="margin-top:0px">
				<a class="logo" href="."><img class="logo" src='imagens/editora.png' alt=' Editora Ramos'></a>
			</div>
			<div class="w3-container">
				<h3 style="text-align:center;  font-weight: bold" class="w3-pale-blue">Menu Autores</h3>
			</div>
			
			<a class="w3-bar-item w3-button" href="autListar.php" style="text-align:center">Relação de Autores</a>
			<a class="w3-bar-item w3-button" href="autIncluir.php" style="text-align:center">Cadastro de Autores</a>
			


		</div>
		
	</div>


	<script type="text/javascript" src="js/myScriptAut.js"></script>
	
