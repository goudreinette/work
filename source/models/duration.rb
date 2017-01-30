module Duration
  def duration
    seconds = duration_in_seconds.to_i
    [seconds / 3600, seconds / 60 % 60, seconds % 60]
      .map { |t| t.to_s.rjust(2,'0') }.join(':')
  end

  def duration_in_hours
    duration_in_seconds / 60
  end
end
