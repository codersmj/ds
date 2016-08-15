
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpreadSheet {
	private Sheet sheet;

	public static void main(final String[] args) {
		SpreadSheet calculator = new SpreadSheet();
		calculator.readSheet();
		calculator.compute();
		calculator.print();
	}

	public void compute() {
		sheet.compute();
	}

	public void print() {
		sheet.printResults();
	}

	private void buildSheet(List<String> input) {
		if (input == null || input.isEmpty()) {
			System.out.println("No input provided");
			System.exit(1);
		}

		try {
			int count = 0;
			int currentRow = 0;
			int currentCol = 0;
			for (String line : input) {
				if (line == null || line.isEmpty()) {
					System.out.println("No input provided at line num : "
							+ count);
					System.exit(1);
				}

				sheet.addCell(currentRow, currentCol, line);
				currentCol++;
				if (currentCol > sheet.getWidth() - 1) {
					currentCol = 0;
					currentRow++;
				}

				count++;
			}
		} catch (NumberFormatException nex) {
			System.out
					.println("The given row or column argument is either null or not a number	");
			System.exit(1);
		} catch (Exception ex) {
			System.out.println("Cannot read givent input");
			System.exit(1);
		}
	}

	private void readSheet() {
		Scanner stdIn = new Scanner(System.in);
		try {
			List<String> input = new ArrayList<String>();
			int count = 0;
			while (stdIn.hasNextLine()) {
				String expression = stdIn.nextLine();
				if (count == 0) {
					// size of sheet line
					String[] tokens0 = expression.split(" ");
					if (tokens0.length != 2) {
						System.out
								.println("No. of arguments in the first line of input is not 2.");
						System.exit(1);
					}
					int column = Math.abs(Integer.parseInt(tokens0[0]));
					int row = Math.abs(Integer.parseInt(tokens0[1]));
					if (row == 0 || column == 0 || row > 26) {
						System.out.println("Invalid Sheet row or column size ");
						System.exit(1);
					}
					sheet = new Sheet(row, column);
				} else {
					input.add(expression);
				}
				count++;
				if (count > sheet.getHeight() * sheet.getWidth()) {
					break;
				}

			}
			buildSheet(input);

		} finally {
			stdIn.close();
		}
	}

}
