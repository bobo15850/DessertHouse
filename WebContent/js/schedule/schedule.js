/**
 * 
 */
function showModifyBtn(number) {
	var modifyBtn = document.getElementById("modify-btn-" + number.toString());
	modifyBtn.className = 'btn btn-primary unhide';
}// 显示对应序号的按钮

function hideModifyBtn(number) {
	var modifyBtn = document.getElementById("modify-btn-" + number.toString());
	modifyBtn.className = 'btn btn-primary hide';
}// 隐藏对应序号的按钮
