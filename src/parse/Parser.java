package parse;

public class Parser {

	private final String[] HTML_SPECIAL_TAGS = {"<script", "</script>", "<style", "</style>", "<textarea", "</textarea>", "<", ">"};
	private final String[] HTML_SYMBOLS = {"&lt", "/&gt;", "&mp;", "&nbsp;", "  ", "&amp;", "&gt;"};
			
	private StringBuilder sb;
	private String html;
	
	
	public Parser(String html) {
		this.html = html;
		this.sb = new StringBuilder(html);
	}

	private void removeBigTags(String beginTab, String endTab){
		int begin = this.sb.indexOf(beginTab);;
		int end = this.sb.indexOf(endTab);
		while (begin != -1){
	        if(begin > end){
	        	this.sb.deleteCharAt(end);
	        }else{
	        	this.sb.delete(begin, end + endTab.length());
	        }
	        begin = this.sb.indexOf(beginTab);
	        end = this.sb.indexOf(endTab);
		}
	}
	
	public void removeTags(){
		for (int i = 0; i < HTML_SPECIAL_TAGS.length - 1; i+=2) {
			removeBigTags(this.HTML_SPECIAL_TAGS[i], this.HTML_SPECIAL_TAGS[i + 1]);
		}
		removeNewLines();
		this.html = sb.toString();
	}

	private void removeNewLines() {
		int counter = 1;
		while(true){
			if(counter >= sb.length()){
				break;
			}
			if((sb.charAt(counter -1) =='\n' && (sb.charAt(counter) == '\n' || sb.charAt(counter) == ' '))){
				sb.deleteCharAt(counter);
				continue;
			}if(sb.charAt(counter) == '\t'){
				sb.deleteCharAt(counter);
				continue;
			}
			for (int i = 0; i < this.HTML_SYMBOLS.length; i++) {
				removeSymbols(counter, this.HTML_SYMBOLS[i]);
			}
			counter++;
		}
	}
	
	private void removeSymbols(int counter, String symbol) {
		char[] array = symbol.toCharArray();
		boolean result = true;
		for (int i = 0; i < array.length; i++) {
			if(counter + array.length > sb.length()){
				result = false;
				break;
			}
			if(!(sb.charAt(counter + i) == array[i])){
				result = false;
			}
		}
		if(result){
			sb.delete(counter, counter + array.length);
		}
	}
	 
	public boolean isEmpty() {
		 if (this.html.isEmpty()) {
			return true;
		}else{
			return false;
		}
	  }

	@Override
	public String toString() {
		return "HTML content is:\n" + this.sb.toString();
	}
	 
	 
}