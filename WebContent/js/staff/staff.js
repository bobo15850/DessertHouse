/**
 * 员工信息主页的js
 */

function fillShopSelect() {
	var countySelect = document.getElementById("countySelect");
	var shopSelect = document.getElementById("shopSelect");
	var resultShow = document.getElementById("shop-select-result");
	var countyId = countySelect.value;
	shopSelect.options.length = 0;
	resultShow.innerHTML = "";
	if (countyId != null && countyId.length != 0) {
		$.post("/DessertHouse/shop/selectShopByCountyId.action", {
			"map.countyId" : countyId
		}, function(json) {
			if (json.map.idsStr != null) {
				var idsStr = new String(json.map.idsStr);
				var shopnamesStr = new String(json.map.shopnamesStr);
				var ids = idsStr.split("---");
				var shopnames = shopnamesStr.split("---");
				shopSelect.options.add(new Option(shopnames[0], ids[0], true));// 第一个选项默认被选中
				var i = 1;
				for (i = 1; i < ids.length; i++) {
					shopSelect.options.add(new Option(shopnames[i], ids[i]));
				}
			} else {
				resultShow.innerHTML = "该县区不存在分店";
			}
		});
	} else {
		resultShow.innerHTML = "请选择省市区/县再进行搜索";
	}
}// 填充县区里的店铺

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