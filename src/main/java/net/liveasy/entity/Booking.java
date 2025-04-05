package net.liveasy.entity;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import net.liveasy.enums.BookingStatus;

@Entity
@Table(name="Booking")
public class Booking {
	@Id
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(nullable = false)
	private UUID loadId;
	
	@Column(name="transporter_id", nullable = false)
	private String transporterId;
	
	@Column(name = "proposed_rate", nullable = false)
	private double proposedRate;
	
	@Column(name="comment")
	private String comment;

	@Column(name="requested_at", nullable = false, updatable = false)
	private Timestamp requestedAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BookingStatus status = BookingStatus.PENDING;
	
	public Booking() {}
	
	public Booking(UUID id, UUID loadId, String transporterId, double proposedRate, String comment,
			Timestamp requestedAt, BookingStatus status) {
		super();
		this.id = id;
		this.loadId = loadId;
		this.transporterId = transporterId;
		this.proposedRate = proposedRate;
		this.comment = comment;
		this.requestedAt = requestedAt;
		this.status = status;
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getLoadId() {
		return loadId;
	}

	public void setLoadId(UUID loadId) {
		this.loadId = loadId;
	}

	public String getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}

	public double getProposedRate() {
		return proposedRate;
	}

	public void setProposedRate(double proposedRate) {
		this.proposedRate = proposedRate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Timestamp requestedAt) {
		this.requestedAt = requestedAt;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

}
