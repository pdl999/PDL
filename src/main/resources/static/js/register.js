$(function(){



	$("#user1").blur(function checkname(){
		//用户名框失去焦点时调用
		var str = $("#user1").val().trim();
		//trim()方法去除字符串两端的空格，再返回一个字符串
		if(str == ""){
			$("#regtip").text("*用户名不能为空");
			return false;
		}else if(str.length>6){
			$("#regtip").text("*用户名长度必须小于15个字符");
			return false;
		}else{
			$("#username_errorTips").text("");
			return true;
		}
	})
	
	//验证两次密码输入
	$("#pass3").blur(function checkpass1(){
		var str = $("#pass3").val().trim();
		if(str == ""){
			$("#regtip").text("*密码不能为空");
			return false;
		}else if(str.length>6){
			$("#regtip").text("*密码长度必须小于10");
			return false;
		}
		else{
			$("#regtip").text("");
			return true;
		}
	})

	
	//验证手机号码
	$("#phone").blur(function checkphone(){
		var str = $("#phone").val();
		var re= /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
        if(re.test(str)==false){
            $("#regtip").text("*请输入有效手机号码");
            return false;
        }else{
            $("#regtip").text("");
			return true;
        }
	})




})
















































