package data.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBM {
    Connection getConnection();
}
