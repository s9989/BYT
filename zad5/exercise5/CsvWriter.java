package exercise5;
public class CsvWriter {
	public CsvWriter() {
	}

	public void write(String[][] lines) {
		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			for (int wordNumber = 0; wordNumber < lines[lineNumber].length; wordNumber++) {
				if (wordNumber != 0) System.out.print(','); 
				System.out.print(this.escape(lines[lineNumber][wordNumber]));
			}
			System.out.println();
		}
	}
	
	protected String escape(String text) {
		if (text.indexOf(',') == -1 && text.indexOf('\"') == -1) {
			return text;
		}
		
		text = text.replace("\"", "\"\"");
		text = "\"" + text + "\"";
		
		return text;
	}

}