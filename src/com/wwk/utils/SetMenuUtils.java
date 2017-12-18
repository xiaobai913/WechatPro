package com.wwk.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class SetMenuUtils {

	//测试号
	private static final String APPID = "wxeb8a208f1113e1a0";
	private static final String SECRET = "4c2e5d5c415c8f14032e20a5ac68ec8b";
	
	private static final String requstUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeb8a208f1113e1a0&redirect_uri=http%3A%2F%2Fwwk.ngrok.xiaomiqiu.cn%2FWeChatDevelop%2FWechatRedirectServlet&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
	/**
	 * 自定义菜单
	 * @return
	 * @throws JSONException
	 */
	public static String getMenuStr() throws JSONException{
        JSONObject firstLevelMenu = new JSONObject();//一级菜单
        JSONArray firstLevelMenuArray = new JSONArray();//一级菜单列表


        //一级菜单内容1
        JSONObject firstLevelMenuContext1 = new JSONObject();
        firstLevelMenuContext1.put("type", "view");
        firstLevelMenuContext1.put("name", "招聘信息");
        firstLevelMenuContext1.put("url", requstUrl);

        //一级菜单内容2
        JSONObject firstLevelMenuContext2 = new JSONObject();
        //一级菜单内容2的二级菜单列表
        JSONArray firstLevelMenuContext2Array = new JSONArray();
        //一级菜单内容2的二级菜单内容1
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type", "click");
        jsonObject1.put("name", "歌曲");
        jsonObject1.put("key", "V1001_TODAY_MUSIC");
        //一级菜单内容2的二级菜单内容2
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type", "view");
        jsonObject2.put("name", "百度");
        jsonObject2.put("url", "http://www.baidu.com");
        firstLevelMenuContext2Array.add(jsonObject1);
        firstLevelMenuContext2Array.add(jsonObject2);
        firstLevelMenuContext2.put("name", "菜单");
        firstLevelMenuContext2.put("sub_button", firstLevelMenuContext2Array);

        //一级菜单内容3
        JSONObject firstLevelMenuContext3 = new JSONObject();
        firstLevelMenuContext3.put("type", "click");
        firstLevelMenuContext3.put("name", "视频");
        firstLevelMenuContext3.put("key", "V1001_TODAY_MOVIE");


        firstLevelMenuArray.add(firstLevelMenuContext1);
        firstLevelMenuArray.add(firstLevelMenuContext2);
        firstLevelMenuArray.add(firstLevelMenuContext3);


        firstLevelMenu.put("button", firstLevelMenuArray);

        return firstLevelMenu.toString();
    }
	/**
	 * 获取access_token
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken() throws Exception{
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;
        URL url = new URL(accessTokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");

        //获取access_token
        JSONObject jsonObject = JSONObject.fromObject(message);
        return jsonObject.getString("access_token");
    }
	
	/**
	 * 创建菜单
	 * @throws Exception
	 */
	public static void createCustomMenu() throws Exception{
        String custmMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

        //获取access_token
        String accessToken = getAccessToken();
        custmMenuUrl = custmMenuUrl + accessToken;

        URL url = new URL(custmMenuUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(getMenuStr().getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");

        System.out.println(message);
}
	
}
