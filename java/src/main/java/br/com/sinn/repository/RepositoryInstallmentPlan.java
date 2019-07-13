package br.com.sinn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinn.domain.InstallmentPlan;

@Repository
public interface RepositoryInstallmentPlan extends JpaRepository<InstallmentPlan, Integer> {

}
