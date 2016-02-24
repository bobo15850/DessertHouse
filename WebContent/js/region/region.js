/**
 * 对于省市区三级联动的操作
 */
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
	$.post("/DessertHouse/region/lowerRegions.action", {
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
		$.post("/DessertHouse/region/lowerRegions.action", {
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
	$.post("/DessertHouse/region/lowerRegions.action", {
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
