
interface SingleProjectProps {
    project: { id: number; title: string };
}

const SingleProject: React.FC<SingleProjectProps> = ({ project }) => {
    return (
        <div className="popup">
            <div className="popup-content">
                <h2>{project.title}</h2>
            </div>
        </div>
    )
}

export default SingleProject;