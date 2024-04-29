# airbnb

This is the 2nd project as a part of Revature training. The Backend techs are Java's Spring Boot, Data & Web. The Frontend is a React app generated with create-react-app with TypeScript's type checking.

## Relationships 

The Backend models the following relationships in data models and tables:
- An Owner has many Listings
- Listing has many Bookings and belongs to an Owner
- A Renter has many Bookings
- Booking belongs to a Listing and belongs to a Renter

### Owner
```
user_id (referred to as ownerId in data model)
email
password
username
```

### Listing
```
listing_id (referred to as listingId in data model)
owner_id
address
city
description
name
state
photos
```

### Renter
```
user_id (referred to as renterId in data model)
email
password
username
```

### Booking
```
booking_id (referred to as bookingId in data model)
listing_id
renter_id
end_date
start_date
status
```

# User Stories

### As a user (renter/owner), I should be able to view all listings.

GET /listings

## Renter

### As a renter, I should be able to create an account

POST /renters/register

Request body:
```
{
    "username": "renter",
    "password": "password",
    "email": "email@email.com"
}
```

### As a renter, I should be able to login/logout of my account

POST /renters/login

Request body:
```
{
    "username": "renter",
    "password": "password"
}
```

POST /renters/logout

### As a renter, I should be able to view my account details

GET /renters/{renterId}

Request headers:
(key) renter (value) renter's username

### As a renter, I should be able to create a Booking request for a Listing

POST /bookings

Request headers:
(key) renter (value) renter's username

Request body:
```
{
    "startDate": "04/25/24",
    "endDate": "05/1/24",
    "status": "pending",
    "listingId": 1,
    "renterId": 1
}
```

### As a renter, I should be able to view all Listings for which I sent a Booking request

GET /renters/{renterId}/listings

Request headers:
(key) renter (value) renter's username

## Owner

### As an owner, I should be able to create an account

POST /owners/register

Request body:
```
{
    "username": "owner",
    "password": "password",
    "email": "email@email.com"
}
```

### As an owner, I should be able to login/out of my account

POST /owner/login

Request body:
```
{
    "username": "owner",
    "password": "password"
}
```

POST /owners/logout

### As a owner, I should be able to view my account details

GET /owners/{ownerId}

Request headers:
(key) owner (value) owner's username

### As an owner, I should be able to create a new Listing

POST /listings

Request headers:
(key) owner (value) owner's username

Request body:
```
{
    "address": "address",
    "city": "city",
    "state": "state",
    "description": "description",
    "photos": [],
    "name": "name",
    "ownerId": 1
}
```

### As an owner, I should be able to view my Listings

GET /owners/{ownerId}/listings

### As an owner, I should be able to accept/reject Booking requests made on my Listings

TODO: Validate below for correctness after implementing

PATCH /bookings/{bookingId}

Request headers:
(key) owner (value) owner's username

Request body:
```
{
    "status": "accepted"
}
```