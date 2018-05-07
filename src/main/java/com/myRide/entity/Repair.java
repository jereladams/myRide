package com.myRide.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Repair.
 */
@Entity(name = "Repair")
@Table(name = "repairs") // case sensitive!
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private Car car;

    @OneToMany(mappedBy = "repair", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Part> parts = new HashSet<>();

    @Column(name = "service_date")
    private LocalDate serviceDate;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "service_provider")
    private String serviceProvider;

    @Column(name = "labor_cost")
    private double laborCost;

    @Column(name = "current_mileage")
    private Integer currentMileage;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "service_performed")
    private String servicePerformed;

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
     * Gets car.
     *
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets car.
     *
     * @param car the car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Gets parts.
     *
     * @return the parts
     */
    public Set<Part> getParts() {
        return parts;
    }

    /**
     * Sets parts.
     *
     * @param parts the parts
     */
    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    /**
     * Gets service date.
     *
     * @return the service date
     */
    public LocalDate getServiceDate() {
        return serviceDate;
    }

    /**
     * Sets service date.
     *
     * @param year the year
     */
    public void setService_date(LocalDate year) {
        this.serviceDate = year;
    }

    /**
     * Gets invoice number.
     *
     * @return the invoice number
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets invoice number.
     *
     * @param invoiceNumber the invoice number
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * Gets service provider.
     *
     * @return the service provider
     */
    public String getServiceProvider() {
        return serviceProvider;
    }

    /**
     * Sets service provider.
     *
     * @param serviceProvider the service provider
     */
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    /**
     * Gets labor cost.
     *
     * @return the labor cost
     */
    public double getLaborCost() {
        return laborCost;
    }

    /**
     * Sets labor cost.
     *
     * @param laborCost the labor cost
     */
    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    /**
     * Gets current mileage.
     *
     * @return the current mileage
     */
    public Integer getCurrentMileage() {
        return currentMileage;
    }

    /**
     * Sets current mileage.
     *
     * @param currentMileage the current mileage
     */
    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
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
     * Gets service performed.
     *
     * @return the service performed
     */
    public String getServicePerformed() {
        return servicePerformed;
    }

    /**
     * Sets service performed.
     *
     * @param servicePerformed the service performed
     */
    public void setServicePerformed(String servicePerformed) {
        this.servicePerformed = servicePerformed;
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
     * Instantiates a new Repair.
     */
    public Repair() {
    }

    /**
     * Instantiates a new Repair.
     *
     * @param car              the car
     * @param serviceDate      the service date
     * @param invoiceNumber    the invoice number
     * @param serviceProvider  the service provider
     * @param laborCost        the labor cost
     * @param currentMileage   the current mileage
     * @param warranty         the warranty
     * @param servicePerformed the service performed
     * @param description      the description
     */
    public Repair(Car car, LocalDate serviceDate, String invoiceNumber, String serviceProvider, double laborCost, Integer currentMileage, String warranty, String servicePerformed, String description) {
        this.car = car;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.serviceProvider = serviceProvider;
        this.laborCost = laborCost;
        this.currentMileage = currentMileage;
        this.warranty = warranty;
        this.servicePerformed = servicePerformed;
        this.description = description;
    }

    /**
     * Instantiates a new Repair.
     *
     * @param id               the id
     * @param car              the car
     * @param serviceDate      the service date
     * @param invoiceNumber    the invoice number
     * @param serviceProvider  the service provider
     * @param laborCost        the labor cost
     * @param currentMileage   the current mileage
     * @param warranty         the warranty
     * @param servicePerformed the service performed
     * @param description      the description
     */
    public Repair(int id, Car car, LocalDate serviceDate, String invoiceNumber, String serviceProvider, double laborCost, Integer currentMileage, String warranty, String servicePerformed, String description) {
        this.id = id;
        this.car = car;
        this.serviceDate = serviceDate;
        this.invoiceNumber = invoiceNumber;
        this.serviceProvider = serviceProvider;
        this.laborCost = laborCost;
        this.currentMileage = currentMileage;
        this.warranty = warranty;
        this.servicePerformed = servicePerformed;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", car=" + car +
                ", year=" + serviceDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", laborCost=" + laborCost +
                ", currentMileage=" + currentMileage +
                ", warranty='" + warranty + '\'' +
                ", servicePerformed='" + servicePerformed + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return id == repair.id &&
                Double.compare(repair.laborCost, laborCost) == 0 &&
                Objects.equals(car, repair.car) &&
                Objects.equals(serviceDate, repair.serviceDate) &&
                Objects.equals(invoiceNumber, repair.invoiceNumber) &&
                Objects.equals(serviceProvider, repair.serviceProvider) &&
                Objects.equals(currentMileage, repair.currentMileage) &&
                Objects.equals(warranty, repair.warranty) &&
                Objects.equals(servicePerformed, repair.servicePerformed) &&
                Objects.equals(description, repair.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car, serviceDate, invoiceNumber, serviceProvider, laborCost, currentMileage, warranty, servicePerformed, description);
    }
}