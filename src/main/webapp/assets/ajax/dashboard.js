function fill(occupation_id, section, place_id, type, occupation, brand, matricule, services) {
	
	    let line =  `<tr>
            <td>
            <div class="d-flex px-2 py-1">
				<div>
                <img src="assets/img/${type}.PNG" class="avatar avatar-sm me-3" alt="user1">
                </div>
                <div class="d-flex flex-column justify-content-center">
                <h6 class="mb-0 text-sm">${matricule}</h6>
                <p class="text-xs text-secondary mb-0">Brand: ${brand}</p>
                <p class="text-xs text-secondary mb-0">Services:  ${services}</p>
                </div>
            </div>
            </td>
            <td>
			<span class="badge badge-sm bg-gradient-success">#P_${place_id}</span>
            </td>
            <td class="align-middle text-center text-sm">
			<p class="text-xs font-weight-bold mb-0">${section}</p>
            </td>
            <td class="align-middle text-center">
            <span class="text-secondary text-xs font-weight-bold">${occupation}</span>
            </td>
            <td class="align-middle">
            <a href="javascript:;" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user" onClick="showAjaxLoaderMessage(${occupation_id})">
                Free the Place
            </a>
            </td>
          </tr>`
	return line;
}




function setData(data) {
	console.log(data)
	if (data.length >= 1) {
		var all = "";
		for (var i = 0; i < data.length; i++) {
			occupationDate = "" + data[i].occupation.date.day + "/" + data[i].occupation.date.month + "/" + data[i].occupation.date.year;
			occupationDate += " at " + data[i].occupation.time.hour + ":" + data[i].occupation.time.minute + ":" + data[i].occupation.time.second;

			all += fill(data[i].id, data[i].sectionName, data[i].placeId, data[i].type, occupationDate, data[i].vehicleBrand, data[i].matriculation, data[i].services);
		}



		$("#content").html(all);

	}
}

$(document).ready(function() {

$.ajax({
		url: "OccupationController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			setData(data);
			console.log(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("error");
		}
	});
});

function showAjaxLoaderMessage(occupationid) {
	$.ajax({
			url: "OccupationController",
			data: { op: "free", occupationid: occupationid },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				$("#content").empty();
				swal("the place has been feed!", "the ticket is generated !", "success");
				
				setData(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				swal("Erreur", "Place non-libérer !", "error");
				console.log(textStatus);
			}
		});
	/*swal({
		title: "Free the Place",
		text: "Do you wnat to free this palce ?",
		type: "info",
		showCancelButton: true,
		closeOnConfirm: false,
		showLoaderOnConfirm: true,
	}, function(isConfirm) {
		/*console.log("in ok button")
		$.ajax({
			url: "OccupationController",
			data: { op: "free", occupationid: occupationid },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				$("#content").empty();
				swal("the place has been feed!", "the ticket is generated !", "success");
				
				setData(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				swal("Erreur", "Place non-libérer !", "error");
				console.log(textStatus);
			}
		});
	});*/
}