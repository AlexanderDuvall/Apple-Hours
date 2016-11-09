$(document).ready(function (){
$.getJSON("http://192.168.1.90/GSIAC.php?family=evang&order=grade", success = function (data){
var options ="";
for (var i = 0; i<data.length; i+=4) {
	options+= '<tr class = "table_row" data-href = "login.html" >';
	for(var j = i; j<i+4;j++){
		options+="<td>"+data[j]+"</td>";
	}
	options+="</tr>";
}
options+='<script type = "text/javascript" src="http://192.168.1.90:80/tableclick.js"></script>'
$(".t01").append(options);
$(".t01").change();
});
});