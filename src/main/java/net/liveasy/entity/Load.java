package net.liveasy.entity;


import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import net.liveasy.enums.LoadStatus;

import java.sql.Timestamp;

@Entity
@Table(name="Load")
public class Load {
	
	@Id
	@UuidGenerator
	@Column(name = "id", updatable = false, nullable = false)
    private UUID id;

	@Column(name = "shiper_id", nullable = false)
	private String shiperId;
	
	@Column(name = "product_type")
    private String productType;
	
	@Column(name = "truck_type")
    private String truckType;
	
	@Column(name = "no_of_trucks")
    private int noOfTrucks;
	
	@Column(name = "weight")
    private double weight;
	
	@Column(name = "comment")
    private String comment;
	
	@Column(name = "date_posted")
    private Timestamp datePosted;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoadStatus status  = LoadStatus.POSTED;
    
    @Embedded
    private Facility facility;

    
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getShiperId() {
		return shiperId;
	}

	public void setShiperId(String shiperId) {
		this.shiperId = shiperId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public int getNoOfTrucks() {
		return noOfTrucks;
	}

	public void setNoOfTrucks(int noOfTrucks) {
		this.noOfTrucks = noOfTrucks;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Timestamp datePosted) {
		this.datePosted = datePosted;
	}

	public LoadStatus getStatus() {
		return status;
	}

	public void setStatus(LoadStatus status) {
		this.status = status;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Override
	public String toString() {
		return "Load [id=" + id + ", shiperId=" + shiperId + ", productType=" + productType + ", truckType=" + truckType
				+ ", noOfTrucks=" + noOfTrucks + ", weight=" + weight + ", comment=" + comment + ", datePosted="
				+ datePosted + ", status=" + status + ", facility=" + facility + "]";
	}
    
    
}
