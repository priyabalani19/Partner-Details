# Partner-Details
Partner Invite Details Springboot Project
You’re in charge of writing the logic to send invitations to a special two-day event in each country for Mycompany’s partners in those countries. We need to find the dates that’ll work best based on survey results that partners have sent in and determine how many people can attend.
You’re provided with an API that gives you a list of partners, their countries, and which dates they’re available in ISO 8601 format.
Another team will send out the invitations, but you need to tell them when we should host the event and who should attend by POSTing to an API.
The date you send in for the country should be the starting date of the two-day period where most partners can make it for both days in a row. In case of multiple dates with the same number of partners, pick the earlier date. If there are no two days in a row when any partners can make it, return null.
API Docs and Example
To get the list of partners, send an HTTP GET to:
Here’s a sample request with 10 partners: 
```{
    "partners": [
        {
            "firstName": "Darin",
            "lastName": "Daignault",
            "email": "ddaignault@mycompany.com",
            "country": "United States",
            "availableDates": [
                "2017-05-03",
                "2017-05-06"
            ]
        },
        {
            "firstName": "Crystal",
            "lastName": "Brenna",
            "email": "cbrenna@mycompany.com",
            "country": "Ireland",
            "availableDates": [
                "2017-04-27",
                "2017-04-29",
                "2017-04-30"
            ]
        },
        {
            "firstName": "Janyce",
            "lastName": "Gustison",
            "email": "jgustison@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-29",
                "2017-04-30",
                "2017-05-01"
            ]
        },
        {
            "firstName": "Tifany",
            "lastName": "Mozie",
            "email": "tmozie@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-28",
                "2017-04-29",
                "2017-05-01",
                "2017-05-04"
            ]
        },
        {
            "firstName": "Temple",
            "lastName": "Affelt",
            "email": "taffelt@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-28",
                "2017-04-29",
                "2017-05-02",
                "2017-05-04"
            ]
        },
        {
            "firstName": "Robyn",
            "lastName": "Yarwood",
            "email": "ryarwood@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-29",
                "2017-04-30",
                "2017-05-02",
                "2017-05-03"
            ]
        },
        {
            "firstName": "Shirlene",
            "lastName": "Filipponi",
            "email": "sfilipponi@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-30",
                "2017-05-01"
            ]
        },
        {
            "firstName": "Oliver",
            "lastName": "Majica",
            "email": "omajica@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-28",
                "2017-04-29",
                "2017-05-01",
                "2017-05-03"
            ]
        },
        {
            "firstName": "Wilber",
            "lastName": "Zartman",
            "email": "wzartman@mycompany.com",
            "country": "Spain",
            "availableDates": [
                "2017-04-29",
                "2017-04-30",
                "2017-05-02",
                "2017-05-03"
            ]
        },
        {
            "firstName": "Eugena",
            "lastName": "Auther",
            "email": "eauther@mycompany.com",
            "country": "United States",
            "availableDates": [
                "2017-05-04",
                "2017-05-09"
            ]
        }
    ]
}```


For the list of partners above, the proper API response to send would look like this:
```{
    "countries": [
        {
            "attendeeCount": 1,
            "attendees": [
                "cbrenna@mycompany.com"
            ],
            "name": "Ireland",
            "startDate": "2017-04-29"
        },
        {
            "attendeeCount": 0,
            "attendees": [],
            "name": "United States",
            "startDate": null
        },
        {
            "attendeeCount": 3,
            "attendees": [
                "omajica@mycompany.com",
                "taffelt@mycompany.com",
                "tmozie@mycompany.com"
            ],
            "name": "Spain",
            "startDate": "2017-04-28"
        }
    ]
}```
API Guidelines
If your answer is correct, the API will return 200 OK. If the request is malformed or incorrect, the API will return 400 along with a message indicating if the response is of the wrong structure or incorrect.
If you get a 5xx response, let us know and we’ll help you out.
