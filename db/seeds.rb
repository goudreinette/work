data = YAML.load_file('db/seeds.yml')

User.create(data['users'])
Client.create(data['clients'])
Job.create(data['jobs'])
Invoice.create(data['invoices'])

p data
