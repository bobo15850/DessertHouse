/**
 * 
 */
function showPanel(btn) {
	var goodsListPanel = document.getElementById("goods-list-panel");
	var goodsSelectPanel = document.getElementById("goods-select-panel");
	goodsListPanel.className = "hide";
	goodsSelectPanel.className = "hide";
	document.getElementById(btn.value).className = "unhide";
}// 显示特定的面板
