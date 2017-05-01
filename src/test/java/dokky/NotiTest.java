package dokky;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@TransactionConfiguration
@Transactional
//댓글 작성시 알림게시글에 정보 인설트 하는 로직테스트
public class NotiTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testAlramList() throws Exception {
		mockMvc.perform(get("/test.do").param("board_id", "2").param("session_id", "1000").param("board_url", "/test.list").param("kinds", "comment"))
			.andExpect(status().isOk());
		//MockHttpServletRequestBuilder의 모든 요청 정보 생성 메소드는 MockHttpServletRequestBuilder를 리턴하기 때문에 메소트 체이닝 방식으로 요청 정보를 구성할 수 있다.
	}

}
