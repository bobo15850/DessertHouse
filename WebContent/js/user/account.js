var messageShow = document.getElementById("message");
function changeUsername() {
	var nameInput = document.getElementById("usernameInput");
	var newName = nameInput.value;
	if (newName != null && (/\w{1,10}/.test(newName))) {
		$.post("uniqueStringField.action", {
			"map.field" : "username",
			"map.value" : newName
		}, function(json) {
			if (json.map.result == "success") {
				nameInput.value = newName;
				var usernameShow = document.getElementById("username-show");
				usernameShow.innerHTML = newName;
				messageShow.innerHTML = "用户名修改成功";
			} else {
				messageShow.innerHTML = "用户名已被其他人占用";
			}
		});
	} else {
		messageShow.innerHTML = "用户名不能超过10个字符且不能为空，由字母数字下滑线或汉字组成";
	}
}// 改变用户名

function changePhonenumber() {
	var phonenumberInput = document.getElementById("phonenumberInput");
	var newPhone = phonenumberInput.value;
	if (newPhone != null && (/^1\d{10}/.test(newPhone))) {
		$.post("uniqueStringField.action", {
			"map.field" : "phonenumber",
			"map.value" : newPhone
		}, function(json) {
			if (json.map.result == "success") {
				phonenumberInput.value = newPhone;
				messageShow.innerHTML = "绑定手机修改成功";
			} else {
				messageShow.innerHTML = "手机号已被其他人绑定";
			}
		});
	} else {
		messageShow.innerHTML = "请输入正确的手机号格式";
	}
}// 改变用户名

function changeBankId() {
	var bankIdInput = document.getElementById("bankIdInput");
	var newBankId = bankIdInput.value;
	if (newBankId != null && (/^62\d{17}/.test(newBankId))) {
		$.post("uniqueStringField.action", {
			"map.field" : "bankId",
			"map.value" : newBankId
		}, function(json) {
			if (json.map.result == "success") {
				bankIdInput.value = newBankId;
				messageShow.innerHTML = "银行卡设置成功";
			} else {
				messageShow.innerHTML = "银行卡已被其他人绑定";
			}
		});
	} else {
		messageShow.innerHTML = "请输入正确的银行卡格式19位";
	}
}// 改变或绑定银行卡号

function changeLocation() {
	var locationInput = document.getElementById("locationInput");
	var newLocation = locationInput.value;
	if (newLocation != null) {
		$.post("repeatStringField.action", {
			"map.field" : "location",
			"map.value" : newLocation
		}, function(json) {
			if (json.map.result == "success") {
				locationInput.value = newLocation;
				messageShow.innerHTML = "具体地址设置成功";
			} else {
				messageShow.innerHTML = "具体地址设置失败";
			}
		});
	} else {
		messageShow.innerHTML = "地址不能为空";
	}
}// 改变具体地址

function changeGender() {
	var genderSelect = document.getElementById("genderSelect");
	$.post("repeatIntField.action", {
		"map.field" : "gender",
		"map.value" : genderSelect.value
	}, function(json) {
		if (json.map.result == "success") {
			messageShow.innerHTML = "用户性别设置成功";
		} else {
			messageShow.innerHTML = "用户性别设置失败";
		}
	});
}// 设置性别
