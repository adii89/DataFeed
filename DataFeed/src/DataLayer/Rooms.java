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
    private boolean Media; //media room or not, 1 = yes, 0 = no 
    
  public Rooms (int rNum, String build, int cap, String camp, boolean med){
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public boolean getMedia() {
        return Media;
    }

    public void setMedia(boolean Media) {
        this.Media = Media;
    }
  
     @Override 
    public String toString(){
    
        StringBuilder i = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        i.append("Room number: " + getRoomNumber() + NEW_LINE);
        i.append("Building:    " + getBuilding() + NEW_LINE);
        i.append("Capacity:    " + getCapacity() + NEW_LINE);
        i.append("Campus:      " + getCampus() + NEW_LINE);
        i.append("Media:       " + getMedia() + NEW_LINE);
        
        return i.toString();
    
    }//end toString
  
}
