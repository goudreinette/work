require "sinatra"
require "sinatra/namespace"
require "sinatra/activerecord"
require "sinatra/json"
require "mysql2"
require "yaml"
require "time_difference"
require "juxt"
require "prawn"
require "combine_pdf"


local_db = {
    adapter:  'mysql2', # or 'postgresql' or 'sqlite3'
    database: 'work',
    username: 'root',
    password: '',
    socket: '/opt/lampp/var/mysql/mysql.sock'
}

set :database, ENV['DATABASE_URL'] || local_db

database.default_timezone = :local

enable :sessions

require_relative "source/resource.rb"

register Resource

require_relative "source/routes.rb"
require_relative "source/models.rb"
require_relative "source/invoice_template.rb"
