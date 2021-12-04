import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TESTING
// CLASS IS NOT YET CONGRUENT WITH REALTIME DATABASE

public class PostgreLink
    {

        private final String url = "jdbc:postgresql://localhost:5433/postgres";
        private final String user = "postgres";
        private final String password = "pgaccess";

        public Connection connect()
        {
            Connection conn = null;
            try
            {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, user, password);

                if(conn != null)
                {
                    System.out.println("Link successful. ");
                }
                else
                {
                    System.out.println("Connection failed. ");
                }

            }
            catch (SQLException | ClassNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
            return conn;
        }

        public static void main(String[] args)
        {
            PostgreLink link = new PostgreLink();
            link.connect();
        }

    }
