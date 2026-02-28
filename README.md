# GolpeServer

Server-side application for the Golpe card game - a UNO-style multiplayer card game between bots.

## About

GolpeServer is the backend that handles:
- WebSocket connections from clients (port 1598)
- Game state management and rules
- Player registration and turn coordination
- Card deck management and shuffling

## Prerequisites

- Java 21
- Maven 3.9+

## Running the Server

```bash
cd GolpeServer
mvn clean javafx:run
```

The server will start on `localhost:1598` and display a JavaFX window for monitoring.

## Testing

Run all tests:
```bash
mvn test
```

Run a specific test class:
```bash
mvn test -Dtest=CardManagerTest
```

Run a single test method:
```bash
mvn test -Dtest=CardManagerTest#canLayCardTrueTest
```

## Building

```bash
mvn clean package
```

## Game Rules

Golpe is a UNO-style card game where:
1. Players are dealt 7 cards each
2. Players take turns laying cards that match the top card's color or value
3. Special cards (Block, PlusOne, PlusTwo) have unique effects
4. First player to empty their hand wins

## Development

The server uses JSON packets over WebSocket for client communication. See the `websocket/packets` package for packet types.
