package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private String series;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public Car() {
    }

    public Car(String model, String series) {
        this.model = model;
        this.series = series;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeries() {
        return series;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return model +"-" +series;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
