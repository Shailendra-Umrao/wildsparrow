<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box}

/* Set height of body and the document to 100% */
body, html {
    height: 100%;
    margin: 0;
    font-family: Arial;
}

/* Style tab links */
.tablink {
    background-color: #555;
    color: white;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 10px 12px;
    font-size: 15px;
    height:55px;
    width: 16.6%;
}

.tablink:hover {
    background-color: #777;
}

/* Style the tab content (and add height:100% for full page content) */
.tabcontent {
    color: white;
    display: none;
    padding: 100px 20px;
    height: 100%;
}

table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    border-color:black;
    color:black;
    width: 100%;
}

td, th {
    border: 1px solid black;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
/* #7EB535 */
#Added {background-color: transparent;color: black;}
#Removed {background-color: transparent;color: black;}
#Modified {background-color: transparent;color: black;}
#ArgumentDataStructureChangeAdded {background-color: transparent;color: black;}
#ArgumentDataStructureChangeRemoved {background-color: transparent;color: black;}
#Misc {background-color: transparent;color: black;}
</style>
   
</head>
<body>

<button class="tablink" onclick="openPage('Added', this, 'teal')" id="defaultOpen">Added</button>
<button class="tablink" onclick="openPage('Removed', this, 'teal')" >Removed</button>
<button class="tablink" onclick="openPage('Modified', this, 'teal')">Modified</button>
<button class="tablink" onclick="openPage('ArgumentDataStructureChangeAdded', this, 'teal')">Argument Data Structure Change fields Added</button>
<button class="tablink" onclick="openPage('ArgumentDataStructureChangeRemoved', this, 'teal')">Argument Data Structure Change fields removed</button>
<button class="tablink" onclick="openPage('Misc', this, 'teal')">Miscellaneous</button>


<div id="Added" class="tabcontent">
	<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>
  <h3>Added</h3>
  <p>Following API's got added.</p>
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>New Signature</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.addedMethodReportDTOs}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.new_Signature}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>  
</div>

<div id="Removed" class="tabcontent">
	<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>

  <h3>Removed</h3>
  <p>Following API's got removed.</p> 
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>Old Signature</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.removedMethodReportDTOs}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.old_Signature}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  
</div>

<div id="Modified" class="tabcontent">
<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>
  <h3>Modified</h3>
  <p>Following API's got modified.</p>
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>Old Signature</th>
				<th>New Signature</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.modifiedSignatureChange}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.old_Signature}</td>
					<td>${data.new_Signature}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div id="ArgumentDataStructureChangeAdded" class="tabcontent">
<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>
  <h3>Argument Data Structure Change</h3>
  <p>Following API's Argument/Return type Data Structure Changed, fields Added.</p>
  
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>Fields Added in input DTO</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.modifiedInputDTOFieldsAdded}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.dTOs_changed}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="ArgumentDataStructureChangeRemoved" class="tabcontent">
<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>
  <h3>Argument Data Structure Change</h3>
  <p>Following API's Argument/Return type Data Structure Changed, fields Removed.</p>
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>Fields Removed in input DTO</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.modifiedInputDTOFieldRemoved}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.dTOs_changed}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>  
</div>

<div id="Misc" class="tabcontent">
<h4 align="right" style="padding-right: 5ex;"><a href="fileUploadForm">Back</a>&nbsp;&nbsp;&nbsp;<a href="home">Home</a></h4>
  <h3>Miscellaneous</h3>
  <p>Miscellaneous changes.</p>  
  <table>
		<thead>
			<tr>
				<th>Class Name</th>
				<th>Method Name</th>
				<th>Change</th>
				<th>Input DTO</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${differenceReport.ambigiousMethodReportDTOs}"
				var="data" varStatus="status">
				<tr>
					<td>${data.class_Name}</td>
					<td>${data.method_Name}</td>
					<td>${data.change}</td>
					<td>${data.dTOs_changed}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
function openPage(pageName,elmnt,color) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.backgroundColor = "";
    }
    document.getElementById(pageName).style.display = "block";
    elmnt.style.backgroundColor = color;

}
// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
</body>
</html> 