$(function(){
	
	$('.btn1').click(function(){
		var div = document.getElementsByClassName('container');
		$.ajax({
			type: "GET",
	        url: basePath + "/session/addSession.do",
	        data: {},
	        dataType: "json",
			success: function(data){
				if( 0 == data.count){
					alert("新增session成功");
				}else{alert("新增session失败");}
			},error: function(XMLHttpRequest, textStatus){
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
				console.log("error");
			}
		});
	});
	
	$('.btn2').click(function(){
		var div = document.getElementsByClassName('container');
		$.ajax({
			type: "GET",
	        url: basePath + "/session/getSession.do",
	        data: {},
	        dataType: "json",
			success: function(data){
				alert(data.count);
			},error: function(XMLHttpRequest, textStatus){
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
				console.log("error");
			}
		});
	});
	
});
