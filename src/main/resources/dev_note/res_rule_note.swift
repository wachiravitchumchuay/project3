@prefix re: <http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#> .

// =================
// ==== PrePost ====


[Jena1: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena2: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena3: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena9: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunCarbConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "96") ]

[Jena11: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "95") ]

[Jena14: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "93") ]

[Jena16: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunCarbConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "93") ]

[Jena20: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "90") ]

[Jena22: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunCarbConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "90") ]


// ==== PrePost ====
// =================

// ========================
// ==== RestaurantType ====


[Jena6: 
(?Restaurant re:hasRestaurantType ?RestaurantType) 
(?RestaurantType re:type re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena8: 
(?Restaurant re:hasRestaurantType ?RestaurantType) 
(?RestaurantType re:type re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "98") ]

[Jena13: 
(?Restaurant re:hasRestaurantType ?RestaurantType) 
(?RestaurantType re:type re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunCarbConsumtion "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "94") ]

[Jena21: 
(?Restaurant re:hasRestaurantType ?RestaurantType) 
(?RestaurantType re:type re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "90") ]


// ==== RestaurantType ====
// ========================

// ====================
// ==== RunnerType ====

[Jena4: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena5: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena7: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "100") ]

[Jena10: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "95") ]

[Jena12: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "94") ]

[Jena15: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "93") ]

[Jena18: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunCarbConsumtion "Medium") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "91") ]

[Jena19: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Protein "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunProteinConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "90") ]

[Jena23: 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PreRunFatConsumtion "Medium") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "90") ]


// ==== RunnerType ====
// ====================

// ==================================
// ==== RunnerType RestaurantType====

[Jena17: 
(?Restaurant re:hasRestaurantType ?RestaurantType) 
(?RestaurantType re:type re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "92") ]


// ==== RunnerType RestaurantType====
// ==================================


note:
17 wrong 

[Jena17: 
(?Restaurant re:hasRestaurantType re:Fast_Dining_Type) 
(?Restaurant re:hasFoodType ?FoodType) 
(?FoodType re:Fat "Medium") 
(?FoodType re:Carbohydrates "Medium") 
(?User re:RunnerType "Fun run") 
(?User re:PostRunFatConsumtion "Medium") 
(?User re:PostRunCarbConsumtion "Medium") 
(?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) 
-> 
(?User re:hasRecommend ?Restaurant) 
(?Restaurant re:confidence "92") ]



    No re:Carbohydrates "Medium"

    0:
        7,12,15,23
    1:
        
    12 = 15 
    4 = 5
    7 = 23





====================
original

@prefix re: <http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#> .

[Jena1: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PostRunProteinConsumtion "Medium")
(?User re:PostRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "100") ]

[Jena6: (?Restaurant re:hasRestaurantType ?RestaurantType) (?RestaurantType re:type
re:Fast_Dining_Type) (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat
"Medium") (?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium")
(?User re:PreRunProteinConsumtion "Medium") (?User re:PostRunFatConsumtion
"Medium") (?User re:PostRunCarbConsumtion "Medium") (?User
re:hasRestaurantTypeInterest re:Fast_Dining_Type) -> (?User re:hasRecommend
?Restaurant) (?Restaurant re:confidence "100") ]

[Jena7: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Carbohydrates "Medium") (?User re:RunnerType "Fun run") (?User
re:PreRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium") (?User
re:PostRunFatConsumtion "Medium") -> (?User re:hasRecommend ?Restaurant)
(?Restaurant re:confidence "100") ]

[Jena8: (?Restaurant re:hasRestaurantType ?RestaurantType) (?RestaurantType re:type
re:Fast_Dining_Type) (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat
"Medium") (?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium")
(?User re:PreRunProteinConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") (?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) -> (?User
re:hasRecommend ?Restaurant) (?Restaurant re:confidence "98") ]

[Jena9: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunCarbConsumtion "Medium") (?User re:PreRunProteinConsumtion "Medium")
(?User re:PostRunCarbConsumtion "Medium") (?User re:PostRunFatConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "96") ]

[Jena10: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:RunnerType "Fun run") (?User re:PreRunProteinConsumtion "Medium") (?User
re:PostRunCarbConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "95") ]

