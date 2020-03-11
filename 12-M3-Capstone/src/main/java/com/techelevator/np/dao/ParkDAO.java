package com.techelevator.np.dao;

import java.util.List;

import com.techelevator.np.models.Park;

public interface ParkDAO {

	List<Park> getAllParks();
	Park getParkByParkCode(String code);
	
}
