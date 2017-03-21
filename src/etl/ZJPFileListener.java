package etl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class ZJPFileListener implements FileAlterationListener{

	
	@Override
	public void onStart(FileAlterationObserver observer) {
		//System.out.println("onStart");
	}
	@Override
	public void onDirectoryCreate(File directory) {
		System.out.println("onDirectoryCreate:" + directory.getPath());
	}

	@Override
	public void onDirectoryChange(File directory) {
		System.out.println("onDirectoryChange:" + directory.getPath());
	}

	@Override
	public void onDirectoryDelete(File directory) {
		System.out.println("onDirectoryDelete:" + directory.getPath());
	}

	@Override
	public void onFileCreate(File file) {
		System.out.println("onFileCreate:" + file.getPath());
	}

	@Override
	public void onFileChange(File file) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println(df.format(new Date())+"start");// new Date()为获取当前系统时间
			Thread.sleep(10000);
			System.out.println(df.format(new Date())+"end");// new Date()为获取当前系统时间
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("onFileChange: " + file.getPath());
	}

	@Override
	public void onFileDelete(File file) {
		System.out.println("onFileDelete :" + file.getPath());
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		//System.out.println("onStop");
	}

}

