package net.liveasy.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import net.liveasy.entity.Load;
import net.liveasy.enums.LoadStatus;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {
	//Fetching records using JPA Query Methods
	List<Load> findByShiperId(String  shipperId);
	List<Load> findByTruckType(String truckType);
	
	//Updating status by using JPQL @Modifying query
	@Modifying
	@Transactional
	@Query("UPDATE Load l SET l.status = :status WHERE l.id = :loadId")
	int updateLoadStatusById(@Param("loadId") UUID loadId, @Param("status") LoadStatus status);
	
	@Query("SELECT l.status FROM Load l WHERE l.id = :loadId")
	LoadStatus findLoadStatusById(@Param("loadId") UUID loadId);
}
