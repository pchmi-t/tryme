$(document).ready(function() {
	$("#logoutbtn").on("click", function(event) {
		event.preventDefault();
		localStorage.removeItem("user");
		window.location.href= "Home.html";
	});

	if (localStorage.user  == null || localStorage.user  == "") {
		console.log("baba");
		$(".unauth").show();
		$(".logOut").hide();
	} else {
		$(".unauth").hide();
		$(".logOut").show();
	}
});
