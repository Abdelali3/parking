 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="assets/img/favicon.png">
  <title>
    Place
  </title>
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
  <!-- Nucleo Icons -->
  <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link id="pagestyle" href="assets/css/soft-ui-dashboard.css?v=1.0.3" rel="stylesheet" />
  <link id="pagestyle" href="assets/css/placeForm.css" rel="stylesheet" />
  <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.min.css'></link>  
</head>
<body>
	 <%@include file="components/sideBar.jsp"%>
	 
	<main class="main-content position-relative max-height-vh-100 h-100 mt-1 border-radius-lg ">
	 <%@include file="components/navBar.jsp"%>
	 <div class="container-fluid py-4 ">
      <div class="row">
        <div class="col-12">
          <div class="card mb-4">
            <div class="card-header pb-0">

            </div>
            
            <div class="card-body px-0 pt-0 pb-2">
              						<div class="row row-cards">

						<div class="col-xl-12 col-lg-12 col-md-12">


							<div class="card ">
								<div class="header">
									<h4>Revenu hebdomadaire</h4>
								</div>
								<div class="body cn">
									<div id="chart-area" style="height: 300px"></div>
								</div>
							</div>


						</div>
						<div class="col-xl-12 col-lg-12 col-md-12">
							<div class="card">
								<div class="header">
									<h4>Type de places</h4>
								</div>
								<div class="body">
									<div id="chart-pie" style="height: 300px"></div>
								</div>
							</div>
							<div class="card">
								<div class="header">
									<h4>Tickets</h4>
								</div>
								<div class="body">
									<div id="chart-donut" style="height: 300px"></div>
								</div>
							</div>

						</div>

					</div>
            </div>
            
          </div>
        </div>
      </div>
		 </div>

	</main>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@7.12.15/dist/sweetalert2.all.min.js"></script> 
 <script src="assets/bundles/libscripts.bundle.js"></script>
	<script src="assets/bundles/vendorscripts.bundle.js"></script>
 <script src="assets/bundles/c3.bundle.js"></script>
 	<script src="assets/bundles/mainscripts.bundle.js"></script>
<script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
  <script src="assets/js/plugins/chartjs.min.js"></script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="assets/js/soft-ui-dashboard.min.js?v=1.0.3"></script>
  
  
  	<script>
		$(document).ready(function() {
			$.ajax({
				url : "StatisticsController",
				data : {
					op : "load"
				},
				type : 'POST',
				success : function(data, textStatus, jqXHR) {
					setData(data);

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});

		});

		var weeklyIncome;
		var placesNumbers;
		var ticketNumber;

		function setData(data) {
			console.log(data);
			weeklyIncome = [ 'data1', data.lundi, data.mardi, data.mercredi,
					data.jeudi, data.vendredi, data.samedi, data.dimanche ];

			placesNumbers = [ [ 'car', data.cars ], [ 'moto', data.motos ],
					[ 'handicapped', data.handicappeds ],
					[ 'electric', data.electrics ], [ 'truck', data.trucks ] ];

			var ticketNumber = [ [ 'data1', data.unpayed ],
					[ 'data2', data.payed ] ];

			c3.generate({
				bindto : '#chart-area', // id of chart wrapper
				data : {
					columns : [
					// each columns data
					weeklyIncome ],
					type : 'area', // default type of chart
					colors : {
						'data1' : '#61bda1',
					},
					names : {
						// name of each serie
						'data1' : 'Revenu',
					}
				},
				axis : {
					x : {
						type : 'category',
						// name of each category
						categories : [ 'Lundi', 'Mardi', 'Mercredi', 'Jeudi',
								'Vendredi', 'Samedi', 'Dimanche' ]
					},
				},
				legend : {
					show : true, //hide legend
				},
				padding : {
					bottom : 20,
					top : 0
				},
			});

			c3.generate({
				bindto : '#chart-pie', // id of chart wrapper
				data : {
					columns : placesNumbers,
					type : 'pie', // default type of chart
					colors : {
						'car' : '#1c3353',
						'moto' : '#2c83b6',
						'handicapped' : '#61bda1',
						'electric' : '#a5d8a2',
						'truck' : 'rgb(147, 103, 180)',
					},
					names : {
						// name of each serie
						'car' : 'Voiture',
						'moto' : 'Moto',
						'handicapped' : 'handcappé',
						'electric' : 'Voitrue électrique',
						'truck' : 'Camion',
					}
				},
				axis : {},
				legend : {
					show : true, //hide legend
				},
				padding : {
					bottom : 20,
					top : 0
				},
			});
			c3.generate({
				bindto : '#chart-donut', // id of chart wrapper
				data : {
					columns : ticketNumber,
					type : 'donut', // default type of chart
					colors : {
						'data1' : '#2c83b6',
						'data2' : '#61bda1',
					},
					names : {
						// name of each serie
						'data1' : 'Ticket Non-payé',
						'data2' : 'Ticket payé'
					}
				},
				axis : {},
				legend : {
					show : true, //hide legend
				},
				padding : {
					bottom : 20,
					top : 0
				},
			});

		}
	</script>
</body>
</html>