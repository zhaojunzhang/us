package com.us.utils;

import java.util.UUID;

public class IdUtils {
	//�������  ��Ʒ���
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
