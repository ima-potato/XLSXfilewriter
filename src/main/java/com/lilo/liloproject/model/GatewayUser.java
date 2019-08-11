package com.lilo.liloproject.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class GatewayUser {

	private String id;

	private String resourceId;

	private String firstName;

	private String lastName;

	private String loginId;

	private Map<String, String> requestDetails;

	private Map<String, WebApp> webapps;

	public GatewayUser() {
	}

	public GatewayUser(String id, String resourceId, String firstName, String lastName, Map<String, WebApp> webapps) {
		this.id = id;
		this.resourceId = resourceId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.webapps = webapps;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public Map<String, WebApp> getWebapps() {
		return webapps;
	}

	@Override
	public String toString() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Map<String, String> getRequestDetails() {
		if (requestDetails == null) requestDetails = new LinkedHashMap<>();
		return requestDetails;
	}

	public void setRequestDetails(Map<String, String> requestDetails) {
		this.requestDetails = requestDetails;
	}

	public void addRequestDetail(String key, String value) {
		getRequestDetails().put(key, value);
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
