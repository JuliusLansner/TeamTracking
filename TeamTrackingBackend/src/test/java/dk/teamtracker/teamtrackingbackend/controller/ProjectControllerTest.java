package dk.teamtracker.teamtrackingbackend.controller;

import dk.teamtracker.teamtrackingbackend.entity.Project;
import dk.teamtracker.teamtrackingbackend.repository.IssueRepo;
import dk.teamtracker.teamtrackingbackend.repository.ProjectRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IssueRepo issueRepo;

    @MockitoBean
    private ProjectRepo projRepo;


    @Test
    void getAllProjectsTest() throws Exception{
        Project project1 = new Project(1L,"proj 1","desc1");
        Project project2 = new Project(2L,"proj 2","desc2");

        List<Project> projectList = Arrays.asList(project1,project2);

        Mockito.when(projRepo.findAll()).thenReturn(projectList);

        mockMvc.perform(get("/api/project/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].projectName").value("proj 1"))
                .andExpect(jsonPath("$[1].projectName").value("proj 2"));

    }

}