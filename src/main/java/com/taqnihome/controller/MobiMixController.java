package com.taqnihome.controller;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taqnihome.domain.GameConnectionInfo;
import com.taqnihome.domain.GameParticipantsDetail;
import com.taqnihome.domain.ResponseGamePlayers;
import com.taqnihome.service.GameProfileService;

@RestController
//@RequestMapping("mobimix")
public class MobiMixController {
	
	private GameProfileService gameProfileService;
	
	public MobiMixController(GameProfileService gameProfileService) {
		this.gameProfileService = gameProfileService;
	}
	
	@RequestMapping(value = "/updateGameConnectionInfo", method = RequestMethod.POST)
    public ResponseEntity<?> updateGameConnectionInfo(@NotNull @RequestBody GameConnectionInfo connectionInfo) {
		try {
			if( gameProfileService != null ) {
				gameProfileService.updateGameConnectionInfo(connectionInfo);
			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@RequestMapping(value = "/fetchGameParticipantsDetail", method = RequestMethod.POST)
    public ResponseEntity<?> fetchGameParticipantsDetail(@NotNull @RequestBody GameParticipantsDetail gameParticipantsDetail) {
		try {
			if( gameProfileService != null ) {
				ResponseGamePlayers gamePlayers = new ResponseGamePlayers();
				gamePlayers.setGamePlayers(gameProfileService.fetchGameParticipantsDetail(gameParticipantsDetail));
			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
