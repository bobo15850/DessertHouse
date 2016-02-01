function changeUsername() {
	var nameInput = document.getElementById("usernameInput");
	var newName = nameInput.value;
	if (newName == null || newName.length > 10 || newName.length < 1) {
		document.getElementById("message").innerHTML = "用户名不能超过10个字符，且不能为空";
	} else {
		$.post("rename.action", {
			"map.newName" : newName
		}, function(json) {
			if (json.map.result == "success") {
				nameInput.value = newName;
				var usernameShow = document.getElementById("username-show");
				usernameShow.innerHTML = newName;
				document.getElementById("message").innerHTML = "用户名修改成功";
			} else {
				document.getElementById("message").innerHTML = "用户名已被其他人占用";
			}
		});
	}
}// 改变用户名

function changePhonenumber() {
	var phonenumberInput = document.getElementById("phonenumberInput");
	var newPhone = phonenumberInput.value;
	if (newPhone != null && newPhone.length == 11
			&& (/^1\d{10}/.test(newPhone))) {
		$.post("resetPhone.action", {
			"map.newPhone" : newPhone
		}, function(json) {
			if (json.map.result == "success") {
				phonenumberInput.value = newPhone;
				document.getElementById("message").innerHTML = "绑定手机修改成功";
			} else {
				document.getElementById("message").innerHTML = "手机号已被其他人绑定";
			}
		});
	} else {
		document.getElementById("message").innerHTML = "请输入正确的手机号格式";
	}
}// 改变用户名
