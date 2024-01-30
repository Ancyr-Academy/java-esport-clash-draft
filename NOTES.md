# Few notes about the project

- Cascading delete is not implemented. One cannot delete a player without first
  deleting the teams in which they belong.
- We want the TeamViewModel.Member to show the name of the member. We first do
  this by doing redundants requests, it will open the door for CQRS.