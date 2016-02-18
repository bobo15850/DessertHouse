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
	$.post("region/lowerRegions.action", {
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
		$.post("region/lowerRegions.action", {
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
	$.post("region/lowerRegions.action", {
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
