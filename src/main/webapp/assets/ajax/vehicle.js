var globaldata;
function setData(data){
	console.log(data);
};


$(document).ready(function() {

	$.ajax({
		url: "VehicleController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			globaldata = data;
			setData(data);
			let type = $('#veh').val();
			let veh = [];
			globaldata.forEach(element => {
            	if(element.type == type){
					veh.push(element);
				}
        	});
			fillTable(veh);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			
		}
	});
	$( "#veh" ).change(function() {
	
		let type = $('#veh').val();
		if(globaldata){
			
			let veh=[];
			globaldata.forEach(element => {
            	if(element.type == type){
					veh.push(element);
				}
        	});
			fillTable(veh);
		}

	});

	$("#add").click(function() {
		var brand = $("#brand").val();
		var matriculation = $("#matriculation").val();
		var type = $("#type").val();
		$.ajax({
			url: "VehicleController",
			data: {  op: "add", brand: brand, matriculation: matriculation, type: type  },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				setData(data);
				clear();
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	});
	function fillTable(data){
		let all ='';
		
		data.forEach(element => {
            all +=`<tr>
            <td>
            <p  class="text-xs font-weight-bold mb-0">&emsp;${element.id}</p>
            </td>
            <td>
            <p class="text-xs font-weight-bold mb-0">${element.matriculation}</p>
            </td>
			<td>
            <p class="text-xs font-weight-bold mb-0">${element.brand}</p>
            </td>
            </tr>`

        });
		$("#content").html(all);
	}
	
	function clear() {
	$("#brand").val('')
	$("#matriculation").val('');
	$("#type").val('');
	}

});