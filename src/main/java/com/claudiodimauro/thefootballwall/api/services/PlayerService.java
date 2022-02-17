package com.claudiodimauro.thefootballwall.api.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.claudiodimauro.thefootballwall.api.beans.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseOnInsertBean;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.repositories.PlayerRepository;
import com.claudiodimauro.thefootballwall.utils.Constants;


@Service
public class PlayerService {
	private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);
	
	@Autowired
	PlayerRepository repository;
	
	@Autowired
	MongoTemplate template;
	
	public PlayerResponseBean findAllPlayers() {
		List<Player> list = repository.findAll();
		
		if(list.isEmpty()) {			
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.NO_PLAYER_FOUND, 0);
			return new PlayerResponseBean(new PlayerRequestBean(),
					Constants.Flag.NO_PLAYER_FOUND, 0, list);
		} else {
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.OK, list.size());
			return new PlayerResponseBean(new PlayerRequestBean(),
							Constants.Flag.OK, list.size(), list);
		}
	}
	
	
	public PlayerResponseBean findPlayerBySurname(String surname) {	
		List<Player> list = repository.findBySurname(surname);
		
		PlayerRequestBean request = new PlayerRequestBean();
		request.setPlayerSurname(surname);
		
		if(list.isEmpty()) {
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.NO_PLAYER_FOUND, 0);
			return new PlayerResponseBean(request,
					Constants.Flag.NO_PLAYER_FOUND, 0, list); 
		} else {		
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.OK, list.size());
			return new PlayerResponseBean(request,
							Constants.Flag.OK, list.size(), list);
		}
	}
	
	//versione con mongoTemplate
//	public PlayerResponseBean findPlayerByName(String name) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("name").is(name));
//		
//		List<Player> list = template.find(query, Player.class);
//		
//		PlayerRequestBean request = new PlayerRequestBean();
//		request.setPlayerName(name);
//		
//		
//		if(list.isEmpty()) {
//			
//			return new PlayerResponseBean(request,
//					Constants.Flag.NO_PLAYERS_FOUND, 0, list); 
//		} else {
//			
//			for (Player player : list) {
//				System.out.println("\n\n********FAKE_LOG********\n"
//						+ "Printed List: \n"
//						+ player.getName() + " " + player.getSurname()
//						+ "\n*********END_LOG*********\n\n");
//			}
//			
//			return new PlayerResponseBean(request,
//							Constants.Flag.OK, list.size(), list);
//		}	
//		
//		
//	}
	
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
	
	
	public PlayerResponseOnInsertBean addPlayer(Player player) {
		try {
			repository.insert(player);
			return new PlayerResponseOnInsertBean(player, Constants.Flag.OK);
		} catch(Exception ex) {
			return new PlayerResponseOnInsertBean(player, Constants.Flag.KO + " " + ex.getMessage());
		}
	}
	
}
