import java.util.*;
public class Hangman {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word for a game of Hangman:");
		String targetWord = input.nextLine();
		targetWord = targetWord.toUpperCase();
		String blankVersion = "";
		int spaceCount = 0;
		for (int i = 0; i < targetWord.length(); i++) {
			if (targetWord.charAt(i) == ' ') {
				blankVersion += " ";
				spaceCount++;
			}else {
				blankVersion += "_";
			}
		}
		String[] targetWordArray = convertToArray(targetWord);
		String[] blankVersionArray = convertToArray(blankVersion);
		String[] incorrectEntriesArray = {"-", "-", "-", "-", "-", "-", "-"};
		int incorrectEntriesIndex = 0;
		int correct = 0 + spaceCount;
		int incorrect = 0;
		
		String board = 
				"______________________\n" +
				"|    /               |\n" +
				"|   /                |\n" +
				"|  /                 |\n" +
				"| /                  O\n" +
				"|/\n" +
				"|\n" +
				"|\n" +
				"|\n" +
				"|________________";
		ArrayList<String> alreadyInputted = new ArrayList<>();
		do {
			System.out.println(board);
			System.out.println();
			System.out.println("You already entered: " + alreadyInputted.toString());
			System.out.println("Word: ");
			System.out.println();
			for (int i = 0; i < blankVersionArray.length; i++) {
				System.out.print(blankVersionArray[i] + " ");
			}
			System.out.println();
			System.out.println("Guess a letter: ");
			String guess = (input.next().charAt(0) + "").toUpperCase();
			if (targetWord.contains(guess)) {
				if (alreadyContains(blankVersionArray, guess)) {
					System.out.println("You already entered that!");
				}else {
					System.out.println("Correct guess!");
					for (int i = 0; i < targetWordArray.length; i++) {
						if (targetWordArray[i].equals(guess)) {
							blankVersionArray[i] = guess;
							correct++;
						}
					}
					alreadyInputted.add(guess);
				}
			}else {
				if (alreadyContains(incorrectEntriesArray, guess)) {
					System.out.println("You already entered that!");
				}else {
					alreadyInputted.add(guess);
					incorrectEntriesArray[incorrectEntriesIndex] = guess;
					incorrectEntriesIndex++;
					incorrect++;
					if (6 - incorrect > 1) {
						System.out.println("Wrong guess! You have " + (6 - incorrect) + " tries left");
					}else if (6 - incorrect == 1){
						System.out.println("Wrong guess! You have " + (6 - incorrect) + " try left");
					}else {
						System.out.println("Wrong guess! You have no tries left");
					}
					switch(incorrect) {
					case 1: board =  
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/\n" +
							"|\n" +
							"|\n" +
							"|\n" +
							"|________________";break;
					case 2: board = 
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/                   |\n" +
							"|                    |\n" +
							"|\n" +
							"|\n" +
							"|________________";break;
					case 3: board = 
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/                  /|\n" +
							"|                    |\n" +
							"|\n" +
							"|\n" +
							"|________________";break;
					case 4: board = 
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/                  /|\\\n" +
							"|                    |\n" +
							"|\n" +
							"|\n" +
							"|________________";break;
					case 5: board = 
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/                  /|\\\n" +
							"|                    |\n" +
							"|                   / \n" +
							"|\n" +
							"|________________";break;
					case 6: board = 
							"______________________\n" +
							"|    /               |\n" +
							"|   /                |\n" +
							"|  /                 |\n" +
							"| /                 ( )\n" +
							"|/                  /|\\\n" +
							"|                    |\n" +
							"|                   / \\\n" +
							"|\n" +
							"|________________";break;
					}
					if (incorrect == 6) {
						System.out.println(board);
						System.out.println("Gameover, YOU LOSE!");
						break;
					}
				}
			}
		} while(correct < targetWord.length());
		if (incorrect < 6) {
			System.out.println(board);
			System.out.println();
			System.out.println("Word: ");
			System.out.println();
			for (int i = 0; i < blankVersionArray.length; i++) {
				System.out.print(blankVersionArray[i] + " ");
			}
			System.out.println();
			System.out.println("Congratulations, YOU WIN!");
		}
		input.close();
	}
	public static String[] convertToArray(String s1) {
		String[] arrayVersion = new String[s1.length()];
		for (int i = 0; i < s1.length(); i++) {
			arrayVersion[i] = s1.charAt(i) + "";
		}
		return arrayVersion;
	}
	public static boolean alreadyContains(String[] arr, String guess) {
		if (binarySearch(bubbleSort(arr), guess) != -1) {
			return true;
		}
		return false;
	}
	public static int binarySearch(String[] arr, String guess) {
		int low = 0;
		int high = arr.length - 1;
		int mid = (low + high)/2;
		while (low <= high) {
			mid = (low + high)/2;
			if (guess.compareTo(arr[mid]) > 0) {
				low = mid + 1;
			}else if (guess.compareTo(arr[mid]) < 0) {
				high = mid - 1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	public static String[] bubbleSort(String[] arr) {
		String[] tempArr = Arrays.copyOf(arr, arr.length);
		for (int i = 0; i < tempArr.length; i++) {
			for (int j = 0; j < tempArr.length - 1 - i; j++) {
				if (tempArr[j].compareTo(tempArr[j + 1]) > 0) {
					String temp = tempArr[j];
					tempArr[j] = tempArr[j + 1];
					tempArr[j + 1] = temp;
				}
			}
		}
		return tempArr;
	}
}
