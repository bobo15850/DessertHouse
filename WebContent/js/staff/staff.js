/**
 * 员工信息主页的js
 */

function checkAddStaff() {
	return true;
}// 检查添加员工提交框

function showModifyBtn(number) {
	var modifyBtn = document.getElementById("modify-btn-" + number.toString());
	modifyBtn.className = 'btn btn-primary unhide';
	var deleteBtn = document.getElementById("delete-btn-" + number.toString());
	deleteBtn.className = 'btn btn-primary unhide';
}// 显示对应序号的按钮

function hideModifyBtn(number) {
	var modifyBtn = document.getElementById("modify-btn-" + number.toString());
	modifyBtn.className = 'btn btn-primary hide';
	var deleteBtn = document.getElementById("delete-btn-" + number.toString());
	deleteBtn.className = 'btn btn-primary hide';
}// 隐藏对应序号的按钮

function confirmDelete() {
	return confirm("是否确认刪除该员工，該操作不可逆！！！");
}// 确认是否删除

/*
 * 以下为修改员工信息的js
 */
function checkModifyStaff() {
	return true;
}