json.array!(@transactions) do |transaction|
  json.extract! transaction, :id, :kind, :category_id, :payday, :amount, :description
  json.url transaction_url(transaction, format: :json)
end
