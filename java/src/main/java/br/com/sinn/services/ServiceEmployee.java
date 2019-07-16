package br.com.sinn.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.sinn.domain.Employee;
import br.com.sinn.repository.RepositoryEmployee;
import br.com.sinn.services.exception.ObjectNotFoundException;

@Service
public class ServiceEmployee {

	@Autowired
	RepositoryEmployee repo;

	public List<Employee> findAll() {
		List<Employee> obj = repo.findAll();
		return obj;
	}

	public Employee findById(Integer id) {
		Employee obj = repo.findOne(id);
		if (obj == null)
			throw new ObjectNotFoundException("Employee id " + id + "not found! " + Employee.class.getName());
		return obj;
	}

	@Transactional
	public Employee insert(Employee obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Employee update(Employee employee, Integer id) {
		Employee newEmployee = findById(employee.getId());
		updateData(newEmployee, newEmployee);
		newEmployee.setId(id);
		newEmployee = repo.save(newEmployee);
		return newEmployee;
	}

	public void delete(Employee employee) {
		findById(employee.getId());
		try {
			repo.delete(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Page<Employee> findPage(Integer page, Integer linesPerPage, String direction, String order) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), order);
		return repo.findAll(pageRequest);
	}

	private void updateData(Employee newEmployee, Employee employee) {
		newEmployee.setName(employee.getName());
		newEmployee.setSalary(employee.getSalary());
		newEmployee.setJobRole(employee.getJobRole());
	}
}
