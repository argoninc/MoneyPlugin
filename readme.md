#MoneyPlugin

Example json for shop
```json
{
  "shops": [
    {
      "uuid": "2320e42c-3c43-4f04-8f94-a21fe275453f",
      "permissionKey": "TOOLSMITH",
      "name": "Rillis",
      "type": "SWAMP",
      "itens": [
        {
          "material": "DIAMOND",
          "amount": 5,
          "price": 10,
          "amount_bonus": 10,
          "price_bonus": 10,
          "buy": false
        },
        {
          "material": "DIAMOND_SWORD",
          "amount": 1,
          "price": 10,
          "amount_bonus": 1,
          "price_bonus": 17,
          "buy": false
        },
        {
          "material": "IRON_INGOT",
          "amount": 30,
          "price": 10,
          "amount_bonus": 10,
          "price_bonus": 10,
          "buy": true
        },
        {
          "material": "IRON_SWORD",
          "amount": 1,
          "price": 20,
          "enchants": [
            {
              "key": "sharpness",
              "level": 5
            },
            {
              "key": "unbreaking",
              "level": 3
            }
          ],
          "buy": true
        }
      ]
    }
  ]
}
```