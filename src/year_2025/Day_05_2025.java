package year_2025;
import java.io.IOException;
import utils.Utils;
import core.Day;

public class Day_05_2025 extends Day{
	private String inputPath="src/inputs_2025/Input_Day05.txt";
	public Day_05_2025() {
		super(2025,5);
	}

	@Override
	public String solvePart1() {
		try {
            int freshCount = Utils.countFreshIngredients(inputPath);
            System.out.println("Número de ingredientes frescos: " + freshCount);
            return String.valueOf(freshCount);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
		return null;
	}

	@Override
	public String solvePart2() {
		try {
            long totalFreshIds = Utils.countAllFreshIds(inputPath);
            System.out.println("Total de IDs frescos: " + totalFreshIds);
            return String.valueOf(totalFreshIds);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
		return null;
	}

}
