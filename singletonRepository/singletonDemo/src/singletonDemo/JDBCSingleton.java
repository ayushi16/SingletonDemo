package singletonDemo;


import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
class JDBCSingleton {  
     
         private static JDBCSingleton jdbc;  
           
    
       private JDBCSingleton() {  }  
        
    
         public static JDBCSingleton getInstance() {    
                                     if (jdbc==null)  
                                   {  
                                            jdbc=new  JDBCSingleton();  
                                   }  
                         return jdbc;  
             }  
            
         static Connection con=null;
         public static Connection getConnection()
         {
             if (con != null) return con;

             return getConnection(configuration.url,configuration.user,configuration.pass);
         }

         private static Connection getConnection(String db_name,String user_name,String password)
         {
             try
             {
                 
                 Class.forName(configuration.JdbcDriver);
                 
                 con=DriverManager.getConnection("jdbc:mysql://localhost/mydb"+"?user="+configuration.user+"&password="+configuration.pass);
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }

             return con;        
         }
          
            

          public int insert(int id,String fname, String lname, String add, int age) throws SQLException  
          {  
              Connection c=null;  
                
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
                
              try {  
                    
                      c=this.getConnection();  
                      ps=c.prepareStatement("insert into emp(id,fname,lname,address,age)values(?,?,?,?,?)");  
                      ps.setInt(1, id);
                      ps.setString(2, fname);  
                      ps.setString(3, lname);
                      ps.setString(4, add);
                      ps.setInt(5, age);
                      recordCounter=ps.executeUpdate();  
                        
              } catch (Exception e) { e.printStackTrace(); } finally{  
                    if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
              }  
             return recordCounter;  
          }  
  
      
      public  void view(String name) throws SQLException  
      {  
                Connection con = null;  
        PreparedStatement ps = null;  
        ResultSet rs = null;  
                  
                try {  
                      
                        con=this.getConnection();  
                        ps=con.prepareStatement("select * from emp where fname=?");  
                        ps.setString(1, name);  
                        rs=ps.executeQuery();  
                        while (rs.next()) {  
                                  System.out.println("firstName= "+rs.getString(2)+"\t"+"lastname= "+rs.getString(3)+"\t"+"address= "+rs.getString(4)+"\t"+"age= "+rs.getInt(5));      
                         
                        }  
                
          } catch (Exception e) { System.out.println(e);}  
          finally{  
                    if(rs!=null){  
                        rs.close();  
                    }if (ps!=null){  
                      ps.close();  
                  }if(con!=null){  
                      con.close();  
                  }   
                }  
      }  
        
     
      public int update(String name, String address) throws SQLException  {  
              Connection c=null;  
              PreparedStatement ps=null;  
                
              int recordCounter=0;  
              try {  
                      c=this.getConnection();  
                      ps=c.prepareStatement(" update emp set address=? where fname='"+name+"' ");  
                      ps.setString(1, address);  
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) {  e.printStackTrace(); } finally{  
                      
                  if (ps!=null){  
                      ps.close();  
                  }if(c!=null){  
                      c.close();  
                  }   
               }  
              
             return recordCounter;  
          }  
            

         public int delete(int userid) throws SQLException{  
              Connection c=null;  
              PreparedStatement ps=null;  
              int recordCounter=0;  
              try {  
                       c=this.getConnection();  
                      ps=c.prepareStatement(" delete from emp where id='"+userid+"' ");  
                      recordCounter=ps.executeUpdate();  
              } catch (Exception e) { e.printStackTrace(); }   
              finally{  
              if (ps!=null){  
                      ps.close();  
             }if(c!=null){  
                      c.close();  
              }   
           }  
             return recordCounter;  
          }  
 }