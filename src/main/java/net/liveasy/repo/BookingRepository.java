package net.liveasy.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.liveasy.entity.Booking;
import net.liveasy.enums.LoadStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
	@Query("Select b from Booking b JOIN Load l ON b.loadId = l.id where l.shiperId = :shiperId")
	public List<Booking> findByShiperId(String shiperId);
	public List<Booking> findByTransporterId(String transporterId);
	
	@Query("SELECT l.status FROM Load l WHERE l.id = :loadId")
	LoadStatus findLoadIdByBookingId(@Param("loadId") UUID loadId);
}	
