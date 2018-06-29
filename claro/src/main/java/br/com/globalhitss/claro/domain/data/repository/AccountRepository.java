package br.com.globalhitss.claro.domain.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.globalhitss.claro.domain.model.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	
	@Query("select id from Account where username = :username")
	Long getIdByEmail(@Param("username") String username);
	
	@Query
	Account findByusername(String username);
	
}
