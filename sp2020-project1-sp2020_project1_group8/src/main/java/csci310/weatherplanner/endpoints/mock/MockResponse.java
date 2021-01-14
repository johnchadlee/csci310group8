package csci310.weatherplanner.endpoints.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MockResponse implements HttpServletResponse{
	private final PrintWriter out;
	private final StringWriter output;
	private int status;
	
	public MockResponse() {
		output = new StringWriter();
		out = new PrintWriter(output, true);
		status = HttpServletResponse.SC_OK;
	}
	
	public String getOutput() {
		return output.toString();
	}

	@Override
	public String getCharacterEncoding() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getContentType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		throw new UnsupportedOperationException("Call getWriter to write output to mock response");
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return out;
	}

	@Override
	public void setCharacterEncoding(String charset) { }

	@Override
	public void setContentLength(int len) {	}

	@Override
	public void setContentLengthLong(long len) { }

	@Override
	public void setContentType(String type) { }

	@Override
	public void setBufferSize(int size) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getBufferSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flushBuffer() throws IOException {
		out.flush();
	}

	@Override
	public void resetBuffer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCommitted() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reset() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLocale(Locale loc) {	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addCookie(Cookie cookie) {	}

	@Override
	public boolean containsHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeURL(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeRedirectURL(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeUrl(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String encodeRedirectUrl(String url) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendError(int sc) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendRedirect(String location) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setDateHeader(String name, long date) {	}

	@Override
	public void addDateHeader(String name, long date) {	}

	@Override
	public void setHeader(String name, String value) { }

	@Override
	public void addHeader(String name, String value) { }

	@Override
	public void setIntHeader(String name, int value) { }

	@Override
	public void addIntHeader(String name, int value) { }

	@Override
	public void setStatus(int sc) {	
		status = sc;
	}

	@Override
	public void setStatus(int sc, String sm) { 
		status = sc;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getHeader(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getHeaders(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getHeaderNames() {
		throw new UnsupportedOperationException();
	}

}
