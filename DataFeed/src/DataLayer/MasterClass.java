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
    
    //private static 
 
public static void main(String[] args)throws IOException{
        
/*/Store the path for the input files in a String array.  This can done in another way 
 * (reading froma file the different paths), 
    but for simplicity purposes I have it like this.    
    * The reading of the files can also be done using a separate class.  Later on it can be improved.  Valerie
    * 
    */
   String[] filesArray = {"C:\\rooms.txt",  "C:\\preferences.txt", "C:\\sections.txt", "C:\\enroll.txt"};
   String pathFile;
 
   
  
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
                                String roomBuild= room[1].trim();
                                
                                int capacity =  Integer.parseInt(room[2].trim());
                                String campus = room[3].trim();
                                boolean media = true;
                                if(room[4].trim()=="NO"){
                                media = false;
                                }//end if
                                                               
                                Rooms r = new Rooms(roomN, roomBuild,capacity, campus, media);
                                System.out.println(r.toString());
                                rooms.add(r);
                                
                                break;
                                
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
                            
                                        InstructorGeneralPreferences instruct= new InstructorGeneralPreferences(departm, lName, fName, mInitial, numSecToTeach, weekend, p);
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
                                              if(section[5].trim()=="NO"){
                                                 media = false;
                                                    }//end if

                                             Sections s= new Sections(courseN, department, callNumber,days, time, media);
                                             System.out.println(s.toString());
                                             sections.add(s);

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

         
    }//end public static void main
    
//
}//end MasterClass
