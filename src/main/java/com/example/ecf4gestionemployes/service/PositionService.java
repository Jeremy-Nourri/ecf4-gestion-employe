package com.example.ecf4gestionemployes.service;

import com.example.ecf4gestionemployes.model.Position;
import com.example.ecf4gestionemployes.repository.PositionRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService() {
        this.positionRepository = new PositionRepository();
    }

    public Position savePosition(Position position) {
        positionRepository.add(position);
        return position;
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id);
    }

    public Position getPositionByTitle(String title) {
        return positionRepository.findByTitle(title);
    }

    public void updatePosition(Position position) {
        positionRepository.update(position);
    }

    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id);
        if (position == null) {
            throw new RuntimeException("Position not found");
        }
        boolean isDeleted = positionRepository.delete(id);
        if (!isDeleted) {
            throw new RuntimeException("Position not deleted");
        }
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }


}
