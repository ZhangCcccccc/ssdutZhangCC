package net.xidlims.constant;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

//import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;

import edu.yale.its.tp.cas.client.CASAttrPrincipal;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    @SuppressWarnings("unchecked")
	public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
    	javax.servlet.http.HttpSession session = request.getSession(true);
        //response.setContentType("text/html;; charset=utf-8");
        PrintWriter out;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String is_config = request.getRealPath("/client.properties");
        Cookie all_cookies[] = request.getCookies();
        String decodedCookieValue = null;
        if(all_cookies != null)
        {
            for(int i = 0; i < all_cookies.length; i++)
            {
                Cookie myCookie = all_cookies[i];
                if(myCookie.getName().equals("iPlanetDirectoryPro"))
					try {
						decodedCookieValue = URLDecoder.decode(myCookie.getValue(), "GB2312");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
            }

        }
        IdentityFactory factory = null;
        try
        {
            factory = IdentityFactory.createFactory(is_config);
        }
        catch(Exception exception) { }
        IdentityManager im = factory.getIdentityManager();
        String username = "";
        if(decodedCookieValue != null)
            username = im.getCurrentUser(decodedCookieValue);
        String gotoURL = HttpUtils.getRequestURL(request).toString();
        String outUrl = (new StringBuilder(String.valueOf(im.getLogoutURL()))).append("?goto=").toString();
        if(decodedCookieValue != null)
            username = im.getCurrentUser(decodedCookieValue);
        if(username.length() == 0)
            System.out.println("[false]");
        else
        	System.out.println((new StringBuilder("[\"")).append(username).append("\",\"").append(outUrl).append("\"]").toString());

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("00014", "00014");
      //authentication.WebAuthenticationDetails实例到details中  
        setDetails(request, authRequest);
      //通过AuthenticationManager:ProviderManager完成认证任务  
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}