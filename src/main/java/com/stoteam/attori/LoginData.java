/**
@author Gianluca Tiribelli, Marino Cervoni, Diego Viglianisi
@version 1.0
*/

package com.stoteam.attori;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.config.PropertyVisibilityStrategy;

public class LoginData {

	private String email;
	private String password;
	
	@JsonbCreator
	public LoginData(@JsonbProperty("email") String email, @JsonbProperty("password") String password) {
		setEmail(email);
		setPassword(password);
	}
	
/**
 * @param getEmail - Ottiene l'indirizzo email
 * @return Indirizzo Email
 */
	
	public String getEmail() {
		return email;
	}
	
/**
 * @param setEmail - Imposta l'indirizzo email
 * @return Indirizzo Email
 */
	
	public void setEmail(String email) {
		if(email != null && !email.trim().isEmpty())
			this.email = email;
		else
			throw new IllegalArgumentException();
	}
	
/**
 * @param getPassword - Ottiene la password
 * @return Password
 */
		
	public String getPassword() {
		return password;
	}
	
/**
 * @param setPassword - Imposta la password
 * @return Password
 */
		
	public void setPassword(String password) {
		if(password != null && !password.trim().isEmpty())
			this.password = password;
		else
			throw new IllegalArgumentException();
	}
	
	public String toJson() {
		JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
			
			@Override
			public boolean isVisible(Method arg0) {
				return false;
			}
			
			@Override
			public boolean isVisible(Field arg0) {
				return true;
			}
		});
		return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
	}
	@Override
	public String toString() {
		return "LoginData [email=" + email + ", password=" + password + "]";
	}
}
