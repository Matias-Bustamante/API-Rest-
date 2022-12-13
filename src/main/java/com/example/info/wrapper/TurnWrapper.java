package com.example.info.wrapper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.example.info.dto.TurnDTO;
import com.example.info.utils.*;
import com.example.info.model.Event;
import com.example.info.model.Status;
import com.example.info.model.Turn;

public class TurnWrapper {
	
	public static Turn TurnDTOentity(TurnDTO turn_dto) 
	{ 
		String key=UUID.randomUUID().toString().substring(0,generar_key.generar_key()); 
		Turn turn = new Turn(); 
		turn.setId(1L);
		turn.setDate(turn_dto.getDate_turn()); 
		turn.setEvent(turn_dto.getEvent()); 
		turn.setPerson(turn_dto.getPerson());
		turn.setStatus(TurnWrapper.settearStatus(turn_dto.getDate_turn()));
		turn.setKey_code(key);
		return turn; 
	}
	
	public static TurnDTO entityTOTurnDTO(Turn turn) 
	{ 
		TurnDTO turn_dto = new TurnDTO(); 
		turn_dto.setDate_turn(turn.getDate());
		turn_dto.setEvent(turn.getEvent());
		turn_dto.setPerson(turn.getPerson());
		
		return turn_dto; 
	}
	
	
	public static Status settearStatus (Calendar date_event) 
	{ 
		int anio=date_event.getTime().getYear() +1900; 
		int mes=date_event.getTime().getMonth()+1; 
		int dias= date_event.getTime().getDate()+1; 
		Date new_fecha= new Date(anio,mes, dias); 
		int fecha=new_fecha.compareTo(new Date()); 
		
		if (fecha==0 || fecha>0) 
		{ 
			
			return Status.ACTIVO; 
		}
		return Status.INACTIVO; 
	}
	
	public static Calendar settearDate(TurnDTO turn_dto, Event evento) 
	{ 
		if (evento.getPeriodicity().toString().equals("RECURRENTE"))
		{ 
			return turn_dto.getDate_turn();
		}
		return evento.getDate_event(); 
	}
	
	public static Turn TurnDTOUniqueentity(TurnDTO turn_dto) 
	{ 
		String key=UUID.randomUUID().toString().substring(0,generar_key.generar_key()); 
		Turn turn = new Turn();  
		turn.setDate(turn_dto.getEvent().getDate_event());
		turn.setEvent(turn_dto.getEvent()); 
		turn.setPerson(turn_dto.getPerson());
		turn.setStatus(TurnWrapper.settearStatus(turn_dto.getEvent().getDate_event()));
		turn.setKey_code(key);
		return turn; 
	}
	

}
