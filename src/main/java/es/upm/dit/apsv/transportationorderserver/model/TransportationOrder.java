package es.upm.dit.apsv.transportationorderserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransportationOrder {

    private String toid;

    @Id
    private String truck;

    private long originDate;
    private double originLat;
    private double originLong;

    private long dstDate;
    private double dstLat;
    private double dstLong;

    private long lastDate;
    private double lastLat;
    private double lastLong;

    private int st;

    // Constructor with all fields
    public TransportationOrder(String toid, String truck, long originDate,
                               double originLat, double originLong,
                               long dstDate, double dstLat, double dstLong,
                               long lastDate, double lastLat, double lastLong,
                               int st) {
        this.toid = toid;
        this.truck = truck;
        this.originDate = originDate;
        this.originLat = originLat;
        this.originLong = originLong;
        this.dstDate = dstDate;
        this.dstLat = dstLat;
        this.dstLong = dstLong;
        this.lastDate = lastDate;
        this.lastLat = lastLat;
        this.lastLong = lastLong;
        this.st = st;
    }

    // Method to calculate the distance to the destination
    public double distanceToDestination() {
        return Math.sqrt(Math.pow(this.dstLat - this.lastLat, 2)
                + Math.pow(this.dstLong - this.lastLong, 2));
    }

    // Override equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportationOrder that = (TransportationOrder) o;
        return Objects.equals(truck, that.truck);
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(truck);
    }
}
