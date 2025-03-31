Needed endpoints:

/api/users: 
####
POST /create - Create user, will be replaced by /api/auth/register eventually

GET /getall - getAll users

GET /{id} - get specific user

DELETE /{id} - delete specific user

PUT /{id} - update specific users info
####



/api/project:
####
GET /getprojects - list all projects

GET /{id} - get specific project

POST /createProject - Create new project

PUT /{id} - Update specific projects info

DELETE /{id} - delete specific project

GET /{id}/issues - Get all issues within a project

POST /{id}/issues - create issues in a specific project

GET /search?name= - Endpoint for searching for project by name / attributes

Ideally i'd also want something like GET /{id}/activity
#####
/api/auth:

POST /login - Login endpoint

POST /register - register account 

POST /reset-password or /forgot-password - resetting lost password


