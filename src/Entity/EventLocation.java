/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author mahmoud
 */
public class EventLocation {

    private int location_id;
    private int event_id;
    private double lattitude;
    private double longitude;

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public EventLocation(int location_id, int event_id, double lattitude, double longitude) {
        this.location_id = location_id;
        this.event_id = event_id;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }
    

    public EventLocation(int event_id, double lattitude, double longitude) {
        this.event_id = event_id;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public EventLocation() {
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
