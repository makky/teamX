package rodeo.agile.impress.pos;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class StockLogicTest {

	private StockDao dao = null;
	private StockLogic stockLogic = null;
	
	@Before
	public void setup() {
		dao = mock(StockDao.class);
		stockLogic = new StockLogic(dao);
	}
	
	
	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {		
		stockLogic.add("ValidName", 5);
		
		verify(dao, times(1)).insert("ValidName", 5);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfPriceIsZero() throws ClassNotFoundException, SQLException {
		stockLogic.add("ValidName", 0);
	}

}
