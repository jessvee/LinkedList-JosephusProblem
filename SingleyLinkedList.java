/**A one-way linked list class*/
public class SingleyLinkedList
{
	private Node firstNode;
	private Node lastNode;
	
	private Node currentPointer;
	private Node previousPointer;
	
	private int totalEntries;
	
	public SingleyLinkedList()
	{
		firstNode = null;
		lastNode = firstNode;
		
		currentPointer = null;
		previousPointer = null;
		
		totalEntries = 0;
	}
	/**Adds to the beginning of the list, bumping the front node to the second position,
	 * firstNode will reference the addition at the end of execution.
	 * @param data		the data element to define the new node being created and added to the list
	 */
	public void addToFront(Object data)
	{
		Node newNode = new Node(data);
		
		if (firstNode != null)
		{
			newNode.setNextNode(firstNode);
			firstNode = newNode;
		} 
		
		if (firstNode == null)
		{
			firstNode = newNode;
			lastNode = newNode;
			currentPointer = newNode;
		}
		
		if (firstNode.getDataElement() != null)
			totalEntries++;
			
	}
	
	/**Adds to the end of the list, bumping the last node up to the second to last position,
	 * lastNode will reference the addition at the end of execution.
	 * @param data		the data element to define the new node being created and added to the list
	 */
	public void addToEnd(Object data)
	{
		Node newNode = new Node(data);
		
		if (lastNode != null)//if list is not empty
		{
			lastNode.setNextNode(newNode);
			//newNode.next is null
			lastNode = newNode;
			lastNode.setNextNode(firstNode);
		}
		
		if (lastNode == null)//if list is empty
		{
			firstNode = newNode;
			currentPointer = newNode;
			lastNode = newNode;
		}

		totalEntries++;
	
	}
	
	/**Adds a new node to the position before currentPointer; 
	 * 		new node becomes referenced by previousPointer.
	 * Precondition: The list is not empty.
	 * @param data		the data element to be incorporated into the generated node that will be added
	 * */
	public void addBeforeCurrent(Object data) 
	{
		Node newNode = new Node(data);
		previousPointer.setNextNode(newNode);
		newNode.setNextNode(currentPointer);
		totalEntries++;
	}
	
	public void addAfterCurrent(Object data) 
	{
		Node newNode = new Node();
		newNode.setDataElement(data);
		
		newNode.setNextNode(currentPointer.getNextNode()); 
		currentPointer.setNextNode(newNode);
		
		totalEntries++;
	}
	
	
	/**Moves forward one node. Contains a condition to give pointers list a circular habit.*/
	public void traverse()
	{
		if (currentPointer.getNextNode() != null)
		{
			previousPointer = currentPointer;
			currentPointer = currentPointer.getNextNode();
		} 
		else
		{
			currentPointer = firstNode;
			previousPointer = lastNode;
		}
		
	}
	
	/**Moves forward for a specified number of moves
	 * @param	numberOfMoves		the integer representing the number of times to traverse
	 */
	public void traverse(int numberOfMoves) 
	{
		for(int i = 0; i < numberOfMoves; i++)
		{
			this.traverse();
		}
	}
	
	/**Moves current to a specified entry number, previous follows one node behind current
	 * 
	 * @param entryNumber		the number of entry to be visited; entries start at 1
	 */
	public void goTo(int entryNumber)
	{
		currentPointer = firstNode;
		previousPointer = firstNode;
		
		if (entryNumber > 1)
			traverse(entryNumber - 1);
	}
	
	/**Locates the first node that contains the specified data element.
	 * 
	 * @param findData		the data element to be located in a node 
	 * @return position		the integer of the position of the desired node,
	 * 							 positions start at 1, returns -1 if entry is not found.
	 */
	public int search(Object findData)
	{
		currentPointer = firstNode;
		int position = 1;
		
		do
		{
			if (findData == currentPointer.getDataElement())
				return position;
			else
				traverse();
			position++;
		} while (position <= totalEntries);
		
		position = -1;
		
		return position;
		
	}
	/**Removes the first node.
	 * 
	 * @return removedNode		the copy node which contains the same data as the removed node, but with 
	 * 							a null next reference
	 */
	public Node removeFromFront()
	{
		Node removedNode = new Node (firstNode.getDataElement());		
		if(firstNode.getDataElement() != null) //if the list is not empty...
		{
			firstNode.setNextNode(firstNode.getNextNode());
			totalEntries--;//don't want to decrement to negative int if list is empty
							//so decrement is within the if
		}
		//no else needed, as if the list is empty, there is nothing to remove
		return removedNode; //removedNode will be null if list was empty when called
	}
	
	/**Removes the last node.
	 * 
	 * @return		a reference to the node that is removed
	 */
	public Node removeFromEnd()
	{
		Node removedNode = new Node(lastNode.getDataElement());
		
		goTo(totalEntries - 1);
		
		lastNode = currentPointer;
		lastNode.setNextNode(firstNode);
		
		totalEntries--;
		return removedNode;
	}
	
	/**Removes the node referenced by currentPointer and moves currentPointer up one node. Decrements
	 * totalEntries by one.*/
	public Node removeCurrent() 
	{
		Node tempCurrent;
		Node removedNode = new Node(currentPointer.getDataElement());
		//the next line removes the link to the node to be deleted:
		previousPointer.setNextNode(currentPointer.getNextNode());
		
		//the if condition moves tempNext ahead, so current can refer to it, after we delete the node it ref's:
		if (currentPointer.getNextNode() != null)
			tempCurrent = currentPointer.getNextNode();
		else
		{
			tempCurrent = firstNode;
			previousPointer = lastNode;	
		}
		
		//the statements that do the actual removal:
			//the next statement edits the node referenced by currentPointer, like above, which deletes it
		currentPointer.setNextNode(null);
		currentPointer = tempCurrent;
		totalEntries--;
		
		return removedNode;
	}
	
	/**Finds a node with the specified data element and removes it.
	 * 
	 * @param data			the data element to identify the node to remove
	 * @return removedNode	the node copy of the node that is removed, null if node with specified entry
	 * 							 was not located
	 */
	public Node removeNode(Object removeData)
	{
		Node removedNode = new Node();
		int removePosition = search(removeData);
		
		if (removePosition > 0)
		{
			removedNode.setDataElement(removeData);
			goTo(removePosition);
			removeCurrent();
		}
		else removedNode = null;
	
		totalEntries--;
		return removedNode;
	}
	
	/**Removes all nodes and sets the totalEntries to 0.*/
	public void clear()
	{
		firstNode = null;
		lastNode = null;
		currentPointer = null;
		previousPointer = null;
		totalEntries = 0;
	}
	
	/**Linked list getters, below:
	 */
	
	public Node getFirstNode()
	{
		return firstNode;
	}
	
	public Object getFirstDataElement()
	{
		return firstNode.getDataElement();
	}
	
	public Node getLastNode()
	{
		return lastNode;
	}
	
	public Node getCurrentNode()
	{
		return currentPointer;
	}
	
	public Node getPreviousNode()
	{
		return previousPointer;
	}
	
	public int getNumberOfEntries() 
	{
		return totalEntries;
	}
	
}
