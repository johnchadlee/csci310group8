package csci310.weatherplanner.endpoints.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

public class MockRequest implements HttpServletRequest{
	private Map<String, Object> attributes;
	private Map<String, String[]> parameters;
	private final String body;
	private final HttpSession session;
	
	public MockRequest(HttpSession session, Map<String, Object> attributes, Map<String, String[]> parameters, String body) {
		this.session = session;
		this.attributes = attributes;
		this.parameters = parameters;
		this.body = body;
	}
	
	@Override
	public Object getAttribute(String name) {
		return attributes.getOrDefault(name, null);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return Collections.enumeration(attributes.keySet());
	}

	@Override
	public String getCharacterEncoding() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getContentLength() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getContentLengthLong() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContentType() {
		return "json";
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		throw new UnsupportedOperationException("Use getReader to read mock body");
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name)[0];
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(parameters.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameters.get(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameters;
	}

	@Override
	public String getProtocol() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getScheme() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServerName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getServerPort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new StringReader(body));
	}

	@Override
	public String getRemoteAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteHost() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAttribute(String name, Object o) {
		attributes.put(name, o);
	}

	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRealPath(String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getRemotePort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLocalAddr() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLocalPort() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletContext getServletContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAsyncStarted() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAsyncSupported() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncContext getAsyncContext() {
		throw new UnsupportedOperationException();
	}

	@Override
	public DispatcherType getDispatcherType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getAuthType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cookie[] getCookies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getDateHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getIntHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getMethod() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPathInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPathTranslated() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContextPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getQueryString() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRemoteUser() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isUserInRole(String role) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Principal getUserPrincipal() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestedSessionId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getRequestURI() {
		throw new UnsupportedOperationException();
	}

	@Override
	public StringBuffer getRequestURL() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getServletPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSession getSession(boolean create) {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpSession getSession() {
		return session;
	}

	@Override
	public String changeSessionId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void login(String username, String password) throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void logout() throws ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

}
