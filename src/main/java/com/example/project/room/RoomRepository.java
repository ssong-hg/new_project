package com.example.project.room;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface RoomRepository extends JpaRepository<Room, String>{
	Optional<Room> findByRoomNo(String roomNo);
	List<Room> findByRoomType(String roomType);
	List<Room> findAll();
	
	
	
}
