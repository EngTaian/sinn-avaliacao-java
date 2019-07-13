package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinn.domain.Advance;

@Repository
public interface RepositoryAdvance extends JpaRepository<Advance, Integer> {

}
