package com.example.project.room;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {
	private final RoomRepository roomRepository;
	
	
	public List<Room> getRoomTypeList(String roomType){
		
		return this.roomRepository.findByRoomType(roomType);
	}
	
	public List<Room> getAllList(){
		return this.roomRepository.findAll();
	}
	
	public Room getRoom(String roomNo) {
		
		Optional<Room> oRoom = this.roomRepository.findByRoomNo(roomNo);
		if(oRoom.isPresent()) {
			Room room = oRoom.get();
			
			return room;
		}
		
		return null;
	}
	
	public String getRoomType(String roomNo) {
		
		Optional<Room> oRoom = this.roomRepository.findByRoomNo(roomNo);
		if(oRoom.isPresent()) {
			Room room = oRoom.get();
			
			return room.getRoomType();
		}
		
		return null;
	}
	
	public void setRoom() {
		List<Room> roomList = new ArrayList<Room>(18);
		roomList.get(0).setRoomNo("201");
		roomList.get(0).setRoomType("Superior Double");
		roomList.get(1).setRoomNo("202");
		roomList.get(1).setRoomType("Superior Double");
		roomList.get(2).setRoomNo("203");
		roomList.get(2).setRoomType("Superior Twin");
		roomList.get(3).setRoomNo("204");
		roomList.get(3).setRoomType("Superior Twin");
		roomList.get(4).setRoomNo("301");
		roomList.get(4).setRoomType("Superior Double");
		roomList.get(5).setRoomNo("302");
		roomList.get(5).setRoomType("Superior Double");
		roomList.get(6).setRoomNo("303");
		roomList.get(6).setRoomType("Superior Twin");
		roomList.get(7).setRoomNo("304");
		roomList.get(7).setRoomType("Superior Twin");
		
		roomList.get(8).setRoomNo("401");
		roomList.get(8).setRoomType("Deluxe Double");
		roomList.get(9).setRoomNo("402");
		roomList.get(9).setRoomType("Deluxe Double");
		roomList.get(10).setRoomNo("403");
		roomList.get(10).setRoomType("Deluxe Double");
		
		roomList.get(11).setRoomNo("501");
		roomList.get(11).setRoomType("Deluxe Twin");
		roomList.get(12).setRoomNo("502");
		roomList.get(12).setRoomType("Deluxe Twin");
		roomList.get(13).setRoomNo("503");
		roomList.get(13).setRoomType("Deluxe Twin");
		
		roomList.get(14).setRoomNo("601");
		roomList.get(14).setRoomType("Junior Suite");
		roomList.get(15).setRoomNo("602");
		roomList.get(15).setRoomType("Junior Suite");
		
		roomList.get(16).setRoomNo("700");
		roomList.get(16).setRoomType("Royal Suite");
		roomList.get(17).setRoomNo("800");
		roomList.get(17).setRoomType("Royal Suite");
		
		for(int i = 0 ; i < roomList.size(); i++) {
			roomRepository.save(roomList.get(i));
		}
		
	}
	

}
