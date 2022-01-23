var globaldata;
function setData(data){
        let options = '';
        data.forEach(element => {
            options += `<option value="${element.id}">${element.name}</option>`
        });

	$("#places").html(options);
	$("#sectionsSelector").html(options);
	
};


$(document).ready(function() {

	$.ajax({
		url: "PlaceController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			globaldata = data;
			setData(data);
			let place = $('#places').val();
			globaldata.forEach(element => {
            	if(element.id == place){
					fillTable(element.places);
				}
        	});

		},
		error: function(jqXHR, textStatus, errorThrown) {
			
		}
	});
	$( "#places" ).change(function() {
	
		let place = $('#places').val();
		console.log(place);
		if(globaldata){
			globaldata.forEach(element => {
            	if(element.id == place){
					fillTable(element.places);
				}
        	});
		}

	});

	$("#add").click(function() {
		var sectionName = $("#sectionName").val();
		var sectionId = $("#sectionsSelector").val();
		var type = $("#type").val();
		var pH1 = $("#pH1").val();
		var pH2 = $("#pH2").val();
		var pHn = $("#pHn").val();
		$.ajax({
			url: "PlaceController",
			data: { op: "add", sectionName: sectionName, sectionId: sectionId, type: type, pH1: pH1, pH2: pH2, pHn: pHn },
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
           	console.log(element)
			let status = element.free ? 'Its Free' : 'Its Taken'
            all +=`<tr>
            <td>
            <p  class="text-xs font-weight-bold mb-0">&emsp;${element.id}</p>
            </td>
            <td>
            <p class="text-xs font-weight-bold mb-0">${element.type}</p>
            </td>
            <td >
            <p class="text-xs font-weight-bold mb-0">first hour: ${element.priceH1} second hout: ${element.priceH2}</p>
			</td>
			<td>
            <p class="text-xs font-weight-bold mb-0">${status}</p>
            </td>
            </tr>`

        });
		console.log(all);
		$("#content").html(all);
	}
});

