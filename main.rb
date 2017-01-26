require "sinatra"
require "active_record"
require "mysql2"
require "timerizer"
require "time_difference"

ActiveRecord::Base.establish_connection(
  adapter:  'mysql2', # or 'postgresql' or 'sqlite3'
  database: 'work',
  username: 'root',
  password: '',
  socket: '/opt/lampp/var/mysql/mysql.sock'
)

require_relative "source/routes.rb"
require_relative "source/models.rb"
