package com.example.info.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.info.dao.IEventDAO;
import com.example.info.dao.IPersonDao;
import com.example.info.dao.ITurnDAO;
import com.example.info.exception.Not_Found;
import com.example.info.model.Event;
import com.example.info.model.Person;
import com.example.info.model.Turn;

@Service
public class TurnServiceImpl implements ITurnService{

	@Autowired
	private ITurnDAO turn_dao; 
	@Autowired
	private IEventDAO event_dao; 
	@Autowired
	private IPersonDao person_dao; 
	
	@Override
	public Turn SaveTurn(Turn turn) {
		// TODO Auto-generated method stub
			
			if (person_dao.existsByDni(turn.getPerson().getDni()) && event_dao.existsByName(turn.getEvent().getName())) 
			{ 
				List<Turn> turnos=getAllTurn(); 
				boolean condicion=false; 
				for (int i=0; i<turnos.size(); i++) 
				{ 
					if (turnos.get(i).getEvent().getId()==turn.getEvent().getId() && turnos.get(i).getPerson().getId()==turn.getPerson().getId())
					{ 
						condicion=true; 
					}
				}
				
				if (condicion==false) 
				{ 
					return turn_dao.save(turn); 
				}
				 return null;  
				
				
			}
			 throw new Not_Found("Error no existe el dni o el evento"); 
			
			
			
	}
	@Override
	public List<Turn> getById(Long Id) {
		// TODO Auto-generated method stub
		return turn_dao.findAllById(Id); 
	}
	
	@Override
	public List<Turn> getAllTurn() {
		// TODO Auto-generated method stub
		return turn_dao.findAll(); 
	}
	
	@Override
	public Turn updateTurn(Turn turn) {
		// TODO Auto-generated method stub
		
		return turn_dao.save(turn); 
	}
	@Override
	public Turn SaveTurnUnique(Turn turn) {
		// TODO Auto-generated method stub
		System.out.println(event_dao.existsByName(turn.getEvent().getName())); 
		if (event_dao.existsByName(turn.getEvent().getName())) 
		{ 
				return turn_dao.save(turn); 
			
			
		}
		throw new Not_Found("El nombre del evento no existe"); 
	}
	
	public List<Turn> getActivate() 
	{ 
		List<Turn> turnos=turn_dao.findAll(); 
		List<Turn> turn_list=new ArrayList(); 
		for (int i=0; i<turnos.size(); i++) 
		{ 
			if (turnos.get(i).getStatus().toString().equals("ACTIVO")) 
			{ 
				turn_list.add(turnos.get(i)); 
			}
		}
		return turn_list; 
	}
	
	@Override
	public List<Turn> getTurnByName(String name) {
		// TODO Auto-generated method stub
		List<Turn> turnos=getActivate(); 
		List<Turn> turn_list=new ArrayList(); 
		
		for (int i=0; i<turnos.size(); i++) 
		{ 
			if (turnos.get(i).getEvent().getName().equals(name)) 
			{ 
				turn_list.add(turnos.get(i)); 
			}
		}
		return turn_list; 
	}
	@Override
	public Turn deleteTurnById(Turn turn) {
		// TODO Auto-generated method stub
	
		return turn_dao.save(turn); 
	}
	
	
	
	
	
	

}
