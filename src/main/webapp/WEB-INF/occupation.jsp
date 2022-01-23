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
	 <div class="container-fluid py-4">
      <div class="row">
        <div class="col-12">
          <div class="card mb-4">
            <div class="card-header pb-0">
				
            </div>
            
            <div class="card-body px-0 pt-0 pb-2 test">
              
                <div class="card card-plain mt-8">
                <div class="card-header pb-0 text-left bg-transparent">
                  <h5 class="font-weight-bolder text-info text-gradient formcontT">Add Place</h5>
                </div>
                <div class="card-body">
               		<div class="formCont1">
               		
                  
                  <label>Place</label>
                    <div class="mb-3">
        				<select class="form-control" aria-label="Name" ria-describedby="email-addon" name="place" id="places">
						</select>
	 				</div>
	 				<label>Vehicle</label>
                    <div class="mb-3">
        				<select class="form-control" aria-label="Name" ria-describedby="email-addon" name="vehicle" id="vehs">
						</select>
	 				</div>
	 				<div class="form-check form-switch">
                      <input class="form-check-input" type="checkbox" id="w" checked>
                      <label class="form-check-label" for="rememberMe">Washing</label>
                    </div>
                    <div class="form-check form-switch">
                      <input class="form-check-input" type="checkbox" id="c" >
                      <label class="form-check-label" for="rememberMe">Charging</label>
                    </div>

                    <div class="text-center">
                      <button type="submit" id="add" class="btn bg-gradient-info w-100 mt-4 mb-0">Add</button>
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
 <script src="assets/ajax/ocupation.js"></script>
<script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
  <script src="assets/js/plugins/chartjs.min.js"></script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="assets/js/soft-ui-dashboard.min.js?v=1.0.3"></script>
</body>
</html>