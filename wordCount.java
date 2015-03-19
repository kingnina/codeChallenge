import java.util.*;
import java.io.*;
/**
 * Code Challenge Word Count 
 * @author Lina Jin
 * Insight data Engineering
 */
public class wordCount {
static TreeMap<String, Integer> wordCount = new TreeMap<String, Integer>();
	public static void main(String[] args) throws Exception {
		wordCount Counter = new wordCount();
		String stream = Counter.readFile();
		String newStream = Counter.preProcess(stream);
		Counter.CountWords(newStream);
		Counter.writeToFile(wordCount);
	}
	/**
	 * combine all the texts in .txt files into a single stream
	 * for the latter word counting
	 * @return the combined string
	 * @throws Exception
	 */
	public String readFile() throws Exception{
		String stream = "";
		String line = null;
		String path = System.getProperty("user.dir") +"/wc_input";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles){
			if (file.getName().endsWith(".txt")){
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				while ((line = br.readLine())!=null){
					stream = stream + line;
				}
			}
		}	
		return stream;		
	}
	/**
	 * Remove punctuation and hyphens
	 * Set all characters to lowercase
	 * Leave words, digits and white spaces
	 * @param s a single string read from all files
	 * @return
	 */
	public String preProcess(String s){
		String newString = s.replaceAll("[^a-zA-Z_0-9\\s]", "");
		return newString.toLowerCase();
	}
	
	/**
	 * Count word numbers and store in a TreeMap
	 * @param stream, a stream after preprocessing
	 */
	public void CountWords(String stream){
		StringTokenizer tokenizer = new StringTokenizer(stream);
		while(tokenizer.hasMoreTokens()){
			String currentWord = tokenizer.nextToken();
			Integer freq = wordCount.get(currentWord);
			if (freq==null){
				wordCount.put(currentWord, 1);
			}else{
				wordCount.put(currentWord, freq+1);
			}
		}
	}
	/**
	 * Write the results to the wc_result.txt in the directory /wc_output 
	 * @param wc, a TreeMap
	 * @throws IOException
	 */
	public void writeToFile(TreeMap<String, Integer> wc) throws IOException{
		String path = System.getProperty("user.dir")+"/wc_output"+"/wc_result.txt";
		FileWriter fw = new FileWriter(path);
		for (String key: wc.keySet()){
			fw.write(key+" "+wc.get(key)+"\n");
		}
		fw.close();
	}

}
