package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinn.domain.Enterprise;

@Repository
public interface RepositoryEnterprise  extends JpaRepository<Enterprise, Integer>{

}
