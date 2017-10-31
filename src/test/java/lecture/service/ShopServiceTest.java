package lecture.service;

import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.repository.ShopRepository;
import com.visma.lecture.service.ShopService;
import lecture.util.ShopTestUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ShopServiceTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	private ShopRepository shopRepository;
	private ShopService shopService;
	
	@Before
	public void setUp() throws Exception {
		shopRepository = new ShopRepository(new ShopTestUtil().getItems());
		shopService = new ShopService(shopRepository);
	}
	
	@Test
	public void shouldThrowExceptionOnMissingInput() throws Exception {
		expected.expect(InvalidCriteriaException.class);
		expected.expectMessage("Input was null, empty or lower than 0.");
	}
	
}