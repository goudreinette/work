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

  def html_date date
    date.strftime('%Y-%m-%d')
  end

  def html_datetime date
    date.strftime('%Y-%m-%d %H:%M')
  end

  def duration n
    seconds = if n.is_a? Numeric or n.nil? then n.to_i else n.duration_in_seconds.to_i end
    [seconds / 3600, seconds / 60 % 60, seconds % 60]
      .map { |t| t.to_s.rjust(2,'0') }.join(':')
  end

  def cost_calculation project
    case project.pricing_type
    when 'fixed'
      "#{money project.cost} / #{project.duration} = #{money (project.pricing_value / project.duration_in_hours)}/hour"
    when 'hourly'
      "#{money project.pricing_value} * #{project.duration} = #{money project.cost}"
    end
  end

  def project_cost project
    "#{project.pricing_type.capitalize}: #{cost_calculation project}"
  end

  def session_span session
    "#{session.start_date.strftime '%a %e %B  %H:%M'}
     -
     #{session.end_date&.strftime('%H:%M') || 'now'}"
  end
end
