package com.myRide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The type Part.
 */
@Entity(name = "Part")
@Table(name = "parts") // case sensitive!
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private Repair repair;

    @Column(name = "part_name")
    private String partName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "part_number")
    private String partNumber;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Part() {
    }

    public Part(Repair repair, String partName, String manufacturer, String partNumber, String warranty, String supplier, double price, String description) {
        this.repair = repair;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.warranty = warranty;
        this.supplier = supplier;
        this.price = price;
        this.description = description;
    }

    public Part(int Id, Repair repair, String partName, String manufacturer, String partNumber, String warranty, String supplier, double price, String description) {
        this.id = Id;
        this.repair = repair;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.warranty = warranty;
        this.supplier = supplier;
        this.price = price;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id &&
                Double.compare(part.price, price) == 0 &&
                Objects.equals(partName, part.partName) &&
                Objects.equals(manufacturer, part.manufacturer) &&
                Objects.equals(partNumber, part.partNumber) &&
                Objects.equals(warranty, part.warranty) &&
                Objects.equals(supplier, part.supplier) &&
                Objects.equals(description, part.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, partName, manufacturer, partNumber, warranty, supplier, price, description);
    }
}