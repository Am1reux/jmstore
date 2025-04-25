package cn.tedu.jmstore.dbtest;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDB {
	@Autowired
	public DataSource dataSource;
	@Test
	public void testDB() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
}
