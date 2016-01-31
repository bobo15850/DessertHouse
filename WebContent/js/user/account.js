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
				nameInput.value = json.map.newName;
				var usernameShow = document.getElementById("username-show");
				usernameShow.innerHTML = json.map.newName;
				document.getElementById("message").innerHTML = "用户名修改成功";
			} else {
				document.getElementById("message").innerHTML = "用户名已被其他人占用";
			}
		});
	}
}// 改变用户名
