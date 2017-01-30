namespace "/invoices" do
  get do
    @prefix = 'invoices'
    @cards = Invoice.all

    @header = lambda(&:no)
    @meta = lambda(&:facts)

    erb :"partials/cards"
  end
end
