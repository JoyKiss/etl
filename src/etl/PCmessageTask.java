package etl;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import util.IpUtil;

public class PCmessageTask extends TimerTask {

	@Override
	public void run() {
		System.out.println(IpUtil.getLocalIP());
//		System.out.println(IpUtil.getMacId());
//		System.out.println(IpUtil.getLocalIPS());
//		System.out.println(IpUtil.getMacIds());
		
//		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		InetAddress addr;
//		try {
//
//			addr = InetAddress.getLocalHost();
//			String ip = getLocalIP();// 获得本机IP
//			String address = addr.getHostName().toString();// 获得本机名称
//			System.out.println(startTime + " " + ip + " " + address);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
//			while (en.hasMoreElements()) {
//				NetworkInterface ni = en.nextElement();
//				printParameter(ni);
//			}
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public String getServerIp() {
		String SERVER_IP = "";
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				ip = (InetAddress) ni.getInetAddresses().nextElement();
				SERVER_IP = ip.getHostAddress();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
					SERVER_IP = ip.getHostAddress();
					break;
				} else {
					ip = null;
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SERVER_IP;
	}

	public static void printParameter(NetworkInterface ni) throws SocketException {
		if (null != ni.getDisplayName() && ni.getDisplayName().contains("Wireless") && ni.isUp()) {
			System.out.println(" Name = " + ni.getName());
			System.out.println(" Display Name = " + ni.getDisplayName());
			System.out.println(" Is up = " + ni.isUp());
			System.out.println(" Support multicast = " + ni.supportsMulticast());
			System.out.println(" Is loopback = " + ni.isLoopback());
			System.out.println(" Is virtual = " + ni.isVirtual());
			System.out.println(" Is point to point = " + ni.isPointToPoint());
			System.out.println(" Hardware address = " + ni.getHardwareAddress());
			System.out.println(" MTU = " + ni.getMTU());

			System.out.println("\nList of Interface Addresses:");
			List<InterfaceAddress> list = ni.getInterfaceAddresses();
			Iterator<InterfaceAddress> it = list.iterator();

			while (it.hasNext()) {
				InterfaceAddress ia = it.next();
				if (null != ia.getBroadcast()) {
					System.out.println(" 本机实际联网网卡IPv4地址： " + ia.getAddress().getHostAddress());
				}
			}

			System.out.println("**************************************************");
		}
	}

	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	}

}
