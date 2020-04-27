# Payments Micro-Service
This Micro Service is responsible for handling and managing all app payments

## Important notes:
API Key: pk_test_ThBTJz1fGcXyhAkLnESkAy2q008e3Mh6cG

Secret Key: sk_test_eMJA07HTa4ephNA3lR42CuHG00k6gjNgTz


We must have a way of unify users with their user account in the users micro-service (ie: email or userId)

## REST API
We must have the following exposed Routes:
- Get payment status for a given user
- Update user payments card
- Create a new product subscription
- Update user product subscription
- Delete a user subscription
- Expose a WebHook Endpoint (or multiple) to interact with Stripe Payment Gateway (ie: be notified when a payment failed, or succeeded). When it succeeds -> update the payment_expire_date field to the next month. If it fails (set the payment_status to failed or so

#### Note: Please pay attention to the token expiration. We need to have an INVALIDATED_TOKENS table so we can keep track of the invalidated token as well as invalidate an existing one whenever we want to.

## Environment
This will be deployed on a Kubernetes environment. Since it communicates with an external API all it needs to know is the Stripe Dev & Production endpoints. As well as their credentials.

## WebHooks
Expose endpoints for the interaction from Stripe whenever a payment succeeds or fails. Please keep in mind that only authorized requests can pass the security layer.

### Failed Request to WebHook
#### /payments-gateway/api/webhooks/charge/failed

{
  "created": 1326853478,
  "livemode": false,
  "id": "evt_00000000000000",
  "type": "charge.failed",
  "object": "event",
  "request": null,
  "pending_webhooks": 1,
  "api_version": null,
  "data": {
    "object": {
      "id": "ch_00000000000000",
      "object": "charge",
      "amount": 100,
      "amount_refunded": 0,
      "application": null,
      "application_fee": null,
      "application_fee_amount": null,
      "balance_transaction": "txn_00000000000000",
      "billing_details": {
        "address": {
          "city": null,
          "country": null,
          "line1": null,
          "line2": null,
          "postal_code": null,
          "state": null
        },
        "email": null,
        "name": null,
        "phone": null
      },
      "calculated_statement_descriptor": null,
      "captured": false,
      "created": 1587998901,
      "currency": "eur",
      "customer": null,
      "description": "My First Test Charge (created for API docs)",
      "disputed": false,
      "failure_code": null,
      "failure_message": null,
      "fraud_details": {
      },
      "invoice": null,
      "livemode": false,
      "metadata": {
      },
      "on_behalf_of": null,
      "order": null,
      "outcome": null,
      "paid": false,
      "payment_intent": null,
      "payment_method": "card_00000000000000",
      "payment_method_details": {
        "card": {
          "brand": "visa",
          "checks": {
            "address_line1_check": null,
            "address_postal_code_check": null,
            "cvc_check": "pass"
          },
          "country": "US",
          "exp_month": 8,
          "exp_year": 2021,
          "fingerprint": "vKOjE0RaVBZIO0em",
          "funding": "credit",
          "installments": null,
          "last4": "4242",
          "network": "visa",
          "three_d_secure": null,
          "wallet": null
        },
        "type": "card"
      },
      "receipt_email": null,
      "receipt_number": null,
      "receipt_url": "https://pay.stripe.com/receipts/acct_1GcY5lEwklqNZqKk/ch_1GcYFtEwklqNZqKkwqrT8RHa/rcpt_HAu4gepSBwCwg5CtMwb515d3lqJRGFW",
      "refunded": false,
      "refunds": {
        "object": "list",
        "data": [
        ],
        "has_more": false,
        "url": "/v1/charges/ch_1GcYFtEwklqNZqKkwqrT8RHa/refunds"
      },
      "review": null,
      "shipping": null,
      "source_transfer": null,
      "statement_descriptor": null,
      "statement_descriptor_suffix": null,
      "status": "failed",
      "transfer_data": null,
      "transfer_group": null
    }
  }
}

#### /payments-gateway/api/webhooks/charge/succeeded
{
  "created": 1326853478,
  "livemode": false,
  "id": "evt_00000000000000",
  "type": "charge.succeeded",
  "object": "event",
  "request": null,
  "pending_webhooks": 1,
  "api_version": null,
  "data": {
    "object": {
      "id": "ch_00000000000000",
      "object": "charge",
      "amount": 100,
      "amount_refunded": 0,
      "application": null,
      "application_fee": null,
      "application_fee_amount": null,
      "balance_transaction": "txn_00000000000000",
      "billing_details": {
        "address": {
          "city": null,
          "country": null,
          "line1": null,
          "line2": null,
          "postal_code": null,
          "state": null
        },
        "email": null,
        "name": null,
        "phone": null
      },
      "calculated_statement_descriptor": null,
      "captured": false,
      "created": 1587999301,
      "currency": "eur",
      "customer": null,
      "description": "My First Test Charge (created for API docs)",
      "disputed": false,
      "failure_code": null,
      "failure_message": null,
      "fraud_details": {
      },
      "invoice": null,
      "livemode": false,
      "metadata": {
      },
      "on_behalf_of": null,
      "order": null,
      "outcome": null,
      "paid": true,
      "payment_intent": null,
      "payment_method": "card_00000000000000",
      "payment_method_details": {
        "card": {
          "brand": "visa",
          "checks": {
            "address_line1_check": null,
            "address_postal_code_check": null,
            "cvc_check": "pass"
          },
          "country": "US",
          "exp_month": 8,
          "exp_year": 2021,
          "fingerprint": "vKOjE0RaVBZIO0em",
          "funding": "credit",
          "installments": null,
          "last4": "4242",
          "network": "visa",
          "three_d_secure": null,
          "wallet": null
        },
        "type": "card"
      },
      "receipt_email": null,
      "receipt_number": null,
      "receipt_url": "https://pay.stripe.com/receipts/acct_1GcY5lEwklqNZqKk/ch_1GcYMLEwklqNZqKkHveRtgEx/rcpt_HAuAQ7PWHzjKk0mGiQfRqDdMsWtLHOM",
      "refunded": false,
      "refunds": {
        "object": "list",
        "data": [
        ],
        "has_more": false,
        "url": "/v1/charges/ch_1GcYMLEwklqNZqKkHveRtgEx/refunds"
      },
      "review": null,
      "shipping": null,
      "source_transfer": null,
      "statement_descriptor": null,
      "statement_descriptor_suffix": null,
      "status": "succeeded",
      "transfer_data": null,
      "transfer_group": null
    }
  }
}
