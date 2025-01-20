## Running the application locally

```shell
mvn clean package -DskipTests
```
```shell
docker-compose up --build 
```
### Note that Requests will run successfully for the first time as it's assumed that item name is unique, thus if you want ti rerun APIs change Item names or rerun the application.

## Curls Requests and Responses
### Create Stc Space Api Request
```bash
  curl --location --request POST 'http://localhost:8080/create-stc-space'
```
### Create Space Api Response
```bash
{
    "id": 1,
    "type": "Space",
    "name": "stc-assessments",
    "permissionGroup": {
        "id": 1,
        "groupName": "admin"
    }
}
```

### Create Backend Folder Api Request
```bash
  curl --location --request POST 'http://localhost:8080/create-backend-folder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userEmail": "edit_access_user"
}'
```
### Create Backend Folder Response
```bash
{
    "id": 2,
    "type": "Folder",
    "name": "Backend",
    "permissionGroup": {
        "id": 1,
        "groupName": "admin"
    }
}
```
### Create Assessment File Api Request
```bash
  curl --location --request POST 'http://localhost:8080/create-backend-folder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userEmail": "edit_access_user"
}'
```
### Create Assessment File Api Response
```bash
{
    "id": 3,
    "type": "File",
    "name": "assessment.pdf",
    "permissionGroup": {
        "id": 1,
        "groupName": "admin"
    }
}
```

## Stopping the application
```shell
docker-compose down 
```
