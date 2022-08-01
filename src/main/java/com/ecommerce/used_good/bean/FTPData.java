package com.ecommerce.used_good.bean;

import java.util.Objects;

public class FTPData 
{
	private String ip;
	private int port;
	private String username;
	private String password;
	private String defaultFolderPath;
	
	public FTPData(String ip, int port, String username, String password, String defaultFolderPath) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.defaultFolderPath = defaultFolderPath;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDefaultFolderPath() {
		return defaultFolderPath;
	}

	public void setDefaultFolderPath(String defaultFolderPath) {
		this.defaultFolderPath = defaultFolderPath;
	}
	

	public FTPData() {
	}

	public FTPData ip(String ip) {
		setIp(ip);
		return this;
	}

	public FTPData port(int port) {
		setPort(port);
		return this;
	}

	public FTPData username(String username) {
		setUsername(username);
		return this;
	}

	public FTPData password(String password) {
		setPassword(password);
		return this;
	}

	public FTPData defaultFolderPath(String defaultFolderPath) {
		setDefaultFolderPath(defaultFolderPath);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof FTPData)) {
			return false;
		}
		FTPData fTPData = (FTPData) o;
		return Objects.equals(ip, fTPData.ip) && port == fTPData.port && Objects.equals(username, fTPData.username) && Objects.equals(password, fTPData.password) && Objects.equals(defaultFolderPath, fTPData.defaultFolderPath);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ip, port, username, password, defaultFolderPath);
	}

	@Override
	public String toString() {
		return "{" +
			" ip='" + getIp() + "'" +
			", port='" + getPort() + "'" +
			", username='" + getUsername() + "'" +
			", password='" + getPassword() + "'" +
			", defaultFolderPath='" + getDefaultFolderPath() + "'" +
			"}";
	}
	
}
