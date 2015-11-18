package com.us.utils;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import com.us.po.TotalJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





/**
 * Title: Ext JS ������
 * Description: ��������ת��java����ΪXML�ļ���ʽ��JSON�ļ���ʽ
 * @author weijun
 * @time: 2008.07.09
 */
public class JSNOUtil {
	/**
	 * ͨ��List����XML����
	 * @param recordTotal ��¼��������һ����beanList�еļ�¼�����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�XML����
	 */
	/*public static String getXmlFromList(int recordTotal , List beanList) {
		Total total = new Total();
		total.setResults(recordTotal);
		List results = new ArrayList();
		results.add(total);
		results.addAll(beanList);
		XStream sm = new XStream(new DomDriver());
		for (int i = 0; i < results.size(); i++) {
			Class c = results.get(i).getClass();
			String b = c.getName();
			String[] temp = b.split("\\.");
			sm.alias(temp[temp.length - 1], c);
		}
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"	+ sm.toXML(results);
		return xml;
	}
	*//**
	 * ͨ��List����XML����  
	 * @param beanList ����bean����ļ���    
	 * @return ���ɵ�XML����  
	 *//*
	public static String getXmlFromList(List beanList,int Bsize){
		return getXmlFromList(Bsize,beanList);  
	}        */
	/**
	 * ͨ��List����JSON����
	 * @param recordTotal ��¼��������һ����beanList�еļ�¼�����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromList(long recordTotal , List beanList){
		int start = 0;
		int limit = 10;
		TotalJson total = new TotalJson();
		List Ll = new ArrayList();
		total.setResults(recordTotal);
		total.setItems(beanList);   
		JSONObject JsonObject = JSONObject.fromObject(total);
		return JsonObject.toString();
	}
	/**  
	 * ͨ��List����JSON����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromList(List beanList){
		return getJsonFromList(beanList.size(),beanList);
	}
	/**
	 * ͨ��bean����JSON����
	 * @param bean bean����
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromBean(Object bean){
		JSONObject JsonObject = JSONObject.fromObject(bean);
		return JsonObject.toString();
	}
	/**  
	 * ͨ��JSON����List����
	 */
	public static List<?> getListFromJson(String jsonString,Class<?> tclass){
	     //��json�����ַ���ת����json����
		  JSONArray jsonArray2= JSONArray.fromObject(jsonString);
		  //��josn����ת���ɶ�Ӧ��ķ��ͼ���
		   List<?> tList=JSONArray.toList(jsonArray2, tclass);
		
		return tList;
		
	}

	   /** 
     * ��������:��������һ�� javabean ��������һ��ָ�������ַ��� 
     * 
     * @param bean 
     *             bean���� 
     * @return String 
     */    
   public static String beanToJson(Object bean) {     
        StringBuilder json = new StringBuilder();     
        json.append("{");     
        PropertyDescriptor[] props = null;     
       try {     
            props = Introspector.getBeanInfo(bean.getClass(), Object.class)     
                    .getPropertyDescriptors();     
        } catch (IntrospectionException e) {     
        }     
       if (props != null) {     
           for (int i = 0; i < props.length; i++) {     
               try {    
                    String name = objectToJson(props[i].getName());     
                    String value = objectToJson(props[i].getReadMethod().invoke(bean));    
                    json.append(name);     
                    json.append(":");     
                    json.append(value);     
                    json.append(",");    
                } catch (Exception e) {     
                }     
            }     
            json.setCharAt(json.length() - 1, '}');     
        } else {     
            json.append("}");     
        }     
       return json.toString();     
    }     
   
    /** 
     * @param object 
     *             ������� 
     * @return java.lang.String 
     */    
   public static String objectToJson(Object object) {     
        StringBuilder json = new StringBuilder();     
       if (object == null) {     
            json.append("\"\"");     
        } else if (object instanceof String || object instanceof Integer) {   
            json.append("\"").append(object.toString()).append("\"");    
        } else {     
            json.append(beanToJson(object));     
        }     
       return json.toString();     
    }     
   
	 /** 
     * ��������:ͨ������һ���б����,����ָ���������б��е���������һ��JSON���ָ���ַ��� 
     * 
     * @param list 
     *             �б���� 
     * @return java.lang.String 
     */    
   public static String listToJson(List<?> list) {     
        StringBuilder json = new StringBuilder();     
        json.append("[");     
       if (list != null && list.size() > 0) {     
           for (Object obj : list) {     
                json.append(objectToJson(obj));     
                json.append(",");     
            }     
            json.setCharAt(json.length() - 1, ']');     
        } else {     
            json.append("]");     
        }     
       return json.toString();     
    }  
}