# Payments Micro-Service
This Micro Service is responsible for handling and managing all app payments

## Important notes:
We must have a way of unify users with their user account in the users micro-service (ie: email or userId)

## REST API
We must have the following exposed Routes:
- Get payment status for a given user
- Update user payments card
- Create a new product subscription
- Update user product subscription
- Delete a user subscription
- Expose a WebHook Endpoint (or multiple) to interact with Stripe Payment Gateway (ie: be notified when a payment failed, or succeeded). When it succeeds -> update the payment_expire_date field to the next month. If it fails (set the payment_status to failed or so

## WebHooks
Expose endpoints for the interaction from Stripe whenever a payment succeeds or fails. Please keep in mind that only authorized requests can pass the security layer.

#### Note: Please pay attention to the token expiration. We need to have an INVALIDATED_TOKENS table so we can keep track of the invalidated token as well as invalidate an existing one whenever we want to.

## Environment
This will be deployed on a Kubernetes environment. Since it communicates with an external API all it needs to know is the Stripe Dev & Production endpoints. As well as their credentials.
