package Hotel;

import java.io.Serializable;

public class HotelMan implements Serializable {
    private String ID;
    private String name;
    private String phone;
    private Boolean gender;
    private String room;
    private String checkIn;
    private String checkOut;

    public HotelMan(String ID,String name,String phone,Boolean gender,String room,String checkIn,String checkOut){
        this.ID=ID;
        this.name=name;
        this.phone=phone;
        this.gender=gender;
        this.room=room;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
