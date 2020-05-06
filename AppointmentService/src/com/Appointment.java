package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			// For testing
			// System.out.println("Successfully connected---1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// inserting appointment

	public String insertAppointment(String no, String type, String Date, String desc, String price) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into appointments "
					+ "(`appID`,`appNo`,`appType`,`appDate`,`appDescription`, `appPrice`)"
					+ " values (?, ?, ?, ?, ?, ? )";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, no);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, Date);
			preparedStmt.setString(5, desc);
			preparedStmt.setDouble(6, Double.parseDouble(price));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";

			// output = "Inserted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// read the appointments from database and display

	public String readAppointments() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null)

			{
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Appointment No</th><th>Appointment Type</th><th>Appointment Date</th>"
					+ "<th>Appointment Description</th><th>Appointment Charges</th> <th>Update</th><th>Remove</th></tr>";

			String query = "select * from appointments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String appID = Integer.toString(rs.getInt("appID"));
				String appNo = rs.getString("appNo");
				String appType = rs.getString("appType");
				String appDate = rs.getString("appDate");
				String appDescription = rs.getString("appDescription");
				String appPrice = Double.toString(rs.getDouble("appPrice"));

				// Add into the html table
				output += "<tr><td><input id='hidAppIDUpdate'name='hidAppIDUpdate'type='hidden' value='" + appID + "'>"
						+ appNo + "</td>";
				output += "<td>" + appType + "</td>";
				output += "<td>" + appDate + "</td>";
				output += "<td>" + appDescription + "</td>";
				output += "<td>" + appPrice + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appid='"
						+ appID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";

		}

		catch (Exception e) {
			output = "Error while reading the appointments.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// update appointments

	public String updateAppointment(String appID, String appNo, String appType, String appDate, String appDescription,
			String price) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE appointments SET appNo=?,appType=?,appDate=?,appDescription=?,appPrice=?      WHERE appID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, appNo);
			preparedStmt.setString(2, appType);
			preparedStmt.setString(3, appDate);
			preparedStmt.setString(4, appDescription);
			preparedStmt.setDouble(5, Double.parseDouble(price));
			preparedStmt.setInt(6, Integer.parseInt(appID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";

			// output = "Updated successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the appointment.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// delete appointments

	public String deleteAppointment(String appID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from appointments where appID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(appID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newAppointments = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointments + "\"}";

			// output = "Deleted successfully";
		}

		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
