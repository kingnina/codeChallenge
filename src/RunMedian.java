import java.io.*;
import java.util.*;
/**
 * Running Median
 * @author NinaCC
 * Insight Data Engineering
 */

public class RunMedian {
String path;
File[] listofFiles;
// a list of file names in the wc_input folder
List<String> filenames = new ArrayList<String>();
// sentences in all files
static List<String> lines = new ArrayList<String>();
	public static void main(String[] args) throws Exception {
		RunMedian runMedian = new RunMedian();
		runMedian.sortFiles();
		runMedian.combineFile();
		for (String str:lines){
			runMedian.preProcess(str);
		}
		runMedian.runMedian();
	}
	/**
	 * sort all the files in alphabetical order 
	 */
	public void sortFiles(){
		File thisFile = new File(System.getProperty("user.dir"));
		path = thisFile.getParentFile().getAbsolutePath();
		File folder = new File(path+"/wc_input");
		listofFiles = folder.listFiles();
		//process files to an alphabetical order
		for (File file:listofFiles){
			filenames.add(file.getName());
		}
		Collections.sort(filenames,String.CASE_INSENSITIVE_ORDER);
	}
	/**
	 * Combine all files into a stream
	 * Each line is an element of an Arraylist
	 * @throws Exception
	 */
	public void combineFile() throws Exception{
		String line="";
		for (String name : filenames){
			if (name.endsWith(".txt")){
				File newfile = new File(path+"/wc_input"+"/"+name);
				BufferedReader br = new BufferedReader(new FileReader(newfile));
				while((line=br.readLine())!=null){
					lines.add(line);
				}
			}
		}
	}
	/**
	 * Remove punctuation and hyphens
	 * Set all characters to lowercase
	 * Leave words, digits and white spaces
	 * @param s a single string read from all files
	 * @return
	 */
	public String preProcess(String s){
		s = s.replaceAll("[^a-zA-Z_0-9\\s]", "");
		return s;
	}
	
	/**
	 * Find the median number
	 * Write results to med_reult.txt
	 * @throws IOException
	 */
	public void runMedian() throws IOException{
		List<Integer> values = new ArrayList<Integer>();
		double median = 0;
		FileWriter writer = new FileWriter(path+"/wc_output"+"/med_result.txt");
		for(String sen:lines){
			StringTokenizer tokenizer = new StringTokenizer(sen);
			values.add(tokenizer.countTokens());
			median = findMedian(values);
			writer.write(median+"\n");
		}
		writer.close();
	}
	
	/**
	 * Method of finding medians
	 * @param values
	 * @return
	 */
	public double findMedian(List<Integer> values){
		Collections.sort(values);
		double val1 = Math.floor((values.size()-1.0)/2.0);
		double val2 = Math.ceil((values.size()-1.0)/2.0);
		if(val1==val2){
			return values.get((int)val1);
		}else{
			return (values.get((int)val1)+values.get((int)val2))/2.0;
		}
	}
}
