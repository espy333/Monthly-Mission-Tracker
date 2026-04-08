package com.project.ProjectApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private long nextId = 1L;
    @Autowired
    MissionRepository missionRepository;

    public MissionService() {
    }

    public List<Mission> getMissions() {
        return missionRepository.findAll();
    }

    public Mission getMissions(String month) {
        Optional<Mission> mission = missionRepository.findByMonthIgnoreCase(month);
        return mission.orElse(null);
    }

    public boolean addMissions(Mission mission) {
        if (mission != null) {
            mission.setId(nextId++);
            missionRepository.save(mission);
            return true;
        } else
            return false;
    }

    public boolean updateMission(Long id, Mission updatedMission) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isPresent()) {
            Mission missionToUpdate = mission.get();
            missionToUpdate.setMonth(updatedMission.getMonth());
            missionToUpdate.setDescription(updatedMission.getDescription());
            missionRepository.save(missionToUpdate);
            return true;
        }
        return false;
    }

    public boolean deleteMission(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isPresent()) {
            missionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

