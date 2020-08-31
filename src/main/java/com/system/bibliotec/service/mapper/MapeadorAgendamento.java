package com.system.bibliotec.service.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.system.bibliotec.service.vm.AgendamentoVM;

public class MapeadorAgendamento {

	
	public static ResponseEntity<AgendamentoVM> mapperResponse(Object data, HttpStatus status) {
							
		return new ResponseEntity<AgendamentoVM>(new AgendamentoVM(data), status);
	}

	
}
