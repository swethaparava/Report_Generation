package DBConnections;

import org.postgresql.ds.PGPoolingDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * The type Db connection pool.
 * This provides db connections by connecting to your localhost.
 * To run this program please run below steps
 * 1. please install and run postgres on your local host.
 * 2. create two databases 'customer' and 'orders'
 * 3. run the scripts provided in src/main/resources folder of this project
 */
public class DBConnectionPool {
    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME1 = "customer";
    private static final String DATABASE_NAME2 = "orders";
    private static final String USER = "postgres";
    private static final String PASSWORD = "swetha";
    private static final int MAX_CONNECTIONS = 5;
    private static final String SOURCE1 = "DS1";
    private static final String SOURCE2 = "DS2";
    private PGPoolingDataSource source1;
    private PGPoolingDataSource source2;

    /**
     * Instantiates a new Db connection pool.
     * initializes two data sources which provide connections to two different databases
     */
    public DBConnectionPool() {
        this.source1 = initialize(SOURCE1, DATABASE_NAME1);
        this.source2 = initialize(SOURCE2, DATABASE_NAME2);
    }

    /**
     * using pooling data source provided by the postgresql jdbc driver
     * @param sourceName
     * @param databaseName
     * @return
     */
    private PGPoolingDataSource initialize(String sourceName, String databaseName) {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName(sourceName);
        source.setServerName(SERVER_NAME);
        source.setDatabaseName(databaseName);
        source.setUser(USER);
        source.setPassword(PASSWORD);
        source.setMaxConnections(MAX_CONNECTIONS);
        return source;
    }

    /**
     * Executequery 1 object.
     *
     * @param query the query
     * @return the object
     */
    public synchronized Object executequery1(String query) {
        Connection conn = null;
        try {
            conn = source1.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String s = rs.getString(2);
                return s;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }


    /**
     * Executequery 2 object.
     *
     * @param query the query
     * @return the object
     */
    public synchronized Object executequery2(String query) {
        Connection conn = null;
        try {
            conn = source2.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String s = rs.getString(2);
                return s;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return null;
    }

}
