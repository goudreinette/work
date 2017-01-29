require "sinatra"
require "sinatra/namespace"
require "sinatra/activerecord"
require "mysql2"
require "timerizer"
require "time_difference"


local_db = {
    adapter:  'mysql2', # or 'postgresql' or 'sqlite3'
    database: 'work',
    username: 'root',
    password: '',
    socket: '/opt/lampp/var/mysql/mysql.sock'
}

set :database, ENV['DATABASE_URL'] || local_db

require_relative "source/routes.rb"
require_relative "source/models.rb"
