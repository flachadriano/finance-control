class CreateTransactions < ActiveRecord::Migration
  def change
    create_table :transactions do |t|
      t.integer :kind
      t.references :category, index: true, foreign_key: true
      t.date :payday
      t.decimal :amount
      t.text :description

      t.timestamps null: false
    end
  end
end
