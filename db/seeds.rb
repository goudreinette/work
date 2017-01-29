Client.create name: 'Test Client', address: 'SomeStreet 12', city: 'New York', postcode: '1234AA'
Job.create client: Client.first, name: 'Test Job', pricing_type: 'fixed', pricing_value: 1000
