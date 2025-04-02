package dk.teamtracker.teamtrackingbackend.controller;

import dk.teamtracker.teamtrackingbackend.entity.Issue;
import dk.teamtracker.teamtrackingbackend.entity.Project;
import dk.teamtracker.teamtrackingbackend.repository.IssueRepo;
import dk.teamtracker.teamtrackingbackend.repository.ProjectRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectRepo projRepo;
    @Autowired
    IssueRepo issueRepo;
    @Autowired
    private ProjectRepo projectRepo;

    //Project endpoints
    @GetMapping("/getall")
    public List<Project> getAllProjects(){
        return projRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id){
        return projRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project savedProject = projRepo.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        return projRepo.findById(id)
                .map(deleteProj -> {projRepo.delete(deleteProj);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // issue endpoints
    @GetMapping("{projectId}/issues/all")
    public ResponseEntity<List<Issue>> getAllIssues(@PathVariable Long projectId){
        return projRepo.findById(projectId)
                .map(project -> ResponseEntity.ok(project.getIssue()))
                .orElse(ResponseEntity.notFound().build());

    }
    @PostMapping("{projectId}/issues/new")
    public ResponseEntity<Issue> createIssue(@PathVariable Long projectId,@RequestBody Issue newIssue){
        return projectRepo.findById(projectId)
                .map(project ->  {
                    newIssue.setProject(project);
                    Issue saveIssue = issueRepo.save(newIssue);
                    return ResponseEntity.status(HttpStatus.CREATED).body(saveIssue);
                })
                .orElse(ResponseEntity.notFound().build());
    }



}
