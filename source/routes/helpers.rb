helpers do
  def money(n)
    "€#{format '%.2f', n}"
  end

  def date date
    date.strftime('%A %e %B')
  end
end
