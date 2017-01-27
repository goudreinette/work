module Duration
  def duration
    seconds = duration_in_seconds.to_i - 3600
    [seconds / 3600, seconds / 60 % 60, seconds % 60]
      .map { |t| t.to_s.rjust(2,'0') }.join(':')
  end
end
