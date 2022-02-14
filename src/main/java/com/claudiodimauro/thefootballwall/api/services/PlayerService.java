package com.claudiodimauro.thefootballwall.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.claudiodimauro.thefootballwall.api.beans.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.repositories.PlayerRepository;
import com.claudiodimauro.thefootballwall.utils.Constants;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository repository;
	
	@Autowired
	MongoTemplate template;
	
	public PlayerResponseBean findAllPlayers() {
		List<Player> list = repository.findAll();
		
		if(list.isEmpty()) {
			return new PlayerResponseBean(new PlayerRequestBean(),
					Constants.Flag.NO_PLAYERS_FOUND, list); 
		} else {
			return new PlayerResponseBean(new PlayerRequestBean(),
							Constants.Flag.OK, list);
		}
	}
	
//	public List<Player> findByName(String name) {
////		Query query = new Query();
////		query.addCriteria(Criteria.where("name").is(name));
//		Optional<List<Player>>list = repository.findByName(name);
//		
//		//if(list.isPresent()) return list.get();
//		//return null;
//		
//		return repository
//				.findByName(name)
//			.orElse(null);
//	}
	
	
	
	Query query = new Query();
}
