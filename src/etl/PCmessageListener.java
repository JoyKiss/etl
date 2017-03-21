package etl;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PCmessageListener implements ServletContextListener {
	private Timer timer = null;  
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 // 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能  
		timer = new Timer(true);  
       // 添加日志，可在tomcat日志中查看到  
       System.out.println("定时器已启动");  
       // 调用GoogleTimer，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时，24*60*60*1000表示一天。  
       timer.schedule(new PCmessageTask(), 0, 1*60* 1000); //每2分钟执行一次GoogleTimer类  
       System.out.println("定时器已添加"); 		
		
	}

}
