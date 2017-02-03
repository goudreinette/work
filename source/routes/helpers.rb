helpers do
  def pluralize n, string
    "#{n} #{n == 1 ? string.singularize : string.pluralize}"
  end

  def money n
    "€#{format '%.2f', n || 0}"
  end

  def date date
    date.strftime('%A %e %B')
  end

  def duration n
    seconds = if n.is_a? Numeric or n.nil? then n.to_i else n.duration_in_seconds.to_i end
    [seconds / 3600, seconds / 60 % 60, seconds % 60]
      .map { |t| t.to_s.rjust(2,'0') }.join(':')
  end

  def cost_calculation job
    case job.pricing_type
    when 'fixed'
      "#{money job.cost} / #{job.duration} = #{money (job.pricing_value / job.duration_in_hours)}/hour"
    when 'hourly'
      "#{money job.pricing_value} * #{job.duration} = #{money job.cost}"
    end
  end

  def job_cost job
    "#{job.pricing_type.capitalize}: #{cost_calculation job}"
  end

  def session_span session
    "#{session.start_date.strftime '%a %e %B  %H:%M'}
     -
     #{session.end_date&.strftime('%H:%M') || 'now'}"
  end
end
