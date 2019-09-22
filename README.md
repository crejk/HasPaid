# HasPaid
Check if the player owns a premium minecraft account purchased from mojang.

## Limitations
Because of the Mojang API ratelimit, we can send only 600 requests every 10 minutes.

## How it works
Every second, up to 100 usernames will be verified using the mojang api in a single request.

### why every second?
600 requests / 10 minutes = 60 requests per minute -> request per second.

### Why max 100 nicknames per request
Can't send more than that.

## TODO
- repository (mysql)
- key (must have key to get response from api)

## Endpoints

| Method                                | Query parameters      | Success status codes   | Error status codes |
| ------------------------------------- | --------------------- | ---------------------  | ------------------ |
| **GET  /haspaid?name={name}**         | name                  | 200                    | 404                |

## Response samples

### `GET /haspaid?name=crejk`
```json
true
```

## Usage
```java
URL url = new URL("http://localhost:8080/haspaid?name=crejk");
BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
System.out.println("Player is: " + (Boolean.valueOf(reader.readLine()) ? "cool" : "vocan"));
```
