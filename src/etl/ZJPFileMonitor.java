package etl;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class ZJPFileMonitor implements ServletContextListener {

	FileAlterationMonitor monitor = null;

	public ZJPFileMonitor(long interval) throws Exception {
		monitor = new FileAlterationMonitor(interval);
	}

	public ZJPFileMonitor() throws Exception {
	}

	public void monitor(String path, FileAlterationListener listener) {
		FileAlterationObserver observer = new FileAlterationObserver(
				new File(path));
		monitor.addObserver(observer);
		observer.addListener(listener);
	}

	public void stop() throws Exception {
		monitor.stop();
	}

	public void start() throws Exception {
		monitor.start();
	}

	public static void main(String[] args) throws Exception {
		ZJPFileMonitor m = new ZJPFileMonitor(100);
		m.monitor("D:\\tmp\\log\\cron.txt", new ZJPFileListener());
		m.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ZJPFileMonitor m;
		try {
			m = new ZJPFileMonitor(1);
			m.monitor("E:\\temp", new ZJPFileListener());
			m.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
