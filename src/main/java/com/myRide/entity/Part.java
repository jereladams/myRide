package com.myRide.entity;

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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets repair.
     *
     * @return the repair
     */
    public Repair getRepair() {
        return repair;
    }

    /**
     * Sets repair.
     *
     * @param repair the repair
     */
    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    /**
     * Gets part name.
     *
     * @return the part name
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets part name.
     *
     * @param partName the part name
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * Gets manufacturer.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets manufacturer.
     *
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets part number.
     *
     * @return the part number
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets part number.
     *
     * @param partNumber the part number
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Gets warranty.
     *
     * @return the warranty
     */
    public String getWarranty() {
        return warranty;
    }

    /**
     * Sets warranty.
     *
     * @param warranty the warranty
     */
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    /**
     * Gets supplier.
     *
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Sets supplier.
     *
     * @param supplier the supplier
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Instantiates a new Part.
     */
    public Part() {
    }

    /**
     * Instantiates a new Part.
     *
     * @param repair       the repair
     * @param partName     the part name
     * @param manufacturer the manufacturer
     * @param partNumber   the part number
     * @param warranty     the warranty
     * @param supplier     the supplier
     * @param price        the price
     * @param description  the description
     */
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

    /**
     * Instantiates a new Part.
     *
     * @param Id           the id
     * @param repair       the repair
     * @param partName     the part name
     * @param manufacturer the manufacturer
     * @param partNumber   the part number
     * @param warranty     the warranty
     * @param supplier     the supplier
     * @param price        the price
     * @param description  the description
     */
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