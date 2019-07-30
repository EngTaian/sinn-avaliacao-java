package br.com.sinn.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinn.domain.Enterprise;

@Repository
@Transactional(readOnly = true)
public interface RepositoryEnterprise  extends JpaRepository<Enterprise, Integer>{
	
	
	Enterprise findByCnpj(@Param("cnpj") String cnpj);
	
	
	
}
