package com.claudiodimauro.thefootballwall.api.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.claudiodimauro.thefootballwall.api.beans.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.repositories.PlayerRepository;
import com.claudiodimauro.thefootballwall.utils.Constants;

import io.swagger.v3.oas.annotations.media.Schema;

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
					Constants.Flag.NO_PLAYERS_FOUND, 0, list); 
		} else {
			return new PlayerResponseBean(new PlayerRequestBean(),
							Constants.Flag.OK, list.size(), list);
		}
	}
	
	public PlayerResponseBean findPlayerByName(String name) {
		
		List<Player> list = repository.findByName(name);
		
		PlayerRequestBean request = new PlayerRequestBean();
		request.setPlayerName(name);
		
		
		if(list.isEmpty()) {
			
			return new PlayerResponseBean(request,
					Constants.Flag.NO_PLAYERS_FOUND, 0, list); 
		} else {
			
			for (Player player : list) {
				System.out.println("\n\n********FAKE_LOG********\n"
						+ "Printed player: \n"
						+ player.getName() + " " + player.getSurname()
						+ "\n*********END_LOG*********\n\n");
			}
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
	

	/**
	 * It must return a response
	 * @param player
	 */
	public void addPlayer(Player player) {
		
		if(player == null) {
			//add response error
		} else {

			repository.insert(player);
			
		}
	}
	
}
