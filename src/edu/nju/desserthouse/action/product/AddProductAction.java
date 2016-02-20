package edu.nju.desserthouse.action.product;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Product;
import edu.nju.desserthouse.service.ProductService;
import edu.nju.desserthouse.util.ResultMessage;

public class AddProductAction extends BaseAction {
	private static final long serialVersionUID = 5786390053256291991L;
	@Autowired
	private ProductService productService;
	private Product product;
	private File picture;

	@Override
	@Action(
			value = "addProduct",
			results = { @Result(name = SUCCESS, location = "/product.action", type = "redirectAction"),
					@Result(name = INPUT, location = "/page/product/product.jsp") })
	public String execute() throws Exception {
		String pictureName = "image/product/" + product.getName() + ".jpg";
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		File file = new File(realPath, pictureName);
		boolean uploadPicture = picture.renameTo(file);// renameTo失败表示已经存在该名称的图片
		if (uploadPicture) {
			product.setPicture(pictureName);
			ResultMessage savaResult = productService.addProduct(product);
			if (savaResult == ResultMessage.SUCCESS) {
				return SUCCESS;
			}
		}
		request.setAttribute("errorMessage", "该产品已经存在，无法重新添加");
		return INPUT;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

}
