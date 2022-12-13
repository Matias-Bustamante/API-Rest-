package com.example.info.service;

import java.util.List;

import com.example.info.model.Turn;

public interface ITurnService {

	public Turn SaveTurn(Turn turn); 
	
	public List<Turn> getById(Long Id); 
	
	public List<Turn> getAllTurn(); 
	
	public Turn updateTurn(Turn turn);
	
	public Turn SaveTurnUnique(Turn turn); 
	
	public List<Turn> getTurnByName(String name); 
	
	public Turn deleteTurnById(Turn turn); 
	
	
}
