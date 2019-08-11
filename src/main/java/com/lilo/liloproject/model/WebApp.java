package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebApp implements SalesforceObject {

	@JsonProperty(value = "Id")
	private String id;

	@JsonProperty(value = "MasterLabel")
	private String name;

	@JsonProperty(value = "URL__c")
	private String url;

	@JsonProperty(value = "LoginMapping__c")
	private String loginMapping;

	@JsonProperty(value = "WebAppNumber__c")
	private int webAppNumber;

	@Override
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginMapping() {
		return loginMapping;
	}

	public void setLoginMapping(String loginMapping) {
		this.loginMapping = loginMapping;
	}

	public String getLoginURL() {
		return url + "/" + loginMapping.replaceAll("^/+", "");
	}

	public int getWebAppNumber() {
		return webAppNumber;
	}

	public void setWebAppNumber(int webAppNumber) {
		this.webAppNumber = webAppNumber;
	}

	@Override
	public String toString() {
		return "WebApp [id=" + id + ", name=" + name + ", url=" + url
				+ ", loginMapping=" + loginMapping + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WebApp webApp = (WebApp) o;
		return Objects.equals(id, webApp.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
