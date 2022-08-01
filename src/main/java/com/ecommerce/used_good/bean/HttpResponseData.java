package com.ecommerce.used_good.bean;

import java.util.Objects;

public class HttpResponseData 
{
	private String requestURL;
	private String protocolVersion;
	private int statusCode;
	private String reasonPhrase;
	private String rawBody;
	
	public HttpResponseData(String requestURL, String protocolVersion, int statusCode, String reasonPhrase,
			String rawBody) {
		super();
		this.requestURL = requestURL;
		this.protocolVersion = protocolVersion;
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.rawBody = rawBody;
	}

	public HttpResponseData(String protocolVersion, int statusCode, String reasonPhrase, String rawBody) {
		super();
		this.protocolVersion = protocolVersion;
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.rawBody = rawBody;
	}
	
	public HttpResponseData(String protocolVersion, String statusCode, String reasonPhrase, String rawBody) {
		super();
		this.protocolVersion = protocolVersion;
		this.statusCode = Integer.parseInt(statusCode);
		this.reasonPhrase = reasonPhrase;
		this.rawBody = rawBody;
	}


	public HttpResponseData() {
	}

	public String getRequestURL() {
		return this.requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getProtocolVersion() {
		return this.protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public String getRawBody() {
		return this.rawBody;
	}

	public void setRawBody(String rawBody) {
		this.rawBody = rawBody;
	}

	public HttpResponseData requestURL(String requestURL) {
		setRequestURL(requestURL);
		return this;
	}

	public HttpResponseData protocolVersion(String protocolVersion) {
		setProtocolVersion(protocolVersion);
		return this;
	}

	public HttpResponseData statusCode(int statusCode) {
		setStatusCode(statusCode);
		return this;
	}

	public HttpResponseData reasonPhrase(String reasonPhrase) {
		setReasonPhrase(reasonPhrase);
		return this;
	}

	public HttpResponseData rawBody(String rawBody) {
		setRawBody(rawBody);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof HttpResponseData)) {
			return false;
		}
		HttpResponseData httpResponseData = (HttpResponseData) o;
		return Objects.equals(requestURL, httpResponseData.requestURL) && Objects.equals(protocolVersion, httpResponseData.protocolVersion) && statusCode == httpResponseData.statusCode && Objects.equals(reasonPhrase, httpResponseData.reasonPhrase) && Objects.equals(rawBody, httpResponseData.rawBody);
	}

	@Override
	public int hashCode() {
		return Objects.hash(requestURL, protocolVersion, statusCode, reasonPhrase, rawBody);
	}

	@Override
	public String toString() {
		return "{" +
			" requestURL='" + getRequestURL() + "'" +
			", protocolVersion='" + getProtocolVersion() + "'" +
			", statusCode='" + getStatusCode() + "'" +
			", reasonPhrase='" + getReasonPhrase() + "'" +
			", rawBody='" + getRawBody() + "'" +
			"}";
	}
	

	
	
}