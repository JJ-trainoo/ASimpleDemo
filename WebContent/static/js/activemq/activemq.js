
$(function(){
	
	$('.btn1').click(function(){
		$.ajax({
			type: "GET",
			url: basePath + "/jms/activemq.do",
			data: {},
			dataType: "json",
			success: function(data){
				alert(data.code);
			},error: function(XMLHttpRequest, textStatus){
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				console.log("error");
			}
		});
	});
	
});