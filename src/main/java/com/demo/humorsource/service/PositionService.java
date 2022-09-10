package com.demo.humorsource.service;

import com.demo.humorsource.dao.PositionDao;
import com.demo.humorsource.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionDao positionDAO;

    public List<Position> getAllPosition() {
        return positionDAO.findAll();
    }

    public Position findById(int id) {
        return positionDAO.findById(id).get();
    }

    public Position createPosition(String name, int dept) {
        Position position = new Position();
        position.setId(0);
        position.setName(name);
        position.setDeptId(dept);
        return positionDAO.save(position);
    }

    public Position updatePosition(int id, String name, int dept) {
        Position position = new Position();
        position.setId(id);
        position.setName(name);
        position.setDeptId(dept);
        return positionDAO.save(position);
    }

    public void deletePosition(int id) {
        positionDAO.deleteById(id);
    }

}
