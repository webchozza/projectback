package first.com.service;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SessionManager implements HttpSessionListener {
	
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/root-context.xml");
	
	SqlSessionTemplate sst = ctx.getBean("sqlSessionTemplate",SqlSessionTemplate.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		sst.update("admin.updateCh",arg0.getSession().getAttribute("member_id"));
	}
}
