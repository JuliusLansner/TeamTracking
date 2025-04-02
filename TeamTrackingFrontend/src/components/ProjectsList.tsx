import {  useState } from "react";
import ProjectForm from "./ProjectForm";

/*const mockProjects = [
    { id: 1, title: "Project 1" },
    { id: 2, title: "Project 2" },
    { id: 3, title: "Project 3" },
]*/

function ProjectsList(){

    /*const [projects, setProjects] = useState(mockProjects)*/

    const [projects, setProjects] = useState<{ id: number; title: string }[]>([]);

    function handleAddProject(newProject: {id: number; title: string }) {
        setProjects([...projects, newProject])
    }


    return (
        <div>
            <ProjectForm onAddProject={handleAddProject} />
            <ul className="projects">
                {projects.map((project) => (
                    <li key={project.id}>
                        {project.title}
                    </li>
                ))}
            </ul>
        </div>
    )

}

export default ProjectsList;