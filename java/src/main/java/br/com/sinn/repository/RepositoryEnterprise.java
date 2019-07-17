package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinn.domain.Enterprise;

@Repository
public interface RepositoryEnterprise  extends JpaRepository<Enterprise, Integer>{
	
	@Transactional(readOnly = true)
	Enterprise findByCnpj(String cnpj);
	
}
