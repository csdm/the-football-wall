package com.claudiodimauro.thefootballwall.api.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.claudiodimauro.thefootballwall.api.beans.requests.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.beans.responses.BasePlayerResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.ListPlayerResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.PlayerInsertResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.PlayerPaginationResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.SinglePlayerResponse;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.repositories.PlayerRepository;
import com.claudiodimauro.thefootballwall.utils.Constants;

@Service("PlayerServiceImpl")
public class PlayerServiceImpl implements PlayerService {
	private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	@Autowired
	PlayerRepository repository;

	@Autowired
	MongoTemplate template;

	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: findAllPlayers()
	 * DESCRIPTION: It find a list of players using the findAll() method of MongoRepository 
	 * 				and return the list in a complete response
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> findAllPlayers() {
		List<Player> list = repository.findAll();

		/**
		 * Setting the default order for the list: totalScore DESC
		 * In this case I decided to use a simple sort on the list in order to don't touch the part about 
		 * pagination and sorting with MongoRepository
		 */
		list.sort(new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {
				return p2.getTotalScore() - p1.getTotalScore();
			}
		});

		if(list.isEmpty()) {			
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.NO_PLAYER_FOUND, 0);
			return new ListPlayerResponse(new PlayerRequestBean(), Constants.Flag.NO_PLAYER_FOUND, 0L, list);
		} 

		logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.OK, list.size());
		return new ListPlayerResponse(new PlayerRequestBean(), Constants.Flag.OK, (long)list.size(), list);

	}


	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: findPaginatedPlayers(int pg, int size)
	 * DESCRIPTION: It find a list of players using the findAll() method of MongoRepository 
	 * 				and return the list, with a pagination, in a complete response
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> findPaginatedPlayers(int pg, int size) {

		/**
		 * Default sort: totalScore descending
		 */
		Page<Player> page = repository.findAll(PageRequest.of(pg, size, Sort.by("totalScore").descending()));

		if(page == null) {
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.NO_PLAYER_FOUND, 0);
			logger.info("Search status: {}", Constants.Flag.NO_PLAYER_FOUND);
			
			return new PlayerPaginationResponse(new PlayerRequestBean(), Constants.Flag.NO_PLAYER_FOUND, 0L, page);
		} 

		logger.debug("Search status: {} - Search results: found {} player(s) to show in the page number {}", Constants.Flag.OK, page.getNumberOfElements(), page.getNumber());
		logger.info("Search status: {} - Search results: found {} player(s) to show in the page number {}", Constants.Flag.OK, page.getNumberOfElements(), page.getNumber());
		return new PlayerPaginationResponse(new PlayerRequestBean(), Constants.Flag.OK, page.getTotalElements(), page);

	}

	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: findPagifindPlayeratedPlayers(String surname, String name)
	 * DESCRIPTION: It find a list of players that match with the key surname, 
	 * 				or name or surname-name
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> findPlayer(String surname, String name) {
		PlayerRequestBean request = new PlayerRequestBean();
		Query query = new Query();

		if(surname != null) {
			request.setPlayerSurname(surname);
			query.addCriteria(Criteria.where("surname").is(surname));
		}

		if(name != null) {
			request.setPlayerName(name);
			query.addCriteria(Criteria.where("name").is(name));
		}

		List<Player> list = template.find(query, Player.class);


		if(list.isEmpty()) {
			logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.NO_PLAYER_FOUND, 0);
			logger.info("Search status: {} ", Constants.Flag.NO_PLAYER_FOUND);
			return new ListPlayerResponse(request, Constants.Flag.NO_PLAYER_FOUND, 0L, list); 
		} 

		logger.debug("Search status: {} - Search results: found {} player(s)", Constants.Flag.OK, list.size());
		logger.info("Search status: {} - Search results: found {} player(s)", Constants.Flag.OK, list.size());
		return new ListPlayerResponse(request, Constants.Flag.OK, (long)list.size(), list);
	}


	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: findPlayerByPlayerId(String playerId)
	 * DESCRIPTION: It find a single player using the implicit findByPlayerId(playerId) method 
	 * 				of MongoRepository and return the data in a complete response
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> findPlayerByPlayerId(String playerId) {
		//List<Player> list = repository.findByPlayerId(playerId);

		Optional<Player> player = repository.findByPlayerId(playerId);
		PlayerRequestBean request = new PlayerRequestBean();
		request.setPlayerId(playerId);

		if(! player.isPresent()){
			logger.debug("Search status: {} - Search results: no one player with id {}", Constants.Flag.NO_PLAYER_FOUND, playerId);
			logger.info("Search status: {}", Constants.Flag.NO_PLAYER_FOUND);
			return new SinglePlayerResponse(request, Constants.Flag.NO_PLAYER_FOUND, 0L, null); 
		} 		

		logger.debug("Search status: {} - Search results: found {}", Constants.Flag.OK, playerId);
		logger.info("Search status: {} - Search results: found {}", Constants.Flag.OK, playerId);
		return new SinglePlayerResponse(request, Constants.Flag.NO_PLAYER_FOUND, 1L, player.get()); 		
	}


	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: findTopTenPlayers()
	 * DESCRIPTION: It find a list of 10 players using a custom query with MongoTemplate
	 * 				and return the list in a complete response
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> findTopTenPlayers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("totalScore").gte(80));

		/**
		 * Query ordering
		 */
		Sort.Order orderByTotalScoreDesc = new Order(Sort.Direction.DESC, "totalScore");
		Sort.Order orderBySurnameAsc = new Order(Sort.Direction.ASC, "surname");
		List<Sort.Order> orders = new ArrayList<>();
		orders.add(orderByTotalScoreDesc);
		orders.add(orderBySurnameAsc);
		/*****/

		query.with(Sort.by(orders));
		query.limit(10);

		List<Player> list = template.find(query, Player.class);

		if(list.isEmpty()) {			
			logger.debug("Search status: {} - Search results: found {} player(s) of 10 players", Constants.Flag.NO_PLAYER_FOUND, 0);
			logger.info("Search status: {}", Constants.Flag.NO_PLAYER_FOUND);
			return new ListPlayerResponse(new PlayerRequestBean(), Constants.Flag.NO_PLAYER_FOUND, 0L, list);
		} 

		logger.debug("Search status: {} - Search results: found {} player(s) of 10 players", Constants.Flag.OK, list.size());
		logger.info("Search status: {} - Search results: found {} player(s) of 10 players", Constants.Flag.OK, list.size());
		return new ListPlayerResponse(new PlayerRequestBean(), Constants.Flag.OK, (long)list.size(), list);
	}


	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: addPlayer(Player player)
	 * DESCRIPTION: It add a player in the DB. The method return a complete response
	 * 				with the data of the inserted player inside
	 * 
	 *****************************************************************/
	public BasePlayerResponse<?> addPlayer(Player player) {
		try {
			repository.insert(player);
			logger.debug("Player addedd succesfully: {}", player);
			logger.info("Player addedd succesfully: {} {}", player.getName(), player.getSurname());
			return new PlayerInsertResponse(player, Constants.Flag.OK);
		} catch(Exception ex) {
			logger.debug("ERROR: {}", ex.getMessage());
			logger.info("ERROR: {}", ex.getMessage());
			return new PlayerInsertResponse(player, Constants.Flag.KO + " " + ex.getMessage());
		}
	}

}
