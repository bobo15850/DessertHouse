package edu.nju.desserthouse.action.staff;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nju.desserthouse.action.BaseAction;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.model.Shop;
import edu.nju.desserthouse.model.User;
import edu.nju.desserthouse.service.RegionService;
import edu.nju.desserthouse.service.ShopService;
import edu.nju.desserthouse.service.UserService;

public class ToModifyStaffAction extends BaseAction {
	private static final long serialVersionUID = 3514101035078976421L;
	@Autowired
	private UserService userService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ShopService shopService;

	@Override
	@Action(value = "toModifyStaff", results = { @Result(name = SUCCESS, location = "/page/staff/modifyStaff.jsp") })
	public String execute() throws Exception {
		int staffId = Integer.parseInt(request.getParameter("staffId"));
		User staff = userService.getUserById(staffId);
		request.setAttribute("staff", staff);
		Region targetCounty = regionService.getRegionById(staff.getShop().getRegion().getId());
		Region targetCity = regionService.getRegionById(targetCounty.getParentId());
		Region targetProvince = regionService.getRegionById(targetCity.getParentId());
		request.setAttribute("targetCounty", targetCounty);
		request.setAttribute("targetCity", targetCity);
		request.setAttribute("targetProvince", targetProvince);
		// 取得所有的地区
		List<Region> provinces = regionService.getLowerRegions(1);
		List<Region> cities = regionService.getLowerRegions(targetProvince.getId());
		List<Region> counties = regionService.getLowerRegions(targetCity.getId());
		request.setAttribute("provinces", provinces);
		request.setAttribute("cities", cities);
		request.setAttribute("counties", counties);
		//
		List<Shop> shopes = shopService.getShopesByCountyId(targetCounty.getId());
		request.setAttribute("shopes", shopes);
		request.setAttribute("targetShop", staff.getShop());
		return SUCCESS;
	}

}
