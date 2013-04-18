/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author hdflournoy
 * The objects pertaining to this class will be constructed with the input from the rooms.txt file
 */
public class Rooms {
    
    private int RoomNumber;//room number
    private String building;//building where the room is located at
    private int Capacity;//room capacity 
    private String Campus; //campus is either north south east or west (maybe we call them 1/2/3/4)
    private int Media; //media room or not, 1 = yes, 0 = no 
    
  public Rooms (int rNum, String build, int cap, String camp, int med){
  RoomNumber= rNum;
  building= build;
  Capacity= cap;
  Campus = camp;
  Media= med;
      
  }

    public int getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(int RoomNumber) {
        this.RoomNumber = RoomNumber;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String Campus) {
        this.Campus = Campus;
    }

    public int getMedia() {
        return Media;
    }

    public void setMedia(int Media) {
        this.Media = Media;
    }
  
  
}
