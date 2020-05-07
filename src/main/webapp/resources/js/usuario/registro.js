document.addEventListener('DOMContentLoaded', function() {

	var fecha = document.querySelectorAll('.datepicker');
	var instance = M.Datepicker.init(fecha, {
		format : 'dd-mm-yyyy',
		yearRange : 100
	});

	var opciones = document.querySelectorAll('select');
	var instance2 = M.FormSelect.init(opciones);
	
});
