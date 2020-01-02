
package Entity;



import java.util.Date;


/**
 *
 * @author mahmoud
 */
public class event {
   private int event_id;
    private int user_id;
    private Date event_date;
    private String address;
    private String event_description;
    private String event_title;
    private String event_picture;
    
    
    private Date event_end_date;
    private String country;
    private String categories;
    private String event_houre;
    private int event_nbrolace;

    public event(int event_id, int user_id, Date event_date, String address, String event_description, String event_title, String event_picture, Date event_end_date, String country, String categories, String event_houre, int event_nbrolace) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.event_date = event_date;
        this.address = address;
        this.event_description = event_description;
        this.event_title = event_title;
        this.event_picture = event_picture;
        this.event_end_date = event_end_date;
        this.country = country;
        this.categories = categories;
        this.event_houre = event_houre;
        this.event_nbrolace = event_nbrolace;
    }

    public event(int user_id, Date event_date, String address, String event_description, String event_title, String event_picture, Date event_end_date, String country, String categories, String event_houre, int event_nbrolace) {
        this.user_id = user_id;
        this.event_date = event_date;
        this.address = address;
        this.event_description = event_description;
        this.event_title = event_title;
        this.event_picture = event_picture;
        this.event_end_date = event_end_date;
        this.country = country;
        this.categories = categories;
        this.event_houre = event_houre;
        this.event_nbrolace = event_nbrolace;
    }

    public event() {
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_picture() {
        return event_picture;
    }

    public void setEvent_picture(String event_picture) {
        this.event_picture = event_picture;
    }

    public Date getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(Date event_end_date) {
        this.event_end_date = event_end_date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getEvent_houre() {
        return event_houre;
    }

    public void setEvent_houre(String event_houre) {
        this.event_houre = event_houre;
    }

    public int getEvent_nbrolace() {
        return event_nbrolace;
    }

    public void setEvent_nbrolace(int event_nbrolace) {
        this.event_nbrolace = event_nbrolace;
    }

   
    
    
    
    
}
