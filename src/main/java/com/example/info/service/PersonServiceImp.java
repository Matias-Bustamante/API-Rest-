package com.example.info.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Isolation;
import org.springframework.stereotype.Service;

import com.example.info.dao.IPersonDao;
import com.example.info.dto.PersonDTO;
import com.example.info.exception.KeyIncorrecta;
import com.example.info.exception.PersonException.FieldsEmpty;
import com.example.info.exception.PersonException.Not_FoundDni;
import com.example.info.exception.PersonException.PersonEmpty;
import com.example.info.model.Person;
import com.example.info.model.Status;

@Service
@Transactional
public class PersonServiceImp implements IPersonService{
	
	@Autowired
	private IPersonDao persondao; 
	
	
	@Override
	public  Person save(Person person) {
		// TODO Auto-generated method stub
		
		if (person.getFirstname().isEmpty() || person.getDni().isEmpty() || person.getLastname().isEmpty())
		{ 
			throw new FieldsEmpty("Los campos no pueden estar vacios"); 
		}
		
		return persondao.save(person); 
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		if (getAllPersonActivate().isEmpty()) 
		{ 
			throw new PersonEmpty("No existen Personas en la bases de Datos"); 
			
		}
		return getAllPersonActivate(); 
	}

	@Override
	public Person findByDni(String dni) {
		// TODO Auto-generated method stub
		Person person; 
		if (persondao.existsByDni(dni)) 
		{ 
			person=persondao.findByDni(dni); 
			
			if (person.getStatus().toString().equals("ACTIVO")) 
			{ 
				return persondao.findByDni(dni); 
			}else 
			{ 
				throw new Not_FoundDni("La persona no se encuentra en el sistema"); 
			}
		}else 
		{ 
			throw new Not_FoundDni("El Dni Ingresado no existe"); 
		}
		
	}

	@Override
	public List<Person> findByLastname(String last_name) {
		// TODO Auto-generated method stub
		List<Person> person=new ArrayList(); 
		String name=last_name.toLowerCase(); 
		if(persondao.existsByLastname(name)) 
		{ 
			List<Person> persona=persondao.findByLastname(name); 
			 
			for (int i=0; i<persona.size(); i++) 
			{ 
				if (persona.get(i).getStatus().toString().equals("ACTIVO")) 
				{ 
					person.add(persona.get(i)); 
				}
			}
		}else 
		{ 
			throw new Not_FoundDni("La persona Ingresada no existe"); 
		}
		
		if (person.isEmpty()) 
		{ 
			throw new Not_FoundDni("No existe persona en la base de Datos"); 
		}
		return person; 
	}

	@Override
	public Person Compare_key(Person person, PersonDTO persondto) {
		// TODO Auto-generated method stub
		
		if (persondto.getKey_code() != null)
		{ 
			if (persondto.getKey_code().equals(person.getKey_code())) 
			{ 
				person.setFirstname(persondto.getFirst_name());
				person.setLastname(persondto.getLast_name());
				person.setDni(persondto.getDni());
				return person; 
			}else 
			{ 
				throw new KeyIncorrecta("La clave ingresada es incorrecta, intente nuevamente"); 
			}
			
		}
		throw new NullPointerException("Error. La propiedad Key Code es nula, intente nuevamente"); 
	}
	
	public List<Person> getAllPersonActivate() 
	{ 
		List<Person> person=new ArrayList(); 
		List<Person> persona=persondao.findAll(); 
		
		for (int i=0; i<persona.size(); i++) 
		{ 
			if (persona.get(i).getStatus().toString().equals("ACTIVO")) 
			{ 
				person.add(persona.get(i)); 
			}
		}
		return person; 
	}

	@Override
	public Person updatePerson(Person persona, PersonDTO person) {
		// TODO Auto-generated method stub
		if (person.getKey_code() !=null)
		{ 
			if (person.getKey_code().equals(persona.getKey_code())) 
			{ 
				persona.setFirstname(person.getFirst_name());
				persona.setLastname(person.getLast_name());
				return persondao.save(persona); 
			}
			throw new KeyIncorrecta("La clave ingresada es incorrecta"); 
			
			
		}
		throw new NullPointerException("La propiedad Key Code no puede estar null"); 
		
	}

	@Override
	public Person deletePerson(Person person) {
		// TODO Auto-generated method stub
		
		person.setStatus(Status.INACTIVO);
		return persondao.save(person); 
	}
	
	

	
	
	

	
	
}
