package com.project.ProjectApp;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {
    private MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public ResponseEntity<List<Mission>> getMissions() {
        return new ResponseEntity<>(missionService.getMissions(), HttpStatus.OK);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Mission> getMission(@PathVariable String month) {
        Mission mission = missionService.getMissions(month);
        if (mission != null) {
            return new ResponseEntity<>(mission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addMissions(@RequestBody Mission mission) {
        boolean isMissionAdded = missionService.addMissions(mission);
        if (isMissionAdded) {
            return new ResponseEntity<>("Added mission successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Addition of mission failed", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMission(@PathVariable Long id, @RequestBody Mission updatedMission) {
        boolean isMissionUpdated = missionService.updateMission(id, updatedMission);
        if (isMissionUpdated) {
            return new ResponseEntity<>("Updated mission successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Updation of mission failed", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMission(@PathVariable Long id) {
        boolean isMissionDeleted = missionService.deleteMission(id);
        if (isMissionDeleted) {
            return new ResponseEntity<>("Deleted mission successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deletion of mission failed", HttpStatus.NOT_FOUND);
        }

    }
}
