package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinn.domain.Installments;

@Repository
public interface RepositoryInstallments extends JpaRepository<Installments, Integer> {

}
