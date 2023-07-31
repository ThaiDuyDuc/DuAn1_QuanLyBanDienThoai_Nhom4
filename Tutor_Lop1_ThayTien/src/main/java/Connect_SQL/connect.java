package Connect_SQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class connect {
    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
        String connectionUrl = "jdbc:sqlserver://localhost;database=FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041;"
                +"encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2"; 
        String user = "sa";
        String password = "123456";
        Connection con = DriverManager.getConnection(connectionUrl, user, password);
        //Statement stm =  con.createStatement();
        return con;
    }
}
