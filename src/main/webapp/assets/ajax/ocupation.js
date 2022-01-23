var gPlaces=[];
var gVehs=[];
var cPlaces;
var cVehs;

function fillPlaces(data){
	let options = '';
	 data.forEach(element => {
            options += `<option value="${element.idPlace}">N-${element.idPlace}, Type: ${element.type}, Section: ${element.sectionName}</option>`
      });
	$('#places').html(options);
	
}
function fillVeh(data){

		let options = '';
	 data.forEach(element => {
            options += `<option value="${element.id}">RN: ${element.matriculation}, Type: ${element.type}, Brand: ${element.brand}</option>`
      });
	$('#vehs').html(options);
}
function load(){
	$( "#places" ).change(function() {

		let place = $('#places').val();
		let vehSet;
		gPlaces.forEach(element => {
			if(element.idPlace == place){
				vehSet = gVehs.filter(x => {
					if(x.type==element.type){
						return true;
					}else{
						return false;
					}
				});
			}
		});
		
		fillVeh(vehSet);
	});
		$.ajax({
		url: "Parking",
		data: { op: "loadPlaces" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			fillPlaces(data);
			cPlaces = data;
			gPlaces = data;
			//setPlaces(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("error");
		}
		});
		
	$.ajax({
		url: "Parking",
		data: { op: "loadVehicles" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			
			fillVeh(data);
			cVehs = data;
			gVehs = data;

	setTimeout(() => {
    	let place = $('#places').val();
		console.log(place);
		let vehSet1;
		gPlaces.forEach(element => {
			if(element.idPlace == place){
				vehSet1 = gVehs.filter(x => {
					if(x.type==element.type){
						return true;
					}else{
						return false;
					}
				});
			}
		});
			fillVeh(vehSet1);
			}, 300);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("error");
		}
	});

		
}
$(document).ready( function() {
	 load();
	let washing = true;
	let charging = false;
	$('#w').change(function(){
        if($(this).prop('checked') === true){
         	washing = true;
        }else{
             washing = false;
        }
 	});
	$('#c').change(function(){
        if($(this).prop('checked') === true){
         	charging = true;
        }else{
             charging = false;
        }
 	});
	$("#add").click(function() {
		let p = $('#places').val();
		let v = $('#vehs').val();
		let check = new Array();
		if(washing) {check.push('Washing')};
		if(charging) {check.push('Charging')};

		$.ajax({
		url: "Parking",
		data: { op: "add", vehicleId: v, placeId: p, services: check },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			
			load();
			//setVehicles(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("error");
		}
		});
	});
	
	/*$("#places").change(function() {
		var id = $(this).val();
		
		showPlace(getPlace(id));
		validation();
	});
	
	$("#vehicles").change(function() {
		var id = $(this).val();
		
		showVehicle(getVehicle(id));
		validation();
	});
	*/

	
	
});