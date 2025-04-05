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

import net.liveasy.entity.Booking;
import net.liveasy.service.IBookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		return ResponseEntity.ok(bookingService.createBooking(booking));
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getBooking(@RequestParam(required = false) String shipperId,
			@RequestParam(required = false) String transporterId) {
		return ResponseEntity.ok(bookingService.fetchBookings(shipperId, transporterId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getbookingById(@PathVariable UUID id) {
		return ResponseEntity.ok(bookingService.getBookingById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBookingById(@PathVariable UUID id, @RequestBody Booking updatedBooking) {
		return ResponseEntity.ok(bookingService.updateBookingById(id, updatedBooking));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookingById(@PathVariable UUID id) {
		boolean isDeleted = bookingService.deleteBookingById(id);
		return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}