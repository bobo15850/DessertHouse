/**
 * 
 */
var goodsId;
var goodsName;
var goodsQuantityEle;// 该值需要修改所以用元素来表示
var goodsPrice;
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
	var currentRows = document.getElementById("goods-select-table").rows.length;
	var insertTr = document.getElementById("goods-select-table").insertRow(
			currentRows);
	insertTr.insertCell(0).innerHTML = currentRows;
	insertTr.insertCell(1).innerHTML = goodsId;
	insertTr.insertCell(2).innerHTML = goodsName;
	insertTr.insertCell(3).innerHTML = goodsPrice;
	insertTr.insertCell(4).innerHTML = document.getElementById("buy-quantity").value;
	insertTr.insertCell(5).innerHTML = "<button class='btn btn-primary'>刪除</button>";
	// TODO
	$('#numInputModal').modal('hide');// 显示模态框
}// 加入购物车，需要考虑已经购买过的，考虑删除考虑传递参数
