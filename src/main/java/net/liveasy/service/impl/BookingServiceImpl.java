package net.liveasy.service.impl;

import java.util.Collections;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.liveasy.entity.Booking;
import net.liveasy.entity.Load;
import net.liveasy.enums.BookingStatus;
import net.liveasy.enums.LoadStatus;
import net.liveasy.repo.BookingRepository;
import net.liveasy.repo.LoadRepository;
import net.liveasy.service.IBookingService;
import net.liveasy.service.ILoadService;

@Service
public class BookingServiceImpl implements IBookingService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private LoadRepository loadRepo;

	@Autowired
	private ILoadService loadService;

	@Override
	public Booking createBooking(Booking booking) {
		UUID loadId = booking.getLoadId();
		Optional<Load> loadOptional = loadService.getLoadById(loadId);
		Load load =  loadOptional.get();
		logger.info("Getting Load By Id: loadId: {}", loadOptional);
		
		if (loadOptional.isEmpty()) {
			logger.error("Load  is NEmpty with loadId: {}", loadId);
	        throw new RuntimeException("Load not found with id: " + booking.getLoadId());
	    }
		
		LoadStatus status = loadService.findLoadStatusById(loadId);
		logger.info("Load Status is: "+status);
		if (status != null) {
			if (load.getStatus() == LoadStatus.CANCELLED) {
				throw new RuntimeException("Cannot create booking. Load is already CANCELLED.");				
			}			
			if (booking.getStatus() == null) {
		        booking.setStatus(BookingStatus.PENDING);
		    }			
		}else {
			logger.error("LoadStatus  is Not found with loadId: {}", loadId);
		}
		Booking savedBooking = bookingRepo.save(booking);
		load.setStatus(LoadStatus.BOOKED);
		loadRepo.save(load);
		return savedBooking;
	}

	@Override
	public List<Booking> fetchBookings(String shiperId, String transporterId) {
		if (shiperId != null) {
			return bookingRepo.findByShiperId(shiperId);
		} else if (transporterId != null) {
			return bookingRepo.findByTransporterId(transporterId);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public Booking getBookingById(UUID id) {
		Booking booking = bookingRepo.findById(id).get();// get() is the optional class method
		return booking;
	}

	@Override
	public Booking updateBookingById(UUID id, Booking updatedBooking) {
		Booking oldBooking = bookingRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Booking not found with id: " + id));

		oldBooking.setTransporterId(updatedBooking.getTransporterId());
		oldBooking.setComment(updatedBooking.getComment());
		oldBooking.setProposedRate(updatedBooking.getProposedRate());
		if (updatedBooking.getStatus() != null) {
		    oldBooking.setStatus(updatedBooking.getStatus());
		}
		bookingRepo.save(oldBooking);
		return oldBooking;
	}

	@Override
	public boolean deleteBookingById(UUID id) {
		 Optional<Booking> optionalBooking = bookingRepo.findById(id);
		
		if (optionalBooking.isPresent()) {	
			Booking booking = getBookingById(id);
			UUID loadId = booking.getLoadId();
			bookingRepo.deleteById(id);
			loadRepo.updateLoadStatusById(loadId, LoadStatus.CANCELLED);
			return true;
		} else {
			return false;
		}
	}

}
