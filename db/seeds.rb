client = Client.create(
  name: 'Test Client',
  address: 'SomeStreet 12',
  city: 'New York',
  postcode: '1234AA'
)

job = Job.create(
  client: client,
  name: 'Test Job',
  pricing_type: 'fixed',
  pricing_value: 1000
)

Invoice.create(
  client: client,
  jobs: [job],
  date: Time.now
)
