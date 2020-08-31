package com.system.bibliotec.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordChangeDTO {

	
	@NotNull(message = "É necessario informar uma senha")
	@Size(max = 100)
	private String currentPassword;
	
	@NotNull(message = "É necessario informar a nova senha")
	@Size(max = 100)
    private String newPassword;

    public PasswordChangeDTO() {

    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentPassword == null) ? 0 : currentPassword.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordChangeDTO other = (PasswordChangeDTO) obj;
		if (currentPassword == null) {
			if (other.currentPassword != null)
				return false;
		} else if (!currentPassword.equals(other.currentPassword))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		return true;
	}

	public PasswordChangeDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {

        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    
    
}
