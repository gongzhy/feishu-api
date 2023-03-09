package com.gongzhy.feishu.api.util;

import javax.servlet.http.HttpServletRequest;

public class IpAddressUtil {
	private static String DEFAULT = "127.0.0.1";
	
	/**
	 * IP地址转为INT信息
	 * @param ip
	 * @return
	 */
	public static int IpToInt(String ip) {
		return toInt(toBytes(ip));
	}
	
	/**
	 * int地址转为IP信息
	 * @param addr
	 * @return
	 */
	public static String IntToIp(int addr) {
		return ByteToIp(IntToBytes(addr));
	}
	
	/**
	 * byte数组转IP地址信息
	 * @param bytes
	 * @return
	 */
	private static String ByteToIp(byte[] bytes) {
		StringBuilder ipInfo = new StringBuilder();
		ipInfo.append(bytes[0] & 0xff);
		ipInfo.append(".");
		ipInfo.append(bytes[1] & 0xff);
		ipInfo.append(".");
		ipInfo.append(bytes[2] & 0xff);
		ipInfo.append(".");
		ipInfo.append(bytes[3] & 0xff);
		return ipInfo.toString();
	}
	
	/**
	 * int转换为byte数组
	 * @param addr
	 * @return
	 */
	private static byte[] IntToBytes(int addr) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) ((addr >>> 24) & 0xff);
		bytes[1] = (byte) ((addr >>> 16) & 0xff);
		bytes[2] = (byte) ((addr >>> 8) & 0xff);
		bytes[3] = (byte) (addr & 0xff);
		
		return bytes;
	}
	
	/**
	 * byte数组转为int
	 * @param bytes
	 * @return
	 */
	private static int toInt(byte[] bytes) {
		int addr = bytes[3] & 0xff;
		addr |= ((bytes[2] << 8) & 0xff00);
		addr |= ((bytes[1]  << 16) & 0xff0000);
		addr |= ((bytes[0] << 24) & 0xff000000);
		return addr;
	}
	
	/**
	 * IP转换为byte数组
	 * @param ip
	 * @return
	 */
	private static byte[] toBytes(String ip) {
		String[] ipInfo = ip.split("\\.");
		byte[] results = new byte[4];
		results[0] = (byte) (Integer.parseInt(ipInfo[0]) & 0xff);
		results[1] = (byte) (Integer.parseInt(ipInfo[1]) & 0xff);
		results[2] = (byte) (Integer.parseInt(ipInfo[2]) & 0xff);
		results[3] = (byte) (Integer.parseInt(ipInfo[3]) & 0xff);
		return results;
	}
	
	/**
	 * 解析客户端IP
	 * @return
	 */
	public static final String getIp(HttpServletRequest request) {
		try {			
			String ip = request.getHeader("%>a");//squid传递过来的玩家IP地址
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("x-forwarded-for");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = DEFAULT;
			}
			//如果通过代理访问,可能获取2个IP,这时候去第二个(代理服务端IP)
			if (ip.split(",").length >= 2) {
				ip = ip.split(",")[0].trim();
			}

			if ("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
				ip = DEFAULT;
			}
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
			return DEFAULT;
		}
	}
}
