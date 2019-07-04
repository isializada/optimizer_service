# Optimizer Tool v0.1

The Optimizer Service implemented for optimize workforce. There is only a optimizer for cleaning partner in initial version of tool.


## Cleaning partner

There are Senior and Junior Cleaners who will send to structure for clean but there always needs to be at least one Senior cleaner at every structure to lead the juniors. In Addition, senior cleaner can clean more capacity than junior. The tool will find optimal numbers of juniors and seniors for minimize the overcapacity of cleaning.

### How is works?

REST API has a endpoint for find optimal number of employees.
After run application you can send request to following endpoint.

**POST: http://localhost:8082/optimize/v0.1/cleaning/findOptimalNumbers**

**Request**: Given an array of structure sizes (in no. of rooms) as well as work capacities of Junior and Senior

Example for request Body:
```
{
  "rooms":[24,28],
  "senior": 11,
  "junior": 6
}
```
**Response**: Optimal numbers of Juniors and Seniors for every structure

Example for response.
```
[
    {
        "senior": 2,
        "junior": 1
    },
    {
        "senior": 2,
        "junior": 1
    }
]
```

### Unit Tests are implemented for optimize service, controller and requests 