[Jena12: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?User re:RunnerType "Fun run") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PreRunFatConsumtion "Medium")
(?User re:PostRunFatConsumtion "Medium") -> (?User re:hasRecommend ?Restaurant)
(?Restaurant re:confidence "94") ]

[Jena14: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium") (?User
re:PostRunFatConsumtion "Medium") (?User re:PreRunProteinConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "93") ]

[Jena15: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?User re:RunnerType "Fun run") (?User
re:PreRunFatConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") (?User
re:PreRunProteinConsumtion "Medium") -> (?User re:hasRecommend ?Restaurant)
(?Restaurant re:confidence "93") ]

[Jena16: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunCarbConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium")
(?User re:PostRunFatConsumtion "Medium") (?User re:PreRunProteinConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "93") ]

[Jena17: (?Restaurant re:hasRestaurantType ?RestaurantType) (?RestaurantType re:type
re:Fast_Dining_Type) (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat
"Medium") (?FoodType re:Carbohydrates "Medium") (?User re:RunnerType "Fun run")
(?User re:PostRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") (?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) -> (?User
re:hasRecommend ?Restaurant) (?Restaurant re:confidence "92") ]

[Jena18: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:RunnerType "Fun run") (?User re:PreRunCarbConsumtion "Medium") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "91") ]

[Jena19: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:RunnerType "Fun run") (?User re:PreRunProteinConsumtion "Medium") (?User
re:PostRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "90") ]

[Jena20: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PreRunFatConsumtion "Medium")
(?User re:PostRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "90") ]

[Jena21: (?Restaurant re:hasRestaurantType ?RestaurantType) (?RestaurantType re:type
re:Fast_Dining_Type) (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat
"Medium") (?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium")
(?User re:PreRunProteinConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") (?User re:PostRunFatConsumtion "Medium") (?User
re:hasRestaurantTypeInterest re:Fast_Dining_Type) -> (?User re:hasRecommend
?Restaurant) (?Restaurant re:confidence "90") ]

[Jena22: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunCarbConsumtion "Medium") (?User re:PreRunProteinConsumtion "Medium")
(?User re:PostRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "90") ]

[Jena23: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Carbohydrates "Medium") (?User re:RunnerType "Fun run") (?User
re:PreRunFatConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") (?User
re:PostRunCarbConsumtion "Medium") -> (?User re:hasRecommend ?Restaurant)
(?Restaurant re:confidence "90") ]

[Jena13: (?Restaurant re:hasRestaurantType ?RestaurantType) (?RestaurantType re:type
re:Fast_Dining_Type) (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat
"Medium") (?FoodType re:Carbohydrates "Medium") (?User re:PreRunCarbConsumtion
"Medium") (?User re:PreRunFatConsumtion "Medium") (?User re:PostRunFatConsumtion
"Medium") (?User re:hasRestaurantTypeInterest re:Fast_Dining_Type) -> (?User
re:hasRecommend ?Restaurant) (?Restaurant re:confidence "94") ]

[Jena4: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:RunnerType "Fun run") (?User re:PostRunCarbConsumtion "Medium") (?User
re:PostRunProteinConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "100") ]

[Jena5: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:RunnerType "Fun run") (?User re:PostRunCarbConsumtion "Medium") (?User
re:PostRunProteinConsumtion "Medium") (?User re:PostRunFatConsumtion "Medium") ->
(?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "100") ]

[Jena2: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PreRunFatConsumtion "Medium")
(?User re:PostRunProteinConsumtion "Medium") (?User re:PostRunCarbConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "100") ]

[Jena3: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunFatConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium") (?User
re:PostRunProteinConsumtion "Medium") (?User re:PreRunProteinConsumtion "Medium") 
-> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "100") ]

[Jena11: (?Restaurant re:hasFoodType ?FoodType) (?FoodType re:Fat "Medium")
(?FoodType re:Protein "Medium") (?FoodType re:Carbohydrates "Medium") (?User
re:PreRunProteinConsumtion "Medium") (?User re:PostRunCarbConsumtion "Medium")
(?User re:PostRunProteinConsumtion "Medium") (?User re:PreRunFatConsumtion
"Medium") -> (?User re:hasRecommend ?Restaurant) (?Restaurant re:confidence "95") ]