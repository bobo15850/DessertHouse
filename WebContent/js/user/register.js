function check_form() {
	var password_repeat = document.getElementsByName("password_repeat");
	return check_password_repeat(password_repeat[0]);
}// 验证整个表单，即为验证最后的密码重复是否正确，其他的都已经验证过了

function check_username(obj) {
	var username = obj.value;
	if (username == null || username.length == 0) {
		obj.placeholder = "用户名不能为空"
	} else {
		if (username.length > 10) {
			obj.placeholder = "用户名不能超过10个字符";
		}
	}
}// 检验用户名

function check_password(obj) {
	var password = obj.value;
	if (password == null || password.length == 0) {
		obj.placeholder = "密码不能为空";
	} else {
		if (password.length < 8) {
			obj.value = "";
			obj.placeholder = "密码至少8位，请重新输入";
		} else if (password.length > 16) {
			obj.value = "";
			obj.placeholder = "密码最多16位，请重新输入"
		}
	}
}// 检验密码

function check_password_repeat(obj) {
	var passwordInput = document.getElementsByName("user.password");
	var password = passwordInput[0].value;
	var password_repeat = obj.value;
	if (password == password_repeat) {
		return true;
	} else {
		obj.value = "";
		obj.placeholder = "两次输入的密码必须相同";
		return false;
	}
}// 检验密码重复
