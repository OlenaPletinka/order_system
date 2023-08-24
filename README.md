# order_system
ordering system
Context:

It is a part of the ConcertHall system, we will work with Payment Processing, Order Completion, and Reservation Confirmation.

Payments - we receive events from 3-th party vendors, where payment was performed. We start with 2 vendors, but in the future, we would like to add 20+. 
Orders - get them from Queue. For each Payment, we should get an Order record before. We get it from 3rd party Vendor TicketMaster with the requested 
seat Reservation - manage seats, let's say we have 1000 seats. If a seat already exists in the system, we should cancel the Order.

CUJ 1: - Critical User Journey 3-th part payment systems A, and B report successful payment(payment confirmation) to the system. A - using REST, 
B - using a message queue.

CUJ 2: System stores all payments in specific DB

CUJ 3: Once the System gets payment confirmation, the system should set the Order to Completed status.

CUJ 4: Once Payment and Order are completed, we will need to Book the seat at the Reservation entity

CUJ 5: If a Reservation Seat is not available, we will need to cancel the Order and Payment in our DB and Stream event to Queue XX

NFR: 
Modifiability: Adding a new payment system is easy 
Availability: The system is highly available, with at least 99.99% of the time 
Performance: 
Async connection: event processing latency - 5s, throughput - 200 req/s 
Scalability: The system is able to horizontally scale Possible bottlenecks are defined 
Security: Data is encrypted in the rest 
Fault tolerance: Source of failures defined and mitigated We need to minimize system failure and data loss 
Data integrity: There is audit logging for order status update Monitoring We can troubleshoot the logic through services
