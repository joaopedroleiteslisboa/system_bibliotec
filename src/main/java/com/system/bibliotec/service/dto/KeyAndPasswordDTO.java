package com.system.bibliotec.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KeyAndPasswordDTO {

		@NotNull(message = "É necessario a chave de redefinição da senha")		
		private String key;

		@NotNull(message = "É necessario informar a nova senha")
		@Size(max = 100)
	    private String newPassword;

		@NotNull(message = "É necessario informar a confirmação de senha")
		@Size(max = 100)
	    private String confirmPassword;

	    public String getKey() {
	        return key;
	    }

	    public void setKey(String key) {
	        this.key = key;
	    }

	    public String getNewPassword() {
	        return newPassword;
	    }

	    public void setNewPassword(String newPassword) {
	        this.newPassword = newPassword;
	    }

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}




}
