namespace "/invoices" do
  get do
    @prefix = 'invoices'
    @cards = Invoice.all

    @header = lambda {|invoice| invoice.no }
    @meta = lambda {|invoice| invoice.facts }

    erb :"partials/cards"
  end
end
