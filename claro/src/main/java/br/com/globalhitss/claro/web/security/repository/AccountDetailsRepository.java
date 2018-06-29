package br.com.globalhitss.claro.web.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.globalhitss.claro.domain.model.Account;


@Repository
public interface AccountDetailsRepository extends JpaRepository<Account, Long> {
	
	@Query
	public Account findByUsername(String username);
	
}
