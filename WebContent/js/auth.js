$(document).ready(function() {
	$("#logoutbtn").on("click", logout);

	var currentUser = sessionStorage.getItem('accountUser');
	if (!currentUser) {
		console.log("not logged in");
		$("#loginbtn").show();
		$("#regbtn").show();
		$("#profilebtn").hide();
		$("#logoutbtn").hide();
	} else {
		console.log("logged in as " + currentUser);
		$("#logoutbtn").show();
		$("#loginbtn").hide();
		$("#regbtn").hide();
		$("#profilebtn").show();
	}
});

function logout() {
	event.preventDefault();
	sessionStorage.removeItem("accountUser");
	window.location.href= "Home.html";
}