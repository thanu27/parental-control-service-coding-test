# parental-control-service-coding-test

**Prevent access to movies based on parental control level**
As a customer I don’t want my account to be able to access movies that have a higher parental control level than my
current preference setting.

**Parental Control Levels:**
U, PG, 12, 15, 18 (where U is the least restrictive and 18 is the most restrictive)

ParentalControlService accepts as input the customer’s *parental control level
preference* and a *movie id*. If the customer is eligible to watch the movie then the *ParentalControlService* should return true else false.


