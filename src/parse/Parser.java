package parse;

public class Parser {

	private final String OpenTag = "<";
	private final String CloseTag = ">";
	private final String JAVASCRIPT_OPEN_TAG = "<script";
	private final String JAVASCRIPT_CLOSE_TAG = "</script>";
	private final String CSS_OPEN_TAG = "<style";
	private final String CSS_CLOSE_TAG = "</style>";
	private final String TEXTARIA_OPEN_TAG = "<textarea";
	private final String TEXTARIA_CLOSE_TAG = "</textarea>";
	
	private StringBuilder sb;
	public String html;
	
	
	public Parser(String html) {
		this.html = html;
		this.sb = new StringBuilder(html);
	}

	private void removeBigTabs(String beginTab, String endTab){
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
		removeBigTabs(this.JAVASCRIPT_OPEN_TAG, this.JAVASCRIPT_CLOSE_TAG);
		removeBigTabs(this.CSS_OPEN_TAG, this.CSS_CLOSE_TAG);
		removeBigTabs(this.OpenTag, this.CloseTag);
		removeBigTabs(this.TEXTARIA_OPEN_TAG, this.TEXTARIA_CLOSE_TAG);
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
			if(sb.charAt(counter -1) ==' ' && (sb.charAt(counter) == ' ')){
				sb.deleteCharAt(counter);
			}
			removeSymbols(counter, "&lt");
			removeSymbols(counter, "/&gt;");
			removeSymbols(counter, "&mp;");
			removeSymbols(counter, "&nbsp;");
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
		
		
		
		
//		if(sb.charAt(counter) == '&' && sb.charAt(counter + 1) == 'n' && sb.charAt(counter + 2) == 'b' && sb.charAt(counter + 3) == 's'&& sb.charAt(counter + 4) == 'p'&& sb.charAt(counter + 5) == ';'){
//			sb.delete(counter, counter + 6);
//		}
	}
	 
	 public static boolean isEmpty(String str) {
	      return str == null || str.length() == 0;
	  }
}