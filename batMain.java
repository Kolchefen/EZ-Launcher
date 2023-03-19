/* Kolchefen
 * 18/3/2023
 */ 
package tarkovSPBat;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class batMain {
	public static void main(String[] args) {
		System.out.println("   ______________________________\r\n"
						 + " / \\                             \\\r\n"
						 + "|   |                            |\r\n"
						 + " \\_ |                            |\r\n"
						 + "    |                            |\r\n"
						 + "    |                            |\r\n"
						 + "    |         Tarkov SP          |\r\n"
						 + "    |      .bat file maker       |\r\n"
						 + "    |         For SPT-SKI        |\r\n"
						 + "    |                            |\r\n"
						 + "    |                            |\r\n"
						 + "    |                            |\r\n"
						 + "    |   _________________________|__\r\n"
						 + "    |  /                           /\r\n"
						 + "    \\_/___________________________/");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please paste the path to the folder that has the EscapeFromTarkov.exe in it. Example: C:\\Games\\TarkovSP"); // input instructions	
		
		String pathReal = "";
		
		int timeDelay = 0;
		
		boolean fail = false;
		
		while (true) {
			
			String pathRaw = sc.nextLine();
			
			
			System.out.println("About how many SECONDS does it take your Aki.Server.exe to load? (how long until you see green text) ");
			
			try {
				timeDelay = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a whole number next time");
				fail = true;
				
			}
			
			Path path = Path.of(pathRaw);
			
			pathReal = path + "\\EasyLaunch.bat";			// gets write-to path
			
			File batFile = new File(pathReal); //.bat file creator 
			
			try {
				batFile.createNewFile();
				break;
			}
			
			catch (IOException e) {
				System.out.println("Oops, that path doesnt exist... Re-enter the path correctly");
			}
		}
		
		timeDelay *= 1000;
		
		try {
			FileWriter wr = new FileWriter(pathReal);
			wr.write("@ECHO OFF\r\n"
					+ "START Aki.Server.exe\r\n"
					+ "ping 192.0.2.1 -n 1 -w " + timeDelay + " >nul\r\n" // if your wondering, this ip does not exist, this is just a delay timer
					+ "START Aki.Launcher.exe");
			wr.close();
		} catch (IOException e) {
			System.out.println("Delete the existing EasyLaunch.bat file"); // print .bat file info to EasyLaunch
			fail = true;
		} 
		
		if (fail == true) {
			System.out.print("Relauch the program");
		
		} else {
		System.out.println(".bat File Created, Have Fun!");}
		
		sc.close();
		
		
		
	
	}
}
