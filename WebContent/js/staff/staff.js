/**
 * 
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
