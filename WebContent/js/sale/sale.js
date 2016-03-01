/**
 * 
 */
var goodsId;
var goodsName;
var goodsQuantityEle;// 该值需要修改所以用元素来表示
var goodsPrice;
var goodsArr = new Array();// 已选购的商品

function showPanel(btn) {
	document.getElementById("goods-list-panel").className = "hide";
	document.getElementById("goods-select-panel").className = "hide";
	document.getElementById(btn.value).className = "unhide";
	document.getElementById("goods-list-btn").className = "btn btn-primary btn-block";
	document.getElementById("goods-select-btn").className = "btn btn-primary btn-block";
	document.getElementById(btn.id).className = "btn btn-info btn-block";
}// 显示特定的面板

function chooseGoods(btn) {
	goodsId = btn.value;
	goodsName = document.getElementById("name-" + goodsId).innerHTML;
	goodsPrice = document.getElementById("price-" + goodsId).innerHTML;
	goodsQuantityEle = document.getElementById("quantity-" + goodsId);
	document.getElementById("cur-goods-name").value = goodsName;
	document.getElementById("cur-goods-price").value = goodsPrice;
	document.getElementById("max-quantity").value = goodsQuantityEle.innerHTML;
	$('#numInputModal').modal('show');// 显示模态框
}

function addSelectGoods() {
	var selectTable = document.getElementById("goods-select-table");
	var buyQuantity = parseInt(document.getElementById("buy-quantity").value);// 模态框中的购买数量
	var hasSelect = 0;// flag指示是否添加过该商品
	var num = goodsArr.length;// 第n个
	for (var i = 0; i < goodsArr.length; i++) {
		if (goodsArr[i].id == goodsId) {
			hasSelect = 1;
			num = i;
			break;
		}
	}// 判断是否已经添加过该商品
	if (hasSelect == 1) {
		var targetRow = selectTable.rows[num];
		var targetCell = targetRow.cells[3];
		var newQuantity = goodsArr[num].quantity + parseInt(buyQuantity);
		targetCell.innerHTML = newQuantity;
		goodsArr[num].quantity = newQuantity;
	}// 已经选购过
	else {
		var currentRows = selectTable.rows.length;
		var insertTr = selectTable.insertRow(currentRows);
		insertTr.insertCell(0).innerHTML = goodsId;
		insertTr.insertCell(1).innerHTML = goodsName;
		insertTr.insertCell(2).innerHTML = goodsPrice;
		insertTr.insertCell(3).innerHTML = buyQuantity;
		insertTr.insertCell(4).innerHTML = "<button onclick='deleteSelect(this)' class='btn btn-primary' value='"
				+ goodsId + "'>刪除</button>";
		goodsArr[num] = new Object();
		goodsArr[num].id = goodsId;
		goodsArr[num].quantity = buyQuantity;
		goodsArr[num].name = goodsName;
		goodsArr[num].price = goodsPrice;
	}// 没有选购过
	goodsQuantityEle.innerHTML = goodsQuantityEle.innerHTML - buyQuantity;
	document.getElementById("generateOrderMsg").innerHTML = "";
	$('#numInputModal').modal('hide');// 显示模态框
}// 加入购物车，需要考虑已经购买过的，考虑删除考虑传递参数

function deleteSelect(btn) {
	var confirm = window.confirm("是否确认刪除该商品");
	if (confirm) {
		var selectTable = document.getElementById("goods-select-table");
		var targetGoodsId = btn.value;
		var num = 0;// 第n个
		for (var i = 0; i < goodsArr.length; i++) {
			if (goodsArr[i].id == targetGoodsId) {
				num = i;
				break;
			}
		}// 判断是否已经添加过该商品
		selectTable.deleteRow(num);// 刪除界面表格对应的行
		var goodsListCell = document
				.getElementById("quantity-" + targetGoodsId);
		goodsListCell.innerHTML = parseInt(goodsListCell.innerHTML)
				+ goodsArr[num].quantity;// 还原待选购商品列表中对应的数量
		goodsArr.splice(num, 1);// 删除保存的goodsArr中的值
	}
}// 刪除一个选中的商品

function deleteAllSelect() {
	var confirm = window.confirm("是否确认清空购物车");
	if (confirm) {
		var selectTable = document.getElementById("goods-select-table");
		for (var i = goodsArr.length - 1; i >= 0; i--) {
			selectTable.deleteRow(i);
			var goodsListCell = document.getElementById("quantity-"
					+ goodsArr[i].id);
			goodsListCell.innerHTML = parseInt(goodsListCell.innerHTML)
					+ goodsArr[i].quantity;// 还原待选购商品列表中对应的数量
			goodsArr.splice(i, 1);// 删除保存的goodsArr中的值
		}
	}
}// 清空购物车

function generateOrder() {
	if (goodsArr.length == 0) {
		document.getElementById("generateOrderMsg").innerHTML = "还没有选购任何商品无法创建订单";
		return;
	}
	document.getElementById("order-panel").className = "unhide";
	document.getElementById("goods-list-btn").disabled = "disabled";
	document.getElementById("goods-select-btn").disabled = "disabled";
	document.getElementById("goods-select-panel").className = "hide";
	var orderTable = document.getElementById("order-table");
	var selectTable = document.getElementById("goods-select-table");
	for (var i = orderTable.rows.length - 1; i >= 0; i--) {
		orderTable.deleteRow(i);
	}// 先清空订单表
	for (var i = 0; i < goodsArr.length; i++) {
		var insertTr = orderTable.insertRow(i);
		insertTr.insertCell(0).innerHTML = i;
		insertTr.insertCell(1).innerHTML = goodsArr[i].name;
		insertTr.insertCell(2).innerHTML = goodsArr[i].id
				+ "<input type='hidden' name='goodsIdList[" + i + "]' value='"
				+ goodsArr[i].id + "'/>";
		insertTr.insertCell(3).innerHTML = goodsArr[i].price;
		insertTr.insertCell(4).innerHTML = goodsArr[i].quantity
				+ "<input type='hidden' name='order.goodsItemList[" + i
				+ "].quantity' value='" + goodsArr[i].quantity + "'/>";
		insertTr.insertCell(5).innerHTML = goodsArr[i].price
				* goodsArr[i].quantity
				+ "<input type='hidden' name='order.goodsItemList[" + i
				+ "].money' value='" + goodsArr[i].price * goodsArr[i].quantity
				+ "'>";
	}// 添加订单项
}// 生成订单

function cancleOrder() {
	document.getElementById("order-panel").className = "hide";
	document.getElementById("goods-list-btn").removeAttribute("disabled");
	document.getElementById("goods-select-btn").removeAttribute("disabled");
	document.getElementById("goods-select-panel").className = "unhide";
	document.getElementById("generateOrderMsg").innerHTML = "";
}// 取消订单
