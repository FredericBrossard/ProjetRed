package co.projet.filrouge.z.loader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
//import co.simplon.rfc.model.DRITemplate;
import co.projet.filrouge.model.*;
import co.projet.filrouge.repository.FoodsRepository;
import co.projet.filrouge.repository.GroupRepository;;
//import co.simplon.rfc.model.FoodGroup;
//import co.simplon.rfc.repository.DRITemplateRepository;
//import co.simplon.rfc.repository.FoodGroupRepository;
//import co.simplon.rfc.repository.FoodRepository;



@Component

public class LoadFoods implements ApplicationListener<ContextRefreshedEvent> {

	private final static char CSV_SEPARATOR = ';';
	private GroupRepository groupRepo;
	private FoodsRepository foodRepo;
	//TEST
	//private DRITemplateRepository driRepo;



	public LoadFoods(GroupRepository groupRepo, FoodsRepository foodRepo) {

		this.groupRepo = groupRepo;
		this.foodRepo = foodRepo;
 
	}



	@Override

	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadGroupTable("groupes.csv");
 		loadFoodTable("aliments.csv");
 		loadGlycemicFile("glycemique.csv");
	}



	/**
	 * Loads group data into FoodGroup table. Reads csv file to import food group
	 * data. This function is used to import a single column file for base groups.
	 * 
	 * @param fileLocation
	 *          the location of csv file to import
	 */

	private final void loadGroupTable(String fileLocation) {

		CSVParser csvParser = new CSVParserBuilder().withSeparator(CSV_SEPARATOR).build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileLocation)).withCSVParser(csvParser).build()) {
			// Reading all file content into fileLineList.
			List<String[]> fileLineList = reader.readAll();
			// For each file line, we get first column (which represents food group name)
			// and create the Food group.
			for (String[] fileLine : fileLineList) {
				//FoodsGroup group = new FoodsGroup(fileLine[0], null);
				 FoodsGroup group = new FoodsGroup(fileLine[0]);
				groupRepo.save(group); 
			}



		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// We do nothing John Snow, as this is for Dev DB fill up

		} catch (IOException e) {
			e.printStackTrace();
			// We do nothing John Snow, as this is for Dev DB fill up
		}
	}



	/**
	 * Loads sub group data into FoodGroup table. Reads csv file to import food
	 * group data. This function is used to import a multi column file as sub
	 * groups. It is assumed that each sub group has a parent group column in csv
	 * file where parent column is located just one column before (left) the sub
	 * group column
	 * 
	 * @param fileLocation
	 *          the location of csv file to import
	 * @param subGroupIndex
	 *          the sub group column index
	 */
//Pour les sous groupes : non   utilise...
	private final void loadSubGroupTable(String fileLocation, int subGroupIndex) {

		CSVParser csvParser = new CSVParserBuilder().withSeparator(CSV_SEPARATOR).build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileLocation)).withCSVParser(csvParser).build()) {
			// Reading all file content into fileLineList.

			List<String[]> fileLineList = reader.readAll();

			/*
			 * For each file line, we search in DB a food group matching name found in sub
			 * group column index - 1. Then we create the new Food group named by sub group

			 * column and with parent found in sub group column index - 1.
			 */
			for (String[] fileLine : fileLineList) {
				FoodsGroup group = groupRepo.findByName(fileLine[subGroupIndex - 1]);
			//	FoodsGroup group = new FoodsGroup(fileLine[subGroupIndex], groupList.get(0));
			//	groupRepo.save(group);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// We do nothing John Snow, as this is for Dev DB fill up
		} catch (IOException e) {
			e.printStackTrace();
			// We do nothing John Snow, as this is for Dev DB fill up
		}
	}



	/**

	 * Loads food data into Food table. Reads csv file to import food group data.
	 * This function is used to import a multi column file as food items.
	 * 
	 * @param foodFileLocation
	 *          the location of csv file to import
	 */

	private final void loadFoodTable(String foodFileLocation) {



		CSVParser csvParser = new CSVParserBuilder().withSeparator(CSV_SEPARATOR).build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(foodFileLocation)).withCSVParser(csvParser).build()) {

			final String d = "\\d+[.]?\\d*";

			// Reading all file content into fileLineList.

			List<String[]> fileLineList = reader.readAll();
			// Getting all food groups from DB to be able to link them to food items.


			/*
			 * For each file line we first try to find the corresponding group. Then we
			 * create a new Food item with the csv file data.
			 */

			List<Foods> newFoodItemList = new ArrayList<>();
			for (String[] fileLine : fileLineList) {
				String groupFullHierarchy = "";
				//FoodsGroup group;
				
				if (fileLine[0] != null && !fileLine[0].isEmpty() && !fileLine[0].equals("-")) {
					groupFullHierarchy += fileLine[0];
	
				}
 				fileLine[4] = fileLine[4].replaceAll(",", ".");

 				fileLine[6] = fileLine[6].replaceAll(",", ".");
 				fileLine[7] = fileLine[7].replaceAll(",", ".");
 				fileLine[8] = fileLine[8].replaceAll(",", ".");
				 
				
				System.out.println(fileLine[4]);
 System.out.println(fileLine[4].matches(d));
				 
 				FoodsGroup lg = groupRepo.findByName(fileLine[0]); 
 				Foods food = new Foods(
 						fileLine[3],
 						lg ,
						0,
						fileLine[4].matches(d) ? Float.valueOf(fileLine[4]).floatValue() : 0,
						fileLine[7].matches(d) ? Float.valueOf(fileLine[7]).floatValue() : 0,
						fileLine[6].matches(d) ? Float.valueOf(fileLine[6]).floatValue() : 0,
						fileLine[8].matches(d) ? Float.valueOf(fileLine[8]).floatValue() : 0,
						"commentaire");
				newFoodItemList.add(food);
			}

			foodRepo.saveAll(newFoodItemList);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

			// We do nothing John Snow, as this is for Dev DB fill up

		} catch (IOException e) {

			e.printStackTrace();

			// We do nothing John Snow, as this is for Dev DB fill up

		}

	}



	private void loadGlycemicFile(String fileLocation) {
		CSVParser csvParser = new CSVParserBuilder().withSeparator(CSV_SEPARATOR).build();
		try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileLocation)).withCSVParser(csvParser).build()) {
			List<String[]> fileLineList = reader.readAll();
			List<Foods> foodToUpdateList = new ArrayList<>();
			for (String[] fileLine : fileLineList) {
				List<Foods> possibleFoodsToUpdate = foodRepo.findByNameContaining(fileLine[0]);
				float glycemicIndex = (StringUtils.isNotEmpty(fileLine[1])) ? Float.valueOf(fileLine[1]).floatValue() : 0;
				for (Foods foodToUpdate : possibleFoodsToUpdate) { 
					foodToUpdate.setGlycIndex(glycemicIndex);
					foodToUpdateList.add(foodToUpdate);
				}
			}



			foodRepo.saveAll(foodToUpdateList);



		} catch (FileNotFoundException e) {

			// We do nothing John Snow, as this is for Dev DB fill up

			e.printStackTrace();

		} catch (IOException e) {

			// We do nothing John Snow, as this is for Dev DB fill up

			e.printStackTrace();

		}

	}



	/**

	 * Loads Daily Recommended Intakes based on ANSES recommendations.

	 */

	 
}
