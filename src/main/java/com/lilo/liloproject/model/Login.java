package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Login implements SalesforceObject {

	private String id;
	
	@JsonProperty(value = "Email__c")
	private String email;
	
	@JsonProperty(value = "Password__c")
	private String password;
	
	@JsonProperty(value = "Salt__c")
	private String salt;
	
	@JsonProperty(value = "Resource__c")
	private String resource;
	
	@JsonProperty(value = "ForgotPassword__c")
	private boolean forgotPassword;
	
	@JsonProperty(value = "ForgotPasswordDate__c")
	private String forgotPasswordDate;
	
	@JsonProperty(value = "Active__c")
	private boolean active;
	
	@JsonProperty(value = "ActivationCode__c")
	private String activationCode;
	
	@JsonProperty(value = "AskForNewPassword__c")
	private boolean askForNewPassword;
	
	private String resourceFirstName;
	
	private String resourceLastName;

	@JsonProperty(value = "GuardAccount__c")
	private boolean isGuard;

	@JsonProperty(value = "RemoteLILOAccess__c")
	private boolean isRemote;

	public boolean isGuard() {
		return isGuard;
	}

	public void setGuard(boolean guard) {
		isGuard = guard;
	}

	public boolean isRemote() {
		return isRemote;
	}

	public void setRemote(boolean remote) {
		isRemote = remote;
	}

	@JsonProperty(value = "IDNumber__c")
	private String idNumber;
	
	@JsonIgnore
	public String getId(){
		return id;
	}
	
	@JsonProperty(value = "Id")
	public void setId(String id){
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getResource() {
		return resource;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public boolean isForgotPassword() {
		return forgotPassword;
	}
	
	public void setForgotPassword(boolean forgotPassword) {
		this.forgotPassword = forgotPassword;
	}
	
	public String getForgotPasswordDate() {
		return forgotPasswordDate;
	}
	
	public void setForgotPasswordDate(String forgotPasswordDate) {
		this.forgotPasswordDate = forgotPasswordDate;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	public boolean isAskForNewPassword() {
		return askForNewPassword;
	}
	
	public void setAskForNewPassword(boolean askForNewPassword) {
		this.askForNewPassword = askForNewPassword;
	}
	
	@JsonIgnore
	public String getResourceFirstName() {
		return resourceFirstName;
	}

	@JsonProperty(value = "ResourceFirstName__c")
	public void setResourceFirstName(String resourceFirstName) {
		this.resourceFirstName = resourceFirstName;
	}

	@JsonIgnore
	public String getResourceLastName() {
		return resourceLastName;
	}

	@JsonProperty(value = "ResourceLastName__c")
	public void setResourceLastName(String resourceLastName) {
		this.resourceLastName = resourceLastName;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", resource=" + resource + ", forgotPassword=" + forgotPassword
				+ ", forgotPasswordDate=" + forgotPasswordDate + ", active=" + active + ", activationCode="
				+ activationCode + ", askForNewPassword=" + askForNewPassword + ", resourceFirstName="
				+ resourceFirstName + ", resourceLastName=" + resourceLastName + ", idNumber=" + idNumber + "]";
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}
