$(function(){
	
	//获取项目路径
	var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	var basePath= window.location.protocol + '//' + window.location.host + '/' + webName;
	console.log(basePath);
	
	$('.btn1').click(function(){
		var div = document.getElementsByClassName('container');
		$.ajax({
			type: "GET",
	        url: basePath + "/druidTest/query.do",
	        data: {},
	        dataType: "json",
			success: function(data){
				alert("数据库一共有："+data.count+"条数据~");
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
	        url: basePath + "/druidTest/insertData.do",
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
	
	$('.btn3').click(function(){
		$.ajax({
			type: "GET",
	        url: basePath + "/druidTest/queryDetail.do",
	        data: {},
	        dataType: "json",
			success: function(data){
				var html = "<table>";
				for(var i=0; i<data.length; i++){
					html += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td></tr>";
				}
				html += "</table>";
				$(".container").html(html);
			},error: function(XMLHttpRequest, textStatus){
				alert(XMLHttpRequest.status);
	            alert(XMLHttpRequest.readyState);
	            alert(textStatus);
				console.log("error");
			}
		});
	});
	
	$('.btn4').click(function(){
		$.ajax({
			type: "GET",
			url: basePath + "/druidTest/deleteData.do",
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
