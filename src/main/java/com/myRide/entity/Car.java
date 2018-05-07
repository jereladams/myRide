package com.myRide.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type Car.
 */
@Entity(name = "Car")
@Table(name = "cars") // case sensitive!
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Repair> repairs = new HashSet<>();

    @Column(name = "year")
    private String year;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "vin")
    private String vin;

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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets repairs.
     *
     * @return the repairs
     */
    public Set<Repair> getRepairs() {
        return repairs;
    }

    /**
     * Sets repairs.
     *
     * @param repairs the repairs
     */
    public void setRepairs(Set<Repair> repairs) {
        this.repairs = repairs;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Gets make.
     *
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets make.
     *
     * @param make the make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets vin.
     *
     * @return the vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets vin.
     *
     * @param vin the vin
     */
    public void setVin(String vin) {
        this.vin = vin;
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
     * Instantiates a new Car.
     */
    public Car() {
    }

    /**
     * Instantiates a new Car.
     *
     * @param user  the user
     * @param year  the year
     * @param make  the make
     * @param model the model
     * @param vin   the vin
     */
    public Car(User user, String year, String make, String model, String vin) {
        this.user = user;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    /**
     * Instantiates a new Car.
     *
     * @param id    the id
     * @param user  the user
     * @param year  the year
     * @param make  the make
     * @param model the model
     * @param vin   the vin
     */
    public Car(int id, User user, String year, String make, String model, String vin) {
        this.id = id;
        this.user = user;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", user=" + user +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(user, car.user) &&
                Objects.equals(year, car.year) &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, year, make, model, vin);
    }
}