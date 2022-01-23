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
             <div class="mb-3">
        	<select class="form-control"  aria-label="Name" ria-describedby="email-addon" name="cars" id="places">
				
			</select>
	 		</div>
            </div>
            
            <div class="card-body px-0 pt-0 pb-2">
              <div class="table-responsive p-0">
                <table class="table align-items-center mb-0">
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">#</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Type</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Price</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Status</th>
                    </tr>
                  </thead>
                  <tbody id="content">
						
                  </tbody>
                </table>
              </div>
              
                <div class="card card-plain mt-8">
                <div class="card-header pb-0 text-left bg-transparent">
                  <h5 class="font-weight-bolder text-info text-gradient formcontT">Add Place</h5>
                </div>
                <div class="card-body">
               		<div class="formCont">
               		
                  
                  <label>Sections</label>
                    <div class="mb-3">
        				<select class="form-control" aria-label="Name" ria-describedby="email-addon" name="cars" id="sectionsSelector">
						</select>
	 				</div>
	 				<div class="form-check form-switch">
                      <input class="form-check-input" type="checkbox" id="rememberMe" checked="">
                      <label class="form-check-label" for="rememberMe">Create New Sction</label>
                    </div>
                    <label>New Section</label>
                    <div class="mb-3">
                      <input type="text" class="form-control" name="newsection" placeholder="New Section" aria-label="New Section" aria-describedby="" id="sectionName">
                    </div>
                    <label>Type</label>
                    <div class="mb-3">
        				<select class="form-control"  aria-label="Type" ria-describedby="email-addon" name="cars" id="type">
										<option value="car">car</option>
										<option value="moto">Motorcycle</option>
										<option value="electric">Electric car</option>
										<option value="handicapped">Disabled</option>
										<option value="truck">Truck</option>							
						</select>
	 				</div>
					<label>First Hour</label>
                    <div class="mb-3">
                      <input type="text" class="form-control" name="firsthour" placeholder="First Hour" aria-label="first" aria-describedby="" id="pH1">
                    </div>
                    <label>Second Hour</label>
                    <div class="mb-3">
                      <input type="text" class="form-control" name="secondhour" placeholder="Second Hour" aria-label="Second" aria-describedby="" id="pH2">
                    </div>
                    <label>Rest of Hours</label>
                    <div class="mb-3">
                      <input type="text" class="form-control" name="resthour" placeholder="Rest of Hour" aria-label="rest" aria-describedby="" id="pHn">
                    </div>
                    <div class="text-center">
                      <button type="submit" id="add" class="btn bg-gradient-info w-100 mt-4 mb-0">Add Place</button>
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
 <script src="assets/ajax/places.js"></script>
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