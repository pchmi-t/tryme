divTemplate = "<div>{content}</div>";

function register(){
	
	cleanupValidateDataDiv();
	
	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var confirmPassword = $("#confirmPassword").val();
	var isValidData = validateData(username, email, password, confirmPassword);
		
	if(isValidData){
		var account = new Object();
		
		account.username = username;
		account.email = email;
		account.password = password;
		
		var serializedAccounts = JSON.stringify(account);
		
		var url="/tryme/api/v1.0/accounts";
		
		$.ajax({
			url : url,
			type : "POST",
			data : serializedAccounts,
			headers: {
		        "Accept":"application/json",
		        "Content-type":"application/json"
		    },
			success : function (data, textStatus, jqXHR){
				console.log("success");
				$("#validationErrors").css({"display":"none"});
				$("#errorMsg").css({"display":"none"});
				$("#successMsg").css({"display":"block"});
				window.location.href = 'sign-in.html';
			},
			error : function (jqXHR, textStatus, errorThrown){
				console.log("Error!");
				console.log(jqXHR.responseText);
				var responseText = jqXHR.responseText;
				$("#validationErrors").css({"display":"block"}).append(divTemplate.replace("{content}", responseText));
				$("#successMsg").css({"display":"none"});
				$("#errorMsg").css({"display":"block"});
			}
		});
			
	}
}

function validateData(username, email, password, confirmPassword){
	
	var isValidData = true;
	
	if(password == "" && confirmPassword == ""){
		if(confirmPassword !== password){
			$("#validationErrors").css({"display":"block"});
			$("#errorMsg").css({"display":"block"});
			isValidData = false;
			$("#validationErrors").append(divTemplate.replace("{content}", "Двете пароли трябва да съвпадат!"));
		}
	}
	
	if(username == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили потребителското си име!"));
	}
	
	if(email == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили E-mail!"));
	}
	
	if(password == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили паролата!"));
	}
	
	if(confirmPassword == ""){
		$("#validationErrors").css({"display":"block"});
		$("#errorMsg").css({"display":"block"});
		isValidData = false;
		$("#validationErrors").append(divTemplate.replace("{content}", "Не сте попълнили потвърдената парола!"));
	}
	
	return isValidData;
}

function cleanupValidateDataDiv(){
	var childrens = $("#validationErrors").children("div").remove();
	$("#validationErrors").css({"display":"none"});
}