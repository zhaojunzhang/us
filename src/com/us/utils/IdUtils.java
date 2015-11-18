package com.us.utils;

import java.util.UUID;

public class IdUtils {
	//生成随机  商品编号
		public static String generateProductId(){
			String uuid = UUID.randomUUID().toString();
			int hashCode = Math.abs(uuid.hashCode());
			return "ep-"+hashCode;
		}
		public static String generateOrderId(){
			String uuid = UUID.randomUUID().toString();
			int hashCode = Math.abs(uuid.hashCode());
			return "Order-"+hashCode;
		}
}
