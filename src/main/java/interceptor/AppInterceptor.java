package interceptor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.json.jackson2.JacksonFactory;


public class AppInterceptor extends HandlerInterceptorAdapter {
	private static final JacksonFactory jsonFactory = new JacksonFactory();

	protected final Log logger = LogFactory.getLog(getClass());
	
	public GoogleIdToken readIdToken(String idtoken) throws IOException {
		GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, idtoken);
		return idToken;
	}

	public Payload getPayload(GoogleIdToken idToken) throws SQLException {
		Payload payload = null;
		if (idToken != null) {
			payload = idToken.getPayload();
		} else {
			System.out.println("Invalid ID token.");
		}
		return payload;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
 		HttpSession session = request.getSession();
		String headerName = request.getHeader("User-Agent");
		
		if (headerName.contains("Android")) {
				String idtoken = "";
				idtoken = request.getParameter("idToken");
				if (!idtoken.equals("")) {
					GoogleIdToken idToken = readIdToken(idtoken);
					Payload payload = getPayload(idToken);
					
					session.setAttribute("id",payload.getEmail());
					request.setAttribute("idToken",idToken);
				}
		}
		return super.preHandle(request, response, handler);
	}
}
