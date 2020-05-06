<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.Appointment"%>

<!DOCTYPE html >
<html>
<head>

<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointments.js"></script>


<title>Appointment Management</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Appointment Management</h1>
				<form id="formAppointment" name="formAppointment">
					Appointment Number: <input id="appNo" name="appNo" type="text"
						class="form-control form-control-sm"> <br>
					Appointment Type: <input id="appType" name="appType" type="text"
						class="form-control form-control-sm"> <br>
					Appointment Date: <input id="appDate" name="appDate" type="text"
						class="form-control form-control-sm"> <br>
					Apppointment Description: <input id="appDescription"
						name="appDescription" type="text"
						class="form-control form-control-sm"> <br>
					Appointment Charges: <input id="appPrice" name="appPrice"
						type="text" class="form-control form-control-sm">
						 <br>
						<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidAppIDSave" name="hidAppIDSave" value="">
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divAppointmentsGrid">

					<%
						Appointment appobj = new Appointment();
						out.print(appobj.readAppointments());
					%>
				</div>

			</div>
		</div>
	</div>



</body>
</html>