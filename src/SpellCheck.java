import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.ArrayList;

public class SpellCheck {

	public static void main(String[] args) {
		ArrayList<String> dictionary = getDictionary();
		HashMap<String,Integer> misspelled = new HashMap<String,Integer>();
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.println("Enter a word or phrase: ");
			String input = in.nextLine();
			String[] words = input.split(" ");
			int i = 0;
			if(input.equals("list")){
				if(!misspelled.isEmpty()){
					for(String key:misspelled.keySet()){
						System.out.println(key+ " was misspelled " + misspelled.get(key) + " times.");
					}
				}
			}
			else{
				while(i <words.length){
					if(!dictionary.contains(words[i])){
						if(misspelled.containsKey(words[i]))misspelled.put(words[i],misspelled.get(words[i]+1));
						else misspelled.put(words[i],1);
						System.out.println(words[i]+" is misspelled, please enter again");
						String correction = in.nextLine();
						correction = correction.split(" ")[0];
						words[i]=correction;
					}
					else i++;
				}
			}
		}
	}
	
	public static ArrayList<String> getDictionary(){
		ArrayList<String> dictionary = new ArrayList<String>();
		
		// Reading the file
		try{
			String file = "/usr/share/dict/words";

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ( (line = br.readLine())!= null)     {
				dictionary.add(line);
			}
		} catch(IOException e) {
			e.getMessage();
		}

		return dictionary;
	}
}
