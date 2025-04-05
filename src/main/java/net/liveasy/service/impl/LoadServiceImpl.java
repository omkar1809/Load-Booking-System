package net.liveasy.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import net.liveasy.entity.Load;
import net.liveasy.enums.LoadStatus;
import net.liveasy.repo.LoadRepository;
import net.liveasy.service.ILoadService;

@Service
public class LoadServiceImpl implements ILoadService {

	@Autowired
	private LoadRepository loadRepo;

	@Override
	public Load createLoad(Load load) {
		Load savedLoad = loadRepo.save(load);
		return savedLoad;
	}

	@Override
	public List<Load> getLoads(String shiperId, String truckType) {
		if (shiperId != null) {
			return loadRepo.findByShiperId(shiperId);
		} else if (truckType != null) {
			return loadRepo.findByTruckType(truckType);
		} else {
			return loadRepo.findAll();
		}
	}

	@Override
	public Optional<Load> getLoadById(UUID loadId) {
		return loadRepo.findById(loadId);
	}

	@Override
	public Load updateLoadById(UUID loadId, Load updatedLoad) {
		Optional<Load> oldLoad = loadRepo.findById(loadId);

		if (oldLoad.isPresent()) {
			Load load = oldLoad.get();
			load.setNoOfTrucks(updatedLoad.getNoOfTrucks());
			load.setProductType(updatedLoad.getProductType());
			load.setTruckType(updatedLoad.getTruckType());
			load.setComment(updatedLoad.getComment());
			load.setWeight(updatedLoad.getWeight());
			return loadRepo.save(load);
		}
		return null;
	}

	@Override
	public boolean deleteLoadById(UUID loadId) {
		if(loadRepo.existsById(loadId)) {
	       loadRepo.deleteById(loadId);;
	       return true;
		}else {
			return false;
		}
	}

	@Override
	public void updateLoadStatusById(UUID id, LoadStatus loadStatus) {		
		 int updatedRows = loadRepo.updateLoadStatusById(id, loadStatus);
		   if(updatedRows == 0) {
			 throw new EntityNotFoundException("Load not found with Id"+ id);
		}
	}

	@Override
	public LoadStatus findLoadStatusById(UUID id) {
		return loadRepo.findLoadStatusById(id);
	}
}