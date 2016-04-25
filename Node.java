public class Node
	{
		private Object dataElement;
		private Node next;
		
		public Node()
		{
			this(null, null);
		}
		
		public Node(Object data)
		{
			this(data, null);
		}
		
		public Node(Node link)
		{
			this(null, link);
		}
		
		public Node(Object data, Node link)
		{
			dataElement = data;
			next = link;
		}//end constructors
		
		public Object getDataElement()
		{
			return dataElement;
		}
		
		public Node getNextNode()
		{
			return next;
		}
		
		public void setDataElement(Object data)
		{
			dataElement = data;
		}
		
		public void setNextNode(Node nextNode)
		{
			next = nextNode;
		}
	}