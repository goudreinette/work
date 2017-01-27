class Session < ActiveRecord::Base
  belongs_to :job

  def duration_in_seconds
    end_date - start_date # Time.now.strftime "%H:%M:%S"
  end
end
