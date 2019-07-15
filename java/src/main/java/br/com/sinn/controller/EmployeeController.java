package br.com.sinn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinn.domain.Employee;
import br.com.sinn.services.ServiceEmployee;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {
	
	@Autowired
	ServiceEmployee service;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<List<Employee>> findAll(){
		List<Employee> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<Employee> findById(@PathVariable Integer id){
		Employee obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	} 
	
	public ResponseEntity<Page<Employee>> findPage(			
			@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", required=false, defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", required=false, defaultValue="nome")String orderBy,
			@RequestParam(value="direction", required=false, defaultValue="ASC")String direction){
		Page<Employee> obj = service.findPage(page, linesPerPage, direction, orderBy);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ResponseEntity<Employee> insert(@RequestBody Employee employee){
		Employee obj = service.insert(employee);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public ResponseEntity<Employee> update(@RequestBody Employee employee){
		Employee obj = service.update(employee);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResponseEntity<Void> delete(@RequestBody Employee employee){
		service.delete(employee);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
