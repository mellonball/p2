package umbc.veggie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataProvider {

	public static HashMap<String, List<String>> getInfo()
	{
		HashMap<String, List<String>> Restaurants_Details = new HashMap<String, List<String>>();
		List<String> Mondo_foods = new ArrayList<String>();
		Mondo_foods.add("Vegetable Wrap with Hummus");
				
		List<String> Famous_foods = new ArrayList<String>();
		Famous_foods.add("Pasta and Marinara Sauce and Garlic Knots (Vegan)");
		Famous_foods.add("Fresca Pizza and Pizza Bianca");
		
		List<String> Outtakes_foods = new ArrayList<String>();
		Outtakes_foods.add("Guacamole with Pita Triangles (Vegan)");
		Outtakes_foods.add("Pita and Hummus (Vegan)");
		Outtakes_foods.add("Edamame (Vegan)");
		
		List<String> Starbucks_foods = new ArrayList<String>();
		Starbucks_foods.add("Juice (Vegan)");
		Starbucks_foods.add("Coffee without flavoring syrups (Vegan)");

		List<String> Salsaritas_foods = new ArrayList<String>();
		Salsaritas_foods.add("Veggie Taco Salad without the Tortilla");
		Salsaritas_foods.add("Tortilla Chips");
		Salsaritas_foods.add("Guacamole and Salsa");
		Salsaritas_foods.add("Bare Burrito Bowl (Vegan)");
		Salsaritas_foods.add("*bean seasoning contains animal-derived preservatives. Tortillas do not contain lard, but do contain casein (milk product) & animal-derived enzymes.");
		
		List<String> Mesquite_foods = new ArrayList<String>();
		Mesquite_foods.add("Black Bean Garden Burger, without the roll (Vegan)");
		Mesquite_foods.add("Fries (Vegan)");
		Mesquite_foods.add("Whole Fruit. (Vegan)");
		
		List<String> JowJing_foods = new ArrayList<String>();
		JowJing_foods.add("Lo-Mein (Vegan)");
		JowJing_foods.add("Tofu (Vegan)");
		JowJing_foods.add("Ask the server for vegan or vegetarian sushi options. (Vegan)");
		
		List<String> Fresh_foods = new ArrayList<String>();
		Fresh_foods.add("http://www.dineoncampus.com/UMBC/webtrition/webtrition.cfm?dt=19-Nov-14&venueName=Salsarita%27s");
		Fresh_foods.add("The menu rotates daily and always provides a vegan and vegetarian option.");
		
		List<String> ChickFilA_foods = new ArrayList<String>();
		ChickFilA_foods.add("Garden Salad, without dressing");
		ChickFilA_foods.add("Fruit Cup");
		ChickFilA_foods.add("Waffle Fries (Vegan)");
		
		List<String> AuBonPan_foods = new ArrayList<String>();
		AuBonPan_foods.add("Oatmeal (Vegan)");
		AuBonPan_foods.add("Fruit Cups (Vegan)");
		AuBonPan_foods.add("Black Bean Soup (Vegan)");
		AuBonPan_foods.add("Chick Peas and Tomato Salad(Vegan)");
		AuBonPan_foods.add("Asparagus/Almond portion (Vegan)");
		AuBonPan_foods.add("Cucumber Salad (Vegan)");
		AuBonPan_foods.add("Check the daily soup for vegetarian or vegan options.");
		AuBonPan_foods.add("Vegetarian Chili");
		AuBonPan_foods.add("Lentil Soup");
		
		Restaurants_Details.put("Mondo Subs", Mondo_foods);
		Restaurants_Details.put("Famous Famiglia", Famous_foods);
		Restaurants_Details.put("Outtakes", Outtakes_foods);
		Restaurants_Details.put("Starbucks", Starbucks_foods);
		Restaurants_Details.put("Salsarita's", Salsaritas_foods);
		Restaurants_Details.put("Mesquite BBQ & Grill", Mesquite_foods);
		Restaurants_Details.put("Jow Jing", JowJing_foods);
		Restaurants_Details.put("Fresh Fusions", Fresh_foods);
		Restaurants_Details.put("Chick-Fil-A", ChickFilA_foods);
		Restaurants_Details.put("Au Bon Pan", AuBonPan_foods);
		
		return Restaurants_Details;
	
	}
	
}
