import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class database extends FilmManager{
    
    private Map<String, movieAbstract> films = new HashMap<>();
    public Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");


        // připojení k databázi (pokud neexistuje, bude vytvořena nová)
        

        // vytvoření tabulky
        Statement stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"
               // + " id INTEGER PRIMARY KEY,\n"
                + " name TEXT NOT NULL,\n"
                + " Director TEXT NOT NULL,\n"
                + " Year INTEGER,\n"
                + " Rating INTEGER,\n"
                + " Actors TEXT,\n"
                + ");";
        stmt.execute(sql);
        System.out.println("Tabulka byla úspěšně vytvořena.");  



    public void insertNewUser(String email, String name, String surname) {
            
                      
       
    
        
    
        String insertUser = "INSERT INTO user " + "(email,name,surname)" + "VALUES(?,?,?)";
    
        try (PreparedStatement prStmt = conn.prepareStatement(insertUser)) {
          prStmt.setString(1, email);
          prStmt.setString(2, name);
          prStmt.setString(3, surname);
    
          prStmt.executeUpdate();
    
          System.out.println("Nov� u�ivatel byl vlo�en do datab�ze!");
        } catch (SQLException e) {
          System.out.println("U�ivatel u� byl vlo�en nebo jste zadali �patn� SQL p��kaz INSERT");
          // e.printStackTrace();
        }
      
}
}

