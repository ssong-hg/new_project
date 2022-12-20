package com.example.project.room;

import java.util.Optional;

import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomTypeService {
	public final RoomTypeRepository roomTypeRepository;
	
	public RoomType getRoomType(String roomType) {
		Optional<RoomType> rType = this.roomTypeRepository.findByRoomType(roomType);
		
			return rType.get();
		
	}
	
	public void setRoomType() {
		RoomType superiorDouble = new RoomType();
		superiorDouble.setRoomType("Superior Double");
		superiorDouble.setBedSize("더블");
		superiorDouble.setPersons(2);
		superiorDouble.setPrice(67200);
		superiorDouble.setRoomSize("28㎡");
		superiorDouble.setRoomView("시티뷰");
		roomTypeRepository.save(superiorDouble);
		
		RoomType superiorTwin = new RoomType();
		superiorTwin.setRoomType("Superior Twin");
		superiorTwin.setBedSize("2 싱글");
		superiorTwin.setPersons(2);
		superiorTwin.setPrice(67200);
		superiorTwin.setRoomSize("28㎡");
		superiorTwin.setRoomView("시티뷰");
		roomTypeRepository.save(superiorTwin);
		
		RoomType deluxeDouble = new RoomType();
		deluxeDouble.setRoomType("Deluxe Double");
		deluxeDouble.setBedSize("더블");
		deluxeDouble.setPersons(4);
		deluxeDouble.setPrice(75600);
		deluxeDouble.setRoomSize("31.4㎡");
		deluxeDouble.setRoomView("시티뷰");
		roomTypeRepository.save(deluxeDouble);
		
		RoomType deluxeTwin = new RoomType();
		deluxeTwin.setRoomType("Deluxe Twin");
		deluxeTwin.setBedSize("1 더블 + 1 싱글");
		deluxeTwin.setPersons(4);
		deluxeTwin.setPrice(75600);
		deluxeTwin.setRoomSize("31.4㎡");
		deluxeTwin.setRoomView("파크뷰");
		roomTypeRepository.save(deluxeTwin);
		
		RoomType juniorSuite = new RoomType();
		juniorSuite.setRoomType("Junior Suite");
		juniorSuite.setBedSize("1 퀸 + 1 싱글");
		juniorSuite.setPersons(6);
		juniorSuite.setPrice(175000);
		juniorSuite.setRoomSize("44.9㎡");
		juniorSuite.setRoomView("파크뷰");
		roomTypeRepository.save(juniorSuite);
		
		RoomType royalSuite = new RoomType();
		royalSuite.setRoomType("Royal Suite");
		royalSuite.setBedSize("1 킹 + 1 싱글");
		royalSuite.setPersons(6);
		royalSuite.setPrice(390000);
		royalSuite.setRoomSize("85.9㎡");
		royalSuite.setRoomView("파크뷰");
		roomTypeRepository.save(royalSuite);
	}
	
}
