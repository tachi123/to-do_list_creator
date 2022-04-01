package com.creatortodolist.webservices.todowebservices.resource;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

import com.creatortodolist.webservices.todowebservices.entity.Todo;
import com.creatortodolist.webservices.todowebservices.service.TodoHardcodedService;
import com.creatortodolist.webservices.todowebservices.repository.TodoJpaRepository;

import org.hibernate.annotations.SortComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.OrderBy;

@CrossOrigin(origins="*")
@RestController
public class TodoJpaResource {
	

	@Autowired
	private TodoJpaRepository todoJpaRepository;

	
	@GetMapping("/jpa/users/{username}/todos")
	@SortComparator(TodoComparator.class)
	public List<Todo> getAllTodos(@PathVariable String username){

		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
	}

	@GetMapping("/jpa/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		return todoJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}

	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable String username, @PathVariable long id) {

		todoJpaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	

	//Edit/Update a Todo
	//PUT /users/{user_name}/todos/{todo_id}
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Todo todo){
		
		todo.setUsername(username);
		
		Todo todoUpdated = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Todo todo){
		
		todo.setUsername(username);
		
		Todo createdTodo = todoJpaRepository.save(todo);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	public class TodoComparator implements Comparator<Todo> {

		public int compare(Todo e1, Todo e2) {
			return e1.getFolder().compareTo(e2.getFolder());
		}

		public String toString() {
			return "TodoComparator";
		}
	}
}
