divTemplate = "<div>{content}</div>";

function login(){

	cleanupValidateDataDiv();

	var username = $("#username").val();
	var password = $("#password").val();
	var isValidData = validateData(username, password);
	var account=JSON.stringify(eval({"username": username, "password" : password}));

	if(isValidData){
		
		console.log(username);
		console.log(password);
		
		var serializedAccounts = JSON.stringify(account);

		var url="/tryme/api/v1.0/accounts/".concat(username);

		$.ajax({
			url : url,
			type : "POST",
			data : account,
			headers: {
				"Accept":"application/json", 
				"Content-type":"application/json"
			},
			success : function (data, textStatus, jqXHR){
				console.log("success");

				document.cookie = jqXHR.getResponseHeader('Set-Cookie');
				//console.log(document.cookie);
					
				window.location.href = 'profile.html';
				$("#validationErrors").css({"display":"none"});
				$("#errorMsg").css({"display":"none"});
				$("#successMsg").css({"display":"block"});
			},
			error : function (jqXHR, textStatus, errorThrown){
				console.log("Error!");
				console.log(textStatus);
				console.log(jqXHR.status);
				//console.log(jqXHR.responseText);
				//var responseText = jqXHR.responseText;
				//$("#validationErrors").css({"display":"block"}).append(divTemplate.replace("{content}", responseText));
				$("#successMsg").css({"display":"none"});
				$("#errorMsg").css({"display":"block"});
			}
		});
	}
}

function validateData(username, password){

	var isValidData = true;

	if(username == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили потребителското си име!"));
	}

	if(password == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили паролата!"));
	}

	return isValidData;
}	

function cleanupValidateDataDiv(){
	var childrens = $("#validationErrors").children("div").remove();
	$("#validationErrors").css({"display":"none"});
}