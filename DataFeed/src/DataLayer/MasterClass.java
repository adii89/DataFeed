/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import java.io.*;
import java.util.*;
import java.text.*;
import DataAccess.Database;
/**
 *
 * @author Valerie
 * //Master Class
 */
public class MasterClass {
 
    private static ArrayList<Rooms> rooms = new ArrayList<Rooms>();
    private static ArrayList<InstructorGeneralPreferences> instructorsGeneralP = new ArrayList<InstructorGeneralPreferences>();
    private static ArrayList<Sections> sections = new ArrayList<Sections>();
    private static ArrayList<Courses> courses = new ArrayList<Courses>();
    
    
//    static final Comparator<Rooms> capacity  = new Comparator<Rooms>(){
//     @Override
//        public int compare(Rooms r1, Rooms r2) {
//                return r2.Capacity().compareTo(r1.Capacity(0));
//    }
    //private static 
 //Building b;
public static void main(String[] args)throws IOException{
        
/*/Store the path for the input files in a String array.  This can done in another way 
 * (reading froma file the different paths), 
    but for simplicity purposes I have it like this.    
    * The reading of the files can also be done using a separate class.  Later on it can be improved.  Valerie
    * 
    */
   String[] filesArray = {"C:\\rooms.txt",  "C:\\preferences.txt", "C:\\sections.txt", "C:\\enroll.txt"};
   String pathFile;
  // String changeBuilding;
   String roomBuild; 
   String previousBuilding= " ";
   String previousDepartment =" ";
   int roomBuildingID=0;
    int departmentID= 0;
   for (int i =0; i<4; i ++){
    
        pathFile = filesArray[i];//holds the current file

        
        File checkFile= new File(pathFile);
      

            if(checkFile.exists()){
                
                
                 BufferedReader br= new BufferedReader(new FileReader(pathFile));
                 String line= br.readLine(); //reads column names
                 String columns[] = line.split("\\|");//gets the name of the columns, this will be used when reading preferences.txt
                 line= br.readLine(); //reads first line of records
                 String delims = " "; //for parsing the Instructor's name
                    while(line!=null){
                        
                        switch(i) {
                        
                            case 0:
                                //reads "C:\\rooms.txt"
                                String[] room = line.split("\\|");
                                int roomN =  Integer.parseInt(room[0].trim());
                                roomBuild= room[1].trim();
                                
                                int capacity =  Integer.parseInt(room[2].trim());
                                String campus = room[3].trim();
                                
                                int campusId=0;//1-north, 2-south, 3-east, 4-west
                                
                                switch (campus){
                                
                                    case "NORTH":
                                        
                                    campusId=1;
                                        break;
                                    case "SOUTH":
                                        campusId=2;
                                        break;
                                        
                                    case "EAST":
                                        campusId=3;
                                        break;
                                        
                                        case "WEST":
                                        campusId=4;
                                        break;
                                }//end switch 
                                
                                boolean media = true;
                                if(room[4].trim().equalsIgnoreCase("no")){
                                media = false;
                                }//end if
                                 //Building b = new Building(campusId); 
                               
                                 
                                 if( roomBuild != previousBuilding){
                                     int buildI=0;
                                    Building b= new Building(campusId, buildI);                  
                                    b.Insert();
                                    roomBuildingID=b.getBuildingID();
                                   }//end if
                                 
                                 
                                ////FIRST ASSING THE VALUE OF 0 TO ROOM ID, INSTANTIATE THE OBJECT AND PUT 0 IN ROOM ID, BEFORE THE INSERT CALL A SET METHOD
                                int roomId=0;
                                Rooms r = new Rooms(roomId, roomN, roomBuildingID,capacity, campus, media);
                                r.Insert();
                                r.setRoomId(roomId);
                              
                                ///r.insert...EXAMPLE
                                System.out.println(r.toString());
                                rooms.add(r);
                               
                                previousBuilding=roomBuild;
                                                          
                                break;
                                
                             case 1:
                                    //reads "C:\\preferences.txt"

                                    String[] preference = line.split("\\|");//takes the line and splits it by the "|" character

                                    String departm=preference[0].trim();
                                   
                                    if(previousDepartment!=departm){
                                    ///different department
                                        
                                    Department d= new Department(departm);
                                    d.Insert();
                                    departmentID=d.getDepartmentId();
                                    
                                    }//end if
                                    
                                    String fullName=preference[1].trim();//reads full name
                                    String[] name=fullName.split(delims);//parse the instructor's name using as a delimiter the space
                                    String lName= name[0];
                                    String fName= name[1];
                                    char mInitial = name[2].trim().charAt(0); // s.charAt(0);
                                    int numSecToTeach=Integer.parseInt(preference[2].trim());
                                    int v=3;

                                    String campusP=columns[3].trim();
                                    String preferenceCampus;
                                    ArrayList<String> p = new ArrayList<String>();
                                        for(int j=0; j<4;j++){
                                            preferenceCampus=preference[v].trim();
                                            if(preferenceCampus.equalsIgnoreCase("yes")){

                                                campusP=columns[v].substring(0, 5).trim();
                                                p.add(campusP.trim());                                                          }
                                            v++;} // end for checking campus preference


                                    int weekend=0;
                                    String preferenceW = preference[7].trim();                                                             
                                    if(preferenceW.equalsIgnoreCase("no")){
                                            weekend=1;}
                                            //end if
                                    int insID=0;
                                    InstructorGeneralPreferences instruct= new InstructorGeneralPreferences(insID, departmentID, lName, fName, mInitial, numSecToTeach, weekend, p);
                                    System.out.println(instruct.toString());
                                    instructorsGeneralP.add(instruct);

                                     break;
                            case 2:

                                    //"C:\\sections.txt"
                                    String[] section = line.split("\\|");
                                    String courseN= section[0].trim();
                                    String department = section[1].trim();

                                    String callNumber =section[2].trim();
                                    String days= section[3];
                                    String time = section[4];
                                    media = true;
                                     if(section[5].equalsIgnoreCase("no")){
                                        media = false;
                                           }//end if

                                    Sections s= new Sections(courseN, department, callNumber,days, time, media);
                                    System.out.println(s.toString());
                                    sections.add(s);

                                    String insertSections;
                                    insertSections="INSERT INTO ";

                                    break;
                               case 3:
                                     
                                    String[] course=line.split("\\|");
                                    String courseNum = course[0].trim();
                                    String departmen = course[1].trim();
                                    int numEnrol= Integer.parseInt(course[2].trim());
                                    Courses c = new Courses(courseNum, departmen, numEnrol);
                                    System.out.println(c.toString());
                                    courses.add(c);
                                   break;
                                                                
                        }//end switch
                       
                        line= br.readLine();//read next record in the file
                    }//end while loop
   

            }//end if

 
    }//end for loop that iterates through each file of the aaray of files

  //Start the INSERTS
   
 //for (Rooms r: rooms){
//   String sqlInsert;
//   String sqlCommand;
//   sqlInsert="INSERT INTO dbo.BuildingRoom (RoomNumber, RoomCapacity, MediaAvailable) VALUES" 
//           + r.getRoomNumber() +  r.getCapacity() + r.getMedia();
//   //sqlCommand=
//           Database.InsertSQL(sqlInsert);
   
   }//??????????
   
   //****Start scheduling courses
   
   
   
   
   //Sorting the rooms by capacity
//    List<Rooms> rms = new ArrayList<Rooms>();  
//    rms=rooms;
//    Collections.sort(rms, new RoomComparator());
    //Sorting the courses by the number of students enrolled in the previous semester
    
//    List<Courses> secs = new ArrayList<Courses>();
//    cour= courses;
//    Collections.sort(cour, new CoursesComparator());
      //System.out.println(rms);

      
    }//end public static void main
    
//
//end MasterClass
