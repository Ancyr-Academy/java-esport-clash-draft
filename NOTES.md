# Few notes about the project

- Cascading delete is not implemented. One cannot delete a player without first
  deleting the teams in which they belong.
- We want the TeamViewModel.Member to show the name of the member. We first do
  this by doing redundants requests, it will open the door for CQRS.
- Domain Tasks with existing features
    - Get all teams
    - Get a schedule by day
    - Get all schedules within a week

## Exercises

### Best of 3

Enhance matches by allowing them to be a best-of-3.
The idea is to rename "Match" by "Encounter".
So that in the Schedule, we have a morning Encounter and an afternoon Encounter.
Instead of a morning Match and an afternoon Match.

Each Encounter has at most 3 Matches.
For now, each Match will have the following informations :

- Kill/Death/Assists of each team
- Winner of the match

Use cases :

1) Add a match to the encounter. Specify the outcome of the match.
   Note : an encounter can have at most 3 matches.
2) Update a match by specifying its ID.
3) Remove a match by specifying its ID.
4) Get the informations of a specific Encounter including the outcomes.

### Match Events

Administrators can record events of a match.
Each events must also include the timestamp of the event.
A timestamp consist of the number of seconds after the beginning of the match.
It is NOT a unix timestamp.

This feature imply to update the Match entity and add two dates :

- When the match started
- When the match ended

It will also change the contract of the Match which will now have a lifecycle :

- The match is started
- Events are recorded
- The match ended

Once the match ended, it is not possible to record events anymore.

Here are the possible events :

#### Kill

Record which user killed which other user.
Add the amount of gold earned from this kill.

#### Turret Destroyed

Record which user destroyed a turret.

#### Inhibitor Destroyed

Record which user destroyed an inhibitor.

#### Ace

Record which team killed all the other players.

#### Double Kill

Record which player killed 2 players.
Also include Triple Kill, Quadrakill and Pentakills.

#### Drake

Record which team killed the drake.

#### Baron

Record which team killed the baron.

Use cases :

1) Begin a match, specify the ID of the encounter
2) Record an event (the administrator provides the timestamp)
3) Get an event by ID
4) Remove an event
5) End a match
6) Get the informations about a match

Note : there's a lot to learn from this exercise (Domain Modeling & OOP-wise),
especially if you tend toward
CQRS & Event-Sourcing. A user fetching informations about a match may want to
know

- The amount of Ace or Double Kills
- Which players earned the most gold from kills
- Which team killed the most drakes

And how about gathering informations about all the matches of a team ?
Or all the matches of a week ?

Lot of potential.