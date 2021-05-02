# employee-api

## Steps to start the API:

- Run application
- Enjoy!

### Insert a record in the database:
Use the following curl (can be in Postman):

```CURL
curl --location --request POST 'http://localhost:8091/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "names": "Tony",
    "surname": "Stark",
    "documentType": "CC",
    "documentNumber": "1037",
    "dateOfBirth": "1970-11-28",
    "dateOfJoiningCompany": "2019-05-01",
    "position": "Filántropo",
    "salary": 1000000000000
}'
```

### Get the saved record
Use the following curl

```CURL
curl --location --request GET 'http://localhost:8091/employee?documentType=CC&documentNumber=1037'
```
