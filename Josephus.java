/**A class that utilizes a circularly linked list.
 * A list circle members, each identified as an integer, are eliminated until only one remains.
 *The remaining member number is printed to the screen.
 * @author Jess
 *
 */
import java.util.Scanner;


public class Josephus 
{
	
	public static void main(String []args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		//asking user to define the size of the death circle:
		System.out.print("Enter a number between 1-100, which will be"
				+ " the number of people who enter the death circle: ");
		int numberOfThoseWhoEnter = keyboard.nextInt();
		
		//asking user to define the number of people to skip:
		System.out.print("Enter the number of people to skip each round of killing: ");
		int skipNumber = keyboard.nextInt();
		
		System.out.println("\n");
		keyboard.close();
		
		//creating the linked list object to represent the death circle:
		SingleyLinkedList deathCircle = new SingleyLinkedList();
		
		//creating an entry for each member in the circle:
		for (int i = numberOfThoseWhoEnter; i > 0; i--)
		{
			int memberNumber = i;
			deathCircle.addToFront(memberNumber);
			System.out.println("Member number " + memberNumber + " added to the death circle.");
		}
		
		System.out.println("\nThe death circle now contains " + deathCircle.getNumberOfEntries()
				+ " members.\n");
		
		//skipping entries and removing:
		deathCircle.goTo(1); //Sets current at lastNode, so the loop begins skipping at 1
		do
		{
			deathCircle.traverse(skipNumber-1);
			Node removedNode;
			Node nodeToRemove;
			
			nodeToRemove = deathCircle.getCurrentNode();
			removedNode = new Node(nodeToRemove.getDataElement());
			deathCircle.removeCurrent();
			System.out.println("Member number "
					+ removedNode.getDataElement() + " has been executed.");
		
		} while (deathCircle.getNumberOfEntries() > 1);
		
		System.out.println("\nMember number " + deathCircle.getFirstDataElement() + " survived.");
	} //almost... fails to kill the last person, and to return the person surviving. Not sure why it would
		//successfully kill the right people in the right order, but fail on the last one.l
}
