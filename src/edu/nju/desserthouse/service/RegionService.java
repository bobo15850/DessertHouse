package edu.nju.desserthouse.service;

import java.util.List;

import edu.nju.desserthouse.model.Region;

public interface RegionService {
	public Region getRegionById(int id);

	public List<Region> getLowerRegions(int id);

	public String getCompleteRegionStr(int regionId);
}
