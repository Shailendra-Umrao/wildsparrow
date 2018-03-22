<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online-API-Diff</title>
<!-- CSS for Bootstrap -->
<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- JQuery -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<%-- <c:import url="/WEB-INF/js/jquery-3.3.1.min.js" /> --%>
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<script src="<c:url value="/js/jquery-3.3.1.min.js" />"></script>

<script>
$(document).ready(function() {
	//add more file components if Add is clicked
	$('#addCurrentVersionFile').click(function() {
// 		var fileIndex = $('#currentVersionFileTable tr').children().length - 1;
		var fileIndex = $('#currentVersionFileTable tr').length;

		$('#currentVersionFileTable').append(
				'<tr><td>'+
				'	<input type="file" name="newVersionJars['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});

$(document).ready(function() {
	//add more file components if Add is clicked
	$('#addPreviousVersionFile').click(function() {
// 		var fileIndex = $('#PrevioupVersionFileTable tr').children().length - 1;
		var fileIndex = $('#PrevioupVersionFileTable tr').length;

		$('#PrevioupVersionFileTable').append(
				'<tr><td>'+
				'	<input type="file" name="oldVersionJars['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});
</script>


<script type="text/javascript">
$(function() {
  $('button[type=submit]').click(function(e) {
    e.preventDefault();
    //Disable submit button
    $(this).prop('disabled',true);
    
    var form = document.forms[0];
    var formData = new FormData(form);
    	
    // Ajax call for file uploaling
    var ajaxReq = $.ajax({
      url : 'fileUpload',
      type : 'POST',
      data : formData,
      cache : false,
      contentType : false,
      processData : false,
      xhr: function(){
        //Get XmlHttpRequest object
         var xhr = $.ajaxSettings.xhr() ;
        
        //Set onprogress event handler 
         xhr.upload.onprogress = function(event){
          	var perc = Math.round((event.loaded / event.total) * 100);
          	$('#progressBar').text(perc + '%');
          	$('#progressBar').css('width',perc + '%');
         };
         return xhr ;
    	},
    	beforeSend: function( xhr ) {
    		//Reset alert message and progress bar
    		$('#alertMsg').text('');
    		$('#progressBar').text('');
    		$('#progressBar').css('width','0%');
              }
    });
  
    // Called on success of file upload
    ajaxReq.done(function(msg) {
      $('#alertMsg').text("Jars uploaded Sucessfully. Generating Reports, Please wait....");
      $('input[type=file]').val('');
      $('button[type=submit]').prop('disabled',false);       
      setTimeout(function(){
    	  $(document.body).html(msg);
      }, 2000);
     
    });
    
    // Called on failure of file upload
    ajaxReq.fail(function(jqXHR) {
    	
    	var textmsg= jqXHR.responseText;
    	if(textmsg !=null && textmsg.startsWith("<html><head>")){
    		textmsg =textmsg.replace("<html><head><title>Error</title></head><body>/wildsparrow-api-diff-webapp/WEB-INF/jsp/", "");
    		textmsg= textmsg.replace(".jsp</body></html>", "");
        	  $('#alertMsg').text(textmsg);
    	}else{
    		  $('#alertMsg').text(jqXHR.responseText+'('+jqXHR.status+
    		      		' - '+jqXHR.statusText+')');	
    	}
    	
    
      $('button[type=submit]').prop('disabled',false);
    });
  });
});
</script>

</head>
<body>
	<h4 align="right" style="padding-right: 5ex;"><a href="home">Home</a></h4>

	<div class="container" align="center">
		<h2>API Difference Utility Version 1.0</h2>
		<hr>
		<br>
		<h3>Along with your project jars don't forget to add Third party jars. Select Current and Previous Version jars to upload. Press Add button to	add more jars inputs.</h3>
		<br>
		<hr>
		<!-- File Upload From -->
		<form:form method="post" modelAttribute="uploadForm"  enctype="multipart/form-data">
			<table >
				<tr>
					<td>
						<p>Current Version jars.</p> 
						
						<input id="addCurrentVersionFile" type="button" value="Add Files" />
						<br>
						<table id="currentVersionFileTable">
							<tr>
								<td><input name="newVersionJars[0]" type="file" /></td>
							</tr>
							<tr>
								<td><input name="newVersionJars[1]" type="file" /></td>
							</tr>
						</table>

					</td>

					<td>
					<p>Previous Version jars.</p> 
						<input id="addPreviousVersionFile"	type="button" value="Add Files" />
						<br>
						<table id="PrevioupVersionFileTable">
							<tr>
								<td><input name="oldVersionJars[0]" type="file" /></td>
							</tr>
							<tr>
								<td><input name="oldVersionJars[1]" type="file" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<div class="form-group">
				<button class="btn btn-primary" type="submit">Generate Difference Report</button>
			</div>
		</form:form>
		<br />

		<!-- Bootstrap Progress bar -->
		<div class="progress">
			<div id="progressBar" class="progress-bar progress-bar-success"
				role="progressbar" aria-valuenow="0" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%">0%</div>
		</div>

		<!-- Alert -->
		<div id="alertMsg" style="color: red; font-size: 18px;"></div>
	</div>

</body>
</html>