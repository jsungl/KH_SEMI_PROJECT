<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
    String clientId = "ICRNCSNcCKTC8uNgGtqe";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:9090/yeogiyo/member/naverlogin", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>

	<div class="content-wrap">
        <form id="loginFrm" action="<%= request.getContextPath() %>/member/memberlogin" method="POST" name="loginFrm">
            <div class="login-container">
                <div class="title"><img src="<%= request.getContextPath() %>/images/logo.png" alt="로고" width="150" height="50"></div>
                <ul>
                    <li>
                        <input type="text" name="memberId" id="memberId" placeholder="아이디" required="required" value="<%= saveId != null ? saveId : ""%>">
                        <span class="err-msg">유효한 아이디 형식이 아닙니다.</span>
		    		</li>
                    <li>
                        <input type="password" name="password" id="password" placeholder="비밀번호" required="required">
                    </li>
                    
                </ul>
                <div class="checkbox">
                    <input type="checkbox" name="saveId" id="saveId" <%= saveId != null ? "checked" : "" %>>
                    <label for="saveId">아이디저장</label>
                    <span><a href="<%= request.getContextPath() %>/member/searchId">아이디찾기</a> | <a href="<%= request.getContextPath() %>/member/searchPwd">비밀번호찾기</a></span>
                </div>
            </div>
            <div class="login-submit">
                <input type="submit" value="로그인" name="loginbtn" disabled="disabled">
            </div>
        </form>
        <div style="width: 500px; margin: 0 auto;">
        	<a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
        </div>
        <div class="join-container">
            <a href="<%= request.getContextPath() %>/member/memberEnroll">
                <img src="<%= request.getContextPath() %>/images/sign_banner.png" alt="가입배너">
            </a>
        </div>
	</div>

<script>

$("#memberId").keyup(function(){
	if(/^[a-zA-Z0-9]{4,12}$/.test($("#memberId").val()) == false){
		$(".err-msg").css("display","block");
	}else {
		$(".err-msg").css("display","none");
	}
	
	
	if($("#memberId").val() == ""){
		$(".err-msg").css("display","none");
	}
	
});


$("#password").keyup(function(){
	if(/^[a-zA-Z0-9]{4,12}$/.test($("#memberId").val()) == true && /^[a-zA-Z0-9]{4,12}$/.test($("#password").val()) == true){
		$("[name=loginbtn]").css("background-color","#fa0050")
							.css("cursor","pointer")
							.removeAttr("disabled");
	}else{
		$("[name=loginbtn]").css("background-color","#ccc")
							.css("cursor","")
							.attr("disabled","true");
	}
	
});


	
	




</script>




<%@ include file="/WEB-INF/views/common/footer.jsp" %>