package br.com.globalhitss.claro.domain.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.globalhitss.claro.domain.model.CellPhone;

@Repository
public interface CellPhoneRepository extends MongoRepository<CellPhone, String>{

}
