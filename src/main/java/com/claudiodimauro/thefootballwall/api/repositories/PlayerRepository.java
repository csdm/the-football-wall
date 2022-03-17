package com.claudiodimauro.thefootballwall.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.claudiodimauro.thefootballwall.api.models.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String>{	
	List<Player> findBySurname(String surname);
	//List<Player> findByPlayerId(String playerId);
	Optional<Player> findByPlayerId(String playerId);
}