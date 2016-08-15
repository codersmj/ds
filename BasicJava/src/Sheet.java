

import java.util.Stack;

public class Sheet {
	private Cell[][] cells;
	private int width;
	private int height;

	public Sheet(int rows, int columns) {
		cells = new Cell[rows][columns];
		width = columns;
		height = rows;
	}

	public int getColumnSize() {
		return cells[0].length;
	}

	public int getRowSize() {
		return cells.length;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addCell(int row, int col, String expression) {
		Cell newCell = new Cell(expression, row, col);
		cells[row][col] = newCell;
	}

	private boolean isOperator(String c) {
		if (c.length() != 1) {
			return false;
		}
		switch (c.charAt(0)) {
		case '+':
		case '-':
		case '*':
		case '/':
			return true;
		}
		return false;
	}

	private boolean isDigit(String s) {
		int startIndex = 0;
		if (s.charAt(0) == '-' && s.length() != 1) {
			startIndex = 1;
		}

		for (int i = startIndex; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}


	public  Cell.CellCoordinate getCoordinates(String spreadSheetRef) {
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
		return new Cell.CellCoordinate(rowNum, colNum - 1);
	}

	public  boolean isCellReference(String str) {
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
	private double evalPostfixExpression(Cell cell) {
		Stack<Double> stack = new Stack<Double>();
		if (isDigit(cell.getExpression())) {
			cell.setComputedValue(Double.parseDouble(cell.getExpression()));
			return cell.getComputedValue();
		}
		String[] tokens = cell.getExpression().split(" ");
		for (int i = 0; i < tokens.length; i++) {
			String nextToken = tokens[i];
			if (isDigit(nextToken)) {
				stack.push(Double.parseDouble(nextToken));
			} else if (isCellReference(nextToken)) {
				Cell.CellCoordinate refCoordinate = getCoordinates(nextToken);

				if (refCoordinate.row > height || refCoordinate.col > width) {
					throw new IllegalArgumentException("Input: "
							+ cell.getExpression() + " for cell at ("
							+ cell.getRowNum() + "," + cell.getColumnNum()
							+ "), is Invalid.");
				}
				if (!cells[refCoordinate.row][refCoordinate.col].isComputed()) {
					if (cells[refCoordinate.row][refCoordinate.col]
							.isInProcess()) {
						throw new IllegalStateException(
								"Cyclic Dependency Found at Cell ["
										+ cell.getRowNum() + ","
										+ cell.getColumnNum() + "] and Cell ["
										+ refCoordinate.row + ","
										+ refCoordinate.col + "].");
					}
					cells[refCoordinate.row][refCoordinate.col]
							.markProcessing();
					stack.push(evalPostfixExpression(cells[refCoordinate.row][refCoordinate.col]));

				} else {
					stack.push(cells[refCoordinate.row][refCoordinate.col]
							.getComputedValue());
				}
			} else if (isUnaryOperator(nextToken)) {
				double a = stack.pop();
				stack.push(execUnaryOp(nextToken, a));
			} else if (isOperator(nextToken)) {
				double b = stack.pop();
				double a = stack.pop();
				stack.push(execOp(nextToken, a, b));
			} else {
				throw new IllegalArgumentException("Input : "
						+ cell.getExpression() + " for cell at ("
						+ cell.getRowNum() + "," + cell.getColumnNum()
						+ "), is Invalid.");
			}
		}
		double result = stack.pop();
		if (!stack.isEmpty()) {
			throw new IllegalArgumentException("Input : "
					+ cell.getExpression() + " for cell at ("
					+ cell.getRowNum() + "," + cell.getColumnNum()
					+ "), is Invalid.\n Not a valid Postfix Expresion");
		}
		cell.setComputedValue(result);
		return result;
	}

	private Double execUnaryOp(String nextToken, double a) {
		if ("++".equals(nextToken)) {
			return a + 1;
		} else if ("--".equals(nextToken)) {
			return a - 1;
		}
		throw new IllegalArgumentException("Invalid Expression");
	}

	public boolean isUnaryOperator(String op) {
		if ("++".equals(op) || "--".equals(op)) {
			return true;
		}
		return false;
	}

	private double execOp(String op, double a, double b) {

		switch (op.charAt(0)) {
		case '+':
			return (a + b);
		case '-':
			return (a - b);
		case '*':
			return (a * b);
		case '/':
			if (b == 0) {
				throw new IllegalArgumentException("Cannot divide by 0");
			}
			return a / b;
		}
		throw new IllegalArgumentException("Invalid Expression");
	}

	public void printResults() {
		System.out.println(getWidth() +" " + getHeight());
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double value = cells[i][j].getComputedValue();
				System.out.printf("%.5f \n", value);
			}
		}
	}

	public void compute() {
		for (Cell[] cells : cells) {
			for (Cell cell : cells) {
				if (!cell.isComputed()) {
					evalPostfixExpression(cell);
				}
			}
		}
	}
}
