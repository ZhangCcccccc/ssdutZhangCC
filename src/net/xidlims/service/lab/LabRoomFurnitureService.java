package net.xidlims.service.lab;

import java.util.List;

import net.xidlims.domain.LabRoomFurniture;

/**
 * Spring service that handles CRUD requests for LabRoomFurniture entities
 * 
 */
public interface LabRoomFurnitureService {
		
	
	
	public List<LabRoomFurniture> findLabRoomFurnitureByRooId(Integer id);
	
	public void deleteLabRoomFurniture(LabRoomFurniture labRoomFurniture);
	
	public void saveLabRoomFurniture(LabRoomFurniture labRoomFurniture);
}