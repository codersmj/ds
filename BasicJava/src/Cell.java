

public class Cell {
	private int rowNum;
	private int columnNum;
	private String expression;
	private double computedValue;
	private Status status;

	public enum Status {
		COMPUTED, COMPUTING, NOT_COMPUTED
	}

	public static class CellCoordinate {
		public CellCoordinate(int rowNum, int colNum) {
			this.row = rowNum;
			this.col = colNum;
		}

		int row;
		int col;

		public boolean isValid() {
			return row >= 0 && row <= 25 && col != -1;
		}
	}

	public static class CellUtil {

		public static CellCoordinate getCoordinates(String spreadSheetRef) {
			char row = spreadSheetRef.charAt(0);// Assuming only 1 char since only between A
												// to Z
			String col = spreadSheetRef.substring(1, spreadSheetRef.length());
			int colNum = Integer.valueOf(col);
			int rowNum = -1;
			if (row >= 'A' && row <= 'Z') {
				rowNum = row - 'A';
			} else {
				return null;
			}
			return new CellCoordinate(rowNum, colNum - 1);
		}

		public static boolean isCellReference(String str) {
			char letter = str.charAt(0);
			String num = str.substring(1, str.length());
			if (letter >= 'A' && letter <= 'Z') {
				try {
					Integer.parseInt(num);
					return true;
				} catch (NumberFormatException nfe) {
					return false;
				}
			}
			return false;
		}
	}

	public Cell(String expression, int r, int c) {
		this.expression = expression;
		this.rowNum = r;
		this.columnNum = c;
		this.status = Status.NOT_COMPUTED;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public boolean isComputed() {
		return Status.COMPUTED == status;
	}

	public void markComputed() {
		this.status = Status.COMPUTED;
	}

	public void markProcessing() {
		this.status = Status.COMPUTING;
	}

	public boolean isInProcess() {
		return Status.COMPUTING == status;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String cellExp) {
		this.expression = cellExp;
	}

	public double getComputedValue() {
		return computedValue;
	}

	public void setComputedValue(double computedValue) {
		this.computedValue = computedValue;
		this.status = Status.COMPUTED;
	}

}
