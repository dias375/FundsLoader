# FundsLoader 💸
## Financial Market Funds Loading API (Java / Spring Boot / SQL).

In finance, "velocity limits" are necessary for security reasons. In this task, you'll create a Java Spring boot application
that accepts or declines attempts to load funds into customers' accounts in real-time. Each attempt to load funds will come as a
single-line JSON payload, structured as follows:
```
{
"id": "1234",
"customer_id": "1234",
"load_amount": "$123.45",
"time": "2018-01-01T00:00:00Z"
}
```

Each customer is subject to three limits:
* A maximum of $5,000 can be loaded per day
* A maximum of $20,000 can be loaded per week
* A maximum of 3 loads can be performed per day, regardless of amount

As such, a user attempting to load $3,000 twice in one day would be declined on the second attempt, as would a user attempting
to load $400 four times in a day. For each load attempt, you should return a JSON response indicating whether the fund load was
accepted based on the user's activity, with the structure:
```
{
"id": "1234",
"customer_id": "1234",
"accepted": true
}
```

Input arrives in ascending chronological order and that if a load ID is observed more than once for a particular user, all but the
first instance can be ignored (i.e. no response given). Each day is considered to end at midnight UTC, and weeks start on Monday
(i.e. one second after 23:59:59 on Sunday).
