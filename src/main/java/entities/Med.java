package entities;

import java.time.LocalDate;

public class Med {
    private int id;
    private String name;
    private double price;
    private LocalDate Expiration_date;
    private String manufacturer;

    public Med() {
    }

    public Med(int id, String name, double price, LocalDate Expiration_date, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.Expiration_date = Expiration_date;
        this.manufacturer = manufacturer;
    }

    public Med(String name, double price, LocalDate Expiration_date, String manufacturer) {
        this.name = name;
        this.price = price;
        this.Expiration_date = Expiration_date;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiration_date() {
        return Expiration_date;
    }

    public void setExpiration_date(LocalDate Expiration_date) {
        this.Expiration_date = Expiration_date;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Med{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Expiration_date=" + Expiration_date +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
