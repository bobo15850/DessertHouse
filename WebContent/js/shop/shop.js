function checkAddShop() {
	return true;
}// 检验添加分店

function showModifyBtn(number) {
	var targetBtn = document.getElementById("modify-btn-" + number.toString());
	targetBtn.className = 'btn btn-primary modify-shop-btn unhide';
}// 显示对应序号的按钮

function hideModifyBtn(number) {
	var targetBtn = document.getElementById("modify-btn-" + number.toString());
	targetBtn.className = 'btn btn-primary modify-shop-btn hide';
}// 隐藏对应序号的按钮
