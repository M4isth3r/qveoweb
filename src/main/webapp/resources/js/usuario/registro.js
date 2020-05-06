document.addEventListener('DOMContentLoaded', function() {

	var fecha = document.querySelectorAll('.datepicker');
	var instance = M.Datepicker.init(fecha, {
		format : 'dd-mm-yyyy',
		yearRange : 100
	});

	var opciones = document.querySelectorAll('select');
	var instance2 = M.FormSelect.init(opciones);

	var elems = document.querySelectorAll('.chips');
	var plataformasDisponibles = document.getElementsByClassName('plataformas');
	
	var datosObj = {};

	for (var i = 0; i < plataformasDisponibles.length; i++) {
		datosObj[plataformasDisponibles[i].id] = null;
	}

	var options = {
		autocompleteOptions: {	
				data: datosObj
		}
	}

	var instance3 = M.Chips.init(elems, options);
	
	var plataformasUsuario = document.getElementsByClassName('plataformas-usuario');
	
	datosObjUser = {};
	
	var instancia = M.Chips.getInstance(elems);
	
	if(plataformasUsuario.length > 0){
		for (var i = 0; i < plataformasUsuario.length; i++) {
			datosObjUser["tag"] = plataformasUsuario[i];
			$('.chips-initial').chips({
				data: {
					tag: 'Apple'
				}
			})
		}
	}
	
	
	let botones = document.getElementsByClassName('botones');
	for(let i = 0; i<botones.length; i++){
		botones[i].addEventListener('click', crearInput);
	}
	
	function crearInput(event){
		var etiquetas = document.getElementsByClassName('chip');
		for(var i=0; i<etiquetas.length; i++){
			var identificador = etiquetas[i].textContent.replace('close','');
			var caja = document.getElementById(identificador);
			caja.innerHTML += '<input name="plataformas" value='+caja.innerText+' type="hidden">';
		}
		
	}
});
