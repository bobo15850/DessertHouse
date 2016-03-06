package edu.nju.desserthouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.desserthouse.dao.RegionDao;
import edu.nju.desserthouse.model.Region;
import edu.nju.desserthouse.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;

	@Override
	public Region getRegionById(int id) {
		return regionDao.get(Region.class, id);
	}

	@Override
	public List<Region> getLowerRegions(int id) {
		return regionDao.findByColumns(Region.class, new String[] { "parentId" }, new Integer[] { id });
	}

	@Override
	public String getCompleteRegionStr(int regionId) {
		Region county = regionDao.get(Region.class, regionId);
		Region city = regionDao.get(Region.class, county.getId());
		Region province = regionDao.get(Region.class, city.getId());
		String regionStr = province.getName() + "省" + city.getName() + "市" + county.getName();
		return regionStr;
	}
}
