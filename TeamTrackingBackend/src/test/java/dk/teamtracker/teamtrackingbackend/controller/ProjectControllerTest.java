package dk.teamtracker.teamtrackingbackend.controller;

import dk.teamtracker.teamtrackingbackend.entity.Project;
import dk.teamtracker.teamtrackingbackend.repository.IssueRepo;
import dk.teamtracker.teamtrackingbackend.repository.ProjectRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    void getProjectById() throws Exception{
        Long testId = 1L;
        Project project1 = new Project(testId,"proj1","desc1");


        Mockito.when(projRepo.findById(testId)).thenReturn(Optional.of(project1));

        mockMvc.perform(get("/api/project/"+testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.projectName").value("proj1"));


    }

    @Test
    void getProjectById_notFound() throws Exception{
        Long testId = 2L;
        Mockito.when(projRepo.findById(testId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/project"+testId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateProject() throws Exception{
        Long testId = 1L;
        Project newProject = new Project(testId,"proj1","desc1");
        Project savedProject = new Project(testId,"proj1","desc1");

        Mockito.when(projRepo.save(Mockito.any(Project.class))).thenReturn(savedProject);

        String requestJson = """
                {
                "id":"1",
                "name": "proj1",
                "description": "desc1"
                }
                """;

        mockMvc.perform(post("/api/project/create")
                        .with(csrf())
                        .with(user("testUser").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testId))
                .andExpect(jsonPath("$.projectName").value("proj1"))
                .andExpect(jsonPath("$.description").value("desc1"));

    }
}