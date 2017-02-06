class Template
  def self.render_to_pdf(invoice)
    file = "#{generated_path}/Invoice #{invoice.no} for #{invoice.client.name}.pdf"
    puts "Saving to '#{file}'..."

    Prawn::Document.generate(file) { |pdf| render(invoice, pdf) }

    combine(file)

    file
  end

  def self.combine(file)
    template = CombinePDF.load("#{templates_path}/default.pdf")
    rendered = CombinePDF.load(file)

    template.pages.zip(rendered.pages) { |t, r| t << r }
    template.save file
  end


  def self.root_path
    "#{Dir.pwd}/resources/pdf"
  end

  def self.generated_path
    "#{root_path}/generated"
  end

  def self.templates_path
    "#{root_path}/templates"
  end

  def self.fonts_path
    "#{root_path}/fonts"
  end
end

class DefaultTemplate < Template
  def self.path
    'default'
  end

  def self.fonts_path
    "#{super}/#{path}"
  end

  def self.text(pdf, text, options = {})
    x = -36 + options[:at][0]
    y = 805 - options[:at][1]

    options = options.merge({width: 200, height: 100, at: [x, y]})
    pdf.text_box text, options
  end

  def self.format_money(number)
    "€ %.2f" % number
  end

  def self.combined_address(client)
    ["\n#{client.address}", "\n#{client.postcode}", client.city].reject(&:blank?).join ', '
  end

  def self.recipient(client)
    "#{client.name}, #{combined_address client}"
  end

  def self.render(invoice, pdf)
    pdf.font_size 9
    pdf.font_families.update 'SourceSans' => {
      :normal      => "#{fonts_path}/SourceSansPro.ttf",
      :italic      => "#{fonts_path}/SourceSansPro-It.ttf",
      :bold        => "#{fonts_path}/SourceSansPro-Semibold.ttf",
      :bold_italic => "#{fonts_path}/SourceSansPro-SemiboldIt.ttf"
    }

    pdf.font "SourceSans"

    # items
    invoice.jobs.each_with_index do |job, index|
      text pdf, job.name,              			           at: [90, 360 + index * 56], style: :bold
      text pdf, format_money(job.cost), at: [450, 360 + index * 56]
    end

    text pdf, recipient(invoice.client),              at: [85, 121], leading: 4
    text pdf, invoice.no,                  					  at: [168, 247], size: 12
    text pdf, invoice.date.strftime("%d-%m-%Y"),      at: [85.5, 292]
    text pdf, invoice.client.name,           					at: [276, 292]
    text pdf, invoice.client.name,              			at: [414, 292]
    text pdf, format_money(invoice.subtotal),         at: [90, 550]
    text pdf, format_money(invoice.tax),              at: [159, 550]
    text pdf, format_money(invoice.total),            at: [385, 538], size: 18
  end
end
