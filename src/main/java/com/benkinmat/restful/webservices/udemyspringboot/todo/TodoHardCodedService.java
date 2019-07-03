package com.benkinmat.restful.webservices.udemyspringboot.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardCodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int idCounter = 0;
	
	static {
		todos.add(new Todo(++ idCounter, "benkinmat", "Learn Angular", new Date(), true));
		todos.add(new Todo(++ idCounter, "benkinmat", "Learn Spring Boot", new Date(), false));
		todos.add(new Todo(++ idCounter, "benkinmat", "Learn Database", new Date(), false));
	}

	public List<Todo> findAll(){
		return todos;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if (todo == null) 
			return null;
		
		if (todos.remove(todo)) {
			return todo;
		};
		
		return null;
	}

	public Todo findById(long id) {
		// TODO Auto-generated method stub
		for (Todo todo: todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		
		return null;
	}
}
