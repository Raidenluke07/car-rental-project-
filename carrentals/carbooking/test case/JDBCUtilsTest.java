package carbooking;



import org.junit.Test;
import java.sql.Connection;
import static org.junit.Assert.*;

public class JDBCUtilsTest {
    @Test
    public void testGetConnection() {
        try (Connection connection = JDBCUtils.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        } catch (Exception e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}
