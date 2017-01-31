user = User.create(
  username: 'demo',
  password: 'demo'
)

client = Client.create(
  user_id: user.id,
  name: 'Test Client',
  address: 'SomeStreet 12',
  city: 'New York',
  postcode: '1234AA'
)

job = Job.create(
  user_id: user.id,
  client: client,
  name: 'Test Job',
  pricing_type: 'fixed',
  pricing_value: 1000
)

Invoice.create(
  user_id: user.id,
  client: client,
  jobs: [job],
  date: Time.now
)
