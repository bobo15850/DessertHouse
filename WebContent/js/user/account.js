function changeUsername() {
	var messageShow = document.getElementById("message");
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
	var messageShow = document.getElementById("message");
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
	var messageShow = document.getElementById("message");
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
	var messageShow = document.getElementById("message");
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
	var messageShow = document.getElementById("message");
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

function changePassword() {
	var messageShow = document.getElementById("message");
	var newPasswordInput = document.getElementById("newPasswordInput");
	var passwordConfirmInput = document.getElementById("passwordConfirmInput");
	var newPassword = newPasswordInput.value;
	var passwordConfirm = passwordConfirmInput.value;
	if (newPassword.length >= 8 && newPassword.length <= 16) {
		if (newPassword == passwordConfirm) {
			var oldPasswordInput = document.getElementById("oldPasswordInput");
			$.post("checkOldPassword.action", {
				"map.oldPassword" : oldPasswordInput.value
			}, function(json) {
				if (json.map.result == "success") {
					$.post("repeatStringField.action", {
						"map.field" : "password",
						"map.value" : newPassword
					}, function(json) {
						if (json.map.result == "success") {
							oldPasswordInput.value = "";
							newPasswordInput.value = "";
							passwordConfirmInput.value = "";
							messageShow.innerHTML = "密码设置成功";
						} else {
							oldPasswordInput.value = "";
							newPasswordInput.value = "";
							passwordConfirmInput.value = "";
							messageShow.innerHTML = "密码设置失败";
						}
					});
				} else {
					messageShow.innerHTML = "原密码输入错误";
				}
			});
		} else {
			newPasswordInput.value = "";
			passwordConfirmInput.value = "";
			messageShow.innerHTML = "两次输入的新密码必须相同";
		}
	} else {
		newPasswordInput.value = "";
		passwordConfirmInput.value = "";
		messageShow.innerHTML = "新密码为8-16位";
	}
}

$(function() {
	$('#birthdayInput').datetimepicker({
		viewMode : 'years',
		format : 'YYYY-MM-DD',
	});
});

function changeBirthday() {
	var messageShow = document.getElementById("message");
	var birthday = document.getElementById('birthdayInput').value;
	$.post("setBirthday.action", {
		"map.birthday" : birthday
	}, function(json) {
		if (json.map.result == "success") {
			messageShow.innerHTML = "用户生日设置成功";
		} else {
			messageShow.innerHTML = "用户生日设置失败";
		}
	});
}// 设置用户生日

function changeProvince() {
	var provinceSelect = document.getElementById("provinceSelect");
	var citySelect = document.getElementById("citySelect");
	var countySelect = document.getElementById("countySelect");
	if (provinceSelect.value == "notset") {
		citySelect.options.length = 0;
		countySelect.options.length = 0;
		citySelect.options.add(new Option("未设置", "notset", true));
		countySelect.options.add(new Option("未设置", "notset", true));
		return;
	}
	$.post("lowerRegions.action", {
		"map.id" : provinceSelect.value
	}, function(json) {
		var idsStr = new String(json.map.idsStr);
		var namesStr = new String(json.map.namesStr);
		var ids = idsStr.split("-");
		var names = namesStr.split("-");
		citySelect.options.length = 0;
		citySelect.options.add(new Option(names[0], ids[0], true));// 第一个城市默认被选中
		var i = 1;
		for (i = 1; i < ids.length; i++) {
			citySelect.options.add(new Option(names[i], ids[i]));
		}
		$.post("lowerRegions.action", {
			"map.id" : ids[0]
		}, function(json) {
			idsStr = new String(json.map.idsStr);
			namesStr = new String(json.map.namesStr);
			ids = idsStr.split("-");
			names = namesStr.split("-");
			countySelect.options.length = 0;
			countySelect.options.add(new Option(names[0], ids[0], true));// 第一个区县默认被选中
			for (i = 1; i < ids.length; i++) {
				countySelect.options.add(new Option(names[i], ids[i]));
			}
		});// 设置区县
	});// 设置城市
}// 改变省份

function changeCity() {
	var citySelect = document.getElementById("citySelect");
	var countySelect = document.getElementById("countySelect");
	$.post("lowerRegions.action", {
		"map.id" : citySelect.value
	}, function(json) {
		var idsStr = new String(json.map.idsStr);
		var namesStr = new String(json.map.namesStr);
		var ids = idsStr.split("-");
		var names = namesStr.split("-");
		countySelect.options.length = 0;
		countySelect.options.add(new Option(names[0], ids[0], true));// 第一个选项默认被选中
		var i = 1;
		for (i = 1; i < ids.length; i++) {
			countySelect.options.add(new Option(names[i], ids[i]));
		}
	});
}// 改变城市

function changeRegion() {
	var messageShow = document.getElementById("message");
	var countySelect = document.getElementById("countySelect");
	if (countySelect.value == "notset") {
		messageShow.innerHTML = "用户区域尚未设置，无法保存";
	} else {
		$.post("setRegion.action", {
			"map.id" : countySelect.value
		}, function(json) {
			if (json.map.result == "success") {
				messageShow.innerHTML = "用户地区设置成功";
			} else {
				messageShow.innerHTML = "用户地区设置失败";
			}
		});
	}
}// 设置所属区域

function inactiveAccount() {
	var messageShow = document.getElementById("message");
	$.post("inactiveAccount.action", {}, function(json) {
		if (json.map.result == "success") {
			document.getElementById("userStateInput").value = "正常使用";
			document.getElementById("balanceInput").value = json.map.balance;
			messageShow.innerHTML = "账户激活成功";
		} else {
			messageShow.innerHTML = "账户激活失败";
		}
	});
}// 激活账户

function renewalAccount() {
	var messageShow = document.getElementById("message");
	$.post("renewalAccount.action", {}, function(json) {
		if (json.map.result == "success") {
			document.getElementById("userStateInput").value = "正常使用";
			document.getElementById("balanceInput").value = json.map.balance;
			document.getElementById("userLevelInput").value = json.map.level;
			messageShow.innerHTML = "账户续费成功";
		} else {
			messageShow.innerHTML = "账户续费失败";
		}
	});
}// 续费账户

function rechargeAccount() {
	var messageShow = document.getElementById("message");
	var amount = document.getElementById("amountInput").value;// 充值金额
	if (amount == null || isNaN(amount) || amount.length > 5) {
		document.getElementById("amountInput").value = "";
		document.getElementById("amountInput").placeholder = "请输入数字,且不超过99999";
	} else {
		$
				.post(
						"rechargeAccount.action",
						{
							"map.amount" : amount
						},
						function(json) {
							if (json.map.result == "success") {
								document.getElementById("balanceInput").value = json.map.balance;
								document.getElementById("userLevelInput").value = json.map.level;
								messageShow.innerHTML = "账户充值成功";
							} else {
								messageShow.innerHTML = "账户充值失败";
							}
						});
		$('#rechargeAccountModel').modal('hide');// 隐藏模态框
	}
}

function cancleMembership() {
	var messageShow = document.getElementById("message");
	var password = document.getElementById("cancleConfirmInput").value;
	if (password == null || password.length < 8 || password.length > 16) {
		document.getElementById("cancleConfirmInput").value = "";
		document.getElementById("cancleConfirmInput").placeholder = "请输入密码，8-16位";
	} else {
		$
				.post(
						"cancelMembership.action",
						{
							"map.password" : password
						},
						function(json) {
							if (json.map.result == "success") {
								messageShow.innerHTML = "会员资格已终止";
								document.getElementById("userStateInput").value = "停止";
								document.getElementById("cancleMemberBtn").disabled = "disabled";
								var btns = [ "inactiveAccountBtn",
										"renewalAccountBtn",
										"rechargeAccountBtn" ];
								var i = 0;
								for (i = 0; i < btns.length; i++) {
									if (document.getElementById(btns[i]) != null) {
										document.getElementById(btns[i]).disabled = "disabled";
									}
								}
								$('#cancleMembershipModel').modal('hide');// 隐藏模态框
							} else if (json.map.result == "passwordError") {
								document.getElementById("cancleConfirmInput").value = "";
								document.getElementById("cancleConfirmInput").placeholder = "密码错误";
							} else {
								document.getElementById("cancleConfirmInput").value = "";
								document.getElementById("cancleConfirmInput").placeholder = "未知错误请重新尝试";
							}
						});
	}
}// 取消会员资格
