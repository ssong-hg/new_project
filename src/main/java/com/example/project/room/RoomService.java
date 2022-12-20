package com.example.project.room;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.project.room.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
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
		
		
		roomList.add(new Room("201", "Superior Double"));
		roomList.add(new Room("202", "Superior Double"));
		roomList.add(new Room("203", "Superior Twin"));
		roomList.add(new Room("204", "Superior Twin"));
		
		roomList.add(new Room("301", "Superior Double"));
		roomList.add(new Room("302", "Superior Double"));
		roomList.add(new Room("303", "Superior Twin"));
		roomList.add(new Room("304", "Superior Twin"));
		
		
		roomList.add(new Room("401", "Deluxe Double"));
		roomList.add(new Room("402", "Deluxe Double"));
		roomList.add(new Room("403", "Deluxe Double"));
		
		roomList.add(new Room("501", "Deluxe Twin"));
		roomList.add(new Room("502", "Deluxe Twin"));
		roomList.add(new Room("503", "Deluxe Twin"));
		
		roomList.add(new Room("601", "Junior Suite"));
		roomList.add(new Room("602", "Deluxe Twin"));
		
		roomList.add(new Room("700", "Royal Suite"));
		roomList.add(new Room("800", "Royal Suite"));
		
		for(int i = 0 ; i < roomList.size(); i++) {
			roomRepository.save(roomList.get(i));
		}
		
	}
	

}
