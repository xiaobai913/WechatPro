package com.wwk.wechatServlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wwk.utils.CommonUtil;

import net.sf.json.JSONObject;


/**
 * 微信公众号网页授权获取用户信息
 * Servlet implementation class WechatRedirectServlet
 */
@WebServlet("/WechatRedirectServlet")
public class WechatRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//登录网页授权接口
	private static final String authorizationUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxeb8a208f1113e1a0&redirect_uri=http%3A%2F%2Fwwk.ngrok.xiaomiqiu.cn%2FWeChatDevelop%2FWechatRedirectServlet&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
	
	//测试号
	private static final String APPID = "wxeb8a208f1113e1a0";
	private static final String SECRET = "4c2e5d5c415c8f14032e20a5ac68ec8b";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		
		//第一步：用户同意授权，获取code
		String code = request.getParameter("code");
		System.out.println(code);
		
		//第二步：通过code换取网页授权access_token
		String url1 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
		JSONObject accessJson = CommonUtil.httpsRequest(url1, "GET", null);
		
		String accessToken = accessJson.getString("access_token");
		String refreshToken = accessJson.getString("refresh_token");
		String openId = accessJson.getString("openid");
		
		//第三步：刷新access_token（如果需要）
//		String url2 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+refreshToken; 
//		JSONObject refreshTokenJson = CommonUtil.httpsRequest(url2, "GET", null);
		
		//第四步：拉取用户信息(需scope为 snsapi_userinfo)
		String url3 = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN"; 
		JSONObject userInfoJson = CommonUtil.httpsRequest(url3, "GET", null);
		
		System.out.println(userInfoJson.toString());
		
		//附：检验授权凭证（access_token）是否有效
		String url4 = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN"; 
		JSONObject jiaoyanJson = CommonUtil.httpsRequest(url4, "GET", null);
		
		System.out.println(jiaoyanJson.toString());
		System.out.println(userInfoJson.toString());
//		response.getWriter().print(userInfoJson);
		
		//获取用户信息成功跳转到web页面
//		String url5 = "http://wwk.ngrok.xiaomiqiu.cn/recruit_web";
		
		request.setAttribute("userInfoJson", userInfoJson);
		request.setAttribute("openId", openId);
		request.setAttribute("nickname", userInfoJson.getString("nickname"));
		
//		request.getRequestDispatcher("recruit_web/index.jsp").forward(request, response);
		response.sendRedirect("http://wwk.ngrok.xiaomiqiu.cn/recruit_web");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
