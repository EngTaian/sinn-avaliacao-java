package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinn.domain.Employee;

@Repository
public interface RepositoryEmployee extends JpaRepository<Employee, Integer>{
	
	@Transactional(readOnly=true)
	Employee findByCpf(String cpf);
	
}
