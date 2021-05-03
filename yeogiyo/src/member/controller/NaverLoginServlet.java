package member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import member.model.vo.Member;

/**
 * Servlet implementation class NaverLoginServlet
 */
@WebServlet("/member/naverlogin")
public class NaverLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = "ICRNCSNcCKTC8uNgGtqe";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "o5peFKYdPS";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    System.out.println("code@NaverLoginServlet = " + code);
	    String state = request.getParameter("state");
	    System.out.println("state@NaverLoginServlet = " + state);
	    String redirectURI = URLEncoder.encode("http://localhost:9090/yeogiyo", "UTF-8");
	    String apiURL;
	    
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    JSONParser parsing;
	    JSONObject jsonObj;
	    JSONObject resObj;
	    HttpSession session = request.getSession();
	    
	    try {
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        System.out.print("responseCode="+responseCode);
	        if(responseCode==200) { // 정상 호출
	          br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	          br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	          res.append(inputLine);
	        }
	        br.close();
	        if(responseCode==200) {
	        	//오류없이 정상작동했을때
	        	//JSONParser를 이용해 Object로 업캐스팅을 한 후 다시 JSONObject로 다운캐스팅
	        	System.out.println(res.toString());
	        	parsing = new JSONParser();
	        	Object obj = parsing.parse(res.toString());
	        	jsonObj = (JSONObject)obj;
	     
	        	
	        	access_token = (String)jsonObj.get("access_token");
	        	refresh_token = (String)jsonObj.get("refresh_token");
	        	System.out.println("access_token = " + access_token);
	        	System.out.println("refresh_token = " + refresh_token);
	        	
	        	if(access_token != null) { // access_token을 잘 받아왔다면
	        		String token = access_token;// 네아로 접근 토큰 값";
	        	    String header = "Bearer " + token; // Bearer 다음에 공백 추가
	        		try {
	        			 String profileInquiryapiURL = "https://openapi.naver.com/v1/nid/me";
	        	            URL profileInquiryUrl = new URL(profileInquiryapiURL);
	        	            HttpURLConnection profileInquiryCon = (HttpURLConnection)profileInquiryUrl.openConnection();
	        	            profileInquiryCon.setRequestMethod("GET");
	        	            profileInquiryCon.setRequestProperty("Authorization", header);
	        	            int profileInquiryRespCode = profileInquiryCon.getResponseCode();
	        	            BufferedReader br2;
	        	            if(profileInquiryRespCode==200) { // 정상 호출
	        	                br2 = new BufferedReader(new InputStreamReader(profileInquiryCon.getInputStream()));
	        	            } else {  // 에러 발생
	        	                br2 = new BufferedReader(new InputStreamReader(profileInquiryCon.getErrorStream()));
	        	            }
	        	            String inputLine2;
	        	            StringBuffer response2 = new StringBuffer();
	        	            while ((inputLine2 = br2.readLine()) != null) {
	        	                response2.append(inputLine2);
	        	            }
	        	            br2.close();
	        	            System.out.println("response = " + response2.toString());
	        	            
	        	            if(response2 != null) {
		        	            parsing = new JSONParser();
		    	        		Object obj2 = parsing.parse(response2.toString());
		    	        		jsonObj = (JSONObject)obj2;
		    	        		resObj = (JSONObject)jsonObj.get("response");
		    	        		
		    	        		String naverCode = (String)resObj.get("id");
		    	        		String email = (String)resObj.get("email");
		    	        		String gender = (String)resObj.get("gender");
		    	        		String name = (String)resObj.get("name");
		    	        		String mobile = (String)resObj.get("mobile");
		    	        		
		    	        		System.out.println("naverCode = " + naverCode);
		    	        		System.out.println("email = " + email);
		    	        		System.out.println("gender = " + gender);
		    	        		System.out.println("name = " + name);
		    	        		System.out.println("mobile = " + mobile);
		    	        		
		    	        		//db에 insert, update
		    	        		
		    	        		
		    	        		//session.setAttribute("msg", "네이버 로그인에 성공하셨습니다");
		    	        		Member naverMember = new Member(naverCode,name,null,null,gender,null,null,mobile,email,null,"U");
		    	        		session.setAttribute("loginMember", naverMember);
		    	        		session.setAttribute("msg", "로그인에 성공하셨습니다.");
		    	        		response.sendRedirect(request.getContextPath());
		    	        		
	        	            }
	    	        		
	        	    } catch (Exception e) {
	        	    	e.printStackTrace();
	        	    }
	        		
	        		
	        		
	        	}else {
	        		//access_token을 못가져왔을때
	        	}
	        	
	        }else {
	        	//responsecode == 200이 아닐때, 오류가발생시
	        }
	        
	        
	        
	      } catch (Exception e) {
	    	e.printStackTrace();
//	        System.out.println(e);
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
