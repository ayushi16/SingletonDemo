package singletonDemo;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.sql.Connection;
class JDBCSingletonDemo{  
    static int count=1;  
    static int  choice;  
    public static void main(String[] args) throws IOException {  
          
        JDBCSingleton jdbc= JDBCSingleton.getInstance();  
          
        Connection con = null;  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
   do{   
        System.out.println("DATABASE OPERATIONS");  
        System.out.println(" --------------------- ");  
        System.out.println(" 1. Insertion ");  
        System.out.println(" 2. View      ");  
        System.out.println(" 3. Delete    ");  
        System.out.println(" 4. Update    ");  
        System.out.println(" 5. Exit      ");  
          
        System.out.print("\n");  
        System.out.print("Please enter the choice what you want to perform in the database: ");  
          
        choice=Integer.parseInt(br.readLine());  
        switch(choice) {  
              
           case 1:{  
        	   		System.out.println("Enter id : ");  
        	   		int id = Integer.parseInt(br.readLine());  
                    System.out.println("Enter firstname : ");  
                    String firstname = br.readLine();  
                    System.out.println("Enter lastname :");  
                    String lastname = br.readLine();  
                    System.out.println("Enter address : ");  
                    String address = br.readLine();    
                    System.out.println("Enter age : ");  
                    int age=Integer.parseInt(br.readLine());  
                    try {  
                            int i= jdbc.insert(id,firstname, lastname,address,age);  
                            if (i>0) {  
                            System.out.println((count++) + " Data has been inserted successfully");  
                            }else{  
                                System.out.println("Data has not been inserted ");      
                            }  
                          
                        } catch (Exception e) {  
                          System.out.println(e);  
                        }  
                      
                     System.out.println("Press Enter key to continue...");  
                     System.in.read();  
                       
                   }//End of case 1  
                   break;  
            case 2:{  
                    System.out.print("Enter the username : ");  
                    String username=br.readLine();  
               
                    try  {  
                            jdbc.view(username);  
                         } catch (Exception e) {  
                          System.out.println(e);  
                        }  
                     System.out.println("Press Enter key to continue...");  
                     System.in.read();  
                       
                   }//End of case 2  
                  break;  
             case 3:{  
                     System.out.print("Enter the userid,  you want to delete: ");  
                     int userid=Integer.parseInt(br.readLine());  
            
                     try {  
                            int i= jdbc.delete(userid);  
                            if (i>0) {  
                            System.out.println((count++) + " Data has been deleted successfully");  
                            }else{  
                                System.out.println("Data has not been deleted");      
                            }  
                          
                         } catch (Exception e) {  
                          System.out.println(e);  
                         }  
                     System.out.println("Press Enter key to continue...");  
                     System.in.read();  
                       
                    }//End of case 3  
                   break;  
             case 4:{  
                    System.out.print("Enter the username,  you want to update: ");  
                    String username=br.readLine();  
                    System.out.print("Enter new address ");  
                    String address=br.readLine();  
                      
                    try {  
                            int i= jdbc.update(username, address);  
                            if (i>0) {  
                            System.out.println((count++) + " Data has been updated successfully");  
                            }  
                          
                        } catch (Exception e) {  
                          System.out.println(e);  
                        }  
                     System.out.println("Press Enter key to continue...");  
                     System.in.read();  
                      
                   }// end of case 4  
                 break;  
                   
             default:  
                     return;  
        }  
          
       } while (choice!=4);   
   }  
}  
