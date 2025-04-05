package net.liveasy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.liveasy.entity.Load;
import net.liveasy.enums.LoadStatus;

public interface ILoadService {
	public Load createLoad(Load load);
	public List<Load> getLoads(String shiperId, String truckType);
	public Optional<Load> getLoadById(UUID loadId);
	public Load updateLoadById(UUID loadId, Load updatedLoad);
	public boolean deleteLoadById(UUID loadId);
	public void updateLoadStatusById(UUID id, LoadStatus loadStatus);
	public LoadStatus findLoadStatusById(UUID id);
}
