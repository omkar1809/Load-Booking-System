package net.liveasy.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.liveasy.entity.Load;
import net.liveasy.service.ILoadService;

@RestController
@RequestMapping("/load")
public class LoadController {
	@Autowired
	private ILoadService loadService;
	
	@PostMapping
	public ResponseEntity<Load> createLoad(@RequestBody Load load) {
		return ResponseEntity.ok(loadService.createLoad(load));
	}
	
	@GetMapping
	public ResponseEntity<List<Load>> fetchLoadsByShipperIdOrTruckType(@RequestParam(required = false) String shipperId,
												@RequestParam(required = false) String truckType){
		 List<Load> loadList = loadService.getLoads(shipperId, truckType);
		 return ResponseEntity.ok(loadList);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Load> getLoadById(@PathVariable UUID id){
		Load load = loadService.getLoadById(id).get();
		return ResponseEntity.ok(load);
	}
	
	@PutMapping("/{loadId}")
	public ResponseEntity<Load> updateLoadById(@PathVariable UUID loadId, @RequestBody Load updatedLoad){
		  return ResponseEntity.ok(loadService.updateLoadById(loadId, updatedLoad));
	}
	
	@DeleteMapping("/{loadId}")
	public ResponseEntity<Void> deleteLoadById(@PathVariable UUID loadId) {
		boolean isDeleted = loadService.deleteLoadById(loadId);
		return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}
