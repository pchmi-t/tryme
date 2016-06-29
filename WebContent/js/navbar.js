$(document).ready(function() {

  $(window).scroll(function () {
      //if you hard code, then use console
      //.log to determine when you want the
      //nav bar to stick.
      console.log($(window).scrollTop())
    if ($(window).scrollTop() < 201) {
      $('.upper').slideDown("fast");
      $('.navbar').attr("style", "top: 0 !important");
    }
    if ($(window).scrollTop() > 200) {
      $('.upper').slideUp("fast");
      $('.navbar').attr("style", "top:" + $(window).scrollTop() + " !important");
    }
  });
});
