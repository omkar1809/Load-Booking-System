package net.liveasy.service;

import java.util.List;
import java.util.UUID;

import net.liveasy.entity.Booking;

public interface IBookingService {
	Booking createBooking(Booking booking);
	List<Booking> fetchBookings(String shiperId, String transporterId);
	Booking getBookingById(UUID id);
	Booking updateBookingById(UUID id, Booking updatedBooking);
	boolean deleteBookingById(UUID id);
}
