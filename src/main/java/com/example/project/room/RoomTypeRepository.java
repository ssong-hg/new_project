package com.example.project.room;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
	Optional<RoomType> findByRoomType(String roomType);

}
