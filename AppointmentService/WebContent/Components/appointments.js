 $(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

// SAVE 

$(document).on("click", "#btnSave", function(event)
{
	
// Clear alerts
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

// Form validation
var status = validateAppointmentForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid

var type = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		url : "AppointmentsAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
		onAppointmentSaveComplete(response.responseText, status);
		}
		});


     //$("#formAppointment").submit();

});


function onAppointmentSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divAppointmentsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{ 
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidAppIDSave").val("");
$("#formAppointment")[0].reset();
}	



$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "AppointmentsAPI",
		type : "DELETE",
		data : "appID=" + $(this).data("appid"),
		dataType : "text",
		complete : function(response, status)
		{
		onAppointmentDeleteComplete(response.responseText, status);
		}
		});
		});






function onAppointmentDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divAppointmentsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}





// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());
$("#appNo").val($(this).closest("tr").find('td:eq(0)').text());
$("#appType").val($(this).closest("tr").find('td:eq(1)').text());
$("#appDate").val($(this).closest("tr").find('td:eq(2)').text());
$("#appDescription").val($(this).closest("tr").find('td:eq(3)').text());
$("#appPrice").val($(this).closest("tr").find('td:eq(4)').text());
});
// CLIENTMODEL=========================================================================
function validateAppointmentForm()
{
// APPOINTMENT NUMBER
if ($("#appNo").val().trim() == "")
{
return "Insert Appointment Number.";
}

// APPOINTMENT TYPE
if ($("#appType").val().trim() == "")
{
return "Insert Appointment Type.";
}

//DATE
if ($("#appDate").val().trim() == "")
{
return "Insert Appointment Date.";
}

// is numerical value
//var tmpPrice = $("#itemPrice").val().trim();
//if (!$.isNumeric(tmpPrice))
//{
//return "Insert a numerical value for Item Price.";
//}
// convert to decimal price
//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));


// DESCRIPTION
if ($("#appDescription").val().trim() == "")
{
return "Insert Appointment Description.";
}

//PRICE
if ($("#appPrice").val().trim() == "")
{
return "Insert Appointment Charges.";
}
// is numerical value
var tmpPrice = $("#appPrice").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Appointment Charges.";
}
// convert to decimal price
$("#appPrice").val(parseFloat(tmpPrice).toFixed(2));

return true;
}

