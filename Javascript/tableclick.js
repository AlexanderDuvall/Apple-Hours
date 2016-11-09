jQuery(document).ready(function($) {
    $(".table_row").click(function() {
        window.document.location = $(this).data("href");
        var email = "";
$.getJSON("http://192.168.1.90/getImage.php?email="+email, success = function (data){
        document.getElementbyId("studentimage").src = data;
    	});
    });
});
