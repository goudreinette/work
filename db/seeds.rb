data = YAML.load_file('db/seeds.yml')

User.create(data['users'])
Client.create(data['clients'])
Project.create(data['projects'])
Invoice.create(data['invoices'])
