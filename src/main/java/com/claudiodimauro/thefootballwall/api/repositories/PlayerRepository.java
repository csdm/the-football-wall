package com.claudiodimauro.thefootballwall.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.claudiodimauro.thefootballwall.api.models.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{
	
	List<Player> findBySurname(String surname);
	
//	public Optional<List<Player>> findByNameAndNumero(String name,int numero); //uso Optional perché non è detto che il metodo mi ritorni effettivamente qualcosa
//
//	public default Optional<List<Player>> findBymProrAfess (String name , int num ) {
//		return findByNameAndNumero( name, num);
//	}

}
