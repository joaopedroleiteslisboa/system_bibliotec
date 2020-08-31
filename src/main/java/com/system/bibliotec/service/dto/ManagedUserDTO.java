package com.system.bibliotec.service.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.system.bibliotec.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagedUserDTO extends UserAnonimoDTO{

	
	 	public static final int PASSWORD_MIN_LENGTH = 4;

	    public static final int PASSWORD_MAX_LENGTH = 100;

	    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	    private String password;

	  
		public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    @Override
	    public String toString() {
	        return "ManagedUserDTO{" + super.toString() + "} ";
	    }
}
