import {  useState } from "react";

interface ProjectFormProps {
    onAddProject: (project: { id: number; title: string }) => void;
}


function ProjectForm({ onAddProject }: ProjectFormProps){

    const [projectTitle, setProjectTitle] = useState("");

    function handleSubmit(e: React.FormEvent) {
        e.preventDefault();

        if (!projectTitle.trim()) return;

        const newProject = { id: Date.now(), title: projectTitle };
        onAddProject(newProject);

        setProjectTitle("");
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input 
                    type="text" 
                    placeholder="Insert project title" 
                    value={projectTitle} 
                    onChange={(e)=> setProjectTitle(e.target.value)}>
                </input>
                <button>Add project</button>
            </form>
        </div>
    )

} 

export default ProjectForm;