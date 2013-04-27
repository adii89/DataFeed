/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;
import java.io.*;
import java.util.*;
import java.text.*;
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
    
public static void main(String[] args)throws IOException{
        
/*/Store the path for the input files in a String array.  This can done in another way 
 * (reading froma file the different paths), 
    but for simplicity purposes I have it like this.    
    * The reading of the files can also be done using a separate class.  Later on it can be improved.  Valerie
    * 
    */
   String[] filesArray = {"C:\\rooms.txt",  "C:\\preferences.txt", "C:\\sections.txt", "C:\\enroll.txt", "C:\\travel.txt", "C:\\instructors.txt"};
   String pathFile;
   String[] yesNoCampus= new String[6]; //preferences on campus location...the last entry on the array will make reference the weekend availability
   //Preferences[] prefe= new Preferences[1000];
   //Preferences p;
   ///Instructor instruct;
   int counterInstructorPref=0;
   for (int i =0; i<4; i ++){
    
        pathFile = filesArray[i];//holds the current file

        Scanner path= new Scanner(new File(pathFile));
        File checkFile= new File(pathFile);
        String tempString;

            if(checkFile.exists()){
                
                
                 BufferedReader br= new BufferedReader(new FileReader(pathFile));
                 String line= br.readLine(); //reads column names
                 line= br.readLine(); //reads first line of records
                 String delims = " "; //for parsing the Instructor's name
                    while(line!=null){
                        
                        switch(i) {
                        
                            case 0:
                                //reads "C:\\rooms.txt"
                                String[] room = line.split("\\|");
                                         
                                int roomN =  Integer.parseInt(room[0].trim());///trim?
                                String roomBuild= room[1].trim();
                                //tempString=room[2];
                                int capacity =  Integer.parseInt(room[2].trim());
                                String campus = room[3].trim();
                                tempString=room[4].trim();
                                int media  =    Integer.parseInt(room[4].trim());                           
                                Rooms r = new Rooms(roomN, roomBuild,capacity, campus, media);
                                rooms.add(r);
                                rooms.toString();///to check if it's working
                         
                             case 1:
                                //reads "C:\\preferences.txt"
                                     
                                String[] preference = line.split("\\|");//takes the line and splits it by the "|" character
                                
                                String departm=preference[0].trim();
                                String fullName=preference[1].trim();//reads full name
                                String[] name=fullName.split(delims);//parse the instructor's name using as a delimiter the space
                                String lName= name[0];
                                String fName= name[1];
                                char mInitial = name[2].trim().charAt(0); // s.charAt(0);
                                int numSecToTeach=Integer.parseInt(preference[2].trim());
                                
                                int v=3;
                                for(int j=0; j<5;j++){
                                
                                   
                                    yesNoCampus[j]= preference[v].trim();
                                    v++;
                                                     
                                  } // end for chechink campus preference
                               
                                int north=0, south=0, east=0, west=0, weekend=0;
                                for(int j=0; j<5; j++){
                                String preferenceLocation =" ";
                                
                                    if (yesNoCampus[j]=="Yes")
                                    
                                        switch(j) {

                                            case 0:
                                                north=0;
                                                preferenceLocation="north ";
                                            case 1:
                                                south=0;
                                                preferenceLocation =  preferenceLocation +"south";
                                            case 2:
                                                east=0;

                                            case 3:
                                                west=0;
                                            case 4:
                                                weekend=0;


                                        }//end switch
                                    else //"No"
                                        switch(j) {

                                            case 0:
                                                north=1;
                                            case 1:
                                                south=1;
                                            case 2:
                                                east=1;

                                            case 3:
                                                west=1;
                                            case 4:
                                                weekend=1;

                                        }//end switch
                                }//end for
                               
                                 
                                 
                               // prefe[counterInstructorPref] = new Preferences(departm, lName, fName, mInitial, numSecToTeach, north, south, east, west, weekend);
                                //counterInstructorPref++;                               
                              
                                 case 2:
                                     //"C:\\sections.txt"
                                     
                                     //int courseNum, int numEnro, String departm, String callNum, String days, String time, int media
                                     String[] section = line.split("\\|");
                                     String courseN= section[0].trim();
                                     int numEnrolled =Integer.parseInt(section[1].trim());
                                     String department = section[2].trim();
                                     String callNumber =section[3].trim();
                                     String days= section[4];
                                     String time = section[5];
                                     media = Integer.parseInt(section[6].trim());
                                     Sections s= new Sections(courseN,numEnrolled, department, callNumber,days, time, media);
                                     sections.add(s);
                                     sections.toString();
                                                                    
                                                                     
                                     
                                 case 3:
                                     //"C:\\enroll.txt"
                                     //String courseNum, String depart, int numEnro
                                     String[] course=line.split("\\|");
                                     String courseNum = course[0].trim();
                                     String departmen = course[1].trim();
                                     int numEnrol= Integer.parseInt(course[2].trim());
                                     Courses c = new Courses(courseNum, departmen, numEnrol);
                                     courses.add(c);
                                     //courses
                                     
                                     
                                 
                                     
                                 //
                                     //"C:\\instructors.txt"
<<<<<<< HEAD
//                                    counterInstructorPref=0;
//                                    String[] instructor=line.split("\\|");
//                                    String depart=instructor[0].trim();
//                                    String fullNameI=instructor[1].trim();
//                                    String[] nameI=fullNameI.split(delims);
//                                    String lNameI= nameI[0];
//                                    String fNameI= nameI[1];
//                                    char mInitialI = nameI[2].trim().charAt(0);///****ASSUMPTION: Sll instructors have middle initial
//                                    int temp=0; //*************TEMPORARY VARIABLE...VALERIE HAS TO ASK ADRIAN ABOUT IT
//                                    p = prefe[counterInstructorPref];
//                                   Instructor instruct = new Instructor(temp, depart, lNameI, fNameI, mInitialI,p);
//                                    
//                                    /// counterInstructorPref++; //******ASSUMPTION: the instructor file and the preference file are "in order"
=======
                                    counterInstructorPref=0;
                                    String[] instructor=line.split("\\|");
                                    String depart=instructor[0].trim();
                                    String fullNameI=instructor[1].trim();
                                    String[] nameI=fullNameI.split(delims);
                                    String lNameI= nameI[0];
                                    String fNameI= nameI[1];
                                    char mInitialI = nameI[2].trim().charAt(0);///****ASSUMPTION: Sll instructors have middle initial
                                    int temp=0; //*************TEMPORARY VARIABLE...VALERIE HAS TO ASK ADRIAN ABOUT IT
                                    p = prefe[counterInstructorPref];
                                   //Instructor instruct = new Instructor(temp, depart, lNameI, fNameI, mInitialI,p);
                                    
                                    /// counterInstructorPref++; //******ASSUMPTION: the instructor file and the preference file are "in order"
>>>>>>> 98f323f32889f5c2fd15585630f0dca2864272b0
                        }
                        
                       
                        
                        
                        line= br.readLine();//read next record
                    }//end while loop


            }//end if

 
    }//end for


         
         
         
    }
    

}
